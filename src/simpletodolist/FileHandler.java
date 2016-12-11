/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletodolist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Simple interface for handling file operations
 *
 * @author Marko Mäkeläinen
 */
public class FileHandler {

    /**
     * Saves current TodoList content to JSON file.
     *
     * @param taskList List of existing tasks
     */
    public static void saveTodoListToJSON(List<Task> taskList) {

        try {
            // Create FileWriter file
            FileWriter file = new FileWriter(SimpleTodoList.JSON_FILENAME);
            
            // go through existing tasks
            synchronized (taskList) {
                Iterator i = taskList.iterator();

                while (i.hasNext()) {
                    Task task = (Task) i.next();

                    // Write task as JSON and line separator to JSON file
                    file.write(task.toJSON().toJSONString());
                    file.write(System.getProperty("line.separator"));
                }

                // flush & close file
                file.flush();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads previously saved JSON file and parses tasks to the taskList for
     * TodoList Table.
     *
     * @param taskList List of existing tasks
     */
    public static void readSavedJSONs(List<Task> taskList) {

        JSONObject obj;
        String line = null;

        try {
            // FileReader & BufferedReader for reading JSON file
            FileReader fileReader = new FileReader(SimpleTodoList.JSON_FILENAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // read line by line
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    // parse JSON to object
                    obj = (JSONObject) new JSONParser().parse(line);
                    Task task = new Task((String) obj.get("title"), (String) obj.get("description"), (String) obj.get("deadline"), (String) obj.get("status"));
                    taskList.add(task);

                    // Handle exception
                } catch (ParseException ex) {
                    Logger.getLogger(SimpleTodoList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Close reader
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + SimpleTodoList.JSON_FILENAME + "'" + ". New file created.");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + SimpleTodoList.JSON_FILENAME + "'.");
        }

    }

}
