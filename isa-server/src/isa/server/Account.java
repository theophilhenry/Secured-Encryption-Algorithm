/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class Account extends MyConnection {

    public String InfoSaldo(String username) {
        this.getConnect();
        String balance = "0";
        try {
            // Find Account Number from Username
            String sql = "SELECT * FROM accounts WHERE username = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                balance = result.getString("balance");
                // Decrypt?
            }
        } catch (Exception e) {
            System.out.println("Error Insert : " + e);
            return balance;
        }
        return balance;
    }

}
