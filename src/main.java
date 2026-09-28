import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static StudentLinkedList studentList =
            new StudentLinkedList();

    private static ActionStack actionStack =
            new ActionStack();

    private static ServiceQueue serviceQueue =
            new ServiceQueue();

    private static StudentBST studentBST =
            new StudentBST();

    private static StudentHashTable hashTable =
            new StudentHashTable();

    private static CampusGraph campusGraph =
            new CampusGraph();

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println(" UNIVERSITY STUDENT RECORD AND CAMPUS");
        System.out.println(" ROUTE MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice =
                    InputValidator.readInteger(
                            scanner,
                            "Enter your choice: ",
                            1,
                            16);

            System.out.println();

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
                    displayStudents();
                    break;

                case 5:
                    addServiceRequest();
                    break;

                case 6:
                    processServiceRequest();
                    break;

                case 7:
                    displayRecentActions();
                    break;

                case 8:
                    displayStudentsUsingBST();
                    break;

                case 9:
                    searchStudentUsingHashing();
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
                    displayCampusConnections();
                    break;

                case 15:
                    traverseCampus();
                    break;

                case 16:
                    running = false;
                    System.out.println(
                            "Thank you for using the system.");
                    break;

                default:
                    System.out.println(
                            "Invalid menu option.");
            }

            if (running) {
                System.out.println(
                        "\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // ============================================
    // MAIN MENU
    // ============================================

    private static void displayMenu() {

        System.out.println("\n==============================================");
        System.out.println("                  MAIN MENU");
        System.out.println("==============================================");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS/DFS");
        System.out.println("16. Exit");
        System.out.println("==============================================");
    }

    // ============================================
    // 1. ADD STUDENT
    // ============================================

    private static void addStudent() {

        System.out.println("========== ADD STUDENT ==========");

        String studentId =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Student ID: ");

        if (studentList.searchStudent(studentId) != null) {

            System.out.println(
                    "Student ID already exists.");

            return;
        }

        String name =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Student Name: ");

        String programme =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Programme: ");

        double marks =
                InputValidator.readMarks(scanner);

        Student student =
                new Student(
                        studentId,
                        name,
                        programme,
                        marks);

        studentList.addStudent(student);
        studentBST.insert(student);
        hashTable.insert(student);

        actionStack.push(
                new ActionRecord(
                        "Added student record: "
                                + studentId));

        System.out.println(
                "Student record added successfully.");
    }

    // ============================================
    // 2. UPDATE STUDENT
    // ============================================

    private static void updateStudent() {

        System.out.println("========== UPDATE STUDENT ==========");

        String studentId =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Student ID: ");

        Student existing =
                studentList.searchStudent(studentId);

        if (existing == null) {

            System.out.println(
                    "Student record not found.");

            return;
        }

        String name =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter New Name: ");

        String programme =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter New Programme: ");

        double marks =
                InputValidator.readMarks(scanner);

        studentList.updateStudent(
                studentId,
                name,
                programme,
                marks);

        /*
         * BST and Hash Table contain references to the
         * same Student object, so the updated values
         * are automatically reflected there.
         */

        actionStack.push(
                new ActionRecord(
                        "Updated student record: "
                                + studentId));

        System.out.println(
                "Student record updated successfully.");
    }

    // ============================================
    // 3. DELETE STUDENT
    // ============================================

    private static void deleteStudent() {

        System.out.println("========== DELETE STUDENT ==========");

        String studentId =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Student ID: ");

        Student deleted =
                studentList.deleteStudent(studentId);

        if (deleted == null) {

            System.out.println(
                    "Student record not found.");

            return;
        }

        /*
         * Rebuild BST because the assignment requires
         * the BST to represent current student records.
         */

        rebuildBST();

        hashTable.delete(studentId);

        actionStack.push(
                new ActionRecord(
                        "Deleted student record: "
                                + studentId));

        System.out.println(
                "Student record deleted successfully.");
    }

    // ============================================
    // 4. DISPLAY LINKED LIST
    // ============================================

    private static void displayStudents() {

        System.out.println(
                "========== LINKED LIST ==========");

        studentList.displayAll();

        System.out.println(
                "Total Students: "
                        + studentList.size());
    }

    // ============================================
    // 5. ADD SERVICE REQUEST
    // ============================================

    private static void addServiceRequest() {

        System.out.println(
                "========== ADD SERVICE REQUEST ==========");

        String requestId =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Request ID: ");

        String studentId =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Student ID: ");

        if (studentList.searchStudent(studentId) == null) {

            System.out.println(
                    "Student ID does not exist.");

            return;
        }

        String requestType =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Service Request: ");

        ServiceRequest request =
                new ServiceRequest(
                        requestId,
                        studentId,
                        requestType);

        serviceQueue.enqueue(request);

        actionStack.push(
                new ActionRecord(
                        "Added service request: "
                                + requestId));

        System.out.println(
                "Service request added to queue.");
    }

    // ============================================
    // 6. PROCESS SERVICE REQUEST
    // ============================================

    private static void processServiceRequest() {

        System.out.println(
                "========== PROCESS SERVICE REQUEST ==========");

        ServiceRequest request =
                serviceQueue.dequeue();

        if (request == null) {

            System.out.println(
                    "No service requests available.");

            return;
        }

        System.out.println(
                "Processing request:");

        System.out.println(request);

        actionStack.push(
                new ActionRecord(
                        "Processed service request: "
                                + request.getRequestId()));

        System.out.println(
                "Request processed successfully.");
    }

    // ============================================
    // 7. DISPLAY STACK
    // ============================================

    private static void displayRecentActions() {

        System.out.println(
                "========== STACK ==========");

        actionStack.display();
    }

    // ============================================
    // 8. DISPLAY BST
    // ============================================

    private static void displayStudentsUsingBST() {

        System.out.println(
                "========== BST ==========");

        studentBST.displayInOrder();
    }

    // ============================================
    // 9. HASH SEARCH
    // ============================================

    private static void searchStudentUsingHashing() {

        System.out.println(
                "========== HASHING SEARCH ==========");

        String studentId =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Student ID: ");

        Student student =
                hashTable.search(studentId);

        if (student == null) {

            System.out.println(
                    "Student not found.");

        } else {

            System.out.println(
                    "Student found using Hashing:");

            System.out.println(student);
        }
    }

    // ============================================
    // 10. ADD CAMPUS LOCATION
    // ============================================

    private static void addCampusLocation() {

        System.out.println(
                "========== ADD CAMPUS LOCATION ==========");

        String location =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Campus Location: ");

        if (campusGraph.addLocation(location)) {

            actionStack.push(
                    new ActionRecord(
                            "Added campus location: "
                                    + location));

            System.out.println(
                    "Campus location added successfully.");

        } else {

            System.out.println(
                    "Location already exists or is invalid.");
        }
    }

    // ============================================
    // 11. REMOVE CAMPUS LOCATION
    // ============================================

    private static void removeCampusLocation() {

        System.out.println(
                "========== REMOVE CAMPUS LOCATION ==========");

        String location =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Campus Location: ");

        if (campusGraph.removeLocation(location)) {

            actionStack.push(
                    new ActionRecord(
                            "Removed campus location: "
                                    + location));

            System.out.println(
                    "Campus location removed successfully.");

        } else {

            System.out.println(
                    "Campus location not found.");
        }
    }

    // ============================================
    // 12. ADD CONNECTION
    // ============================================

    private static void addCampusConnection() {

        System.out.println(
                "========== ADD CAMPUS CONNECTION ==========");

        String location1 =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter First Location: ");

        String location2 =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Second Location: ");

        if (!campusGraph.hasLocation(location1)
                || !campusGraph.hasLocation(location2)) {

            System.out.println(
                    "Both locations must exist first.");

            return;
        }

        if (campusGraph.addConnection(
                location1,
                location2)) {

            actionStack.push(
                    new ActionRecord(
                            "Added campus connection: "
                                    + location1
                                    + " - "
                                    + location2));

            System.out.println(
                    "Campus connection added successfully.");

        } else {

            System.out.println(
                    "Connection already exists or is invalid.");
        }
    }

    // ============================================
    // 13. REMOVE CONNECTION
    // ============================================

    private static void removeCampusConnection() {

        System.out.println(
                "========== REMOVE CAMPUS CONNECTION ==========");

        String location1 =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter First Location: ");

        String location2 =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Second Location: ");

        if (campusGraph.removeConnection(
                location1,
                location2)) {

            actionStack.push(
                    new ActionRecord(
                            "Removed campus connection: "
                                    + location1
                                    + " - "
                                    + location2));

            System.out.println(
                    "Campus connection removed successfully.");

        } else {

            System.out.println(
                    "Connection not found.");
        }
    }

    // ============================================
    // 14. DISPLAY CAMPUS CONNECTIONS
    // ============================================

    private static void displayCampusConnections() {

        System.out.println(
                "========== CAMPUS GRAPH ==========");

        campusGraph.displayConnections();
    }

    // ============================================
    // 15. BFS / DFS
    // ============================================

    private static void traverseCampus() {

        System.out.println(
                "========== CAMPUS TRAVERSAL ==========");

        String startLocation =
                InputValidator.readNonEmptyString(
                        scanner,
                        "Enter Starting Location: ");

        if (!campusGraph.hasLocation(startLocation)) {

            System.out.println(
                    "Starting location does not exist.");

            return;
        }

        System.out.println();
        System.out.println("1. BFS");
        System.out.println("2. DFS");

        int choice =
                InputValidator.readInteger(
                        scanner,
                        "Select Traversal: ",
                        1,
                        2);

        if (choice == 1) {

            campusGraph.bfs(startLocation);

            actionStack.push(
                    new ActionRecord(
                            "Performed BFS from: "
                                    + startLocation));

        } else {

            campusGraph.dfs(startLocation);

            actionStack.push(
                    new ActionRecord(
                            "Performed DFS from: "
                                    + startLocation));
        }
    }

    // ============================================
    // REBUILD BST
    // ============================================

    private static void rebuildBST() {

        hashTable.rebuildBST(studentBST);
    }
}