package dynamicInformation;

public class NodeUsers {
    private final String user;
    private NodeQuery firstQuery;
    private NodeUsers nextUser;

    public NodeUsers(String user, NodeQuery resource) {
        this.user = user;
        this.firstQuery = resource;
        this.nextUser = null;
    }

    public String getUser() {
        return user;
    }

    public NodeQuery getFirstQuery() {
        return firstQuery;
    }

    public void setFirstQuery(NodeQuery firstQuery) {
        this.firstQuery = firstQuery;
    }

    public NodeUsers getNextUser() {
        return nextUser;
    }

    public void setNextUser(NodeUsers nextUser) {
        this.nextUser = nextUser;
    }
}
