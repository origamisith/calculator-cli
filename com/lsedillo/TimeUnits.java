package com.lsedillo;

public enum TimeUnits {
    SECOND(1, "seconds"),
    MINUTE(60, "minutes"),
    HOUR(3600, "hours"),
    DAY(3600 * 24, "days"),
    WEEK(3600*24*7, "weeks"),
    MONTH(3600 * 24 * 30.436875, "months"),
    YEAR(3600 * 24 * 365, "years");

    public double seconds;
    public String name;

    private TimeUnits(double seconds, String name) {
        this.seconds = seconds;
        this.name = name;
    }

    public static double convert(double num, TimeUnits from, TimeUnits to) {
        return (num * from.seconds) / to.seconds;
    }

    public static double convert(TimeUnits from, TimeUnits to) {
        return (from.seconds) / to.seconds;
    }

    public static void main(String[] args) {
        System.out.println(convert(1, TimeUnits.DAY, TimeUnits.SECOND));
    }

    public static String readableTime(double seconds) {
        StringBuilder result = new StringBuilder();
        int monthsLeft = readableAux((int)seconds, result, TimeUnits.YEAR);
        int weeksLeft = readableAux(monthsLeft, result, TimeUnits.WEEK);
        int daysLeft = readableAux(weeksLeft, result, TimeUnits.WEEK);
        int hoursLeft = readableAux(daysLeft, result, TimeUnits.DAY);
        int minutesLeft = readableAux(hoursLeft, result, TimeUnits.HOUR);
        int secondsLeft = readableAux(minutesLeft, result, TimeUnits.MINUTE);
        result.append((secondsLeft < 1) ? "" : secondsLeft + " " + SECOND.name);
        return result.toString();
    }

    public static int readableAux(int seconds, StringBuilder result, TimeUnits unit) {
        int q = seconds / (int)unit.seconds;
        int r = seconds % (int)unit.seconds;
        result.append((q < 1) ? "" : q + " " + unit.name + " ");
        return r;
    }
}
