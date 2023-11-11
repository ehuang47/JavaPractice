package com.example.d5_mvc_jsp_quiz.dao;

import com.example.d5_mvc_jsp_quiz.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private static final List<User> users;
    private static final User EMPTY_USER = new User(-1, "", "");

    JdbcTemplate jdbcTemplate;
    UserDaoRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserDaoRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    static {
        users = new ArrayList<>();
        users.add(new User(1, "user1", "pass1"));
        users.add(new User(2, "user2", "pass2"));
        users.add(new User(3, "user3", "pass3"));
    }

    public User getUserById(int id) {
        return users.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(EMPTY_USER);
    }

    public void createNewUser(User user) { users.add(user);}
    public List<User> getAllUsers() { return users;}
}
