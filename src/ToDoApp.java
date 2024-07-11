

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ToDoApp {
    private static ToDoList todoList = new ToDoList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
            System.out.println("5. Exit\n");
            // Input: (int) choice
            System.out.println("Input: ");
            int choice = scanner.nextInt();
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
        //If Todolist is empty:
        if (todoList.getTodos().isEmpty()) {
            System.out.println("You have no more TODOs left!!!\n");
        }
        // Else: not empty
        else {
            System.out.println("Your TODOs:");
            // case: undone != 0
            int countUndone = 0;
            for (int i = 0; i < todoList.getTodos().size(); i++) {
                ToDo todo = todoList.getTodos().get(i);
                if (!todo.isDone()) {
                    //Print list of to-do
                    countUndone++;
                    System.out.println(countUndone + ". " + todo);
                }
            }
            //case: undone == 0
            if (countUndone == 0) {
                System.out.println("You have no more TODOs left!!!\n");
            } else if (countUndone == 1) {
                System.out.println("You have 1 TODO left!!!\n");
            } else {
                System.out.println("You have " + countUndone + " TODOs left!!!\n");
            }
        }
    }

    //Index 1: Create ToDoList
    private static void createTodo() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        scanner.nextLine();
        LocalDate until = null;
        while (until == null) {
            System.out.println("Until (yyyy-mm-dd): ");
            String untilStr = scanner.nextLine();
            scanner.nextLine();
            try {
                until = LocalDate.parse(untilStr);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format.");
            }
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
            System.out.println("Title: ");
            String title = scanner.nextLine();
            System.out.println("Until (yyyy-mm-dd): ");
            String untilStr = scanner.nextLine();
            LocalDate until = LocalDate.parse(untilStr);
            todoList.edit(index, title, until);
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


