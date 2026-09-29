public class StudentBST {

    private BSTNode root;

    public StudentBST() {
        root = null;
    }

    // Insert student
    public boolean insert(Student student) {

        if (search(student.getStudentId()) != null) {
            return false;
        }

        root = insertRecursive(root, student);

        return true;
    }

    private BSTNode insertRecursive(
            BSTNode node,
            Student student) {

        if (node == null) {
            return new BSTNode(student);
        }

        int comparison = student.getStudentId()
                .compareToIgnoreCase(node.student.getStudentId());

        if (comparison < 0) {

            node.left = insertRecursive(node.left, student);

        } else if (comparison > 0) {

            node.right = insertRecursive(node.right, student);
        }

        return node;
    }

    // Search student
    public Student search(String studentId) {

        BSTNode current = root;

        while (current != null) {

            int comparison = studentId.compareToIgnoreCase(
                    current.student.getStudentId());

            if (comparison == 0) {
                return current.student;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    // Display students in sorted order
    public void displayInOrder() {

        if (root == null) {
            System.out.println("\nBST is empty.");
            return;
        }

        System.out.println("\n========== STUDENTS USING BST ==========");

        inOrder(root);

        System.out.println("========================================");
    }

    private void inOrder(BSTNode node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);

        System.out.println(node.student);

        inOrder(node.right);
    }

    // Rebuild BST
    public void clear() {
        root = null;
    }
}