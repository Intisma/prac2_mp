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
    public void addQuery(String date, String hour, String alias, String resource) {
        boolean found = false;
        Query query = new Query (date, hour, alias);

        //para añadir si no existe
        resources.putIfAbsent(resource, new ArrayList<>());
        //si ya existe el recurso
        resources.get(resource).add(query);

        //para añadir si no existe este usuario
        users.putIfAbsent(alias, new ArrayList<>());
        //si ya existe el usuario y el recurso no está añadido previamente
        for (int i=0; i<users.get(alias).size() && !found; i++) {
            //aumentamos contador de consultas a un recurso
            if (users.get(alias).get(i).getResource().equals(resource)) {
                users.get(alias).get(i).setCont(users.get(alias).get(i).getCont()+1);
                found = true;
            }
        }
        if (!found) users.get(alias).add(new ContResource(resource));

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
    public void removeQueriesFromResourceDate(String date, String resource) {
        ArrayList<Query> listQuerys = resources.get(resource);
        ArrayList<Query> delete = new ArrayList<>();

        for (Query query : listQuerys) {
            if (query.getDate().equals(date)) {
                //Delete the resource from the list
                delete.add(query);
                //Search and delete the resource from the list in users if there is less than 1 query
                for (int i=0; i<users.get(query.getAlias()).size(); i++) {
                    if (users.get(query.getAlias()).get(i).getResource().equals(resource)) {
                        if (users.get(query.getAlias()).get(i).getCont() == 1) {
                            users.get(query.getAlias()).remove(i);
                        }
                        else {
                            users.get(query.getAlias()).get(i).setCont(users.get(query.getAlias()).get(i).getCont()-1);
                        }
                    }
                }

            }
        }
        resources.get(resource).removeAll(delete);
    }

    /**
     * Return a list with the users who queried a certain resource
     */
    @Override
    public ArrayList<String> getUsersFromResource(String resource) {
        ArrayList<Query> listQuerys = resources.get(resource);
        ArrayList<String> usersResource = new ArrayList<>();
        for (Query listQuery : listQuerys) {
            if (!usersResource.contains(listQuery.getAlias())) {
                usersResource.add(listQuery.getAlias());
            }
        }
        return usersResource;
    }


    /**
     * Return a list with the users who queried a certain resource in a specific date
     */
    @Override
    public ArrayList<String> getUsersFromResourceDate(String date, String resource) {
        ArrayList<String> usersResourceDate = new ArrayList<>();
        ArrayList<Query> listQuerys = resources.get(resource);
        for (Query listQuery : listQuerys) {
            if (listQuery.getDate().equals(date)) {
                if (!usersResourceDate.contains(listQuery.getAlias())) {
                    usersResourceDate.add(listQuery.getAlias());
                }
            }
        }
        return usersResourceDate;
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
    public ArrayList<String> getResourcesFromUser(String name) {
        ArrayList<String> result = new ArrayList<>();
        for (int i =0; i<users.get(name).size(); i++){
            result.add(users.get(name).get(i).getResource());
        }
        return result;
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
