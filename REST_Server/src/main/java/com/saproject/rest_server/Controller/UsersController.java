package com.saproject.rest_server.Controller;


import com.saproject.rest_server.Model.Users;
import com.saproject.rest_server.REPO.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/users/getallusers")
    public List<Users> get_all_users(){
        return userRepo.findAll();
    }

    @GetMapping("/users/getuser/{id}")
    public Users get_user(@PathVariable long id){
        return userRepo.findById(id).get();
    }

    @PostMapping("/users/adduser")
    public void save_user(@RequestBody Users user){
        userRepo.save(user);
    }

    @PutMapping("/users/updateuser/{id}")
    public void update_user(@PathVariable long id,@RequestBody Users list){
        Users new_user = userRepo.findById(id).get();
        new_user.setUser_name(list.getUser_name());
        new_user.setPassword(list.getPassword());

        userRepo.save(new_user);
    }

    @DeleteMapping("/users/deleteuser/{id}")
    public void delete_user(@PathVariable long id){
        Users del_user = userRepo.findById(id).get();
        userRepo.delete(del_user);
    }
}
