package information;

public class ResourceQuery {
    private String user;
    private Date date;

    public ResourceQuery(String user, Date date) {
        this.user = user;
        this.date = date;

    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }
}
