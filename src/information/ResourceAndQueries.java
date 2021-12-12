package information;

public class ResourceAndQueries {
    private String resource;
    private ResourceQueries resourceQueries = new ResourceQueries();

    public ResourceAndQueries(Query information) {
        resource = information.getResource();
        resourceQueries.addQuery(information.getUser(), information.getDate());
    }

    public ResourceQueries getQueries() {
        return resourceQueries;
    }

    public void addQuery(Query query) {
        resourceQueries.addQuery(query.getUser(), query.getDate());

    }

    public boolean removeQueries(Date date) {
        return resourceQueries.removeQueries(date);
    }

    public String[] getUsers() {
        return resourceQueries.getUsers();
    }

    public String[] getUsersDate(Date date) {
        return resourceQueries.getUsersDate(date);
    }

    /**
     * Get the resource
     *
     * @return the resource
     */
    public String getResource() {
        return resource;
    }

    public boolean isUser(String user) {
        return resourceQueries.isUser(user);
    }

    public String[] getUsersDateRange(Date start, Date end) {
        return resourceQueries.getUsersDateRange(start, end);
    }

    public String toString() {
        return "ResourceAndQueries: " + resource + "\n" + resourceQueries.toString();
    }
}
