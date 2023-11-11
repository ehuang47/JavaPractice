package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.dao.UserDao;
import com.example.d5_mvc_jsp_quiz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;
    private static final User EMPTY_USER = new User(-1, "","");
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(int id) { return userDao.getUserById(id);}
    public void createNewUser(User user) { userDao.createNewUser(user);}
    public User getUserByUsername(String username) {
        return userDao.getAllUsers().stream()
                .filter(a -> a.getUsername().equals(username))
                .findFirst()
                .orElse(EMPTY_USER);
    }

    public List<User> getAllUsers(){ return userDao.getAllUsers();}

}
