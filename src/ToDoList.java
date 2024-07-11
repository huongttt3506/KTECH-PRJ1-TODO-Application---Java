import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ToDoList {
  private List<ToDo> todos;
  protected static String filename = "todoList.txt";

  public ToDoList() {
    this.todos = new ArrayList<>();
    TodoReadWrite.loadData(filename);
  }

  public List<ToDo> getTodos() {
    return todos;
  }

  // Create To-do
  public void create(String title, LocalDate until) {
    ToDo todo = new ToDo(title, until);
    todos.add(todo);
    TodoReadWrite.saveData(todos, filename);
  }
  // Add more to-do
  public void add(List<ToDo> todos) {
    this.todos.addAll(todos);
    TodoReadWrite.saveData(this.todos, filename);
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
        todo.setUntil(dueDate);
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
