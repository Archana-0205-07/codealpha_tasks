
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Dashboard extends JFrame {

    private StudentManager manager;
    private JTable table;
    private DefaultTableModel model;

    private JTextField nameField;
    private JTextField rollField;
    private JTextField subjectField;
    private JTextField marksField;
    private JTextField searchField;

    private JLabel totalLabel;
    private JLabel avgLabel;
    private JLabel highestLabel;
    private JLabel lowestLabel;
    private JLabel passLabel;
    private JLabel topLabel;

    public Dashboard() {

        manager = new StudentManager();
        manager.setStudents(FileManager.loadStudents());

        setTitle("Student Grade Tracker");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 5, 5, 5));

        nameField = new JTextField();
        rollField = new JTextField();
        subjectField = new JTextField();
        marksField = new JTextField();
        searchField = new JTextField();

        inputPanel.add(new JLabel("Name"));
        inputPanel.add(new JLabel("Roll Number"));
        inputPanel.add(new JLabel("Subject"));
        inputPanel.add(new JLabel("Marks"));
        inputPanel.add(new JLabel("Search Roll No"));

        inputPanel.add(nameField);
        inputPanel.add(rollField);
        inputPanel.add(subjectField);
        inputPanel.add(marksField);
        inputPanel.add(searchField);

        add(inputPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"Name","Roll No","Subject","Marks","Grade"},0);

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton exportBtn = new JButton("Export CSV");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(exportBtn);

        bottom.add(buttonPanel, BorderLayout.NORTH);

        JPanel stats = new JPanel(new GridLayout(6,1));

        totalLabel = new JLabel();
        avgLabel = new JLabel();
        highestLabel = new JLabel();
        lowestLabel = new JLabel();
        passLabel = new JLabel();
        topLabel = new JLabel();

        stats.add(totalLabel);
        stats.add(avgLabel);
        stats.add(highestLabel);
        stats.add(lowestLabel);
        stats.add(passLabel);
        stats.add(topLabel);

        bottom.add(stats, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addStudent());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        searchBtn.addActionListener(e -> searchStudent());
        exportBtn.addActionListener(e -> exportCSV());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0){
                nameField.setText(model.getValueAt(row,0).toString());
                rollField.setText(model.getValueAt(row,1).toString());
                subjectField.setText(model.getValueAt(row,2).toString());
                marksField.setText(model.getValueAt(row,3).toString());
            }
        });

        refreshTable();
        updateStats();
    }

    private void addStudent() {
        try {
            Student s = new Student(
                    nameField.getText(),
                    rollField.getText(),
                    subjectField.getText(),
                    Double.parseDouble(marksField.getText())
            );

            manager.addStudent(s);
            saveRefresh();
            clearFields();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Invalid Input");
        }
    }

    private void updateStudent() {
        int row = table.getSelectedRow();

        if(row < 0){
            JOptionPane.showMessageDialog(this,"Select a row");
            return;
        }

        try {
            Student s = new Student(
                    nameField.getText(),
                    rollField.getText(),
                    subjectField.getText(),
                    Double.parseDouble(marksField.getText())
            );

            manager.updateStudent(row,s);
            saveRefresh();

        } catch (Exception ex){
            JOptionPane.showMessageDialog(this,"Invalid Input");
        }
    }

    private void deleteStudent() {
        int row = table.getSelectedRow();

        if(row < 0){
            JOptionPane.showMessageDialog(this,"Select a row");
            return;
        }

        manager.removeStudent(row);
        saveRefresh();
        clearFields();
    }

    private void searchStudent() {

        String roll = searchField.getText().trim();

        Student s = manager.searchByRollNumber(roll);

        if(s == null){
            JOptionPane.showMessageDialog(this,"Student Not Found");
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Name: " + s.getName() +
                "\nSubject: " + s.getSubject() +
                "\nMarks: " + s.getMarks() +
                "\nGrade: " + s.getGrade()
        );
    }

    private void exportCSV() {
        FileManager.exportToCSV(manager.getStudents());
        JOptionPane.showMessageDialog(this,
                "student_report.csv exported successfully");
    }

    private void refreshTable() {

        model.setRowCount(0);

        ArrayList<Student> students = manager.getStudents();

        for(Student s : students){

            model.addRow(new Object[]{
                    s.getName(),
                    s.getRollNumber(),
                    s.getSubject(),
                    s.getMarks(),
                    s.getGrade()
            });
        }
    }

    private void updateStats() {

        totalLabel.setText("Total Students: " + manager.getTotalStudents());
        avgLabel.setText("Average Marks: " +
                String.format("%.2f", manager.getAverageMarks()));
        highestLabel.setText("Highest Marks: " +
                manager.getHighestMarks());
        lowestLabel.setText("Lowest Marks: " +
                manager.getLowestMarks());
        passLabel.setText("Pass Percentage: " +
                String.format("%.2f", manager.getPassPercentage()) + "%");

        Student top = manager.getTopPerformer();

        if(top != null){
            topLabel.setText("Top Performer: " +
                    top.getName() +
                    " (" + top.getMarks() + ")");
        } else {
            topLabel.setText("Top Performer: N/A");
        }
    }

    private void saveRefresh() {
        FileManager.saveStudents(manager.getStudents());
        refreshTable();
        updateStats();
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        subjectField.setText("");
        marksField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
}
