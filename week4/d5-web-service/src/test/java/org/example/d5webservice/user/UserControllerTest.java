package org.example.d5webservice.user;

import org.example.d5webservice.DataResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        // Common stubs
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(transaction);
    }

    @Test
    @DisplayName("GET /users/{id} - Success")
    void getUser_WhenUserExists_ReturnsUser() throws Exception {
        // Given
        User user = User.builder()
          .id(1L)
          .name("John Doe")
          .username("johndoe")
          .email("john@example.com")
          .build();

        when(session.get(User.class, 1L)).thenReturn(user);

        // When/Then
        mockMvc.perform(get("/users/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.success").value(true))
          .andExpect(jsonPath("$.data.id").value(1))
          .andExpect(jsonPath("$.data.name").value("John Doe"));
    }

    @Test
    @DisplayName("GET /users/{id} - Not Found")
    void getUser_WhenUserNotExists_ReturnsNotFound() throws Exception {
        // Given
        when(session.get(User.class, 999L)).thenReturn(null);

        // When/Then
        mockMvc.perform(get("/users/999"))
          .andExpect(status().isOk())  // Your controller returns 200 with error message
          .andExpect(jsonPath("$.success").value(false))
          .andExpect(jsonPath("$.message").value("User not found with ID: 999"));
    }

    @Test
    @DisplayName("POST /users - Success")
    void createUser_WithValidData_ReturnsCreated() throws Exception {
        // Given
        String userJson = """
            {
                "name": "Jane Doe",
                "username": "janedoe",
                "email": "jane@example.com"
            }
            """;

        // When/Then
        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.success").value(true))
          .andExpect(jsonPath("$.message").value("User saved successfully"));

        // Verify user was saved
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(session).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals("Jane Doe", savedUser.getName());
        assertEquals("janedoe", savedUser.getUsername());
    }

    @Test
    @DisplayName("POST /users - Invalid Data")
    void createUser_WithInvalidData_ReturnsBadRequest() throws Exception {
        // Given
        String invalidUserJson = """
            {
                "name": " ",
                "username": "a",  // too short
                "email": "not-an-email"
            }
            """;

        // When/Then
        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidUserJson))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.success").doesNotExist());
    }

    @Test
    @DisplayName("DELETE /users/{id} - Success")
    void deleteUser_WhenUserExists_DeletesUser() throws Exception {
        // Given
        User user = User.builder().id(1L).build();
        when(session.load(User.class, 1L)).thenReturn(user);

        // When/Then
        mockMvc.perform(delete("/users/1"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.success").value(true))
          .andExpect(jsonPath("$.message").value("User deleted successfully"));

        verify(session).delete(user);
    }
}

//@ExtendWith(MockitoExtension.class)
//class UserControllerTest {
//
//    @Mock
//    private SessionFactory sessionFactory;
//
//    @Mock
//    private Session session;
//
//    @Mock
//    private Transaction transaction;
//
//    @Mock
//    @SuppressWarnings("rawtypes")
//    private Query query;
//
//
//    @InjectMocks
//    private UserController userController;
//
//    @BeforeEach
//    void setUp() {
//        lenient().when(sessionFactory.getCurrentSession()).thenReturn(session);
//        lenient().when(session.beginTransaction()).thenReturn(transaction);
//    }
//
//    @Test
//    void findAll_WhenUsersExist_ReturnsUsersList() {
//        // Arrange
//        User user1 = User.builder().id(1L).name("John Doe").username("johndoe").email("john@example.com").build();
//        User user2 = User.builder().id(2L).name("Jane Doe").username("janedoe").email("jane@example.com").build();
//        List<User> users = Arrays.asList(user1, user2);
//
//        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
//        when(query.getResultList()).thenReturn(users);
//
//        // Act
//        DataResponse<List<UserDto>> response = userController.findAll();
//
//        // Assert
//        assertTrue(response.success());
//        assertNotNull(response.data());
//        assertEquals(2, response.data().size());
//        assertEquals("John Doe", response.data().get(0).name());
//        assertEquals("Jane Doe", response.data().get(1).name());
//    }
//
//    @Test
//    void findById_WhenUserExists_ReturnsUser() {
//        // Arrange
//        Long userId = 1L;
//        User user = User.builder()
//                .id(userId)
//                .name("John Doe")
//                .username("johndoe")
//                .email("john@example.com")
//                .build();
//
//        when(session.get(User.class, userId)).thenReturn(user);
//
//        // Act
//        DataResponse<UserDto> response = userController.findById(userId);
//
//// Assert
//        assertTrue(response.success());
//        assertNotNull(response.data());
//        assertEquals("John Doe", response.data().name());
//        assertEquals("johndoe", response.data().username());
//        assertEquals("john@example.com", response.data().email());
//    }
//
//    @Test
//    void findById_WhenUserDoesNotExist_ReturnsError() {
//        // Arrange
//        Long userId = 999L;
//        when(session.get(User.class, userId)).thenReturn(null);
//
//        // Act
//        DataResponse<UserDto> response = userController.findById(userId);
//
//        // Assert
//        assertFalse(response.success());
//        assertEquals("User not found with ID: " + userId, response.message());
//    }
//
//    @Test
//    void create_WithValidUser_ReturnsSuccess() {
//        // Arrange
//        UserCreateDto userDto = new UserCreateDto("John Doe", "johndoe", "john@example.com");
//        BindingResult bindingResult = new BeanPropertyBindingResult(userDto, "userDto");
//
//        // Act
//        DataResponse<?> response = userController.create(userDto, bindingResult);
//
//        // Assert
//        assertTrue(response.success());
//        assertEquals("User saved successfully", response.message());
//        verify(session).save(any(User.class));
//    }
//
//    @Test
//    void create_WithInvalidUser_ReturnsValidationError() {
//        // Arrange
//        UserCreateDto userDto = new UserCreateDto("", "", "");
//        BindingResult bindingResult = new BeanPropertyBindingResult(userDto, "userDto");
//        bindingResult.addError(new FieldError("userDto", "name", "Name is required"));
//        bindingResult.addError(new FieldError("userDto", "username", "Username is required"));
//        bindingResult.addError(new FieldError("userDto", "email", "Email is required"));
//
//        // Act
//        DataResponse<?> response = userController.create(userDto, bindingResult);
//
//        // Assert
//        assertFalse(response.success());
//        assertTrue(response.message().contains("is required"));
//        verify(session, never()).save(any(User.class));
//    }
//
//    @Test
//    void update_WithValidUser_ReturnsSuccess() {
//        // Arrange
//        Long userId = 1L;
//        UserDto userDto = new UserDto(userId, "John Updated", "johnupdated", "john.updated@example.com");
//        BindingResult bindingResult = new BeanPropertyBindingResult(userDto, "userDto");
//
//        User existingUser = User.builder()
//                .id(userId)
//                .name("John Doe")
//                .username("johndoe")
//                .email("john@example.com")
//                .build();
//
//        when(session.load(User.class, userId)).thenReturn(existingUser);
//
//        // Act
//        DataResponse<?> response = userController.update(userDto, bindingResult);
//
//        // Assert
//        assertTrue(response.success());
//        assertEquals("User updated successfully", response.message());
//        assertEquals("John Updated", existingUser.getName());
//        assertEquals("johnupdated", existingUser.getUsername());
//        assertEquals("john.updated@example.com", existingUser.getEmail());
//    }
//
//    @Test
//    void update_WithInvalidUser_ReturnsValidationError() {
//        // Arrange
//        UserDto userDto = new UserDto(1L, "", "", "");
//        BindingResult bindingResult = new BeanPropertyBindingResult(userDto, "userDto");
//        bindingResult.addError(new FieldError("userDto", "name", "Name is required"));
//
//        // Act
//        DataResponse<?> response = userController.update(userDto, bindingResult);
//
//        // Assert
//        assertFalse(response.success());
//        assertEquals("Name is required", response.message());
//        verify(session, never()).update(any(User.class));
//    }
//
//    @Test
//    void delete_WhenUserExists_ReturnsSuccess() {
//        // Arrange
//        Long userId = 1L;
//        User user = User.builder()
//                .id(userId)
//                .name("John Doe")
//                .username("johndoe")
//                .email("john@example.com")
//                .build();
//
//        when(session.load(User.class, userId)).thenReturn(user);
//
//        // Act
//        DataResponse<?> response = userController.delete(userId);
//
//        // Assert
//        assertTrue(response.success());
//        assertEquals("User deleted successfully", response.message());
//        verify(session).delete(user);
//    }
//
//    @Test
//    void delete_WhenUserDoesNotExist_ThrowsException() {
//        // Arrange
//        Long userId = 999L;
//        when(session.load(User.class, userId)).thenThrow(new EntityNotFoundException("User not found"));
//
//        // Act
//        DataResponse<?> response = userController.delete(userId);
//
//        // Assert
//        assertFalse(response.success());
//        assertEquals("User not found", response.message());
//    }
//}
