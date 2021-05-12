package com.revature.project_0.services;

import com.revature.project_0.database.ConnectionFactory;
import com.revature.project_0.util.LinkedList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {

    public LinkedList getTransactions(int userId) {
        LinkedList<Transaction> transactionList = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            String sql = "select * from project0.transactions where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
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
}
