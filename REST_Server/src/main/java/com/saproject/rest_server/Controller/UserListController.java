package com.saproject.rest_server.Controller;

import com.saproject.rest_server.Model.User_List;
import com.saproject.rest_server.REPO.UserListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserListController {
    @Autowired
    private UserListRepo userlistRepo;

    @GetMapping("/userlist/getalluserlist")
    public List<User_List> get_all_userlist(){
        return userlistRepo.findAll();
    }

    @GetMapping("/userlist/getuserlist/{id}")
    public User_List get_userlist(@PathVariable long id){
        return userlistRepo.findById(id).get();
    }

    @PostMapping("/userlist/adduserlist")
    public void save_userlist(@RequestBody User_List item){
        userlistRepo.save(item);
    }

    @PutMapping("/userlist/updateuserlist/{id}")
    public void update_userlist(@PathVariable long id,@RequestBody User_List item){
        User_List new_item = userlistRepo.findById(id).get();
        new_item.setFor_sale(item.getFor_sale());
        new_item.setMy_cart(item.getMy_cart());
        new_item.setRecommended_items(item.getRecommended_items());
        new_item.setPast_purchases(item.getPast_purchases());
        new_item.setUser_id(item.getUser_id());
        userlistRepo.save(new_item);
    }

    @DeleteMapping("/userlist/deleteuserlist/{id}")
    public void delete_userlist(@PathVariable long id){
        User_List del_item = userlistRepo.findById(id).get();
        userlistRepo.delete(del_item);
    }
}
