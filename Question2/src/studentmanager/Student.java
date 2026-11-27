
package studentmanager;

public class Student {
    private String studentId;
    private String name;
    private String course;
    private int age;

    public Student(String studentId, String name, String course, int age) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return studentId + " | " + name + " | " + course + " | " + age;
    }
}
