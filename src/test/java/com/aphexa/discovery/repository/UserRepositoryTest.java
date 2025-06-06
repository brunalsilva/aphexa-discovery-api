package com.aphexa.discovery.repository;

import com.aphexa.discovery.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUserByUsername() {
        // given
        User user = User.builder()
                .username("some-username")
                .email("somemail@example.com")
                .password("password")
                .build();

        userRepository.save(user);

        // when
        Optional<User> result = userRepository.findByUsername("some-username");

        // then
        assertTrue(result.isPresent());
        assertEquals("some-username", result.get().getUsername());
        assertEquals("somemail@example.com", result.get().getEmail());
    }

    @Test
    void shouldReturnEmptyWhenUserNotFound() {
        Optional<User> result = userRepository.findByUsername("notauser");

        assertTrue(result.isEmpty());
    }
}
