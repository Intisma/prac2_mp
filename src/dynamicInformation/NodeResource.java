package dynamicInformation;

/**
 * Node of the resource for the second dynamic implementation
 */
public class NodeResource {
    private NodeQuery firstQuery;
    private NodeResource nextRes;
    private String resource;
    private int cont;

    /**
     * Constructor
     * @param resource the name of the resource
     * @param query the first query of the resource
     */
    public NodeResource(String resource, NodeQuery query) {
        this.resource = resource;
        this.nextRes = null;
        this.firstQuery = query;
        this.cont=1;
    }

    /**
     * Method to get the first query of the resource
     * @return the query
     */
    public NodeQuery getFirstQuery() {
        return firstQuery;
    }

    /**
     * Method to set the first query of the resource
     * @param firstRes the query to add
     */
    public void setFirstQuery(NodeQuery firstRes) {
        this.firstQuery = firstRes;
    }

    /**
     * Method to get the next query of the resource
     * @return the query
     */
    public NodeResource getNextRes() {
        return nextRes;
    }

    /**
     * Method to set the next query of the resource
     * @param nextRes the query to add
     */
    public void setNextRes(NodeResource nextRes) {
        this.nextRes = nextRes;
    }

    /**
     * Method to get the name of the resource
     * @return the name of the resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * Method to get the counter
     * @return the counter
     */
    public int getCont() {
        return cont;
    }

    /**
     * Method to set the counter of the resource
     * @param cont the new counter
     */
    public void setCont(int cont) {
        this.cont = cont;
    }

    /**
     * Method to set the resource
     * @param resource the resource to add
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

}
