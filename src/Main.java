import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    // ---------- Shared data structures ----------
    static Scanner sc = new Scanner(System.in);
    static LinkedList<Student> studentList = new LinkedList<>();      // Requirement 2: Linked List
    static Stack<ActionRecord> historyStack = new Stack<>();          // Requirement 3: Stack
    static Queue<String> serviceQueue = new LinkedList<>();           // Requirement 4: Queue
    static StudentBST bst = new StudentBST();                        // Requirement 5: BST
    static StudentHashTable hashTable = new StudentHashTable(10);    // Requirement 6: Hashing
    static CampusGraph campusGraph = new CampusGraph();               // Requirement 7-11: Graph

    public static void main(String[] args) {

        int choice = 0;

        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    displayAllStudents();
                    break;
                case 5:
                    addServiceRequest();
                    break;
                case 6:
                    processNextServiceRequest();
                    break;
                case 7:
                    displayRecentActions();
                    break;
                case 8:
                    bst.displayInOrder();
                    break;
                case 9:
                    searchStudentByHash();
                    break;
                case 10:
                    addCampusLocation();
                    break;
                case 11:
                    removeCampusLocation();
                    break;
                case 12:
                    addCampusConnection();
                    break;
                case 13:
                    removeCampusConnection();
                    break;
                case 14:
                    campusGraph.displayConnections();
                    break;
                case 15:
                    traverseCampus();
                    break;
                case 16:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 16.");
            }

        } while (choice != 16);

        sc.close();
    }

    // ================= MENU =================
    private static void printMenu() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("  UNIVERSITY STUDENT RECORD AND");
        System.out.println("  CAMPUS ROUTE MANAGEMENT SYSTEM");
        System.out.println("====================================");
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST/AVL");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
        System.out.println("====================================");
    }

    // ================= INPUT HELPERS (SAFE, nextLine-only) =================
    // Using only nextLine() everywhere avoids the classic nextInt()/nextLine()
    // buffer bug that causes "" to be read on the following prompt.

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // ================= STUDENT RECORD OPERATIONS =================

    private static Student findStudentInList(String studentId) {
        for (Student s : studentList) {
            if (s.studentId.equalsIgnoreCase(studentId)) {
                return s;
            }
        }
        return null;
    }

    private static void addStudent() {
        String id = readNonEmptyString("Enter Student ID: ");

        if (findStudentInList(id) != null) {
            System.out.println("Error: Student ID already exists: " + id);
            return;
        }

        String name = readNonEmptyString("Enter Name: ");
        String programme = readNonEmptyString("Enter Programme: ");
        double marks = readDouble("Enter Marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Error: Marks must be between 0 and 100. Student not added.");
            return;
        }

        Student student = new Student(id, name, programme, marks);
        studentList.add(student);
        bst.insert(student);
        hashTable.insert(student);

        historyStack.push(new ActionRecord("ADD", id, "Added student " + name));
        System.out.println("Student added successfully: " + student);
    }

    private static void updateStudent() {
        String id = readNonEmptyString("Enter Student ID to update: ");
        Student existing = findStudentInList(id);

        if (existing == null) {
            System.out.println("Error: No student found with ID: " + id);
            return;
        }

        String name = readNonEmptyString("Enter new Name: ");
        String programme = readNonEmptyString("Enter new Programme: ");
        double marks = readDouble("Enter new Marks (0-100): ");

        if (marks < 0 || marks > 100) {
            System.out.println("Error: Marks must be between 0 and 100. Update cancelled.");
            return;
        }

        // Remove old record from all structures
        studentList.remove(existing);

        Student updated = new Student(id, name, programme, marks);
        studentList.add(updated);
        bst.update(id, updated);
        hashTable.update(id, updated);

        historyStack.push(new ActionRecord("UPDATE", id, "Updated student " + name));
        System.out.println("Student updated successfully: " + updated);
    }

    private static void deleteStudent() {
        String id = readNonEmptyString("Enter Student ID to delete: ");
        Student existing = findStudentInList(id);

        if (existing == null) {
            System.out.println("Error: No student found with ID: " + id);
            return;
        }

        studentList.remove(existing);
        bst.delete(id);
        hashTable.delete(id);

        historyStack.push(new ActionRecord("DELETE", id, "Deleted student " + existing.name));
        System.out.println("Student deleted successfully: " + id);
    }

    private static void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("=== All Students (Linked List, insertion order) ===");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    // ================= QUEUE: SERVICE REQUESTS =================

    private static void addServiceRequest() {
        String id = readNonEmptyString("Enter Student ID making the request: ");
        String requestDetails = readNonEmptyString("Enter Service Request details: ");
        String entry = "Student " + id + ": " + requestDetails;
        serviceQueue.add(entry);
        System.out.println("Service request added to queue: " + entry);
    }

    private static void processNextServiceRequest() {
        if (serviceQueue.isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        String next = serviceQueue.poll();
        System.out.println("Processing service request: " + next);
    }

    // ================= STACK: RECENT ACTIONS =================

    private static void displayRecentActions() {
        if (historyStack.isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("=== Recent Actions (most recent first) ===");
        for (int i = historyStack.size() - 1; i >= 0; i--) {
            System.out.println(historyStack.get(i));
        }
    }

    // ================= HASHING: SEARCH =================

    private static void searchStudentByHash() {
        String id = readNonEmptyString("Enter Student ID to search: ");
        Student found = hashTable.search(id);
        if (found != null) {
            System.out.println("Student Found: " + found);
        } else {
            System.out.println("Student ID not found: " + id);
        }
    }

    // ================= GRAPH: CAMPUS LOCATIONS =================

    private static void addCampusLocation() {
        String location = readNonEmptyString("Enter Campus Location name: ");
        campusGraph.addLocation(location);
    }

    private static void removeCampusLocation() {
        String location = readNonEmptyString("Enter Campus Location name to remove: ");
        campusGraph.removeLocation(location);
    }

    private static void addCampusConnection() {
        String loc1 = readNonEmptyString("Enter first location: ");
        String loc2 = readNonEmptyString("Enter second location: ");
        campusGraph.addConnection(loc1, loc2);
    }

    private static void removeCampusConnection() {
        String loc1 = readNonEmptyString("Enter first location: ");
        String loc2 = readNonEmptyString("Enter second location: ");
        campusGraph.removeConnection(loc1, loc2);
    }

    private static void traverseCampus() {
        String startLocation = readNonEmptyString("Enter starting location: ");
        if (!campusGraph.hasLocation(startLocation)) {
            System.out.println("Location not found: " + startLocation);
            return;
        }
        String type = readNonEmptyString("Enter traversal type (BFS/DFS): ");
        if (type.equalsIgnoreCase("BFS")) {
            campusGraph.bfsTraversal(startLocation);
        } else if (type.equalsIgnoreCase("DFS")) {
            campusGraph.dfsTraversal(startLocation);
        } else {
            System.out.println("Invalid traversal type. Please enter BFS or DFS.");
        }
    }
}