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

class UserTest {
    private Validator validator;
    private User validUser;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        
        validUser = User.builder()
                .id(1L)
                .name("John Doe")
                .username("johndoe")
                .email("john.doe@example.com")
                .build();
    }

    @Test
    void whenAllFieldsValid_thenNoViolations() {
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertTrue(violations.isEmpty(), "No violations should be present for a valid user");
    }

    @Test
    void whenNameIsNull_thenOneViolation() {
        validUser.setName(null);
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(1, violations.size(), "Should have 1 violation for null name");
        assertEquals("Name is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenNameIsBlank_thenOneViolation() {
        validUser.setName(" ");
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(1, violations.size(), "Should have 1 violation for blank name");
        assertEquals("Name is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenNameExceedsMaxLength_thenOneViolation() {
        validUser.setName("a".repeat(101)); // 101 characters
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(1, violations.size(), "Should have 1 violation for long name");
        assertEquals("Name must be less than 100 characters", violations.iterator().next().getMessage());
    }

    @Test
    void whenUsernameIsNull_thenOneViolation() {
        validUser.setUsername(null);
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(1, violations.size(), "Should have 1 violation for null username");
        assertEquals("Username is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenUsernameIsBlank_thenOneViolation() {
        validUser.setUsername(" ");
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(2, violations.size(), "Should have 2 violations for blank username");
        List<String> violationsList = violations.stream()
          .map(ConstraintViolation::getMessage)
          .toList();
        assertTrue(violationsList.contains("Username is required"));
        assertTrue(violationsList.contains("Username must be between 3 and 50 characters"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "a1"}) // Less than min length
    void whenUsernameTooShort_thenOneViolation(String username) {
        validUser.setUsername(username);
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertFalse(violations.isEmpty(), "Should have violations for short username");
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Username must be between 3 and 50 characters")));
    }

    @Test
    void whenUsernameExceedsMaxLength_thenOneViolation() {
        validUser.setUsername("a".repeat(51)); // 51 characters
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(1, violations.size(), "Should have 1 violation for long username");
        assertEquals("Username must be between 3 and 50 characters", violations.iterator().next().getMessage());
    }

    @Test
    void whenEmailIsNull_thenOneViolation() {
        validUser.setEmail(null);
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(1, violations.size(), "Should have 1 violation for null email");
        assertEquals("Email is required", violations.iterator().next().getMessage());
    }

    @Test
    void whenEmailIsBlank_thenOneViolation() {
        validUser.setEmail(" ");
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(2, violations.size(), "Should have 2 violations for blank email");
        List<String> violationsList = violations.stream()
          .map(ConstraintViolation::getMessage)
          .toList();
        assertTrue(violationsList.contains("Email is required"));
        assertTrue(violationsList.contains("Email should be valid"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid-email", "@missing-username.com", "user@.com"})
    void whenEmailInvalidFormat_thenOneViolation(String invalidEmail) {
        validUser.setEmail(invalidEmail);
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertFalse(violations.isEmpty(), "Should have violations for invalid email: " + invalidEmail);
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().contains("Email should be valid")));
    }

    @Test
    void whenEmailExceedsMaxLength_thenOneViolation() {
        validUser.setEmail("a".repeat(64) + "@" + "a".repeat(40) + ".com"); // 101+ characters
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(1, violations.size(), "Should have 1 violation for long email");
        assertEquals("Email must be less than 100 characters", violations.iterator().next().getMessage());
    }
}
