package dynamicInformation;

public class NodeResource {
    private NodeQuery firstQuery;
    private NodeResource nextRes;
    private String resource;
    private int cont;

    public NodeResource(String resource, NodeQuery query) {
        this.resource = resource;
        this.nextRes = null;
        this.firstQuery = query;
        this.cont=1;
    }

    public NodeQuery getFirstQuery() {
        return firstQuery;
    }

    public void setFirstQuery(NodeQuery firstRes) {
        this.firstQuery = firstRes;
    }

    public NodeResource getNextRes() {
        return nextRes;
    }

    public void setNextRes(NodeResource nextRes) {
        this.nextRes = nextRes;
    }

    public String getResource() {
        return resource;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

}
