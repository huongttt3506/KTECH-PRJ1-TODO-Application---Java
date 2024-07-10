

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ToDoApp {
    private static ToDoList todoList = new ToDoList();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Create new scanner and new TodoList to receive information
        ToDoList toDoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Your TODOs:");
        // If Todolist is empty:
        if (todoList.getTodos().isEmpty()) {
            System.out.println("You have no more TODOs left!!!\n");
        }
        // Else: not empty
        else {
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
    private static void createTodo () {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Until (yyyy-mm-dd: ");
        String untilStr = scanner.nextLine();
        LocalDate until = LocalDate.parse(untilStr);
        ToDo toDo = new ToDo(title, until);
        todoList.add((List<ToDo>) toDo);
        System.out.println("Saved!!!\n");
    }

    //Edit ToDoList
    private static void editTodo () {
        //Case: todolist is empty:
        if (todoList.getTodos().isEmpty()) {
            System.out.println("You have no TODOs to edit\n");
        } else {
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
    private static void finishTodo () {
        // todolist is empty:
        if (todoList.getTodos().isEmpty()) {
            System.out.println("You have no TODOs to finish!\n");
        }
        else {
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
    private static void deleteTodo () {
        if (todoList.getTodos().isEmpty()) {
            System.out.println("You have no TODOs to deletle");
        } else {
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

