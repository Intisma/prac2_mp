package information;

import dynamicInformation.ContResource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * First implementation of the dynamic resource set
 */
public class DynamicSetResources implements ADTsetResources {

    private final HashMap<String, ArrayList<Query>> resources;
    private final HashMap<String, ArrayList<ContResource>> users;

    /**
     * Constructor
     */
    public DynamicSetResources() {
        resources = new HashMap<>();
        users = new HashMap<>();
    }


    /**
     * Add query to the data structure
     */
    @Override
    public void addQuery(Query query) {
        boolean found = false;

        //para añadir si no existe
        resources.putIfAbsent(query.getResource(), new ArrayList<>());
        //si ya existe el recurso
        resources.get(query.getResource()).add(query);

        //para añadir si no existe este usuario
        users.putIfAbsent(query.getUser(), new ArrayList<>());
        //si ya existe el usuario y el recurso no está añadido previamente
        for (int i = 0; i < users.get(query.getUser()).size() && !found; i++) {
            //aumentamos contador de consultas a un recurso
            if (users.get(query.getUser()).get(i).getResource().equals(query.getResource())) {
                users.get(query.getUser()).get(i).setCont(users.get(query.getUser()).get(i).getCont() + 1);
                found = true;
            }
        }
        if (!found) users.get(query.getUser()).add(new ContResource(query.getResource()));

    }


    /**
     * Remove all the queries of a resource
     */
    @Override
    public void removeQueriesFromResource(String resource) {
        int i;
        boolean found = false;
        try {
            resources.get(resource).clear(); //MIRAR DE HACER UN POCO MEJOR
            //Recorrer mapa
            for (String name : this.users.keySet()) {
                for (i = 0; i < users.get(name).size() && !found; i++) {
                    if (users.get(name).get(i).getResource().equals(resource)) {
                        users.get(name).remove(i);
                        found = true;
                    }
                }
                found = false;
            }
        } catch (NullPointerException exception) {
            System.out.println("Resource doesn't exist yet");
        }

    }

    /**
     * Remove all the queries of a resource  in a specific date
     */
    @Override
    public void removeQueriesFromResourceDate(String resource, Date date) {
        ArrayList<Query> listQueries = resources.get(resource);
        ArrayList<Query> delete = new ArrayList<>();
        try {
            for (Query query : listQueries) {
                if (query.getDate().equals(date)) {
                    //Delete the resource from the list
                    delete.add(query);
                    //Search and delete the resource from the list in users if there is less than 1 query
                    for (int i = 0; i < users.get(query.getUser()).size(); i++) {
                        if (users.get(query.getUser()).get(i).getResource().equals(resource)) {
                            if (users.get(query.getUser()).get(i).getCont() == 1) {
                                users.get(query.getUser()).remove(i);
                            } else {
                                users.get(query.getUser()).get(i).setCont(users.get(query.getUser()).get(i).getCont() - 1);
                            }
                        }
                    }
                }
            }
            resources.get(resource).removeAll(delete);
        } catch (NullPointerException excepcion) {
            System.out.println("Resource doesn't exist yet");
        }
    }

    /**
     * Return a list with the users who queried a certain resource
     *
     * @return a String[] with the users
     */
    @Override
    public String[] getUsersFromResource(String resource) {
        ArrayList<Query> listQueries = resources.get(resource);
        ArrayList<String> usersResource = new ArrayList<>();
        try {
            for (Query listQuery : listQueries) {
                if (!usersResource.contains(listQuery.getUser())) {
                    usersResource.add(listQuery.getUser());
                }
            }
        } catch (NullPointerException excepcion) {
            System.out.println("Resource doesn't exist yet");
        }
        return usersResource.toArray(new String[0]); //es que era una ArrayList
    }


    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    @Override
    public String[] getUsersFromResourceDate(String resource, Date date) {
        ArrayList<String> usersResourceDate = new ArrayList<>();
        try {
            ArrayList<Query> listQueries = resources.get(resource);
            for (Query listQuery : listQueries) {
                if (listQuery.getDate().equals(date)) {
                    if (!usersResourceDate.contains(listQuery.getUser())) {
                        usersResourceDate.add(listQuery.getUser());
                    }
                }
            }
        } catch (NullPointerException excepcion) {
            System.out.println("Resource doesn't exist yet");
        }

        return usersResourceDate.toArray(new String[0]);
    }

    /**
     * Return the most queried resource
     */
    @Override
    public String getMostQueriedResource() {
        String most = null;
        int length = 0;
        for (String res : this.resources.keySet()) {
            if (length < resources.get(res).size()) {
                length = resources.get(res).size();
                most = res;
            }
        }
        return most;
    }


    /**
     * Return a list with the resources queried by a user
     *
     * @return result
     */
    @Override
    public String[] getResourcesFromUser(String user) {
        ArrayList<String> result = new ArrayList<>();
        try {
            for (int i = 0; i < users.get(user).size(); i++) {
                result.add(users.get(user).get(i).getResource());
            }
        } catch (NullPointerException exception) {
            System.out.println("This user doesn't exist yet");
        }

        return result.toArray(new String[0]);
    }

    /**
     * Return a list with the users who queried a resource in a given date range
     *
     * @param resource to check
     * @param start    date of the range
     * @param end      date of the range
     * @return String[] with the resources
     */
    public String[] getUsersFromResourceDateRange(String resource, Date start, Date end) {
        ArrayList<String> usersResourceDate = new ArrayList<>();
        try {
            ArrayList<Query> listQueries = resources.get(resource);
            for (Query listQuery : listQueries) {
                if (listQuery.getDate().inRange(start, end)) {
                    if (!usersResourceDate.contains(listQuery.getUser())) {
                        usersResourceDate.add(listQuery.getUser());
                    }
                }
            }
        } catch (NullPointerException excepcion) {
            System.out.println("Resource doesn't exist yet");
        }
        return usersResourceDate.toArray(new String[0]);
    }

    /**
     * Method to check if a user has consulted a resource
     *
     * @param resource to check
     * @param user     to check
     * @return true if the user has consulted the resource, false if not
     */
    @Override
    public boolean userHasConsultedResource(String resource, String user) {
        boolean found = false;
        int i=0;
        try {
            while (i < users.get(user).size() && !found) {
                if (users.get(user).get(i).getResource().equals(resource)) found = true;
                i++;
            }
        } catch (NullPointerException exception) {
            System.out.println("This user doesn't exist yet");
        }
        return found;
    }

    /**
     * Method toString that transforms the set information to a String
     *
     * @return String with the information
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String name : this.resources.keySet()) {
            for(Query q: this.resources.get(name)){
                result.append(q.toString()).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Method to print the set's information in a file
     *
     * @return String with the information
     */
    @Override
    public String toStringFile() {
        StringBuilder result = new StringBuilder();
        for (String name : this.resources.keySet()) {
            for(Query q: this.resources.get(name)){
                result.append(q.toStringFile()).append("\n");
            }
        }
        return result.toString();
    }

}
