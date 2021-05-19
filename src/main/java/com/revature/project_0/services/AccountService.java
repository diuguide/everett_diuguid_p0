package com.revature.project_0.services;

import com.revature.project_0.Exceptions.InvalidInputException;
import com.revature.project_0.util.ConnectionFactory;
import com.revature.project_0.models.Transaction;
import com.revature.project_0.util.LinkedList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Account service page.  Stores methods for gathering all transaction history
 *
 * @author Everett Diuguid
 */
public class AccountService {

    // Get all transactions from db based on userId store them to LinkedList for use on transactionHistory screen
    public LinkedList getTransactions(int userId) {
        LinkedList<Transaction> transactionList = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.transactions where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HashMap<String, Object> resultMap = new HashMap<>();
                java.util.LinkedList<HashMap> taskList = new java.util.LinkedList<>();


                Transaction transaction = new Transaction();
                transaction.setTransaction_id(rs.getInt("transaction_id"));
                transaction.setAmount(rs.getInt("amount"));
                transaction.setType(rs.getString("trans_type"));
                transaction.setTransaction_date(rs.getString("transaction_date"));
                transactionList.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;

    }

    public boolean checkInput(double input) throws InvalidInputException {
        if (input < 0) {
            throw new InvalidInputException("Input values must be greater than zero!");
        }
        return true;
    }
}
