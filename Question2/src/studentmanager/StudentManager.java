
package studentmanager;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Random;

import java.util.Stack;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
public class StudentManager {

public void displayStudentsInGUI(javax.swing.JTextArea area) {

    Enumeration<Student> e = students.elements();

    while (e.hasMoreElements()) {
        area.append(e.nextElement().toString() + "\n");
    }
}

public void searchStudentInGUI(
        String search,
        javax.swing.JTextArea area) {

    boolean found = false;

    for (Student s : students) {

        if (s.getName().equalsIgnoreCase(search)
                || s.getStudentId().equalsIgnoreCase(search)) {

            area.append(s.toString() + "\n");
            found = true;
        }
    }

    if (!found) {
        area.setText("Student not found!");
    }
}


public void searchStudent() {

    Scanner input = new Scanner(System.in);

    System.out.print("Enter student name or ID: ");
    String search = input.nextLine();

    boolean found = false;

    for (Student s : students) {

        if (s.getName().equalsIgnoreCase(search)
                || s.getStudentId().equalsIgnoreCase(search)) {

            System.out.println("Student found:");
            System.out.println(s);

            found = true;
        }
    }

    if (!found) {
        System.out.println("Student not found!");
    }
}


public void saveStudents() {

    try {
        FileWriter writer = new FileWriter("students.txt");

        for (Student s : students) {

            writer.write(
                s.getStudentId() + "," +
                s.getName() + "," +
                s.getCourse() + "," +
                s.getAge() + "\n"
            );
        }

        writer.close();

        System.out.println("Records saved successfully!");

    } catch (IOException e) {

        System.out.println("Error saving records: " + e.getMessage());
    }
}

public void loadStudents() {

    try {
        BufferedReader reader = new BufferedReader(
            new FileReader("students.txt")
        );

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",");

            if (data.length == 4) {

                Student s = new Student(
                    data[0],
                    data[1],
                    data[2],
                    Integer.parseInt(data[3])
                );

                students.add(s);
            }
        }

        reader.close();

        System.out.println("Records loaded successfully!");

    } catch (IOException | NumberFormatException e) {

        System.out.println("Error loading records: " + e.getMessage());
    }
}

    // Undo last student addition
public void undoLastAddition() {

    if (!undoStack.empty()) {

        Student student = undoStack.pop();

        students.remove(student);

        System.out.println("Last student addition undone!");

    } else {

        System.out.println("Nothing to undo!");
    }
}
    private Stack<Student> undoStack = new Stack<Student>();
    private Vector<Student> students = new Vector<Student>();
    private Random random = new Random();

    // Generate student ID
    public String generateStudentID() {
        return "STU" + (1000 + random.nextInt(9000));
    }

    // Add student
    public void addStudent(String name, String course, int age) {

    String id = generateStudentID();

    Student student = new Student(id, name, course, age);

    students.add(student);

    undoStack.push(student);

    System.out.println("Student added successfully!");
}
    }

    // Display all students
    public void displayStudents() {

        Enumeration<Student> e = students.elements();

        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }
}
