package org.example.d5webservice.user;

import org.example.d5webservice.DataResponse;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

  @PostMapping("")
  @Transactional
  public DataResponse<?> create(@Valid @RequestBody UserCreateDto userDto, BindingResult result) {
    if (result.hasErrors()){
      return DataResponse.failure(result.getFieldError().getDefaultMessage());
    }
    try {
      User user = User.builder()
        .name(userDto.name())
        .username(userDto.username())
        .email(userDto.email())
        .build();
      sessionFactory.getCurrentSession().save(user);
      return DataResponse.successWithMessage("User saved successfully");
    } catch (Exception e) {
      return DataResponse.failure(e.getMessage());
    }
  }

  @PatchMapping("")
  @Transactional
  public DataResponse<?> update(@Valid @RequestBody UserDto userDto, BindingResult result) {
    if (result.hasErrors()){
      return DataResponse.failure(result.getFieldError().getDefaultMessage());
    }
    try {
      User user = sessionFactory.getCurrentSession().load(User.class, userDto.id());
      user.setUsername(userDto.username());
      user.setName(userDto.name());
      user.setEmail(userDto.email());
      return DataResponse.successWithMessage("User updated successfully");
    } catch (Exception e) {
      return DataResponse.failure(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  @Transactional
  public DataResponse<?> delete(@PathVariable Long id) {
    try {
      User user = sessionFactory.getCurrentSession().load(User.class, id);
      sessionFactory.getCurrentSession().delete(user);
      return DataResponse.successWithMessage("User deleted successfully");
    } catch (Exception e) {
      return DataResponse.failure(e.getMessage());
    }
  }
}
