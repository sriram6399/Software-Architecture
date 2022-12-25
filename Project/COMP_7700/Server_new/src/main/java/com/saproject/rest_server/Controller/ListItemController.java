package com.saproject.rest_server.Controller;

import com.saproject.rest_server.Model.Item_List_Pair;
import com.saproject.rest_server.REPO.ListItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListItemController {
    @Autowired
    private ListItemRepo listitemsRepo;

    @GetMapping("/listitem/getalllistitems")
    public List<Item_List_Pair> get_all_listitems(){
        return listitemsRepo.findAll();
    }

    @GetMapping("/listitem/getlistitem/{id}")
    public Item_List_Pair get_listitem(@PathVariable long id){
        return listitemsRepo.findById(id).get();
    }

    @PostMapping("/listitem/addlistitem")
    public void save_listitem(@RequestBody Item_List_Pair item){
        listitemsRepo.save(item);
    }

    @PutMapping("/listitem/updateitem/{id}")
    public void update_listitem(@PathVariable long id,@RequestBody Item_List_Pair item){
        Item_List_Pair new_item = listitemsRepo.findById(id).get();
        new_item.setQty_of_item(item.getQty_of_item());
        new_item.setId_of_item(item.getId_of_item());
        new_item.setId_of_list(item.getId_of_list());
        listitemsRepo.save(new_item);
    }

    @DeleteMapping("/listitem/deleteitem/{id}")
    public void delete_listitem(@PathVariable long id){
        Item_List_Pair del_item = listitemsRepo.findById(id).get();
        listitemsRepo.delete(del_item);
    }
}
