package doc;
import com.google.gson.JsonArray;
import com.google.gson.*;
import service.Database;

import java.util.ArrayList;

public class ViewJson {
    String email, key, value,date;



    public ViewJson() {
    }



    public ViewJson(String email) {
        this.email = email;
    }

   public ViewJson(String key, String value,String date) {
        this.key = key;
        this.value = value;
    this.date=date;}


    public JsonObject getJSONObject() {
        JsonObject obj = new JsonObject();
        try {
            obj.addProperty("key", key);
            obj.addProperty("value",value);
            obj.addProperty("date",date);
        } catch (JsonParseException e) {
            System.out.println(" JSONException: "+e.getMessage());
        }
        return obj;
    }
    public JsonArray fetch() {
        ArrayList<ViewJson> arrayList= new Database(email).fetchDetails();
      //  hashMap.forEach((key, value) -> list.add(new ViewJson(key, value).toString()));
        JsonArray jsonArray = new JsonArray();
        for (int i=0; i < arrayList.size(); i++) {
            jsonArray.add(arrayList.get(i).getJSONObject());
        }

        return jsonArray;
     }


}