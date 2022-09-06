package com.agileactors.usersproject.controllers;

import com.agileactors.usersproject.exceptions.InvalidIdException;
import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
        return usersService.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<?> get(@PathVariable Long id){
        if(usersService.existsById(id)){
            User user = usersService.getOne(id);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(user.getUserId())))
                        .body(user);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else throw new InvalidIdException();
    }



    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user){
        User newUser;
            newUser = usersService.save(user);

        try{

            return ResponseEntity.created(new URI(String.valueOf(newUser.getUserId())))
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
        else throw new InvalidIdException();
    }


    @RequestMapping(value="{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user){
        if(usersService.existsById(id)){
        User updatedUser = usersService.update(id, user);
            try {
                return ResponseEntity.ok().location(new URI(String.valueOf(updatedUser.getUserId())))
                        .body(updatedUser);
            } catch (URISyntaxException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else throw new InvalidIdException();
    }


}
