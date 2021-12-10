package information;

public class Resources {
    static final int maxResources = 10000;
    private String[] resources = new String[maxResources];
    private int numResources = 0;

    public Resources() {

    }

    public String[] getResources() {
        return resources;
    }

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

    public int binarySearch(String user) {
        int start = 0, index, end = numResources - 1, compare;
        while (start <= end) {
            index = start + (end - start) / 2;
            compare = resources[index].compareTo(user);
            if (compare == 0) return index;
            if (compare < 0) start = index + 1;
            else end = index - 1;
        }
        return start;
    }

    @Override
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numResources; index++) {
            total.append("\n").append(index + 1).append(" ").append(resources[index]);
        }
        return total.toString();
    }
}
