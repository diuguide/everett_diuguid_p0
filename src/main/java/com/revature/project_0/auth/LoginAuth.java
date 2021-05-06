package com.revature.project_0.auth;

import com.revature.project_0.database.ConnectionFactory;
import com.revature.project_0.models.AppUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAuth {

    private String username;
    private String password;

    public LoginAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser authenticateUser() {

        AppUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                user = new AppUser();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
