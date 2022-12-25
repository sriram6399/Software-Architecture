package com.saproject.rest_server.Controller;

import com.saproject.rest_server.Model.Lists;
import com.saproject.rest_server.REPO.ListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListsController {
    @Autowired
    private ListRepo listsRepo;


    @GetMapping("/lists/getalllists")
    public List<Lists> get_all_lists(){
        return listsRepo.findAll();
    }

    @GetMapping("/lists/getlist/{id}")
    public Lists get_list(@PathVariable long id){
        return listsRepo.findById(id).get();
    }

    @PostMapping("/lists/addlist")
    public void save_list(@RequestBody Lists item){
        listsRepo.save(item);
    }

    @PutMapping("/lists/updatelist/{id}")
    public void update_list(@PathVariable long id,@RequestBody Lists list){
        Lists new_list = listsRepo.findById(id).get();
        new_list.setTitle(list.getTitle());
        new_list.setDescription(list.getDescription());
        new_list.setList_type(list.getList_type());
        new_list.setOwner_id(list.getOwner_id());
        listsRepo.save(new_list);
    }

    @DeleteMapping("/lists/deletelist/{id}")
    public void delete_list(@PathVariable long id){
        Lists del_list = listsRepo.findById(id).get();
        listsRepo.delete(del_list);
    }
}
