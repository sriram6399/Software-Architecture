package com.saproject.rest_server.Controller;

import com.saproject.rest_server.Model.Items;
import com.saproject.rest_server.REPO.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemsController {
    @Autowired
    private ItemRepo itemsRepo;

    @GetMapping("/")
    public String get_page(){
    return "Hi Welcome to backend webserver";
    }

    @GetMapping("/items/getallitems")
    public List<Items> get_all_items(){
        return itemsRepo.findAll();
    }

    @GetMapping("/items/getitem/{id}")
    public Items get_item(@PathVariable long id){
        return itemsRepo.findById(id).get();
    }

    @PostMapping("/items/additem")
    public void save_item(@RequestBody Items item){
        itemsRepo.save(item);
    }

    @PutMapping("/items/updateitem/{id}")
    public void update_item(@PathVariable long id,@RequestBody Items item){
            Items new_item = itemsRepo.findById(id).get();
            new_item.setTitle(item.getTitle());
            new_item.setDescription(item.getDescription());
            new_item.setPrice(item.getPrice());
            new_item.setQty_avail(item.getQty_avail());
            new_item.setQty_sold(item.getQty_sold());
            new_item.setSeller_id(item.getSeller_id());
            itemsRepo.save(new_item);
    }

    @DeleteMapping("/items/deleteitem/{id}")
    public void delete_item(@PathVariable long id){
        Items del_item = itemsRepo.findById(id).get();
        itemsRepo.delete(del_item);
    }
}
