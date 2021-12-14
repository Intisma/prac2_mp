package dynamicInformation;

import information.Query;

public class NodeQuery {
    private Query query;
    private NodeQuery nextQueryUser;
    private NodeQuery nextQueryRes;
    private NodeQuery prevQueryUser;

    public NodeQuery(Query query) {
        this.query = query;
        nextQueryRes = null;
        nextQueryUser=null;
        prevQueryUser=null;
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

    public void setQuery(Query query) {
        this.query = query;
    }

    public NodeQuery getPrevQueryUser() {
        return prevQueryUser;
    }

    public void setPrevQueryUser(NodeQuery prevQueryUser) {
        this.prevQueryUser = prevQueryUser;
    }

    public String toStringFile(){ return this.query.toStringFile(); }
}
