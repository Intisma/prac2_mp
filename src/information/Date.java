package information;

public class Date {
    private final int year;
    private final char month;
    private final char day;
    private final char hour;
    private final char minute;
    private final char second;

    /**
     * Constructor
     *
     * @param year   of the date
     * @param month  of the date
     * @param day    of the date
     * @param hour   of the date
     * @param minute of the date
     * @param second of the date
     */
    public Date(int year, char month, char day, char hour, char minute, char second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;


    }

    /**
     * Get the year of the date
     *
     * @return year of the date
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the month of the date
     *
     * @return month of the date
     */
    public char getMonth() {
        return month;
    }

    /**
     * Get the day of the date
     *
     * @return day of the date
     */
    public char getDay() {
        return day;
    }

    /**
     * Get the hour of the date
     *
     * @return hour of the date
     */
    public char getHour() {
        return hour;
    }

    /**
     * Get the minute of the date
     *
     * @return minute of the date
     */
    public char getMinute() {
        return minute;
    }

    /**
     * Get the second of the date
     *
     * @return second of the date
     */
    public char getSecond() {
        return second;
    }

    /**
     * Compare which date is more recent
     *
     * @param compare date to compare
     * @return boolean indicating if the date who called the method is more recent than the one introduced as an argument. If equals return false
     */
    public boolean moreRecentThan(Date compare) {
        if (year > compare.getYear()) return true;
        if (year < compare.getYear()) return false;
        if (month > compare.getMonth()) return true;
        if (month < compare.getMonth()) return false;
        if (day > compare.getDay()) return true;
        if (day < compare.getDay()) return false;
        if (hour > compare.getHour()) return true;
        if (hour < compare.getHour()) return false;
        if (minute > compare.getMinute()) return true;
        if (minute < compare.getMinute()) return false;
        return second > compare.getSecond();

    }

    /**
     * Compares if two dates are equals
     *
     * @param compare date to compare
     * @return boolean indicating if two dates are equal
     */
    public boolean equals(Date compare) {
        return (year == compare.getYear() && month == compare.getMonth() && day == compare.getDay() && hour == compare.getHour() && minute == compare.getMinute() && second == compare.getSecond());
    }

    /**
     * Method to check if the date is in the given range
     *
     * @param start of the range
     * @param end   of the range
     * @return boolean indicanting if its inside of the range
     */
    public boolean inRange(Date start, Date end) {
        if (start.getYear() > end.getYear()) {
            Date aux = start;
            start = end;
            end = aux;
        } else if (start.getYear() == end.getYear()) {
            if (start.getMonth() > end.getMonth()) {
                Date aux = start;
                start = end;
                end = aux;
            } else if (start.getMonth() == end.getMonth()) {
                if (start.getDay() > end.getDay()) {
                    Date aux = start;
                    start = end;
                    end = aux;
                }
            }
        }

        if (year > start.getYear() && year < end.getYear()) return true;
        if (year == start.getYear()) {
            if (month > start.getMonth()) return true;
            else if (month == start.getMonth() && day >= start.getDay()) return true;
        } else if (year == end.getYear()) {
            if (month < end.getMonth()) return true;
            else if (month == end.getMonth() && day <= start.getDay()) return true;
        }
        return false;
    }

    /**
     * Method toString
     *
     * @return String with the class information
     */
    public String toString() {
        return (day + 0) + "/" + (month + 0) + "/" + year + "  " + (hour + 0) + ":" + (minute + 0) + ":" + (second + 0);
    }


}
