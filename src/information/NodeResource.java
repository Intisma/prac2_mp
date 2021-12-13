package information;

public class NodeResource {
    private NodeQueries firstRes;
    private NodeResource nextRes;
    private String resource;

    public NodeResource(String resource, NodeQueries query) {
        this.resource = resource;
        this.nextRes = null;
        this.firstRes = query;
    }


}
