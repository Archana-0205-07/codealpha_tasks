import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_PATH = "data/students.dat";

    @SuppressWarnings("unchecked")
    public static ArrayList<Student> loadStudents() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<Student>();
        }

        try {

            ObjectInputStream ois =
                    new ObjectInputStream(
                            new FileInputStream(file));

            ArrayList<Student> students =
                    (ArrayList<Student>) ois.readObject();

            ois.close();

            return students;

        } catch (Exception e) {

            System.out.println("Error loading student data.");
            return new ArrayList<Student>();
        }
    }

    public static void saveStudents(ArrayList<Student> students) {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            ObjectOutputStream oos =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_PATH));

            oos.writeObject(students);
            oos.close();

        } catch (Exception e) {

            System.out.println("Error saving student data.");
        }
    }

    public static void exportToCSV(ArrayList<Student> students) {

        try {

            PrintWriter writer =
                    new PrintWriter("student_report.csv");

            writer.println("Name,Roll Number,Subject,Marks,Grade");

            for (Student s : students) {

                writer.println(
                        s.getName() + "," +
                        s.getRollNumber() + "," +
                        s.getSubject() + "," +
                        s.getMarks() + "," +
                        s.getGrade()
                );
            }

            writer.close();

        } catch (Exception e) {

            System.out.println("Error exporting CSV.");
        }
    }
}