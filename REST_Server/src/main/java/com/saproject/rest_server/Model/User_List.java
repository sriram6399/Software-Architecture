package com.saproject.rest_server.Model;

import javax.persistence.*;

@Entity
public class User_List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Column
    private long user_id;

    @Column
    private int for_sale;

    @Column
    private int my_cart;

    @Column
    private int recommended_items;

    @Column
    private int past_purchases;

    public int getFor_sale() {
        return for_sale;
    }

    public void setFor_sale(int for_sale) {
        this.for_sale = for_sale;
    }

    public int getMy_cart() {
        return my_cart;
    }

    public void setMy_cart(int my_cart) {
        this.my_cart = my_cart;
    }

    public int getRecommended_items() {
        return recommended_items;
    }

    public void setRecommended_items(int recommended_items) {
        this.recommended_items = recommended_items;
    }

    public int getPast_purchases() {
        return past_purchases;
    }

    public void setPast_purchases(int past_purchases) {
        this.past_purchases = past_purchases;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
