/**
 * File used to send data from the Frontend (application) to the Backend and receive data from the
 * Backend
 */

package FinalProject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedHashMap;

import java.util.*; 

import FinalProject.User; 
import FinalProject.Item; 
import FinalProject.ItemList; 

public class DataSender {

  //This is for use as an example and testing only.
  //It still needs to be determined whether to connect for each query or to connect once, as well
  //as where to connect in the codebase.
  public static void main(String[] args) {
    new DataSender("127.0.0.1", 23987);

/*
    DataSender.getItemDataCall(0); 
    System.out.println(); 
    DataSender.getItemDataCall(1);
    System.out.println(); 
    DataSender.getListDataCall(0); 
    System.out.println(); 
    DataSender.getListDataCall(1); 
*/ 
    System.out.println(); 
    DataSender.getUserDataCall(0); 
    DataSender.getUserDataCall(1); 

  }

  private static String ipAddress;
  private static int port;
  private static Socket s;
  private static ObjectInputStream in;
  private static ObjectOutputStream out;

  public DataSender(String ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
  }

  private static void createSocket() {
    try {
      s = new Socket(ipAddress, port);
    } catch (Exception e) {
      System.out.println("Could not connect to server");
      e.printStackTrace();
      return;
    }
    //Must create output stream first
    makeOutputStream();
    makeInputStream();
  }

  private static boolean makeInputStream() {
    try {
      in = new ObjectInputStream(s.getInputStream());
    } catch (Exception e) {
      System.out.println("Could not make Input Stream from Socket");
      e.printStackTrace();
      try {
        s.close();
      } catch (Exception ee) {
        System.out.println("Could not close Socket correctly");
        ee.printStackTrace();
      }
      return false;
    }
    return true;
  }

  private static boolean makeOutputStream() {
    try {
      out = new ObjectOutputStream(s.getOutputStream());
    } catch (Exception e) {
      System.out.println("Could not make Output Stream from Socket");
      e.printStackTrace();
      try {
        s.close();
      } catch (Exception ee) {
        System.out.println("Could not close Socket correctly");
        ee.printStackTrace();
      }
      return false;
    }
    return true;
  }

  public static String sendData(String data) {
    JSONObject jsonOutput = populateJSON("mysql_query", data);
    sendJSON(jsonOutput);
    JSONObject jsonInput = getJson();
    return jsonInput.toString();
  }

  public static String sendUpdate(String data) {
    JSONObject jsonOutput = populateJSON("mysql_update", data);
    sendJSON(jsonOutput);
    JSONObject jsonInput = getJson();
    return jsonInput.toString();
  }

  private static JSONObject populateJSON(String type, String data) {
    JSONObject json = new JSONObject();
    json.put("type", type);
    json.put(type, data);
    return json;
  }

  private static boolean sendJSON(JSONObject outJson) {
    try {
      out.writeObject(outJson);
      out.flush();
    } catch (Exception e) {
      System.out.println("Could not send data");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static JSONObject getJson() {
    JSONObject json = new JSONObject();
    JSONParser parser = new JSONParser();
    try {
      json = (JSONObject) parser.parse(in.readObject().toString());
    } catch (EOFException e) {
    } catch (Exception e) {
      System.out.println("Could not read data");
      e.printStackTrace();
      try {
        s.close();
      } catch (Exception ee) {
        System.out.println("Could not close Socket correctly");
        ee.printStackTrace();
      }
    }
    return errorCheck(json);
  }

  public static JSONObject errorCheck(JSONObject received) {
    if (checkReceivedType(received)) {
      String type = received.get("type").toString();
      System.out.println(type);
      JSONArray jar = (JSONArray) received.get(type);
      for (Object obj: jar.toArray()) {
        System.out.println(obj + ": " + received.get(obj.toString()));
      }
      received = new JSONObject();
    }
    return received;
  }

  public static boolean checkReceivedType(JSONObject received) {
    return (!received.isEmpty() &&
        !received.get("type").equals("mysql_data") &&
        !received.get("type").equals("mysql_update"));
  }

  public static boolean sendClose() {
    JSONObject json = new JSONObject();
    json.put("type", "close");
    boolean returnable = true;
    try {
      out.writeObject(json);
      out.flush();
    } catch (Exception e) {
      System.out.println("Could not send data");
      e.printStackTrace();
      returnable = false;
    } finally {
      try {
        s.close();
      } catch (Exception ee) {
        System.out.println("Could not close Socket correctly");
        ee.printStackTrace();
        returnable = false;
      }
    }
    return returnable;
  }
 private static LinkedHashMap<String, String[]> parseData(String jsonString, String... keys) {
    LinkedHashMap<String, String[]> outputList = new LinkedHashMap<String, String[]>();
    Object data = JSONValue.parse(jsonString);
    JSONObject jsonDecode = (JSONObject) data;
    Object innerData = JSONValue.parse(jsonDecode.get("mysql_data").toString());
    JSONObject innerJson = (JSONObject) innerData;
    for (String key:keys) {
      JSONArray jar = (JSONArray) innerJson.get(key);
      String[] values = new String[jar.size()];
      int i = 0;
      for (Object o: jar.toArray()) {
        values[i++] = o.toString();
      }
      outputList.put(key, values);
    }
    return outputList;
  }

////////////////////////////////////////////////////////////////////////////
// Users Calls 
///////////////////////////////////////////////////////////////////////////
  public static LinkedHashMap<String, String[]> getAllUsers(){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    outputList = parseData(sendData("SELECT user_id,user_name FROM users;"), "user_id", "user_name");
    sendClose();
    return outputList; 
  }

  public static ArrayList<User> getAllUsersCall(){

  /*
    ArrayList<User> usersList = new ArrayList<User>(); 

    LinkedHashMap<String, String[]> usersHashMap = getAllUsers(); 
    Set<String> keys = usersHashMap.keySet(); 

    for (String key : keys){
        String [] result = usersHashMap.get(key); 
        int ID = Integer.parseInt(result[0]); 
        String userName = result[1]; 
        User user = new User(ID, userName); 
      
        ArrayList <ItemList> userLists = getUserListDataCall(ID); 

        // Get the For Sale Items 
        user.setSellerForSaleList(userLists.get(0)); 

        // Get the my Cart Items 
        user.setMyCart(userLists.get(1)); 
        
        user.setRecommendedItemsList(userLists.get(2));

        user.setPastPurchasesList(userLists.get(3)); 

        //Finally Add User to ArrayList 
        usersList.add(user); 
    }
    return usersList; 
    */ 
    return null; 
  }


  public static LinkedHashMap<String, String[]> getUserData(int userID){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    //What specific data do we want? just name and id#?
    outputList = parseData(sendData("SELECT user_name FROM users WHERE user_id=" + userID + ";"), "user_name");
    sendClose();
    return outputList; 
  }
  public static User getUserDataCall(int userID){
    User user = null; 
    String userName = ""; 
    
    // Get List Summary Data 
    LinkedHashMap<String, String[]> userHashMap = getUserData(userID); 
    Set<String> keys = userHashMap.keySet(); 
    String[] values; 
    for( String k : keys ){
        values = userHashMap.get(k); 
        for( String s : values){
            user = new User (userID, s); 
        }
    }

    ArrayList<ItemList> lists  = getUserListDataCall(userID);
    lists.get(0).setItemListType(ItemListType.SELLER_ITEM); 
    user.setSellerForSaleList(lists.get(0)); 

    lists.get(1).setItemListType(ItemListType.MY_CART_ITEM); 
    user.setMyCart(lists.get(1));

    lists.get(2).setItemListType(ItemListType.RECOMMENDED_ITEM); 
    user.setRecommendedItemsList(lists.get(2)); 

    lists.get(3).setItemListType(ItemListType.PAST_PURCHASE_ITEM); 
    user.setPastPurchasesList(lists.get(3)); 
    //user.printUser(); 

    return user; 
  }

  public static boolean updateUserData(User usr){
    createSocket();
    //What specifically are we updating? We'll need to pass it in -> either the user object or the
    //things passed as arguments. I think the user would be easier. See next function for example.
    int newID = usr.getUserID();
    String newName = usr.getName();
    sendUpdate("UPDATE users SET user_name='" + newName + "' WHERE user_id=" + newID + ";");
    sendClose();
    return true; 
  }
  public static boolean addNewUser(User usr, String password){
    createSocket();
    int newID = usr.getUserID();
    String newName = usr.getName();
    String newPass = password;
    sendUpdate("INSERT INTO users VALUES (" + newID + ",'" + newName + "','" + newPass + "');");
    sendClose();
    return true; 
  }
  public static boolean removeUser(int usrID){
    createSocket();
    sendUpdate("DELETE FROM users WHERE user_id=" + usrID + ";");
    sendClose();
    return true; 
  }

////////////////////////////////////////////////////////////////////////////
// Items Calls 
///////////////////////////////////////////////////////////////////////////
  public static LinkedHashMap<String, String[]> getAllItems(){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    outputList = parseData(sendData("SELECT * FROM items;"), "item_id", "title", "description", "price", "qty_avail", "qty_sold", "seller_id");
    sendClose();
    return outputList; 
  }
 
  public static ArrayList<Item> getAllItemsCall(){
   /* 
    ArrayList<Item> itemsList = new ArrayList<Item>(); 

    LinkedHashMap<String, String[]> itemsHashMap = getAllItems(); 
    Set<String> keys = itemsHashMap.keySet(); 

    for (String key : keys){
        // Get the next Item 
        String [] result = itemsHashMap.get(key);

        //Parse out all of the data 
        int ID = Integer.parseInt(result[0]); 
        String itemName = result[1];
        String desc = result[3]; 
        int price = Integer.parseInt(result[2]); 

        //Create an instance of the item class with that data 
        Item item = new Item(ID, itemName, price, desc); 

        //Finally Add User to ArrayList 
        itemsList.add(item); 
    }
    return itemsList; 
    */ 
    return null; 
  }

  public static LinkedHashMap<String, String[]> getItemData(int itemID){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    outputList = parseData(sendData("SELECT title,description,price,qty_avail,qty_sold,seller_id FROM items WHERE item_id=" + itemID + ";"), "title", "description", "price", "qty_avail", "qty_sold", "seller_id");
    sendClose();
    return outputList; 
  }

  public static Item getItemDataCall(int itemID){
    Item item; 

    String itemName = "GOD";
    String desc =" for the love of GOD"; 
    Double price = 0.0; 
    int qty = 0; 

    LinkedHashMap<String, String[]> itemHashMap = getItemData(itemID); 
    Set<String> keys = itemHashMap.keySet(); 

    Iterator it = itemHashMap.entrySet().iterator(); 
    while(it.hasNext()){
        String[] a; 
        Map.Entry<String, String[]> pair = (Map.Entry)it.next(); 
        String key = pair.getKey(); 
        a = itemHashMap.get(pair.getKey());
        switch(key) {
            case "title":
                itemName = a[0]; 
                break; 
            case "description":
                desc = a[0]; 
                break; 
            case "price": 
                price = Double.parseDouble(a[0]); 
                break; 
            case "qty_avail": 
                qty = Integer.parseInt(a[0]); 
        }
    }
    item = new Item(itemID, itemName, price, desc, qty); 
    return item; 

/*
    ArrayList<Integer> listIDs = new ArrayList<Integer>();
    ArrayList<ItemList> itemLists = new ArrayList<ItemList>(); 

    LinkedHashMap<String, String[]> userListHashMap = getUserListData(userID); 
    Set<String> keys = userListHashMap.keySet(); 
    String[] values; 
    for( String k : keys ){
        values = userListHashMap.get(k); 
        for( String s : values){
            listIDs.add(Integer.parseInt(s));
        }
    }
    
    //Get the lists from IDs 
    for(int i = 0; i< listIDs.size(); i++){
        itemLists.add(getListDataCall(listIDs.get(i))); 
    }
    return itemLists; 
*/ 


  }

  public static boolean updateItemData(Item item){
    createSocket();
    int itemID = item.getItemID();
    String newName = item.getName();
    String newDescription = item.getDescription();
    double newPrice = item.getPrice();
    int newQuantity = item.getQuantity();
    int newQuantitySold = 0; //until needed
    int newSellerID = item.getSellerID();
    sendUpdate("UPDATE items SET title='" + newName + "',description='" + newDescription + "',price=" + newPrice + ",qty_avail=" + newQuantity + ",seller_id=" + newSellerID + " WHERE item_id=" + itemID + ";");
    sendClose();
    return true; 
  }
  public static boolean addNewItem(Item item){
    createSocket();
    int itemID = item.getItemID();
    String newName = item.getName();
    String newDescription = item.getDescription();
    double newPrice = item.getPrice();
    int newQuantity = item.getQuantity();
    int newQuantitySold = 0; //until needed
    int newSellerID = item.getSellerID();
    sendUpdate("INSERT into items VALUES (" + itemID + ",'" + newName + "','" + newDescription + "'," + newPrice + "," + newQuantity + "," + newQuantitySold + "," + newSellerID + ");");
    sendClose();
    return true; 
  }
  public static boolean removeItem(int itemID){
    createSocket();
    sendUpdate("DELETE FROM items WHERE item_id=" + itemID + ";");
    sendClose();
    return true;
  }

////////////////////////////////////////////////////////////////////////////
// Items Calls 
///////////////////////////////////////////////////////////////////////////
  public static LinkedHashMap<String, String[]> getAllLists(){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    outputList = parseData(sendData("SELECT * FROM lists;"), "list_id", "title", "description", "list_type", "owner_id");
    sendClose();
    return outputList; 
  }

  public static ArrayList<ItemList> getAllListsCall(){
    // TODO Need to determine use case here & return data 
/*
    ArrayList<ItemList> lists = new ArrayList(); 
    return lists; 
*/ 
    return null; 
  }

  public static LinkedHashMap<String, String[]> getListData(int listID){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    outputList = parseData(sendData("SELECT title,description,list_type,owner_id FROM lists WHERE list_id=" + listID + ";"), "title", "description", "list_type", "owner_id");
    sendClose();
    return outputList; 
  }
  public static ItemList getListDataCall(int listID){
    ItemList list; 
    Item item; 

    String listName = "";
    String desc =""; 
    int ownerID = 0;
    ItemListType type = ItemListType.SELLER_ITEM; 

    // Get List Summary Data 
    LinkedHashMap<String, String[]> listHashMap = getItemData(listID); 
    Set<String> keys = listHashMap.keySet(); 

    Iterator it = listHashMap.entrySet().iterator(); 
    while(it.hasNext()){
        String[] a; 
        Map.Entry<String, String[]> pair = (Map.Entry)it.next(); 
        String key = pair.getKey(); 
        a = listHashMap.get(pair.getKey());
        switch(key) {
            case "title":
                listName = a[0]; 
                break; 
            case "description":
                desc = a[0]; 
                break; 
            case "list_type":
                type = ItemListType.valueOf(a[0]); 
                break; 
            case "owner_id": 
                ownerID = Integer.parseInt(a[0]); 
                break; 
        }
    }
    // TODO Fix type; 
    list = new ItemList(listID, listName, desc, type, ownerID); 

    //get Items In List 
    ArrayList<Item> items  = getItemListDataCall(listID);
    list.setItemsList(items); 

    return list; 
   }

  public static boolean updateListData(ItemList list){
    createSocket();
    int listID = list.getlistID();
    String newTitle = list.getTitle();
    String newDescription = list.getDescription();
    ItemListType itemType = list.getItemListType();
    int ownerID = list.getOwnerID();
    sendUpdate("UPDATE lists SET title='" + newTitle + "',description='" + newDescription + "',list_type='" + itemType + "' WHERE list_id=" + ownerID + ";");
    sendClose();
    return true; 
  }
  public static boolean addNewList(ItemList list){
    createSocket();
    int listID = list.getlistID();
    String newTitle = list.getTitle();
    String newDescription = list.getDescription();
    ItemListType itemType = list.getItemListType();
    int ownerID = list.getOwnerID();
    sendUpdate("INSERT into lists VALUES (" + listID + ",'" + newTitle + "','" + newDescription + "','" + itemType + "'," + ownerID + ");");
    sendClose();
    return true; 
  }
  public static boolean removeList(int listID){
    createSocket();
    sendUpdate("DELETE FROM lists WHERE list_id=" + listID + ";");
    sendClose();
    return true; 
  }

////////////////////////////////////////////////////////////////////////////
// ItemLists Calls 
///////////////////////////////////////////////////////////////////////////
  public static LinkedHashMap<String, String[]> getAllItemLists(){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    outputList = parseData(sendData("SELECT * FROM item_list_pair;"), "id_of_list", "id_of_item", "qty_of_item");
    sendClose();
    return outputList; 
  }
  public static LinkedHashMap<String, String[]> getItemListData(int listID){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    outputList = parseData(sendData("SELECT id_of_item,qty_of_item FROM item_list_pair WHERE id_of_list=" + listID + ";"), "id_of_item", "qty_of_item");
    sendClose();
    return outputList; 
  }

  public static ArrayList<Item> getItemListDataCall(int listID){

    int ID = 0;
    int qty = 0; 

    Item item = null; ; 
    ArrayList<Item> list = new ArrayList<Item>(); 

    LinkedHashMap<String, String[]> itemListHashMap = getItemListData(listID); 
    Set<String> keys = itemListHashMap.keySet(); 

    Iterator it = itemListHashMap.entrySet().iterator(); 
    while(it.hasNext()){
        String[] a; 
        Map.Entry<String, String[]> pair = (Map.Entry)it.next(); 
        String key = pair.getKey(); 
        a = itemListHashMap.get(pair.getKey());
        switch(key) {
            case "id_of_item":
                ID =Integer.parseInt(a[0]); 
                break; 
            case "description":
                qty = Integer.parseInt(a[0]); 
                break; 
        }

        // get Item From ID 
        item = getItemDataCall(ID); 
        item.setQuantity(qty);
        list.add(item); 

    }
     
    return list; 

  /*
        ItemList list; 
        // Get Summary ItemList Information 
        LinkedHashMap<String, String[]> itemsListHashMap = getItemListData(listID); 
        Set<String> keys = itemsListHashMap.keySet();
        
        //TODO FIX 
        list = new ItemList("Temp Title", "Temp Desc", ItemListType.SELLER_ITEM, 0); 

        for(String k : keys){
            // Get Item IDs 
            String[] items = itemsListHashMap.get(k); 
            for (int i = 0; i< items.length; i++){
                int id = Integer.parseInt(items[i]); 
                Item item = getItemDataCall(id); 
                list.addItemToList(item); 
            }

        }
       return list;  
*/
  }

  public static boolean updateItemListData(int listID, int itemID, int qty){
    createSocket();
    sendUpdate("UPDATE item_list_pair SET qty_of_item=" + qty + " WHERE id_of_list=" + listID + " AND id_of_item=" + itemID + ";");
    sendClose();
    return true; 
  }
  public static boolean addNewItemList(Item item, ItemList list, int qty){
    createSocket();
    sendUpdate("INSERT INTO item_list_pair VALUES (" + list.getlistID() + "," + item.getItemID() + "," + qty + ");");
    sendClose();
    return true; 
  }
  public static boolean removeItemInList(int itemID, int listID){
    createSocket();
    sendUpdate("DELETE FROM item_list_pair WHERE id_of_list=" + listID + " AND id_of_item=" + itemID + ";");
    sendClose();
    return true;
  }

////////////////////////////////////////////////////////////////////////////
// Users List Calls 
///////////////////////////////////////////////////////////////////////////
 
  public static LinkedHashMap<String, String[]> getUserListData(int userID){
    LinkedHashMap<String, String[]> outputList;
    createSocket();
    outputList = parseData(sendData("SELECT for_sale, my_cart, recommended_items, past_purchases FROM user_lists WHERE user_id=" + userID + ";"), "for_sale", "my_cart", "recommended_items", "past_purchases");
    sendClose();
    return outputList; 
  }
  public static ArrayList<ItemList> getUserListDataCall(int userID){
    int itemID = 0;
    int listID = 0; 

    ArrayList<Integer> listIDs = new ArrayList<Integer>();
    ArrayList<ItemList> itemLists = new ArrayList<ItemList>(); 

    LinkedHashMap<String, String[]> userListHashMap = getUserListData(userID); 
    Set<String> keys = userListHashMap.keySet(); 
    String[] values; 
    for( String k : keys ){
        values = userListHashMap.get(k); 
        for( String s : values){
            listIDs.add(Integer.parseInt(s));
        }
    }
    
    //Get the lists from IDs 
    for(int i = 0; i< listIDs.size(); i++){
        itemLists.add(getListDataCall(listIDs.get(i))); 
    }
    return itemLists; 

  }

} 
