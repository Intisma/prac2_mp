package timeMeasurer;

/**
 * This class is in charge of keeping a list of times corresponding to an execution. The list is automatically ordered
 * by structure size and will not contain duplicates (it checks if the same Time has been added two times). The method
 * toString is used to get the data information in String format, ready to be printed in a file. In the cases where
 * several repeats of the same size have been made the method calculates and stores the mean value.
 */
public class Times {
    public static final int maxTimes = 10000;
    private final Time[] times = new Time[maxTimes];
    private int numTimes = 0;
    private final int repeats;

    /**
     * Constructor
     *
     * @param repeats is the number of times the measures have been made for the same size. This variable is needed to
     *                calculate the mean values in the toString method
     */
    public Times(int repeats) {
        this.repeats = repeats;
    }

    /**
     * This method adds a new Time to the list. It will control that there are no duplicates in the list and the list
     * is ordered by structure size. The method uses binary search to know where to insert the next time of the list
     *
     * @param time that will be added to the list
     */
    public void addTime(Time time) {
        if (numTimes == 0) {
            times[0] = time;
            numTimes++;
        } else if (numTimes < maxTimes) {
            int index = binarySearch(time);
            if (index < numTimes) {
                if (!times[index].equals(time)) {
                    for (int shiftIndex = numTimes; shiftIndex > index; shiftIndex--) {
                        times[shiftIndex] = times[shiftIndex - 1];
                    }
                    times[index] = time;
                    numTimes++;
                }
            } else {
                times[index] = time;
                numTimes++;
            }
        }

    }

    /**
     * This method finds the index where a Time object should be inserted in the list
     *
     * @param time to find in the list
     * @return index where the Time object it should be inserted.
     */
    public int binarySearch(Time time) {
        int start = 0, index, end = numTimes - 1;
        while (start <= end) {
            index = start + (end - start) / 2;
            if (times[index].getSize() == time.getSize()) return index;
            if (times[index].getSize() < time.getSize()) start = index + 1;
            else end = index - 1;
        }
        return start;
    }

    /**
     * This method transforms the data of the list to a String format ready to be printed to a file. It has to
     * calculate the mean values of all the time measures from the same structure size with the help of the variable repeats
     *
     * @return String with the list information ready to be printed to a file
     */
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numTimes; index += repeats) {
            Time currentTime = new Time();
            for (int repeat = 0; repeat < repeats; repeat++) {
                currentTime = currentTime.add(times[index]);
            }
            currentTime = currentTime.divide(repeats);
            total.append(currentTime.toString());
        }
        return total.toString();
    }
}
