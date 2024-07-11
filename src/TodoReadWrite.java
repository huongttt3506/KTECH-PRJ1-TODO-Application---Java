import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TodoReadWrite {
    public static List<ToDo> loadData(String filename) {
        List<ToDo> todos = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] value = line.split(",");
                if (value.length >= 3) { // Ensure there are at least two parts in the line
                    String title = value[0].trim();
                    String dateStr = value[1].trim();
                    String doneStr = value[2].trim();
                    //Parse done status
                    boolean done = Boolean.parseBoolean(doneStr);
                    if (!dateStr.isEmpty() && !dateStr.equals("null")) { // Check for null or empty
                        try {
                            LocalDate dueDate = LocalDate.parse(dateStr);
                            todos.add(new ToDo(title, dueDate));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format in file: " + dateStr);
                            // Handle or skip the invalid entry
                        }
                    } else {
                        System.out.println("Invalid date format or null value in file: " + dateStr);
                        // Handle or skip the invalid entry
                    }
                } else {
                    System.out.println("Invalid data format in file: " + line);
                    // Handle or skip the invalid entry
                }
            }
        } catch (IOException e) {
            System.out.println("Read data error: " + e.getMessage());
        }
        return todos;
    }

    public static void saveData(List<ToDo> todos, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (ToDo todo : todos) {
                writer.write(todo.toTxtRow());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot open file to save: " + e.getMessage());
        }
    }
}