import java.time.LocalDate;

//This class contains attributes: title, dueDate, done

public class ToDo {
    private String title;
    private LocalDate dueDate;
    private boolean done;

    //Constructor
    public ToDo(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        // The default when creating a new to-do  is unfinished
        this.done = false;
    }
    //Setter & Getter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (done) {
            return title + " (Done)";
        }
        else {
            return title;
        }
    }
}
