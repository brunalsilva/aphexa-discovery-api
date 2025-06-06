package com.aphexa.discovery.service;

import com.aphexa.discovery.model.User;
import com.aphexa.discovery.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser_Success() {
        // given
        User user = new User(null, "someUsername", "someemail@gmail.com", "somepassword", "someone", null);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        // when
        User created = userService.create(user);

        // then
        assertNotNull(created.getId());
        assertEquals("someUsername", created.getUsername());
        verify(userRepository, times(1)).save(user);
    }

}
