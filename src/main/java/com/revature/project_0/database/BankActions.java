package com.revature.project_0.database;

import com.revature.project_0.models.AppUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankActions {

    private double balance;
    private LocalDateTime timeStamp = LocalDateTime.now();
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private String formattedDate;
    private int userId;

    public BankActions(AppUser user) {
        this.userId = user.getUserId();
    }

    public void createAccount() {

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

    public double getBalance() {

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

    public double deposit(double depositAmt){

        double newBalance = getBalance() + depositAmt;

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
        logTransaction("Deposit", depositAmt);
        System.out.println("$" + depositAmt + " deposited to account");
        return newBalance;
    }

    public double withdraw(double withdrawAmt){

        double newBalance = getBalance() - withdrawAmt;

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
            logTransaction("Withdraw", withdrawAmt);
            System.out.println("$" + withdrawAmt + " withdrawn from account!");
            return newBalance;
        }
        System.out.println("Insufficient funds!");
        return getBalance();

    }

    public void logTransaction(String type, double amount) {

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
