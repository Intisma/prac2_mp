package information;

public class Users {
    static final int maxUsers = 10000;
    private String[] users = new String[maxUsers];
    private int numUsers = 0;

    public Users() {

    }

    public String[] getUsers() {
        return users;
    }

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

    @Override
    public String toString() {
        StringBuilder total = new StringBuilder();
        for (int index = 0; index < numUsers; index++) {
            total.append("\n").append(index + 1).append(" ").append(users[index]);
        }
        return total.toString();
    }
}
