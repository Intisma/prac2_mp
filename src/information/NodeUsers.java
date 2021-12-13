package information;

public class NodeUsers {
    private final String user;
    private final NodeQueries firstQuery;
    private NodeUsers nextUser;

    public NodeUsers(String user, NodeQueries resource) {
        this.user = user;
        this.firstQuery = resource;
        this.nextUser = null;
    }

    public String getUser() {
        return user;
    }

}
