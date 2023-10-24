package ToDoList;

import java.util.Date;

public class Task {
    private String title;
    private boolean isDone;
    private Date  date;
    private String description;
    
    public Task() {
    }
    
    public Task(String title, boolean isDone, String description) {
    
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public boolean isDone() {
        return isDone;
    }
    
    public void setDone(boolean done) {
        isDone = done;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public void markAsComplete() {
        isDone = true;
    }
}
