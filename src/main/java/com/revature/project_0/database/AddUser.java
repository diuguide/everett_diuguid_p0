package com.revature.project_0.database;

import com.revature.project_0.models.AppUser;

import java.sql.*;

public class AddUser {

     private String firstName;
     private String lastName;
     private String username;
     private String password;

    public AddUser(AppUser newUser) {
        this.firstName = newUser.getFirstName();
        this.lastName = newUser.getLastName();
        this.username = newUser.getUsername();
        this.password = newUser.getPassword();
    }

    public void insertRow() {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "INSERT INTO users(firstname, lastname, username, password) VALUES (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, password);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
