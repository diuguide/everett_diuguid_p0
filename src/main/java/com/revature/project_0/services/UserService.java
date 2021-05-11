package com.revature.project_0.services;

import com.revature.project_0.Exceptions.InvalidRequestException;
import com.revature.project_0.Exceptions.UsernameNotAvailable;
import com.revature.project_0.dao.UserDAO;
import com.revature.project_0.database.ConnectionFactory;
import com.revature.project_0.models.AppUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public void register(AppUser newUser) throws InvalidRequestException, UsernameNotAvailable {
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Please enter valid user information");
        }
        if (!userDao.isUsernameAvailable(newUser.getUsername())) {
            throw new UsernameNotAvailable(("Username taken, please choose another!"));
        }
        userDao.saveUser(newUser);
    }

    public AppUser authenticate(String username, String password) {
        AppUser user = userDao.authenticateUser(username, password);
        return user;
    }

    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().length() > 20) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 255) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getLastName().length() > 25) return false;
        return true;
    }

    public boolean checkForAccount(int userId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.accounts where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
