/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletodolist;

import org.json.simple.JSONObject;

/**
 * Task object
 *
 * @author Marko Mäkeläinen
 */
public class Task {

    private final String title;
    private String description;
    private String deadline;
    private String status;
    public final static String STATUS_OPEN = "Open";
    public final static String STATUS_ONGOING = "Ongoing";
    public final static String STATUS_DONE = "Done";
    public final static int STATUS_OPEN_INDEX = 0;
    public final static int STATUS_ONGOING_INDEX = 1;
    public final static int STATUS_DONE_INDEX = 2;

    /**
     * Constructor
     * 
     * @param title Unique title of the task
     * @param description Description of the task
     * @param deadline Deadline of the task
     * @param status Status of the task
     */
    public Task(String title, String description, String deadline, String status) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    /**
     * Get task content for inserting TodoList Table
     * @return String[] Array of task for TodoList Table
     */
    public String[] getTableContent() {
        return new String[]{getTitle(), getDescription(), getDeadline(), getStatus(), "DELETE"};
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Builds JSONObject of the task
     * 
     * @return JSONObject of the task
     */
    public JSONObject toJSON() {

        JSONObject obj = new JSONObject();
        obj.put("title", title);
        obj.put("description", description);
        obj.put("deadline", deadline);
        obj.put("status", status);   
        return obj;
    }

}
