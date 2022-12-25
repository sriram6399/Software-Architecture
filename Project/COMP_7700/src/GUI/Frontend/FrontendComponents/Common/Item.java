package FinalProject;

import FinalProject.DataSender;
import FinalProject.ItemPanel;
import FinalProject.ItemListType; 
import FinalProject.User; 

public class Item {

    public static int NextItemID = 3; 

    private int itemID; 
    private String name;
    private double price;
    private int qty; 
    private String sellerName; 
    private int sellerID; 

    private ItemListType listType = null; 


    ItemPanel panel; 

    String description;

    public Item(String name, double price, String des){
        this.name = name;
        this.price = price;
        this.description = des;
        this.qty = 1; 
        this.itemID = NextItemID; 
        NextItemID = NextItemID + 1; 


        //TODO implement seller Information; 
        this.sellerName = "TEMP SELLER NAME " + this.itemID; 
        this.sellerID = 0;//this.itemID; 

        this.panel = new ItemPanel(this);         

        // Send New Item To Server Database 
        DataSender.addNewItem(this); 

    }

    public Item(int ID, String name, double price, String desc){
        this.itemID = ID; 
        this.name = name;
        this.price = price;
        this.description = desc;
        this.qty = 1; 
     
        this.panel = new ItemPanel(this); 
    }
    public Item(int ID, String name, double price, String desc, int qty){
        this.itemID = ID; 
        this.name = name;
        this.price = price;
        this.description = desc;
        this.qty = 1; 
     
        this.panel = new ItemPanel(this); 
        this.qty = qty; 
    }
 

// Standard Get/Set Methods, NOTE - Not All Variables have Setters - THIS IS INTENTIONAL 
    public int getItemID(){
        return this.itemID; 
    }
    public void setName(String name) {
        this.name = name; 
    }
    public String getName(){
        return this.name; 
    }

    public void setPrice(double price){
        this.price = price; 
    }
    public double getPrice(){
        return this.price; 
    }

    public void setDescription(String des){
        this.description = des; 
    }
    public String getDescription(){
        return this.description; 
    }

    public void setQuantity(int qty){
        this.qty = qty; 
    }
    public int getQuantity(){
        return this.qty; 
    }

    public void setPanel(ItemPanel panel){
        this.panel = panel; 
    }
    public ItemPanel getPanel(){
        return this.panel; 
    }
    public void setSellerName(String name){
        this.sellerName = name; 
    }
    public String getSellerName(){
        return this.sellerName; 
    }
    public int getSellerID(){
        return this.sellerID; 
    }
    public void setItemListType (ItemListType type){
        this.listType = type;
        this.panel.updateButtonValues(type); 
    }
    public ItemListType getItemListType(){
        return this.listType; 
    }
    public void printItem(){
        System.out.println("\tItem ID: " + this.itemID + " Item Title: " + this.name + " Item Description: " + this.description + " Item Price: " + this.price + " Item QTY: " + this.qty);  

    }
}
