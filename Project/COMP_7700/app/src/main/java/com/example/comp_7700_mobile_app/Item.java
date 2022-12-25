package com.example.comp_7700_mobile_app;

public class Item {
    public static int NextItemID = 3;
    private int itemID;
    private String name;
    private double price;
    private int qty;
    private String sellerName;
    private int sellerID;
    // private ItemListType listType = null;
    String description;

    public Item(String name, double price, String des) {
        this.name = name;
        this.price = price;
        this.description = des;
        this.qty = 1;
        this.itemID = NextItemID++;
        this.sellerName = "TEMP SELLER NAME " + this.itemID;
        this.sellerID = 0;
        // DataSender.addNewItem(this);
    }

    public Item(int ID, String name, double price, String desc) {
        this.itemID = ID;
        this.name = name;
        this.price = price;
        this.description = desc;
        this.qty = 1;
    }

    public Item(int ID, String name, double price, String desc, int qty) {
        this.itemID = ID;
        this.name = name;
        this.price = price;
        this.description = desc;
        this.qty = 1;
        this.qty = qty;
    }

    public int getItemID() {
        return this.itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setDescription(String des) {
        this.description = des;
    }

    public String getDescription() {
        return this.description;
    }

    public void setQuantity(int qty) {
        this.qty = qty;
    }

    public int getQuantity() {
        return this.qty;
    }

    public void setSellerName(String name) {
        this.sellerName = name;
    }

    public String getSellerName() {
        return this.sellerName;
    }

    public int getSellerID() {
        return this.sellerID;
    }
}
