

public class StudentLinkedList {
    private StudentNode head;
    private int size;

    public StudentLinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
    
    public boolean add(Student s) {
        if (search(s.getStudentId()) != null) {
            return false;
        }
        StudentNode newNode = new StudentNode(s);
        if (head == null) {
            head = newNode;
        } else {
            StudentNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }
    
    public Student search(String id) {
        StudentNode current = head;
        while (current != null) {
            if (current.data.getStudentId().equalsIgnoreCase(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public boolean update(String id, String newName, String newProgramme, double newMarks) {
        Student s = search(id);
        if (s == null) {
            return false;
        }
        s.setName(newName);
        s.setProgramme(newProgramme);
        s.setMarks(newMarks);
        return true;
    }

    public Student delete(String id) {
        if (head == null) {
            return null;
        }
        if (head.data.getStudentId().equalsIgnoreCase(id)) {
            Student removed = head.data;
            head = head.next;
            size--;
            return removed;
        }
        StudentNode current = head;
        while (current.next != null) {
            if (current.next.data.getStudentId().equalsIgnoreCase(id)) {
                Student removed = current.next.data;
                current.next = current.next.next;
                size--;
                return removed;
            }
            current = current.next;
        }
        return null;
    }

    public void display() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }
        StudentNode current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.data);
            current = current.next;
            count++;
        }
    }
}