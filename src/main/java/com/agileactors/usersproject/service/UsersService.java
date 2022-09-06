package com.agileactors.usersproject.service;

import com.agileactors.usersproject.exceptions.InvalidPostBodyException;
import com.agileactors.usersproject.exceptions.MailAlreadyExistsException;
import com.agileactors.usersproject.exceptions.WrongMailFormatException;
import com.agileactors.usersproject.models.User;
import com.agileactors.usersproject.repositories.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        BeanUtils.copyProperties(user, existingUser, "userId");
        return this.save(existingUser);
    }
    private void validateAge(User user){
        if(user.getAge()==0) throw new InvalidPostBodyException("");
    }
    private void validateMail(User user){
        if(user.getMail()!=null && (!user.getMail().contains("@") || !user.getMail().contains(".com")
                || user.getMail().charAt(0)<'a' || user.getMail().charAt(0)>'z'))
            throw new WrongMailFormatException(user.getMail());
        if(!usersRepository.findAll().stream()
                .filter(existingUser->existingUser.getMail().equals(user.getMail()) && existingUser.getUserId() !=user.getUserId())
                .findAny().isEmpty())
        throw new MailAlreadyExistsException(user.getMail());
    }
    public User save(User user) {
        validateAge(user);
        validateMail(user);
        return usersRepository.save(user);

    }

}
