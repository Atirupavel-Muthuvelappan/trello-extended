package service;

import com.google.appengine.api.datastore.*;
import doc.ViewJson;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Database {
    String email;
    String password;
    String name;

  public  Database(String email) {
        this.email = email;
    }

   public Database(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public String[] checkUser() {

        String userdetail[] = new String[2];
        try {
            Key key = KeyFactory.createKey("person", email);
            Query q = new Query("person", key);
            PreparedQuery pq = datastore.prepare(q);
            userdetail[0] = pq.asSingleEntity().getProperty("password").toString();
            userdetail[1] = pq.asSingleEntity().getProperty("name").toString();
        } catch (IllegalArgumentException e) {
            System.out.print("there is no such user");
        } catch (NullPointerException e) {
            System.out.print("there is no such user");
        }

        return userdetail;
    }

    public void addUser() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity p = new Entity("person", email);
        p.setProperty("name", name);
        p.setProperty("password", password);
        datastore.put(p);
    }

    public void addDetails(String content, String email, ArrayList people, String date) {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        String uid = UUID.randomUUID().toString();
        Entity p = new Entity("Description", uid);
        p.setProperty("content", content);
        p.setProperty("createdBy", email);
        p.setProperty("date", date);
        p.setProperty("share", people);
        datastore.put(p);

    }

    public ArrayList<ViewJson> fetchDetails() {
        Query.Filter filter1 = new Query.FilterPredicate("createdBy", Query.FilterOperator.EQUAL, email);
        Query.Filter filter2 = new Query.FilterPredicate("share", Query.FilterOperator.EQUAL, email);
        Query q = new Query("Description").setFilter(Query.CompositeFilterOperator.or(filter1, filter2));
        ArrayList<ViewJson> arrayList = new ArrayList<>();
        List<Entity> list = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        try {
            for (Entity e : list) {
                long days = ChronoUnit.DAYS.between(LocalDate.parse(e.getProperty("date").toString()), LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
                arrayList.add(new ViewJson(e.getKey().getName(), e.getProperty("content").toString(), String.valueOf(days)));
            }
        } catch (NullPointerException e) {
            System.out.print("no details available");
        }
        return arrayList;
    }
}
