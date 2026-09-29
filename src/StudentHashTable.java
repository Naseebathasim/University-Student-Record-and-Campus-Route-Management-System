public class StudentHashTable {

    private static final int TABLE_SIZE = 31;

    private class HashNode {

        Student student;
        HashNode next;

        HashNode(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    private HashNode[] table;

    public StudentHashTable() {
        table = new HashNode[TABLE_SIZE];
    }

    private int hash(String studentId) {

        int hashValue = 0;

        for (int i = 0; i < studentId.length(); i++) {

            hashValue =
                    (hashValue * 31 + studentId.charAt(i))
                            % TABLE_SIZE;
        }

        return Math.abs(hashValue);
    }

    public boolean insert(Student student) {

        int index = hash(student.getStudentId());

        HashNode current = table[index];

        while (current != null) {

            if (current.student.getStudentId()
                    .equalsIgnoreCase(student.getStudentId())) {

                return false;
            }

            current = current.next;
        }

        HashNode newNode =
                new HashNode(student);

        newNode.next = table[index];

        table[index] = newNode;

        return true;
    }

    public Student search(String studentId) {

        int index = hash(studentId);

        HashNode current = table[index];

        while (current != null) {

            if (current.student.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    public boolean delete(String studentId) {

        int index = hash(studentId);

        HashNode current = table[index];
        HashNode previous = null;

        while (current != null) {

            if (current.student.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public void clear() {

        table = new HashNode[TABLE_SIZE];
    }

    // Rebuild BST from hash table
    public void rebuildBST(StudentBST bst) {

        bst.clear();

        for (int i = 0; i < TABLE_SIZE; i++) {

            HashNode current = table[i];

            while (current != null) {

                bst.insert(current.student);

                current = current.next;
            }
        }
    }
}