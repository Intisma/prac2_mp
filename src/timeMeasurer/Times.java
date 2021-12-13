package timeMeasurer;

/**
 * Class in charge of keeping a list of times corresponding an execution. It calculates the mean values of all
 * the repeats.
 */
public class Times {
    public static final int maxTimes = 10000;
    private final Time[] times = new Time[maxTimes];
    private int numTimes = 0;
    private final int repeats;

    /**
     * Constructor
     */
    public Times(int repeats) {
        this.repeats = repeats;
    }

    /**
     * Method to add a new time to the list. The list is ordered and does not contain duplicates.
     *
     * @param time to add
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
     * Method to found the position of a Time on the list. If not already in the list, finds the position to insert it.
     *
     * @param time to find
     * @return index where the user is or where it should be inserted.
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
     * Method to get the string with the information of the times
     *
     * @return String with the information
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
