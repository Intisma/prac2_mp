package staticInformation;

/**
 * This class provides a list of users to facilitate method that return a list of String[] with the users that meet some
 * conditions. It stores the name of the users in order and does not allow for duplicates.
 */
public class Users {
    public static final int maxUsers = 10000;
    private final String[] users = new String[maxUsers];
    private int numUsers = 0;

    /**
     * Empty constructor
     */
    public Users() {

    }

    /**
     * Method to get the list of users
     *
     * @return String[] with the list of users
     */
    public String[] getUsers() {
        return users;
    }

    /**
     * Get the number of users in the structure
     *
     * @return the number of users in the structure
     */
    public int getNumUsers() {
        return numUsers;
    }

    /**
     * Get the user at the given index
     *
     * @param index of the user to return
     * @return the user, or null if there isn't one at the index
     */
    public String getUserAtIndex(int index) {
        if (index < numUsers && index >= 0) {
            return users[index];
        }
        return null;
    }

    /**
     * Method to add a new user controlling that the user is not already inside the list and keeping it
     * ordered alphabetically
     *
     * @param user to add to the list
     */
    public void addUser(String user) {
        if (numUsers == 0) {
            users[0] = user;
            numUsers++;
        } else if (numUsers < maxUsers) {
            int index = binarySearch(user);
            if (index < numUsers) {
                if (!users[index].equals(user)) {
                    for (int shiftIndex = numUsers; shiftIndex > index; shiftIndex--) {
                        users[shiftIndex] = users[shiftIndex - 1];
                    }
                    users[index] = user;
                    numUsers++;
                }
            } else {
                users[index] = user;
                numUsers++;
            }
        }

    }

    /**
     * Method to found the position of a user on the list. If not already in the list, finds the position to insert it.
     *
     * @param user to find
     * @return index where the user is or where it should be inserted.
     */
    public int binarySearch(String user) {
        int start = 0, index, end = numUsers - 1, compare;
        while (start <= end) {
            index = start + (end - start) / 2;
            compare = users[index].compareTo(user);
            if (compare == 0) return index;
            if (compare < 0) start = index + 1;
            else end = index - 1;
        }
        return start;
    }

    /**
     * Method toString used to test the correct functioning of the class
     *
     * @return String with the class information
     */
    @Override
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numUsers; index++) {
            total.append("\n").append(index + 1).append(" ").append(users[index]);
        }
        return total.toString();
    }
}
