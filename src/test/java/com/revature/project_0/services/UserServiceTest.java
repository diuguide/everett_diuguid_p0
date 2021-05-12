package com.revature.project_0.services;

import com.revature.project_0.dao.UserDAO;
import com.revature.project_0.models.AppUser;
import org.junit.*;
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
        when(mockUserDao.isUsernameAvailable(anyString())).thenReturn(true);
    }





}

class UserDAOStub extends UserDAO {
    @Override
    public AppUser findUserByUsernameAndPassword(String username, String password) {
        return super.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public void saveUser(AppUser newUser) {
        super.saveUser(newUser);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return super.isUsernameAvailable(username);
    }
}
