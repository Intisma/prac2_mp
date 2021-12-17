package dynamicInformation;

import information.Query;

/**
 * Node for the query in the second dynamic implementation
 */
public class NodeQuery {
    private Query query;
    private NodeQuery nextQueryUser;
    private NodeQuery nextQueryRes;
    private NodeQuery prevQueryUser;

    /**
     * Constructo
     * @param query: the query saved in the node
     */
    public NodeQuery(Query query) {
        this.query = query;
        nextQueryRes = null;
        nextQueryUser=null;
        prevQueryUser=null;
    }

    /**
     * Method to get the query
     * @return the query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * Method to get the next query of the user
     * @return the query
     */
    public NodeQuery getNextQueryUser() {
        return nextQueryUser;
    }

    /**
     * Method to set the next query of the user
     * @param nextQueryUser: the next query to add
     */
    public void setNextQueryUser(NodeQuery nextQueryUser) {
        this.nextQueryUser = nextQueryUser;
    }

    /**
     * Method to get the next query of the resource
     * @return the query
     */
    public NodeQuery getNextQueryRes() {
        return nextQueryRes;
    }

    /**
     * Method to set the next query of the resource
     * @param nextQueryRes the query to add
     */
    public void setNextQueryRes(NodeQuery nextQueryRes) {
        this.nextQueryRes = nextQueryRes;
    }

    /**
     * Method to set the query
     * @param query query to add
     */
    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     * Method to get the previous query of the user
     * @return the query
     */
    public NodeQuery getPrevQueryUser() {
        return prevQueryUser;
    }

    /**
     * Method to set the previous query of the user
     * @param prevQueryUser the query to user
     */
    public void setPrevQueryUser(NodeQuery prevQueryUser) {
        this.prevQueryUser = prevQueryUser;
    }

    /**
     * Method toString to write in the file
     * @return String with the information
     */
    public String toStringFile(){ return this.query.toStringFile(); }
}
