
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ToDoApp {
    private static ToDoList todoList;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        todoList = new ToDoList();
        // First screen:
        System.out.print("Well come!\n");
        while (true) {
            // 1. display to-do list
            displayTodo();
            //2. menu:
            System.out.println("1. Create TODO");
            System.out.println("2. Edit TODO");
            System.out.println("3. Finish TODO");
            System.out.println("4. Delete TODO");
            System.out.println("5. Exit");
            // Input: (int) choice
            int choice;
            System.out.print("Input: ");
            try {choice = Integer.parseInt(scanner.next());}
            catch (NumberFormatException e) {
                System.out.println("Input must be an integer (1 ~ 5)");
                continue;
            }
            scanner.nextLine();
            switch (choice) {
                case 1:
                    createTodo();
                    break;
                case 2:
                    editTodo();
                    break;
                case 3:
                    finishTodo();
                    break;
                case 4:
                    deleteTodo();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
    }
    
    //Display ToDoList
    private static void displayTodo() {
        List<ToDo> todos = todoList.getTodos();
        todos.sort(Comparator.comparing(ToDo::getUntil, Comparator.nullsLast(LocalDate::compareTo)));
        int countUndone = 0;

        if (todoList.getTodos().isEmpty()) {
            System.out.println("You have no more TODOs left!!!\n");
        } else {
            // Count undone TODOs
            for (ToDo todo : todos) {
                if (!todo.isDone()) {
                    countUndone++;
                }
            }

            // Print number of TODOs left
            if (countUndone == 0) {
                System.out.println("You have no more TODOs left!!!\n");
            } else if (countUndone == 1) {
                System.out.println("You have 1 TODO left:");
            } else {
                System.out.println("You have " + countUndone + " TODOs left:");
            }

            // Print TODOs
            for (int i = 0; i < todos.size(); i++) {
                ToDo todo = todos.get(i);
                String status = todo.isDone() ? "(Done)" : ""; // Check if TODO is done
                System.out.println((i + 1) + ". " + todo.getTitle() + " " + status);
            }
            System.out.println(); // Blank line for spacing
        }
    }

    //Index 1: Create ToDoList
    private static void createTodo() {
        System.out.println("Title: ");
        String title = scanner.next();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        LocalDate until = null;
        System.out.println("Until (yyyy-mm-dd): ");
        String untilStr = scanner.next();
        try {
            until = LocalDate.parse(untilStr);
        }
        catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format.");
            }
        todoList.create(title, until);
        System.out.println("Saved!!!\n");

    }

    //Edit ToDoList
    private static void editTodo() {
        displayTodo();
        if(!todoList.getTodos().isEmpty()) {
            System.out.println("Edit TODO number: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            // Case: Invalid Index:
            if (index < 0 || index >= todoList.getTodos().size()) {
                System.out.println("Invalid TODO number!\n");
                return;
            }
            // Retrieve current values
            ToDo currentToDo = todoList.getTodos().get(index);
            String currentTitle = currentToDo.getTitle();
            LocalDate currentUntil = currentToDo.getUntil();

            System.out.println("Title: ");
            String newTitle = scanner.nextLine();
            if (newTitle.isEmpty()) {
                newTitle = currentTitle; // Keep current title if input is empty
            }
            LocalDate newUntil = null;
            System.out.println("Until (yyyy-mm-dd): ");
            String newUntilStr = scanner.nextLine();
            if (!newUntilStr.isEmpty()) {
                try {
                    newUntil = LocalDate.parse(newUntilStr);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Keeping current date.");
                    newUntil = currentUntil; // Keep current date on error
                }
            } else {
                newUntil = currentUntil; // Keep current date if input is empty
            }
            todoList.edit(index, newTitle, newUntil);
            System.out.println("Saved!!!\n");
        }
    }

    //Finish
    private static void finishTodo() {
        displayTodo();
        if (!todoList.getTodos().isEmpty()) {
            System.out.println("Finish TODO number: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            if (index < 0 || index >= todoList.getTodos().size()) {
                System.out.println("Invalid TODO number!\n");
                return;
            }
            todoList.finish(index);
            System.out.println("Saved!!!\n");
        }

    }

    //DeleteToDoList
    private static void deleteTodo() {
        displayTodo();
        if (!todoList.getTodos().isEmpty()) {
            System.out.println("Delete TODO number: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            // Case: invalid number
            if (index < 0 || index >= todoList.getTodos().size()) {
                System.out.println("Invalid TODO number!\n");
                return;
            }
            todoList.delete(index);
            System.out.println("Deleted!!!\n");
        }
    }
}


