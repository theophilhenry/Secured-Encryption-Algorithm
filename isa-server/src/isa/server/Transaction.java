/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Transaction extends MyConnection {

    public String Transfer(String username, String destination, String nominal, String news, String timestamp) {
        this.getConnect();
        String account_number = "000000000000";
        String myBalance = "0";

        String destBalance = "0";

        try {
            // Find Account Number from Username
            String sql = "SELECT * FROM accounts WHERE username = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                account_number = result.getString("account_number");
                myBalance = result.getString("balance");
            }
            String myBalanceNow = String.valueOf(Integer.parseInt(myBalance) - Integer.parseInt(nominal));
            System.out.println(myBalanceNow);

            sql = "SELECT * FROM accounts WHERE account_number = ?";
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, destination);
            result = preparedStatement.executeQuery();
            if (result.next()) {
                destBalance = result.getString("balance");
            }
            String destBalanceNow = String.valueOf(Integer.parseInt(destBalance) + Integer.parseInt(nominal));
            System.out.println(destBalanceNow);

            sql = "INSERT INTO transactions (account_number, dest_account_number, nominal, news, timestamp, idtransaction) values (?, ?, ?, ?, ?, ?)";
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, account_number);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, nominal);
            preparedStatement.setString(4, news);
            preparedStatement.setString(5, timestamp);
            preparedStatement.setString(6, "lastsummer");
            preparedStatement.executeUpdate();

            sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, myBalanceNow);
            preparedStatement.setString(2, account_number);
            preparedStatement.executeUpdate();            

            sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, destBalanceNow);
            preparedStatement.setString(2, destination);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Insert : " + e);
            return "FALSE";
        }
        return "TRUE";
    }
}
