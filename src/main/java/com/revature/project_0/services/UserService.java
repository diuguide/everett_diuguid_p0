package com.revature.project_0.services;

import com.revature.project_0.Exceptions.InvalidRequestException;
import com.revature.project_0.Exceptions.UsernameNotAvailable;
import com.revature.project_0.dao.UserDAO;
import com.revature.project_0.util.ConnectionFactory;
import com.revature.project_0.models.AppUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * UserService class for validating user input and authenticating usercredentials
 *
 * @author Everett Diuguid
 * @author Wezley Singleton
 */

public class UserService {

    private UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    // Register new user with AppUser object, check userinput
    public AppUser register(AppUser newUser) throws InvalidRequestException, UsernameNotAvailable {
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Please enter valid user information");
        }
        if (!userDao.isUsernameAvailable(newUser.getUsername())) {
            throw new UsernameNotAvailable(("Username taken, please choose another!"));
        }
        return userDao.saveUser(newUser);
    }

    // Check user credentials
    public AppUser authenticate(String username, String password) {
        AppUser user = userDao.findUserByUsernameAndPassword(username, password);
        return user;
    }

    // Validate user input
    public boolean isUserValid(AppUser user) {
        if (user == null) return false;
        if (user.getUsername() == null || user.getUsername().trim().isEmpty() || user.getUsername().length() > 20) return false;
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() > 255) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty() || user.getFirstName().length() > 25 || !isValidName(user.getFirstName())) return false;
        if (user.getLastName() == null || user.getLastName().trim().isEmpty() || user.getLastName().length() > 25 || !isValidName(user.getLastName())) return false;
        return true;
    }

    public boolean isValidName(String name) {
        return Pattern.compile("^[a-zA-Z]{1,25}$").matcher(name).matches();
    }

    // Check if user has an account
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
