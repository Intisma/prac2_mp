package information;

public class NodeUsers {
    private final String user;
    private  NodeQueries firstQuery;
    private NodeUsers nextUser;

    public NodeUsers(String user, NodeQueries resource) {
        this.user = user;
        this.firstQuery = resource;
        this.nextUser = null;
    }

    public String getUser() {
        return user;
    }

    public NodeQueries getFirstQuery() {
        return firstQuery;
    }

    public void setFirstQuery(NodeQueries firstQuery) {
        this.firstQuery = firstQuery;
    }

    public NodeUsers getNextUser() {
        return nextUser;
    }

    public void setNextUser(NodeUsers nextUser) {
        this.nextUser = nextUser;
    }
}
