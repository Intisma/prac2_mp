package information;

public class NodeQueries {
    private Query query;
    private NodeQueries nextQueryUser;
    private NodeQueries nextQueryRes;

    public NodeQueries( Query query) {
        this.query = query;
        nextQueryRes = null;
        nextQueryUser=null;
    }

    public Query getQuery() {
        return query;
    }

    public NodeQueries getNextQueryUser() {
        return nextQueryUser;
    }

    public void setNextQueryUser(NodeQueries nextQueryUser) {
        this.nextQueryUser = nextQueryUser;
    }

    public NodeQueries getNextQueryRes() {
        return nextQueryRes;
    }

    public void setNextQueryRes(NodeQueries nextQueryRes) {
        this.nextQueryRes = nextQueryRes;
    }
}
