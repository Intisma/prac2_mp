package dynamicInformation;

import information.Query;

public class NodeQuery {
    private Query query;
    private NodeQuery nextQueryUser;
    private NodeQuery nextQueryRes;

    public NodeQuery(Query query) {
        this.query = query;
        nextQueryRes = null;
        nextQueryUser=null;
    }

    public Query getQuery() {
        return query;
    }

    public NodeQuery getNextQueryUser() {
        return nextQueryUser;
    }

    public void setNextQueryUser(NodeQuery nextQueryUser) {
        this.nextQueryUser = nextQueryUser;
    }

    public NodeQuery getNextQueryRes() {
        return nextQueryRes;
    }

    public void setNextQueryRes(NodeQuery nextQueryRes) {
        this.nextQueryRes = nextQueryRes;
    }

    public String toStringFile(){ return this.query.toStringFile(); }
}
