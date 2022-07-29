package com.agileactors.usersproject.service;

import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.repositories.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UsersService {

    private UsersRepository usersRepository;
    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll(){
        return usersRepository.findAll();
    }
    public boolean existsById(Long id){
        return usersRepository.existsById(id);
    }

    public User getOne(Long id){
        return usersRepository.getOne(id);
    }
    public User saveAndFlush( User user){
        return usersRepository.saveAndFlush(user);
    }

    public void deleteById(Long id){
        usersRepository.deleteById(id);
    }

    public User update(Long id, User user) {
        User existingUser = getOne(id);
        BeanUtils.copyProperties(user, existingUser, "user_id");
        return saveAndFlush(existingUser);
    }


    public User save(User user) {
        return usersRepository.save(user);
    }
}
