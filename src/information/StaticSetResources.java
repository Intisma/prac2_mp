package information;

public class StaticSetResources implements ADTsetResources {
    public static final int size = 100000;
    private final Query[] list = new Query[size];
    private int numElements = 0;

    /**
     * Constructor
     */
    public StaticSetResources() {

    }


    /**
     * Add query to the data structure
     */
    @Override
    public void addQuery(Query query) {
        int index;
        if (numElements == 0) {
            list[0] = query;
            numElements++;
        } else if (numElements < size) {
            index = lowerBinarySearchResource(query.getResource(), 0, numElements - 1);
            while (index < numElements && list[index].getResource().equals(query.getResource()) && list[index].getDate().moreRecentThan(query.getDate())) {
                index++;
            }
            for (int shiftIndex = numElements; shiftIndex > index; shiftIndex--) {
                list[shiftIndex] = list[shiftIndex - 1];
            }
            numElements++;
            list[index] = query;
        }
    }

    /**
     * Binary search to find the lowest index where a resource is located or where it should be inserted.
     *
     * @param resource to find
     * @param lower    index to find
     * @param higher   index to find
     * @return found index
     */
    public int lowerBinarySearchResource(String resource, int lower, int higher) {
        if (lower > higher) return lower;
        int index = lower + (higher - lower) / 2;
        int compare = list[index].getResource().compareTo(resource);
        if (compare == 0) return lowerBinarySearchResource(resource, lower, index - 1);
        if (compare < 0) return lowerBinarySearchResource(resource, index + 1, higher);
        return lowerBinarySearchResource(resource, lower, index - 1);
    }

    public int binarySearchDate(int posIni, int posFin, Date date) {
        return 0;
    }

    /**
     * Remove all the queries of a resource
     */
    @Override
    public void removeQueriesFromResource(String resource) {
        int counter = 0;
        int index;
        if (numElements > 0) {
            index = lowerBinarySearchResource(resource, 0, numElements - 1);
            while (list[index].getResource().equals(resource)) {
                counter++;
                index++;
            }
            while (index < numElements) {
                list[index - counter] = list[index];
                index++;
            }
            numElements -= counter;
        }

    }

    /**
     * Remove all the queries of a resource  in a specific date
     */
    @Override
    public void removeQueriesFromResourceDate(String resource, Date date) {
        int counter = 0;
        int index;
        if (numElements > 0) {
            index = lowerBinarySearchResource(resource, 0, numElements - 1);
            while (list[index].getResource().equals(resource) && list[index].getDate().moreRecentThan(date)) {
                index++;
            }
            while (list[index].getResource().equals(resource) && list[index].getDate().equals(date)) {
                index++;
                counter++;
            }
            while (index < numElements) {
                list[index - counter] = list[index];
                index++;
            }
            numElements -= counter;
        }
    }

    /**
     * Return a list with the users who queried a certain resource
     */
    @Override
    public String[] getUsersFromResource(String resource) {
        Users users = new Users();
        if (numElements > 0) {
            int index = lowerBinarySearchResource(resource, 0, numElements);
            while (list[index].getResource().equals(resource)) {
                users.addUser(list[index].getUser());
                index++;
            }
        }
        return users.getUsers();
    }

    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    @Override
    public String[] getUsersFromResourceDate(String resource, Date date) {
        Users users = new Users();
        if (numElements > 0) {
            int index = lowerBinarySearchResource(resource, 0, numElements);
            while (list[index].getResource().equals(resource) && list[index].getDate().moreRecentThan(date)) {
                index++;
            }
            while (list[index].getResource().equals(resource) && list[index].getDate().equals(date)) {
                users.addUser(list[index].getUser());
                index++;
            }
        }
        return users.getUsers();
    }

    /**
     * Return the most queried resource
     */
    @Override
    public String getMostQueriedResource() {
        int index = 0;
        if (numElements > 0) {
            String max = list[0].getResource();
            int maxCounter = 0;
            int counter;
            do {
                String current = list[index].getResource();
                counter = 0;
                while (index < numElements && list[index].getResource().equals(current)) {
                    index++;
                    counter++;
                }
                if (counter > maxCounter) {
                    max = current;
                    maxCounter = counter;
                }

            } while (index < numElements);
            return max;
        } else return null;
    }

    /**
     * Return a list with the resources queried by a user
     */
    @Override
    public Resources getResourcesFromUser(String user) {
        Resources resources = new Resources();
        for (int index = 0; index < numElements; index++) {
            if (list[index].getUser().equals(user)) {
                resources.addResource(list[index].getResource());
            }
        }
        return resources;
    }

    /**
     * Method toString
     *
     * @return String with the class information
     */
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numElements; index++) {
            total.append(list[index].toString());
            total.append("\n");
        }
        return total.toString();
    }
}