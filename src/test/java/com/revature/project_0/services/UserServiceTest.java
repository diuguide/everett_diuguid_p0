package com.revature.project_0.services;

import com.revature.project_0.Exceptions.InvalidRequestException;
import com.revature.project_0.Exceptions.UsernameNotAvailable;
import com.revature.project_0.dao.UserDAO;
import com.revature.project_0.models.AppUser;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO mockUserDao = mock(UserDAO.class);

    @Before
    public void setUp() {
        sut = new UserService(mockUserDao);
    }

    @After
    public void tearDown() {
        sut = null;
    }

    @Test
    public void test_registerWithValidUser() {
        AppUser user = new AppUser("fake", "fake", "fake", "fake");
        AppUser expectedResult = new AppUser("fake", "fake", "fake", "fake");
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
        when(mockUserDao.saveUser(any())).thenReturn(expectedResult);

        sut.register(user);

        assertEquals( expectedResult.getFirstName(), user.getFirstName());
    }

    @Test (expected = InvalidRequestException.class)
    public void test_registerWithInvalidUser() {
        AppUser user = new AppUser();

        sut.register(user);
    }

    @Test (expected = UsernameNotAvailable.class)
    public void test_registerWithExsistingUser() {
        AppUser user = new AppUser("test", "test", "ediuguid", "password");
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(false);

        sut.register(user);
    }

    @Test
    public void test_checkForAccountAccountExists() {
        int userId = 1;

        sut.checkForAccount(userId);

        assertTrue(true);

    }

    @Test
    public void test_checkForAccountAccountDoesNotExist() {
        int userId = 100;

        sut.checkForAccount(userId);

        assertFalse(false);
    }





}


