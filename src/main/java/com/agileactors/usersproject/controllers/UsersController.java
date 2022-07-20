package com.agileactors.usersproject.controllers;

import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.repositories.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public List<User> list(){
        return usersRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id){
        return usersRepository.getOne(id);
    }

    @PostMapping
    public User create(@RequestBody User user){
        return usersRepository.saveAndFlush(user);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        usersRepository.deleteById(id);
    }

    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user){
        User existingUser = usersRepository.getById(id);
        BeanUtils.copyProperties(user,existingUser, "user_id");
        return usersRepository.saveAndFlush(existingUser);
    }

}
