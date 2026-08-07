package com.airtribe.learntrack.util;

import java.util.Scanner;

public class InputValidator {

    public static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    public static String readNonEmptyString(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.print("Input cannot be empty. Please enter again: ");
        }
    }

    public static String readValidEmail(Scanner scanner) {
        while (true) {
            String email = scanner.nextLine().trim();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }

            System.out.print("Invalid email. Please enter a valid email: ");
        }
    }

    public static boolean readBoolean(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("true") || input.equals("yes")) {
                return true;
            }

            if (input.equals("false") || input.equals("no")) {
                return false;
            }

            System.out.print("Please enter Yes/No or True/False: ");
        }
    }

    public static int readMenuChoice(Scanner scanner, int min, int max) {
        while (true) {

            int choice = readInt(scanner);

            if (choice >= min && choice <= max) {
                return choice;
            }

            System.out.print("Invalid choice. Enter a value between "
                    + min + " and " + max + ": ");
        }
    }
}

