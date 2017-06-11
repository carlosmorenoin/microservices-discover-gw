package org.carlosmorenoin.microsexample.core;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test(expected = IllegalArgumentException.class)
    public void addNullUserError() throws Exception {
        doThrow(IllegalArgumentException.class).when(userRepository).save(Matchers.isNull(User.class));
        userService.addUser(null);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User("1", "The user");
        userService.addUser(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void retrieveUserNotFound() throws Exception {
        when(userRepository.findOne(anyString())).thenReturn(null);
        User user = userService.retrieveUser("1");
        assertNull(user);
    }

    @Test
    public void retrieveUser() throws Exception {
        User testUser = new User("1", "The user");
        when(userRepository.findOne("1")).thenReturn(testUser);
        User user = userService.retrieveUser("1");
        assertEquals(testUser, user);
    }

    @Test
    public void retrieveUsers() throws Exception {
    }

}
