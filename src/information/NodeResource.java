package information;

public class NodeResource {
    private NodeQueries firstRes;
    private NodeResource nextRes;
    private String resource;
    private int cont;

    public NodeResource(String resource, NodeQueries query) {
        this.resource = resource;
        this.nextRes = null;
        this.firstRes = query;
        this.cont=1;
    }

    public NodeQueries getFirstRes() {
        return firstRes;
    }

    public void setFirstRes(NodeQueries firstRes) {
        this.firstRes = firstRes;
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

    public void setResource(String resource) {
        this.resource = resource;
    }
}
