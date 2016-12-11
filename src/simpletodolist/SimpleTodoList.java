/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletodolist;

import simpletodolist.gui.GUIWindow;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Marko Mäkelänen
 */
public class SimpleTodoList {

    protected static List<Task> taskList = new ArrayList<Task>();
    public static final String JSON_FILENAME = "tasks.json";

    /**
     * Simple TodoList Application for managing tasks.
     *
     * Marko Mäkeläinen
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Open dialog for loading saved tasks
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to load saved tasks to TodoList?", "Info", dialogButton);
        // if yes load tasks from file
        if (dialogResult == JOptionPane.YES_OPTION) {
            FileHandler.readSavedJSONs(taskList);
        }

        // Launch GUI window
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUIWindow gui = new GUIWindow();
                gui.setVisible(true);

                // set tasklist for gui
                gui.setTaskList(taskList);
            }
        });

    }

}
