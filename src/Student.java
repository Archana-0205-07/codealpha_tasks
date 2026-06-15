import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String rollNumber;
    private String subject;
    private double marks;

    public Student(String name, String rollNumber, String subject, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subject = subject;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getSubject() {
        return subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrade() {

        if (marks >= 90)
            return "A+";
        else if (marks >= 80)
            return "A";
        else if (marks >= 70)
            return "B";
        else if (marks >= 60)
            return "C";
        else if (marks >= 50)
            return "D";
        else
            return "F";
    }

    public boolean isPassed() {
        return marks >= 50;
    }
}