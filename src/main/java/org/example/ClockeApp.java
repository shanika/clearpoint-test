package org.example;

import java.util.Scanner;

public class ClockeApp {
    public static void main(String[] args) {
        Clocke clocke = new Clocke();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter a valid 24-hour clock time (e.g., 0245) or type 'quit' to exit: ");
            String inputTime = scanner.next();

            if (inputTime.equalsIgnoreCase("quit")) {
                break;
            }

            try {
                double angle = clocke.calculateAngle(inputTime);
                System.out.println("The angle between the hour and minute hands at " + inputTime + " is " + angle + " degrees.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }
    }
}
