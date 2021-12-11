package information;

import java.util.*;
import java.util.HashMap;

public class DynamicSetResources implements ADTsetResources{

    private final HashMap<String,ArrayList<Query>> resources;
    private final HashMap<String,ArrayList<ContResource>> users;

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
        for (int i=0; i<users.get(query.getUser()).size() && !found; i++) {
            //aumentamos contador de consultas a un recurso
            if (users.get(query.getUser()).get(i).getResource().equals(query.getResource())) {
                users.get(query.getUser()).get(i).setCont(users.get(query.getUser()).get(i).getCont()+1);
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
        boolean found=false;
        resources.get(resource).clear();
        //Recorrer mapa
        for (String name: this.users.keySet()){
            for (i=0; i<users.get(name).size() && !found; i++) {
                if (users.get(name).get(i).getResource().equals(resource)) {
                    users.get(name).remove(i);
                    found = true;
                }
            }
            found = false;
        }
    }

    /**
     * Remove all the queries of a resource  in a specific date
     */
    @Override
    public void removeQueriesFromResourceDate(String resource, Date date) {
        ArrayList<Query> listQueries = resources.get(resource);
        ArrayList<Query> delete = new ArrayList<>();

        for (Query query : listQueries) {
            if (query.getDate().equals(date)) {
                //Delete the resource from the list
                delete.add(query);
                //Search and delete the resource from the list in users if there is less than 1 query
                for (int i=0; i<users.get(query.getUser()).size(); i++) {
                    if (users.get(query.getUser()).get(i).getResource().equals(resource)) {
                        if (users.get(query.getUser()).get(i).getCont() == 1) {
                            users.get(query.getUser()).remove(i);
                        }
                        else {
                            users.get(query.getUser()).get(i).setCont(users.get(query.getUser()).get(i).getCont()-1);
                        }
                    }
                }
            }
        }
        resources.get(resource).removeAll(delete);
    }

    /**
     * Return a list with the users who queried a certain resource
     * @return a String[] with the users
     */
    @Override
    public String[] getUsersFromResource(String resource) {
        ArrayList<Query> listQueries = resources.get(resource);
        ArrayList<String> usersResource = new ArrayList<>();
        for (Query listQuery : listQueries) {
            if (!usersResource.contains(listQuery.getUser())) {
                usersResource.add(listQuery.getUser());
            }
        }
        return usersResource.toArray(new String[0]); //es que era una ArrayList
    }


    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    @Override
    public String[] getUsersFromResourceDate(String resource, Date date) {
        ArrayList<String> usersResourceDate = new ArrayList<>();
        ArrayList<Query> listQueries = resources.get(resource);
        for (Query listQuery : listQueries) {
            if (listQuery.getDate().equals(date)) {
                if (!usersResourceDate.contains(listQuery.getUser())) {
                    usersResourceDate.add(listQuery.getUser());
                }
            }
        }
        return usersResourceDate.toArray(new String[0]);
    }

    /**
     * Return the most queried resource
     */
    @Override
    public String getMostQueriedResource() {
        String most=null;
        int length =0;
        for (String res : this.resources.keySet()){
            if (length < resources.get(res).size()){
                length = resources.get(res).size();
                most=res;
            }
        }
        return most;
    }


    /**
     * Return a list with the resources queried by a user
     * @return result
     */
    @Override
    public String[] getResourcesFromUser(String user) {
        ArrayList<String> result = new ArrayList<>();
        for (int i =0; i<users.get(user).size(); i++){
            result.add(users.get(user).get(i).getResource());
        }
        return result.toArray(new String[0]);
    }


    /**
     *
     * @return result
     */
    public String toString(){
        return toStringResources()+toStringUsers();
    }


    /**
     * ToString
     * @return result
     */
    public String toStringUsers(){
        StringBuilder result= new StringBuilder("\n-----------USERS: \n");
        for (String name: this.users.keySet()){
            result.append(name).append(":\n").append(this.users.get(name).toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * ToString resources
     * @return result
     */
    public String toStringResources(){
        StringBuilder result= new StringBuilder("\n-----------RESOURCES: \n");
        for (String name: this.resources.keySet()){
            result.append(name).append(":\n").append(this.resources.get(name).toString()).append("\n");
        }
        return result.toString();
    }
}
