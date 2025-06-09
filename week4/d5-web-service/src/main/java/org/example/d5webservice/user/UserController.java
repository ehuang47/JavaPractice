package org.example.d5webservice.user;

import org.example.d5webservice.DataResponse;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  private final SessionFactory sessionFactory;
  public UserController(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @GetMapping("/all")
  @Transactional(readOnly = true)
  public DataResponse<List<UserDto>> findAll() {
    try {
      String jpql = "SELECT u FROM User u";
      List<User> users = sessionFactory.getCurrentSession()
        .createQuery(jpql, User.class)
        .getResultList();
      List<UserDto> userDtoList = users.stream()
        .map(user -> new UserDto(
          user.getId(),
          user.getName(),
          user.getUsername(),
          user.getEmail()))
        .toList();
      return DataResponse.successWithData(userDtoList);
    } catch (Exception e) {
      return DataResponse.failure(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  @Transactional(readOnly = true)
  public DataResponse<UserDto> findById(@PathVariable Long id) {
    try {
    User user = sessionFactory.getCurrentSession().get(User.class, id);
      if (user == null) {
        return DataResponse.failure("User not found with ID: " + id);
      }
    UserDto returnedUser = new UserDto(
      user.getId(),
      user.getName(),
      user.getUsername(),
      user.getEmail()
    );
    return DataResponse.successWithData(returnedUser);
    } catch (Exception e) {
      return DataResponse.failure(e.getMessage());
    }
  }
}
