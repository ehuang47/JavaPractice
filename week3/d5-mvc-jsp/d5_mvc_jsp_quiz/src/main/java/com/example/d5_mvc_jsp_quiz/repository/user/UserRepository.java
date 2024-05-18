package com.example.d5_mvc_jsp_quiz.repository.user;

import com.example.d5_mvc_jsp_quiz.domain.User;
import com.example.d5_mvc_jsp_quiz.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements ObjectRepository<User> {
    private static final List<User> users;
    private static final User EMPTY_USER = new User(-1, "", "", "",0,"","");

    private JdbcTemplate jdbcTemplate;
    private UserRepositoryRowMapper rowMapper;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, UserRepositoryRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    static {
        users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(new User(i, "user" + i, "pass" + i, String.format("user%s@t.com", i),0,"user"+i,"lastName"+i));
        }
        users.add(new User(999, "admin", "pass", "admin@t.com",1,"admin","istrator"));
    }

    @Override
    public void save(User user) {

    }

    @Override
    public User findById(int id) {
        Optional<User> user = users.stream()
          .filter(a -> a.getId() == id)
          .findFirst();

        return user.orElse(EMPTY_USER);
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
