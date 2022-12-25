package FinalProject;

import FinalProject.DataSender;
import FinalProject.Item;
import FinalProject.ItemList; 
import FinalProject.ItemListType;

import java.util.Arrays;

public class User {

    public static int NextUserID = 0; 

    private int userID; 
    private String userName;
    private String password; 

    private ItemList itemsForSale; 
    private ItemList myCart; 
    private ItemList recommendedProducts; 
    private ItemList pastPurchases; 



    public User(String name, String password){
        this.userName = name;
        this.password = password; 
        NextUserID = getCurrentUserCount();
        this.userID = NextUserID; 
        NextUserID = NextUserID + 1;


        // Send New User to Server Database 
        DataSender.addNewUser(this, password); 
    
        itemsForSale = new ItemList(this.userName + "s Items For Sale", "Items the user wants to sell", ItemListType.SELLER_ITEM, this.userID);
        myCart = new ItemList(this.userName + "s Cart", "Items the user would like to purchase", ItemListType.MY_CART_ITEM, this.userID); 
        recommendedProducts = new ItemList(this.userName + "s Recommened Products", "Items that the website recommends for the user to purchase", ItemListType.RECOMMENDED_ITEM, this.userID); 
        pastPurchases = new ItemList(this.userName +"s Past Purchases", "Items the user has purchases in the past", ItemListType.PAST_PURCHASE_ITEM, this.userID); 

    }

  public User(int ID, String username){
    this.userName = username; 
    this.userID = ID; 
    this.password = null; 

    itemsForSale = null; 
    myCart = null; 
    recommendedProducts = null; 
    pastPurchases = null; 

  }


  private int getCurrentUserCount() {
    int count = 0;
    String[] userIDs = DataSender.getAllUsers().get("user_id");
    int size = userIDs.length;
    Arrays.sort(userIDs);
    count = Integer.parseInt(userIDs[size - 1]);
    return count;
  }

// Standard Get/Set Methods, NOTE - Not All Variables have Setters - THIS IS INTENTIONAL 
    public int getUserID(){
        return this.userID; 
    }
    public void setName(String name) {
        this.userName = name; 
    }
    public String getName(){
        return this.userName; 
    }

    public int compareToPassword(String passwordAttempt){
        if(this.password.equals(passwordAttempt)){
            return 0; 
        }else{
            return -1; 
        }

    }

/*
    Functions pertaining to the Items for Sale List 
*/ 
    public ItemList getSellerForSaleList(){
        return this.itemsForSale; 
    }
    public void setSellerForSaleList(ItemList list){
        this.itemsForSale = list; 
    }
    public int addItemToForSaleList(Item item){
        int ret = itemsForSale.addItemToList(item);
        item.setItemListType(ItemListType.SELLER_ITEM); 
        return ret; 
    }
    public int removeItemFromForSaleList(Item item){
        int ret = itemsForSale.removeItemFromList(item);
        return ret; 
    }

/*
   Functions pertaining to the Items in the Users Cart
*/
    public ItemList getMyCart(){
        return this.myCart; 
    }
    public void setMyCart(ItemList list){
        this.myCart = list; 
    }
    public int addItemToUserCartList(Item item){
        int ret = myCart.addItemToList(item); 
        item.setItemListType(ItemListType.MY_CART_ITEM); 
        return ret; 
    }
    public int removeItemFromUserCartList(Item item){
        int ret = myCart.removeItemFromList(item); 
        return ret; 
    }


/*
    Functions pertaining to the Users Recommeneded Items List 
*/
    public ItemList getRecommendedItemsList(){
        return this.recommendedProducts; 
    }
    public void setRecommendedItemsList(ItemList list){
        this.recommendedProducts = list; 
    }
    public int addItemToRecommendedList(Item item){
        int ret = recommendedProducts.addItemToList(item);  
        item.setItemListType(ItemListType.RECOMMENDED_ITEM); 
        return ret; 
    }
    public int removeItemFromRecommendedList(Item item){
        int ret = recommendedProducts.removeItemFromList(item); 
        return ret; 
    }

/*
    Functions pertaining to the Users Past Purchases List 
*/
    public ItemList getPastPurchases(){
        return this.pastPurchases; 
    }
    public void setPastPurchasesList(ItemList list){
        this.pastPurchases = list; 
    }
    public int addItemToPastPurchasesList(Item item){
        int ret = pastPurchases.addItemToList(item); 
        item.setItemListType(ItemListType.PAST_PURCHASE_ITEM); 
        return ret; 
    }
    public int removeItemFromPastPurchasesList(Item item){
        int ret = pastPurchases.removeItemFromList(item); 
        return ret; 
    }

    public void printUser(){
        System.out.println("User Information ----------------------"); 
    
        System.out.println("User ID: " + this.userID + " Username: " + this.userName); 

        this.itemsForSale.printItemList(); 
        this.myCart.printItemList(); 
        this.recommendedProducts.printItemList(); 
        this.pastPurchases.printItemList(); 


    }


}
