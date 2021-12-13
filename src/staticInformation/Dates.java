package staticInformation;

import information.Date;

/**
 * List of dates that keeps the order. It does not contain duplicates
 */
public class Dates {
    public static final int maxDates = 100000;
    private final Date[] dates = new Date[maxDates];
    private int numDates = 0;

    /**
     * Constructor
     */
    public Dates() {

    }

    /**
     * Get the number of dates in the structure
     *
     * @return the number of dates
     */
    public int getNumDates() {
        return numDates;
    }

    /**
     * Get the date at the given index
     *
     * @param index of the date to return
     * @return the date or null if there isn't one at the index
     */
    public Date getDateAtIndex(int index) {
        if (index < numDates && index >= 0) {
            return dates[index];
        }
        return null;
    }

    /**
     * Method to add a new user to the list. The list is ordered and does not contain duplicates.
     *
     * @param date to add
     */
    public void addDate(Date date) {
        if (numDates == 0) {
            dates[0] = date;
            numDates++;
        } else if (numDates < maxDates) {
            int index = binarySearch(date);
            if (index < numDates) {
                if (!dates[index].equals(date)) {
                    for (int shiftIndex = numDates; shiftIndex > index; shiftIndex--) {
                        dates[shiftIndex] = dates[shiftIndex - 1];
                    }
                    dates[index] = date;
                    numDates++;
                }
            } else {
                dates[index] = date;
                numDates++;
            }
        }

    }

    /**
     * Method to found the position of a user on the list. If not already in the list, finds the position to insert it.
     *
     * @param date to find
     * @return index where the user is or where it should be inserted.
     */
    public int binarySearch(Date date) {
        int start = 0, index, end = numDates - 1;
        while (start <= end) {
            index = start + (end - start) / 2;
            if (dates[index].equals(date)) return index;
            if (dates[index].moreRecentThan(date)) start = index + 1;
            else end = index - 1;
        }
        return start;
    }

    /**
     * Method toString
     *
     * @return String with the class information
     */
    @Override
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numDates; index++) {
            total.append("\n").append(index + 1).append(" ").append(dates[index]);
        }
        return total.toString();
    }
}
