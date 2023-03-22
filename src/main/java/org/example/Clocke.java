package org.example;

public class Clocke {

    public double calculateAngle(String time) {

        validateTimeInput(time);

        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(2));

        // Convert to 12-hour format
        hours = hours % 12;

        // Calculate the positions of hour and minute hands
        double hourPosition = (hours * 30) + (minutes * 0.5); // 360 degrees / 12 hours = 30 degrees per hour, 30 / 60 = 0.5 degrees per minute
        double minutePosition = minutes * 6; // 360 degrees / 60 minutes = 6 degrees per minute

        // Calculate the clockwise angle from the hour-hand to the minute-hand
        double angle = minutePosition - hourPosition;

        // If the angle is minus then we need to add 360 degrees to it
        if (angle < 0) {
            return 360 + angle;
        }

        return angle;
    }

    private static void validateTimeInput(String time) {
        // Validate the input string to be a valid 24-hour clock time
        if (!time.matches("^([01]?[0-9]|2[0-3])[0-5][0-9]$")) {
            throw new IllegalArgumentException("Invalid time format. Please provide a valid 24-hour clock time. Example: 1230");
        }
    }
}
