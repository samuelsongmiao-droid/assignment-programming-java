
package studentmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGUI extends JFrame {

    StudentManager manager = new StudentManager();

    JTextField nameField, courseField, ageField;
    JTextArea displayArea;

    public StudentGUI() {

        setTitle("Student Records Manager");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        JButton addButton = new JButton("Add Student");
        JButton displayButton = new JButton("Display Students");
        JButton undoButton = new JButton("Undo");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton searchButton = new JButton("Search");

        inputPanel.add(addButton);
        inputPanel.add(displayButton);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(undoButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(searchButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add student
        addButton.addActionListener(e -> {

            try {
                String name = nameField.getText();
                String course = courseField.getText();
                int age = Integer.parseInt(ageField.getText());

                if (name.trim().isEmpty() || course.trim().isEmpty()
                        || age <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Enter valid student details!");
                    return;
                }

                manager.addStudent(name, course, age);

                JOptionPane.showMessageDialog(this,
                        "Student added successfully!");

                nameField.setText("");
                courseField.setText("");
                ageField.setText("");

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Age must be a valid number!");
            }
        });

        // Display students
        displayButton.addActionListener(e -> {

            displayArea.setText("");

            displayStudentsInArea();
        });

        // Undo
        undoButton.addActionListener(e -> {

            manager.undoLastAddition();

            displayArea.setText("Last addition undone.\n");
            displayStudentsInArea();
        });

        // Save
        saveButton.addActionListener(e -> {

            manager.saveStudents();

            JOptionPane.showMessageDialog(this,
                    "Student records saved!");
        });

        // Load
        loadButton.addActionListener(e -> {

            manager.loadStudents();

            displayArea.setText("");
            displayStudentsInArea();
        });

        // Search
        searchButton.addActionListener(e -> {

            String search = JOptionPane.showInputDialog(
                    this, "Enter student name or ID:");

            if (search == null || search.trim().isEmpty()) {
                return;
            }

            displayArea.setText("");

            manager.searchStudentInGUI(search, displayArea);
        });

        setVisible(true);
    }

    private void displayStudentsInArea() {

        manager.displayStudentsInGUI(displayArea);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new StudentGUI());
    }
}
