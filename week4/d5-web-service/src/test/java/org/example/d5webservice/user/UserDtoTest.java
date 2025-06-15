package org.example.d5webservice.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    private Validator validator;
    private UserDto validUser;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        
        validUser = new UserDto(1L, "John Doe", "johndoe", "john.doe@example.com");
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<UserDto>> violations = validator.validate(validUser);
        assertTrue(violations.isEmpty(), "No violations should be present for a valid user");
    }

    @Test
    void whenIdIsNull_thenOneViolation() {
        UserDto user = new UserDto(null, "John Doe", "johndoe", "john.doe@example.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(1, violations.size(), "Should have 1 violation for null ID");
        assertEquals("ID is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenNameIsNull_thenOneViolation() {
        UserDto user = new UserDto(1L, null, "johndoe", "john.doe@example.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(1, violations.size(), "Should have 1 violation for null name");
        assertEquals("Name is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenNameIsBlank_thenOneViolation() {
        UserDto user = new UserDto(1L, " ", "johndoe", "john.doe@example.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(1, violations.size(), "Should have 1 violation for blank name");
        assertEquals("Name is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenNameExceedsMaxLength_thenOneViolation() {
        String longName = "a".repeat(101); // 101 characters
        UserDto user = new UserDto(1L, longName, "johndoe", "john.doe@example.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(1, violations.size(), "Should have 1 violation for long name");
        assertEquals("Name must be less than 100 characters", violations.iterator().next().getMessage());
    }

    @Test
    void whenUsernameIsNull_thenOneViolation() {
        UserDto user = new UserDto(1L, "John Doe", null, "john.doe@example.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(1, violations.size(), "Should have 1 violation for null username");
        assertEquals("Username is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenUsernameIsBlank_thenTwoViolations() {
        UserDto user = new UserDto(1L, "John Doe", " ", "john.doe@example.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(2, violations.size(), "Should have 2 violations for blank username");
        List<String> violationMessages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        assertTrue(violationMessages.contains("Username is required"));
        assertTrue(violationMessages.contains("Username must be between 3 and 50 characters"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab"}) // Less than min length
    void whenUsernameTooShort_thenOneViolation(String username) {
        UserDto user = new UserDto(1L, "John Doe", username, "john.doe@example.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Should have violations for short username");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Username must be between 3 and 50 characters")));
    }

    @Test
    void whenUsernameExceedsMaxLength_thenOneViolation() {
        String longUsername = "a".repeat(51); // 51 characters
        UserDto user = new UserDto(1L, "John Doe", longUsername, "john.doe@example.com");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(1, violations.size(), "Should have 1 violation for long username");
        assertEquals("Username must be between 3 and 50 characters", violations.iterator().next().getMessage());
    }

    @Test
    void whenEmailIsNull_thenOneViolation() {
        UserDto user = new UserDto(1L, "John Doe", "johndoe", null);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(1, violations.size(), "Should have 1 violation for null email");
        assertEquals("Email is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenEmailIsBlank_thenTwoViolations() {
        UserDto user = new UserDto(1L, "John Doe", "johndoe", " ");
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(2, violations.size(), "Should have 2 violations for blank email");
        List<String> violationMessages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .toList();
        assertTrue(violationMessages.contains("Email is required"));
        assertTrue(violationMessages.contains("Email should be valid"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid-email", "@missing-username.com", "user@.com"})
    void whenEmailInvalidFormat_thenOneViolation(String invalidEmail) {
        UserDto user = new UserDto(1L, "John Doe", "johndoe", invalidEmail);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Should have violations for invalid email: " + invalidEmail);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Email should be valid")));
    }

    @Test
    void whenEmailExceedsMaxLength_thenOneViolation() {
        String longEmail = "a".repeat(64) + "@" + "a".repeat(40) + ".com"; // 101+ characters
        UserDto user = new UserDto(1L, "John Doe", "johndoe", longEmail);
        Set<ConstraintViolation<UserDto>> violations = validator.validate(user);
        assertEquals(1, violations.size(), "Should have 1 violation for long email");
        assertEquals("Email must be less than 100 characters", violations.iterator().next().getMessage());
    }
}
