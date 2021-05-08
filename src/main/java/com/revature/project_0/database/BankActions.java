package com.revature.project_0.database;

import com.revature.project_0.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankActions {
    static int balance;
    public static void createAccount(int userId) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO project0.accounts(user_id, type, balance) VALUES(?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, "Checking");
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getBalance(int userId) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from project0.accounts where user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                balance = rs.getInt("balance");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;

    }

    public static double deposit(double depositAmt, int userId){
        double newBalance = getBalance(userId) + depositAmt;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "update project0.accounts set balance=? where user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, newBalance);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newBalance;
    }
}
