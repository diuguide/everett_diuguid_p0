package com.revature.project_0.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankActions {
    static double balance;
    static LocalDateTime timeStamp = LocalDateTime.now();
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    static String formattedDate;

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

    public static double getBalance(int userId) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from project0.accounts where user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                balance = rs.getDouble("balance");
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
        System.out.println("$" + depositAmt + " deposited to account");
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
            System.out.println("$" + withdrawAmt + " withdrawn from account!");
            return newBalance;
        }
        System.out.println("Insufficient funds!");
        return getBalance(userId);

    }

    static public void logTransaction(int userId, String type, double amount) {

        formattedDate = timeStamp.format(dateFormat);

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "insert into project0.transactions (user_id, amount, trans_type, transaction_date) values(?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(2, amount);
            pstmt.setInt(1, userId);
            pstmt.setString(3, type);
            pstmt.setObject(4, formattedDate);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
