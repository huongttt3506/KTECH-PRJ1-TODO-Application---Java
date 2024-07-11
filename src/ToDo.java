import java.time.LocalDate;

//This class contains attributes: title, until, done

public class ToDo {
    private String title;
    private LocalDate until;
    private boolean done;

    //Constructor
    public ToDo(String title, LocalDate until) {
        this.title = title;
        this.until = until;
        this.done = false;
    }
    //Setter & Getter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getUntil() {
        return until;
    }

    public void setUntil(LocalDate until) {
        this.until = until;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String status = done ? "(done)" : "";
        return  title + '\'' + status + done;
    }

    public String toTxtRow() {
        return String.format("%s, %s, %s", title, until, done);
    }
}
