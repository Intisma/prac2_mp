package information;

public class Queries {
    private final int size = ADTsetResources.size;
    private final Query[] queries = new Query[size];
    private int numQueries = 0;

    public Queries() {

    }

    /**
     * Method to get the number of queries
     *
     * @return number of queries
     */
    public int getNumQueries() {
        return numQueries;
    }

    /**
     * Method to get the query at a given index
     *
     * @param index to search for the query
     * @return the query or null if not found
     */
    public Query getQueryAtIndex(int index) {
        if (index < numQueries && index >= 0) {
            return queries[index];
        }
        return null;
    }

    /**
     * Method to found the position of a query on the list. If not already in the list, finds the position to insert it.
     *
     * @param query to find
     * @return index where the query is or where it should be inserted.
     */
    public int binarySearch(Query query) {
        int start = 0, index, end = numQueries - 1, compare;
        while (start <= end) {
            index = start + (end - start) / 2;
            compare = queries[index].compareTo(query);
            if (compare == 0) return index;
            if (compare < 0) start = index + 1;
            else end = index - 1;
        }
        return start;
    }

    /**
     * Method to add a new query
     *
     * @param query to add
     */
    public void addQuery(Query query) {
        if (numQueries == 0) {
            queries[0] = query;
            numQueries++;
        } else if (numQueries < size) {
            int index = binarySearch(query);
            if (index < numQueries) {
                if (!queries[index].equals(query)) {
                    for (int shiftIndex = numQueries; shiftIndex > index; shiftIndex--) {
                        queries[shiftIndex] = queries[shiftIndex - 1];
                    }
                    queries[index] = query;
                    numQueries++;
                }
            } else {
                queries[index] = query;
                numQueries++;
            }
        }
    }


    /**
     * Method toString
     *
     * @return String with the class information
     */
    @Override
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numQueries; index++) {
            total.append(queries[index]).append('\n');
        }
        return total.toString();
    }
}
