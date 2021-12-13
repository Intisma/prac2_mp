package information;

public class NodeQueries {
    private final Query query;
    private NodeQueries nextQueryUser;
    private NodeQueries nextQueryRes;

    public NodeQueries( Query query) {
        this.query = query;
        nextQueryRes = null;
        nextQueryUser=null;
    }


}
