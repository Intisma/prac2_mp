package dynamicInformation;

/**
 * Node User for the second dynamic implementation
 */
public class NodeUsers {
    private final String user;
    private NodeQuery firstQuery;
    private NodeUsers nextUser;

    /**
     * Constructor
     * @param user the alias of the user
     * @param resource the query of the first resource
     */
    public NodeUsers(String user, NodeQuery resource) {
        this.user = user;
        this.firstQuery = resource;
        this.nextUser = null;
    }

    /**
     * Method to get the user
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Method to get the first query of the user
     * @return the query
     */
    public NodeQuery getFirstQuery() {
        return firstQuery;
    }

    /**
     * Method to set the new first query
     * @param firstQuery the new query
     */
    public void setFirstQuery(NodeQuery firstQuery) {
        this.firstQuery = firstQuery;
    }

    /**
     * Method to get the next user
     * @return the next user
     */
    public NodeUsers getNextUser() {
        return nextUser;
    }

    /**
     * Method to set the next user
     * @param nextUser the user to add
     */
    public void setNextUser(NodeUsers nextUser) {
        this.nextUser = nextUser;
    }

}
