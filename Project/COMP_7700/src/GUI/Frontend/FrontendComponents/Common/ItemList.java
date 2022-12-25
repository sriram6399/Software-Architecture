package FinalProject;

import FinalProject.DataSender;
import FinalProject.Item; 
import FinalProject.ItemPanel; 

import java.util.*; 

public class ItemList {
    static private int nextID = 0; 


    private int listID; // ID in database for information about the List 
    private String listTitle;
    private String listDescription; 
    private ItemListType type; 
    private int ownerID;
    private String description;

    private double subtotal;
    private double tax;
    private double totalValue; 

    private ArrayList<Item> itemsInList; 
    private int numberOfDescreteItems; 
    private int numberOfTotalItems; 

    private ItemListScroll listScrollPane; 



    public ItemList(String title, String description, ItemListType type, int ownerID){
        this.listTitle = title;
        this.listDescription = description; 
        this.ownerID = ownerID;

        this.subtotal = 0;
        this.tax = 0; 
        this.totalValue = 0; 

        this.itemsInList = new ArrayList<Item>(); 
        this.numberOfDescreteItems = 0; 
        this.numberOfTotalItems = 0; 

        this.type = type; 

        this.listScrollPane = new ItemListScroll(this); 
        
        listID = nextID; 
        nextID++; 

        // Send New List to Server 
        DataSender.addNewList(this); 

    }

    public ItemList(int ID, String title, String desc, ItemListType type, int ownerID){
        this.listID = ID; 
        this.listTitle = title;
        this.listDescription = desc; 
        this.type = type; 
        this.ownerID = ownerID;

        this.listScrollPane = new ItemListScroll(this); 
        this.itemsInList = new ArrayList<Item>(); 
    }


    // Get/Set Methods 
    public int getlistID() {
      return this.listID;
    }

    public void setTitle(String title) {
        this.listTitle = title; 
    }
    public String getTitle(){
        return this.listTitle; 
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getOwnerID() {
        return this.ownerID;
    }

    public void setDescription(String description) {
        this.listDescription = description; 
    }

    public String getDescription(){
        return this.listDescription; 
    }

    public double getSubtotal(){
        return this.subtotal; 
    }

    public double getTax(){
        return this.tax; 
    }

    public double getTotalValue(){
        return this.totalValue; 
    }

    public int getDistinctItemCount(){
        return this.numberOfDescreteItems; 
    }
    
    public int getTotalNumberOfItems(){
        return this.numberOfTotalItems; 
    }


    public Item getItem(int index){
        return this.itemsInList.get(index); 
    }

    public void setScrollPane(ItemListScroll scroll){
        this.listScrollPane = scroll; 
    }
    public ItemListScroll getScrollPane(){
        return this.listScrollPane; 
    }


    public int addItemToList(Item item){
        if(item == null){
            return -1; 
        }else{

            int index = getItemIndexInList(item); 
            if(index != -1){ // Item Exists 
                // Update Local Copy of List
                int curQty = this.itemsInList.get(index).getQuantity(); 
                int newQty = curQty + item.getQuantity(); 
                this.itemsInList.get(index).setQuantity(newQty);

                //Update Server Copy
                DataSender.updateItemListData(this.listID, item.getItemID(), newQty); 

                //Update specific summary information 

            }else{// Item Doesn't exist
                // Update Local Copy of List
                this.itemsInList.add(item); 

                //Update Server Copy 
                //addNewItemList(item, this); 

                // Update specific summary information 
                this.numberOfDescreteItems = this.numberOfDescreteItems + 1; 

            }

            // Update List Summary Information (Local Only) 
            this.numberOfTotalItems = this.numberOfTotalItems + item.getQuantity(); 
            this.subtotal = this.subtotal + item.getPrice(); 
            this.tax = this.subtotal * 0.1; 
            this.totalValue = this.subtotal + this.tax; 

        }
        // Update GUI Visuals for this list 
        this.listScrollPane.updateView(); 

        return 0; 
    }

    public int removeItemFromList(Item item){
        if(item == null){
            return -1; 
        }else{
            int index = getItemIndexInList(item); 
            if(index != -1){
                // Update Local Copy of List 
                this.itemsInList.remove(index); 

                // Update Server Copy of List 
                DataSender.removeItemInList(item.getItemID(), this.listID); 

                // Update Summary Information 
                this.numberOfDescreteItems = this.numberOfDescreteItems - 1; 
                this.numberOfTotalItems = this.numberOfTotalItems - item.getQuantity(); 
                this.subtotal = this.subtotal - item.getPrice(); 
                this.tax = this.subtotal * 0.1; 
                this.totalValue = this.subtotal + this.tax; 
 
            }else{ // Item doesn't exist in List
                return -1; 
            }


            if(this.listScrollPane != null){
                this.listScrollPane.updateView();
                
                if(this.listScrollPane.getListSummaryPanel() != null){
                    this.listScrollPane.getListSummaryPanel().updatePanel(); 
                }
            }
            return 0; 
        }
    
    }
    public int getItemIndexInList(Item item){
        int index = 0; 
        for(index = 0; index < this.itemsInList.size(); index++){
            // Item already exists in list, just add to existing qty
            if(itemsInList.get(index).getItemID() == item.getItemID()){ 
                return index; 
            }
        }// Item doesn't exist and therefore needs to be added 
        return -1; 
    }

    public void addTestItems(int count){
        for(int i = 0; i < count; i++){
            Item item = new Item("Item " + i, i* 10, "This is the description for Item " + i);
            item.setItemListType(type); 
            this.addItemToList(item); 
        }
    }
    public void setItemListType(ItemListType type){
        this.type = type; 
        for(int i = 0; i < itemsInList.size(); i++){
            itemsInList.get(i).setItemListType(type); 
        }

    }
    public ItemListType getItemListType(){
        return this.type; 
    }
    public void setItemsList(ArrayList<Item> list){
        this.itemsInList = list; 
    }
    public void printItemList(){
        System.out.println("\nList Information ------------------------------------------"); 
        System.out.println("List ID: " + this.listID + " List Title: " + this.listTitle + " List Desc: " + this.listDescription ); 
        for(int i = 0; i < itemsInList.size(); i++){
            itemsInList.get(i).printItem(); 
        }
    }
}
