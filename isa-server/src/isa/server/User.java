/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.server;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class User extends MyConnection {

    public String Login(String username, String password) {
        this.getConnect();
        try {
            String hashedPassword = "--later--"; // SALAHH, harusnya yang enkripsi itu user

            String sql = "SELECT * FROM users WHERE username = ? and password = ?";
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

    public String Register(String username, String password, String name, String pin) {
        this.getConnect();
        
        
        //Generate Account Number Here
        String accountNumber = "0";
        
        try {
            String sql = "INSERT INTO users (username, password, name, pin, account_number) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, pin);
            preparedStatement.setString(5, accountNumber);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Insert : " + e);
            return "FALSE";
        }
        return "TRUE";
    }
}
