import java.util.Scanner;

public class InputValidator {

    public static String readNonEmptyString(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Invalid input. Please enter a value.");
        }
    }

    public static double readMarks(Scanner scanner) {

        while (true) {

            System.out.print("Enter Marks (0 - 100): ");

            String input = scanner.nextLine().trim();

            try {

                double marks = Double.parseDouble(input);

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println(
                        "Invalid marks. Marks must be between 0 and 100.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number.");
            }
        }
    }

    public static int readInteger(
            Scanner scanner,
            String message,
            int minimum,
            int maximum) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                int number = Integer.parseInt(input);

                if (number >= minimum && number <= maximum) {
                    return number;
                }

                System.out.println(
                        "Please enter a number between "
                                + minimum + " and " + maximum + ".");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number.");
            }
        }
    }
}