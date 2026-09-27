public class StudentBST {

    // Node class for the tree
    private class Node {
        Student data;
        Node left, right;

        Node(Student data) {
            this.data = data;
        }
    }

    private Node root;

    // ---------- INSERT ----------
    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private Node insertRec(Node current, Student student) {
        if (current == null) {
            return new Node(student);
        }
        int cmp = student.studentId.compareTo(current.data.studentId);
        if (cmp < 0) {
            current.left = insertRec(current.left, student);
        } else if (cmp > 0) {
            current.right = insertRec(current.right, student);
        } else {
            System.out.println("Student ID already exists in BST: " + student.studentId);
        }
        return current;
    }

    // ---------- SEARCH ----------
    public Student search(String studentId) {
        return searchRec(root, studentId);
    }

    private Student searchRec(Node current, String studentId) {
        if (current == null) return null;
        int cmp = studentId.compareTo(current.data.studentId);
        if (cmp == 0) return current.data;
        else if (cmp < 0) return searchRec(current.left, studentId);
        else return searchRec(current.right, studentId);
    }

    // ---------- DELETE ----------
    public void delete(String studentId) {
        root = deleteRec(root, studentId);
    }

    private Node deleteRec(Node current, String studentId) {
        if (current == null) return null;

        int cmp = studentId.compareTo(current.data.studentId);
        if (cmp < 0) {
            current.left = deleteRec(current.left, studentId);
        } else if (cmp > 0) {
            current.right = deleteRec(current.right, studentId);
        } else {
            // Node found
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            // Two children: find smallest in right subtree
            Node successor = findMin(current.right);
            current.data = successor.data;
            current.right = deleteRec(current.right, successor.data.studentId);
        }
        return current;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // ---------- IN-ORDER DISPLAY (sorted by Student ID) ----------
    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records in BST.");
            return;
        }
        System.out.println("=== Students (BST In-Order, sorted by ID) ===");
        inOrderRec(root);
    }

    private void inOrderRec(Node current) {
        if (current != null) {
            inOrderRec(current.left);
            System.out.println(current.data);
            inOrderRec(current.right);
        }
    }

    // ---------- UPDATE (delete + re-insert with new details) ----------
    public void update(String studentId, Student updatedStudent) {
        delete(studentId);
        insert(updatedStudent);
    }
}