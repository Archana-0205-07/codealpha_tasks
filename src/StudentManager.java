import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<Student>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int index) {

        if (index >= 0 && index < students.size()) {
            students.remove(index);
        }
    }

    public void updateStudent(int index, Student student) {

        if (index >= 0 && index < students.size()) {
            students.set(index, student);
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int getTotalStudents() {
        return students.size();
    }

    public double getAverageMarks() {

        if (students.isEmpty())
            return 0;

        double total = 0;

        for (Student s : students) {
            total += s.getMarks();
        }

        return total / students.size();
    }

    public double getHighestMarks() {

        if (students.isEmpty())
            return 0;

        double highest = students.get(0).getMarks();

        for (Student s : students) {

            if (s.getMarks() > highest) {
                highest = s.getMarks();
            }
        }

        return highest;
    }

    public double getLowestMarks() {

        if (students.isEmpty())
            return 0;

        double lowest = students.get(0).getMarks();

        for (Student s : students) {

            if (s.getMarks() < lowest) {
                lowest = s.getMarks();
            }
        }

        return lowest;
    }

    public Student getTopPerformer() {

        if (students.isEmpty())
            return null;

        Student top = students.get(0);

        for (Student s : students) {

            if (s.getMarks() > top.getMarks()) {
                top = s;
            }
        }

        return top;
    }

    public double getPassPercentage() {

        if (students.isEmpty())
            return 0;

        int passed = 0;

        for (Student s : students) {

            if (s.isPassed()) {
                passed++;
            }
        }

        return (passed * 100.0) / students.size();
    }

    public Student searchByRollNumber(String rollNumber) {

        for (Student s : students) {

            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }

        return null;
    }
}