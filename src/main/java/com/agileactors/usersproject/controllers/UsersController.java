package com.agileactors.usersproject.controllers;

import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/users")
public class UsersController {
    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    private UsersService usersService;

    @GetMapping
    public List<User> list(){
        return usersService.getUsersRepository().findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id){
        return usersService.getUsersRepository().getOne(id);
    }

    @PostMapping
    public User create(@RequestBody User user){
        return usersService.getUsersRepository().saveAndFlush(user);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        usersService.getUsersRepository().deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user){
        User existingUser = usersService.getUsersRepository().getById(id);
        BeanUtils.copyProperties(user,existingUser, "user_id");
        return usersService.getUsersRepository().saveAndFlush(existingUser);
    }

}
