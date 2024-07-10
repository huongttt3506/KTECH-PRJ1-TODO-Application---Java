import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ToDoList {
  private List<ToDo> todos;
  protected static final String filename = "todoList.txt";

  public ToDoList() {
    this.todos = new ArrayList<>();
    TodoReadWrite.loadData(filename);
  }

  public List<ToDo> getTodos() {
    return todos;
  }

  // Create to-do filename
  public void create() {
    add(new ArrayList<>());
  }

  // add more to-do
  public void add(List<ToDo> todos) {
      this.todos = todos;
    TodoReadWrite.saveData(todos, filename);
  }

  // Edit to-do
  public void edit(int index, String title, LocalDate dueDate) {
    if(index >= 0 && index < todos.size()) {
      ToDo todo = todos.get(index);
      // if input title, setTitle - else: not change
      if (!title.isEmpty()) {
        todo.setTitle(title);
      }
      // if input new due date, setDueDate - else : not change
      if (dueDate != null) {
        todo.setDueDate(dueDate);
      }
      // save to file
      TodoReadWrite.saveData(todos, filename);
    }
  }
  // Finish to-do
  public void finish(int index) {
    if (index >= 0 && index < todos.size()) {
      ToDo todo = todos.get(index);
      todo.setDone();
      TodoReadWrite.saveData(todos, filename);
    }
  }

  //Delete todos
  public void delete(int index) {
    if (index >= 0 && index < todos.size() ) {
      todos.remove(index);
      TodoReadWrite.saveData(todos, filename);
    }
  }

}
