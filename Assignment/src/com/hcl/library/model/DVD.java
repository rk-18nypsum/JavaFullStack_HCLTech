package com.hcl.library.model;

public class DVD extends LibraryItem {
    private static final double DAILY_FEE = 5.0;

    private int durationMinutes;

    public DVD(String title, int year, int durationMinutes) {
        super(title, year);
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.durationMinutes = durationMinutes;
    }

    @Override
    public double lateFee(int daysLate) {
        return daysLate * DAILY_FEE;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String toString() {
        return "DVD[" + super.toString() + ", " + durationMinutes + " mins]";
    }
}
