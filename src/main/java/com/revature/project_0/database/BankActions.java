package com.revature.project_0.database;

import com.revature.project_0.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BankActions {
    static int balance;
    static LocalDateTime timeStamp = LocalDateTime.now();
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
        logTransaction(userId, "Deposit", depositAmt);
        return newBalance;
    }

    public static double withdraw(double withdrawAmt, int userId){

        double newBalance = getBalance(userId) - withdrawAmt;

        if (newBalance >= 0) {
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
            logTransaction(userId, "Withdraw", withdrawAmt);
            return newBalance;
        }
        System.out.println("Insufficient funds!");
        return getBalance(userId);

    }

    static public void logTransaction(int userId, String type, double amount) {

        // NEED TO ADD DATE TO TRANSACTION

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into project0.transactions (user_id, amount, trans_type, transaction_date) values(?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(2, amount);
            pstmt.setInt(1, userId);
            pstmt.setString(3, type);
            pstmt.setObject(4, timeStamp);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
