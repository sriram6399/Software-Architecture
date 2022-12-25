package com.saproject.rest_server.Model;

import javax.persistence.*;

@Entity
@Table(name= "item_list_pair")
public class Item_List_Pair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    @Column
    private long id_of_item;

    @Column
    private long id_of_list;

    @Column
    private long qty_of_item;

    public long getQty_of_item() {
        return qty_of_item;
    }

    public void setQty_of_item(long qty_of_item) {
        this.qty_of_item = qty_of_item;
    }

    public long getId_of_item() {
        return id_of_item;
    }

    public void setId_of_item(long id_of_item) {
        this.id_of_item = id_of_item;
    }

    public long getId_of_list() {
        return id_of_list;
    }

    public void setId_of_list(long id_of_list) {
        this.id_of_list = id_of_list;
    }
}
