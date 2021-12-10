package information;


public class Query {
    private final String date;
    private final String hour;
    private final String alias;

    public Query(String date, String hour, String alias) {
        this.date = date;
        this.hour = hour;
        this.alias = alias;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getAlias() {
        return alias;
    }


    /**
     * ToString resources
     * @return result
     */
    public String toString(){
        return ("user: " + alias +" date: " + date + " hour: " + hour);
    }

}
