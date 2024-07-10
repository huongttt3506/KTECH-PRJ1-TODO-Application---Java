import java.io.*;
import java.time.LocalDate;
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
                LocalDate dueDate = LocalDate.parse(value[1]);
                todos.add(new ToDo(value[0], dueDate));
            }
        } catch (IOException e) {
            System.out.println("Read data error: " + e.getMessage());
        }
        return todos;
    }

    public static void saveData(List<ToDo> todos, String filename) {
        if (todos.isEmpty()) {
            try {
                // create filename;
                FileWriter fileWriter = new FileWriter(filename);
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Cannot create file");
            }
            return;
        }

        try (FileWriter fileWriter = new FileWriter(filename, true);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (ToDo todo : todos) {
                String line = todo.getTitle() + "," +
                        todo.getDueDate();
                writer.write(line);
            }
        } catch (IOException e) {
            System.out.println("Can not open file to save" + e.getMessage());
        }
    }

}

