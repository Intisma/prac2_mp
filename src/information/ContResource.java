package information;

public class ContResource {
    private int cont;
    private String resource;

    public ContResource(String resource) {
        this.resource = resource;
        cont = 0;
    }

    public int getCont() {
        return cont;
    }

    public String getResource() {
        return resource;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
