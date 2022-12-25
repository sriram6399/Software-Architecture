package com.example.comp_7700_mobile_app;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AppManager {

    private static AppManager appManager = null;
    private static boolean databaseLoaded = false;
    private ArrayList<Item> itemsBeingSold = new ArrayList<Item>();
    private ArrayList<Item> itemsInCart = new ArrayList<Item>();

    public static AppManager getInstance() {
        if (appManager == null) {
            appManager = new AppManager();
        }
        return appManager;
    }

    private AppManager() {

    }

    public ArrayList<Item> getItemsBeingSold() {
        return itemsBeingSold;
    }

    public ArrayList<Item> getItemsInCart() {
        return itemsInCart;
    }

    /* public void addItemToSell(Item newItemToSell) {
        itemsBeingSold.add(newItemToSell);
    } */

    public void removeItemToSell(Item itemToRemoveFromSelling) { itemsBeingSold.remove(itemToRemoveFromSelling); }

    public void addItemToCart(Item itemToAddToCart) {
        itemsInCart.add(itemToAddToCart);
    }

    public void removeItemFromCart(Item itemToRemoveFromCart) { itemsInCart.remove(itemToRemoveFromCart); }

    public void initializeDatabase() throws Exception {
        double[] prices = {10.22, 10, 5.02, 15.48, 50.5, 3.16, 8.28, 62.14, 12.18, 100};
        Random quantityAvailableGenerator = new Random();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        for (int i = 0; i < prices.length; i++) {
            JSONObject objToWrite = new JSONObject();
            objToWrite.put("title", "Item " + (i + 1));
            objToWrite.put("description", "This is the description for Item " + (i + 1) + ".");
            objToWrite.put("price", prices[i]);
            objToWrite.put("qty_avail", quantityAvailableGenerator.nextInt(10) + 1);
            objToWrite.put("qty_sold", 1);
            objToWrite.put("seller_id", 1);

            RequestBody body = RequestBody.create(mediaType, objToWrite.toString());
            Request request = new Request.Builder()
                    .url("http://172.28.16.1:5000/items/additem")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
        }
    }

    public void readAllItemsInDatabase() throws Exception {
        itemsBeingSold.clear();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://172.28.16.1:5000/items/getallitems")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        JSONArray Jarray = new JSONArray(jsonData);

        for (int i = 0; i < Jarray.length(); i++) {
            JSONObject object = Jarray.getJSONObject(i);
            int idOfItemToAdd = (Integer) (object.get("item_id"));
            String titleOfItemToAdd = (String) (object.get("title"));
            double priceOfItemToAdd = (Double) (object.get("price"));
            String descOfItemToAdd =  (String) (object.get("description"));
            int qtyOfItemToAdd = (Integer) (object.get("qty_avail"));
            Item itemToAdd = new Item(idOfItemToAdd, titleOfItemToAdd, priceOfItemToAdd, descOfItemToAdd, qtyOfItemToAdd);
            itemsBeingSold.add(itemToAdd);
        }
    }

    public void deleteItemFromDatabase() throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url("http://172.28.16.1:5000/items/deleteitem/2")
                .method("DELETE", null)
                .build();
        Response response = client.newCall(request).execute();
    }

    public void writeItemToDatabase(String title, String description, double price, int qtyAvailable)
        throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject objToWrite = new JSONObject();
        objToWrite.put("title", title);
        objToWrite.put("description", description);
        objToWrite.put("price", price);
        objToWrite.put("qty_avail", qtyAvailable);
        objToWrite.put("qty_sold", 0);
        objToWrite.put("seller_id", 1);
        RequestBody body = RequestBody.create(mediaType, objToWrite.toString());
        Request request = new Request.Builder()
                .url("http://172.28.16.1:5000/items/additem")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        readAllItemsInDatabase();
    }

    public static boolean hasDatabaseBeenLoaded() { return databaseLoaded; }

    public static void invertDatabaseLoadedStatus() { databaseLoaded = !databaseLoaded; }
}
