

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static StudentLinkedList studentList = new StudentLinkedList();

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: addStudent(); break;
                case 2: updateStudent(); break;
                case 3: deleteStudent(); break;
                case 4: studentList.display(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    static void printMenu() {
        System.out.println("\n===== Student Record Management =====");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("0. Exit (temporary)");
    }

    // ---------- Menu operations ----------

    static void addStudent() {
        String id = readNonEmpty("Enter Student ID: ");
        if (studentList.search(id) != null) {
            System.out.println("Error: Student ID already exists.");
            return;
        }
        String name = readNonEmpty("Enter Name: ");
        String programme = readNonEmpty("Enter Programme: ");
        double marks = readMarks();

        studentList.add(new Student(id, name, programme, marks));
        System.out.println("Student added successfully.");
    }

    static void updateStudent() {
        String id = readNonEmpty("Enter Student ID to update: ");
        if (studentList.search(id) == null) {
            System.out.println("Error: Student not found.");
            return;
        }
        String name = readNonEmpty("Enter new Name: ");
        String programme = readNonEmpty("Enter new Programme: ");
        double marks = readMarks();

        studentList.update(id, name, programme, marks);
        System.out.println("Student updated successfully.");
    }

    static void deleteStudent() {
        String id = readNonEmpty("Enter Student ID to delete: ");
        Student removed = studentList.delete(id);
        if (removed == null) {
            System.out.println("Error: Student not found.");
        } else {
            System.out.println("Deleted: " + removed);
        }
    }

    static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Error: Input cannot be empty.");
        }
    }

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }

    static double readMarks() {
        while (true) {
            System.out.print("Enter Marks (0-100): ");
            try {
                double marks = Double.parseDouble(sc.nextLine().trim());
                if (marks >= 0 && marks <= 100) {
                    return marks;
                }
                System.out.println("Error: Marks must be between 0 and 100.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }
}