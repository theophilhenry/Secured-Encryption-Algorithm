/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.server;

import java.sql.*;

public class User extends MyConnection {

    public String Login(String username, String password) {
        this.getConnect();
        try {
            // Decrypt it here
            String hashedPassword = "--later--"; // Untuk pengecekan

            String sql = "SELECT * FROM accounts WHERE username = ? and password = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next() == true) {
                return "TRUE"; //If Username and Password does not match (It cannot be iterated)
            }
        } catch (Exception e) {
            System.out.println("Error Login : " + e);
        }
        return "FALSE";
    }

    public String Register(String name, String age, String phoneNumber, String username, String password, String pin) {
        this.getConnect();

        //Generate Account Number Here
        String accountNumber = "0";

        try {
            String sql = "INSERT INTO persons (name, age, phoneNumber) values (?, ?, ?)";
            PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, age);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }

            sql = "INSERT INTO accounts (account_number, username, password, pin, idperson, public_key) values (?, ?, ?, ?, ?, ?)"; // Without Public Key and Balance
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, pin);
            preparedStatement.setInt(5, generatedKey);
            preparedStatement.setString(6, "0101");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Insert : " + e);
            return "FALSE";
        }
        return "TRUE";
    }
}
