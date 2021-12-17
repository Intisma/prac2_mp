package dynamicInformation;

/**
 * Node of resource for the first implementation of dynamic
 */
public class ContResource {
    private int cont;
    private String resource;

    /**
     * Constructor
     * @param resource the name of the resource
     */
    public ContResource(String resource) {
        this.resource = resource;
        cont = 1;
    }

    /**
     * Method to get the counter of the resource
     * @return number of times the resource was queried
     */
    public int getCont() {
        return cont;
    }

    /**
     * Method to get the name of the resource
     * @return name of the resource
     */
    public String getResource() {
        return resource;
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
     * @param resource the name to add
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Method toString
     * @return String with the information
     */
    public String toString() {
        return (resource +" "+ cont + " times");
    }
}
