import java.util.Scanner;

public class main {

    private static StudentStack<ActionRecord> historyStack = new StudentStack<>();
    private static ServiceQueue<ServiceRequest> requestQueue = new ServiceQueue<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1;

        while (choice != 0) {
            printMenu();
            choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addServiceRequest();
                    break;
                case 2:
                    processNextServiceRequest();
                    break;
                case 3:
                    requestQueue.display();
                    break;
                case 4:
                    simulateRecordAction(); // demo: simulate an Add/Update/Delete elsewhere in the system
                    break;
                case 5:
                    historyStack.display();
                    break;
                case 0:
                    System.out.println("Exiting Member 2 module demo. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Member 2: Stack & Queue Module =====");
        System.out.println("1. Add Service Request to Queue");
        System.out.println("2. Process Next Service Request");
        System.out.println("3. Display All Pending Service Requests");
        System.out.println("4. Simulate a Student Record Action (Add/Update/Delete)");
        System.out.println("5. Display Recent Actions using Stack");
        System.out.println("0. Exit");
    }

    /** Menu option 5: Add Service Request to Queue. */
    private static void addServiceRequest() {
        String studentId = readNonEmptyString("Enter Student ID: ");
        String description = readNonEmptyString("Enter service request description: ");

        ServiceRequest request = new ServiceRequest(studentId, description);
        requestQueue.enqueue(request);

        System.out.println("Service request added successfully: " + request);
    }

    /** Menu option 6: Process Next Service Request (FIFO order). */
    private static void processNextServiceRequest() {
        if (requestQueue.isEmpty()) {
            System.out.println("No service requests to process.");
            return;
        }
        ServiceRequest processed = requestQueue.dequeue();
        System.out.println("Processing: " + processed);
        System.out.println("Service request completed for Student ID: " + processed.getStudentId());
    }

    /**
     * Demonstrates how other members' Add/Update/Delete methods should
     * call recordAction(...) so the stack captures a history of changes.
     */
    private static void simulateRecordAction() {
        String type = readNonEmptyString("Action type (ADD/UPDATE/DELETE): ").toUpperCase();
        if (!type.equals("ADD") && !type.equals("UPDATE") && !type.equals("DELETE")) {
            System.out.println("Invalid action type. Must be ADD, UPDATE, or DELETE.");
            return;
        }
        String studentId = readNonEmptyString("Enter Student ID involved: ");
        String details = readNonEmptyString("Enter short details of the change: ");

        recordAction(type, studentId, details);
        System.out.println("Action recorded in history stack.");
    }

    /**
     * Call this method from the Add/Update/Delete Student Record
     * operations (owned by Member 1 / the shared Menu class) every
     * time a record changes, so the stack keeps an accurate history.
     */
    public static void recordAction(String actionType, String studentId, String details) {
        ActionRecord record = new ActionRecord(actionType, studentId, details);
        historyStack.push(record);
    }

    /** Undo the most recent action (pops from the stack). Optional extension. */
    public static ActionRecord undoLastAction() {
        if (historyStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return null;
        }
        ActionRecord lastAction = historyStack.pop();
        System.out.println("Undone: " + lastAction);
        return lastAction;
    }

    // ---------- Input validation helpers ----------

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }
}