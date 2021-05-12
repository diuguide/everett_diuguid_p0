package com.revature.project_0.dao;

import com.revature.project_0.util.ConnectionFactory;
import com.revature.project_0.models.AppUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The user data access object.  Contains db queries used for authenticating an AppUser object for validation
 *
 * @author Everett Diuguid
 */

public class UserDAO {

    //Query db with username and password
    public AppUser findUserByUsernameAndPassword(String username, String password) {
        AppUser user = null;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from project0.users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                user = new AppUser();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUserId(rs.getInt("user_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Save user object to db
    public AppUser saveUser(AppUser newUser) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO project0.users(first_name, last_name, username, password) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getFirstName());
            pstmt.setString(2, newUser.getLastName());
            pstmt.setString(3, newUser.getUsername());
            pstmt.setString(4, newUser.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newUser;

    }

    // Check if username exists in db
    public boolean isUsernameAvailable(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

}
