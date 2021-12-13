package staticInformation;

public class Resources {
    public static final int maxResources = 100000;
    private final String[] resources = new String[maxResources];
    private int numResources = 0;

    /**
     * Constructor
     */
    public Resources() {

    }

    /**
     * Get the list of resources
     *
     * @return list of String with the resources
     */
    public String[] getResources() {
        return resources;
    }

    /**
     * Get the number of resources in the structure
     *
     * @return the number of resources in the structure
     */
    public int getNumResources() {
        return numResources;
    }

    /**
     * Get the resource at the given index
     *
     * @param index of the resource to return
     * @return the resource or null if there isn't one at the index
     */
    public String getResourceAtIndex(int index) {
        if (index < numResources && index >= 0) {
            return resources[index];
        }
        return null;
    }

    /**
     * Method to add a new resource to the list. The list is ordered and does not contain duplicates.
     *
     * @param resource to add
     */
    public void addResource(String resource) {
        if (numResources == 0) {
            resources[0] = resource;
            numResources++;
        } else if (numResources < maxResources) {
            int index = binarySearch(resource);
            if (index < numResources) {
                if (!resources[index].equals(resource)) {
                    for (int shiftIndex = numResources; shiftIndex > index; shiftIndex--) {
                        resources[shiftIndex] = resources[shiftIndex - 1];
                    }
                    resources[index] = resource;
                    numResources++;
                }
            } else {
                resources[index] = resource;
                numResources++;
            }
        }

    }

    /**
     * Method to found the position of a resource on the list. If not already in the list, finds the position to insert it.
     *
     * @param resource to find
     * @return index where the resource is or where it should be inserted.
     */
    public int binarySearch(String resource) {
        int start = 0, index, end = numResources - 1, compare;
        while (start <= end) {
            index = start + (end - start) / 2;
            compare = resources[index].compareTo(resource);
            if (compare == 0) return index;
            if (compare < 0) start = index + 1;
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
        for (int index = 0; index < numResources; index++) {
            total.append("\n").append(index + 1).append(" ").append(resources[index]);
        }
        return total.toString();
    }
}
