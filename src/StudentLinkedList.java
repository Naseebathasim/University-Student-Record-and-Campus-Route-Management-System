public class StudentLinkedList {

    private class Node {
        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    private Node head;

    public StudentLinkedList() {
        head = null;
    }

    // Add student to the end
    public boolean addStudent(Student student) {

        if (searchStudent(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        return true;
    }

    // Search student
    public Student searchStudent(String studentId) {

        Node current = head;

        while (current != null) {

            if (current.student.getStudentId().equalsIgnoreCase(studentId)) {
                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    // Update student
    public boolean updateStudent(
            String studentId,
            String name,
            String programme,
            double marks) {

        Student student = searchStudent(studentId);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);

        return true;
    }

    // Delete student
    public Student deleteStudent(String studentId) {

        if (head == null) {
            return null;
        }

        if (head.student.getStudentId().equalsIgnoreCase(studentId)) {

            Student deleted = head.student;
            head = head.next;

            return deleted;
        }

        Node current = head;

        while (current.next != null) {

            if (current.next.student.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                Student deleted = current.next.student;

                current.next = current.next.next;

                return deleted;
            }

            current = current.next;
        }

        return null;
    }

    // Display all students
    public void displayAll() {

        if (head == null) {
            System.out.println("\nNo student records found.");
            return;
        }

        Node current = head;

        System.out.println("\n========== STUDENT RECORDS ==========");

        while (current != null) {

            System.out.println(current.student);

            current = current.next;
        }

        System.out.println("=====================================");
    }

    // Return number of students
    public int size() {

        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }
}