package com.agileactors.usersproject.controllers;

import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.service.UsersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
        return usersService.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        if(usersService.existsById(id)){
            User user = usersService.getOne(id);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(user.getUser_id())))
                        .body(user);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else return ResponseEntity.notFound().build();
    }

    /*

    @GetMapping
    @RequestMapping("{id}")
    public User get(@PathVariable Long id){
        return usersService.getOne(id);}


    @PostMapping
    public User create(@RequestBody User user){
        return usersService.saveAndFlush(user);
    }*/
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User newUser =  usersService.save(user);
        try {
            return ResponseEntity.created(new URI(String.valueOf(newUser.getUser_id())))
                    .body(newUser);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(usersService.existsById(id)){
            usersService.deleteById(id);
            return ResponseEntity.ok().build();}
        else return ResponseEntity.notFound().build();
    }

    /*
    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        usersService.deleteById(id);
    }
     */
    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user){
        if(usersService.existsById(id)){
        User updatedUser = usersService.update(id, user);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(updatedUser.getUser_id())))
                        .body(updatedUser);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else return ResponseEntity.notFound().build();
    }


/*
    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public User update(@PathVariable Long id, @RequestBody User user){
        return usersService.update(id, user);
    }

 */

}
