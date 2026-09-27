public class Student {
    String studentId;
    String name;
    String programme;
    double marks;

    public Student(String studentId, String name, String programme, double marks) {
        this.studentId = studentId;
        this.name = name;
        this.programme = programme;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + studentId + " | Name: " + name +
               " | Programme: " + programme + " | Marks: " + marks;
    }
}