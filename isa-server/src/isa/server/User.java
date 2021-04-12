/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.server;

import java.sql.*;

public class User extends MyConnection {

    String keyAES = "Ha^#Sdb$18Lg1+_~J=_is8g$21a12";
    String publicKeyClient = "MIIBKzANBgkqhkiG9w0BAQEFAAOCARgAMIIBEwKCAQoSBpIc3Y8I2cnHdZhnx1WDSJX4/eV9lUf6keNEEPVM2U0ylkosam+CDqua6I53YQdjnjsQVbzEtz+2XGRR6mOg8ckUKH471y6DnnWEZ9n4ni8Y8Y9lQXfq0kbSULTwGMQRQusn8Ac6iHAXo//m7K/pSGBhjIEIdycFZIQEjBRr7Gbtuo0n0AD/rBu7Fuct6/sf2KyOvHA+wD3/KZqxAA7DyWMkMJ6HBh9lbavczxi3j61iRqvLAM5O6Ia2WzLI3YofwsuCx1d/yTH0pcYePkHVL2+Viws64PgPe6N/rPwaeNh94F7ipGEEhJEMHerSgqThf08d1pWu7QNrRPkWCJ6zjaljoLTsKeUKgwIDAQAB";
    String privateKeyServer = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";
    String publicKeyServer = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";

    public String Login(String username, String password) {
        this.getConnect();
        try {
            // Decrypt it here
            String salt = "--later--"; // Untuk pengecekan
            String pass = "";
            
           

            String sqlSalt = "SELECT salt,password FROM accounts WHERE username = ?";
            PreparedStatement preparedStatement = (PreparedStatement) connect.prepareStatement(sqlSalt);
            preparedStatement.setString(1, username);

            ResultSet result = preparedStatement.executeQuery();
            
            
            

            if (result.next() == false) {
                return "FALSE";
            }
            
            salt = result.getString("salt");
            pass = result.getString("password");
            
            password = Security.MakeHash(password, salt);
           
            
            pass = Security.Decrypt3(pass, keyAES, salt, privateKeyServer);
            
            
            if (!(pass.equals(password))) {
                return "FALSE";
            }
        } catch (Exception e) {
            System.out.println("Error Login : " + e);
        }
        return "TRUE";
    }

    public String generateAccountNumber() {
        String output = "0000000000000001";
        this.getConnect();
        try {
            String sql = "SELECT MAX(account_number) as account_number FROM accounts LIMIT 1";
            Statement stmt = (Statement) connect.createStatement();
            ResultSet result = stmt.executeQuery(sql);

            if (result.next()) {
                int maxNumber = Integer.parseInt(result.getString("account_number"));
                maxNumber += 1;
                output = String.format("%" + 16 + "s", String.valueOf(maxNumber)).replace(' ', '0');
            }

        } catch (Exception e) {
            System.out.println("Generate Account Number Error");
        }
        return output;
    }

    public String Register(String name, String age, String phoneNumber, String username, String password, String pin, String salt) {
        this.getConnect();

        //Generate Account Number Here
        String accountNumber = generateAccountNumber();

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
            
            
            
            sql = "INSERT INTO accounts (account_number, username, password, pin, idperson, public_key,salt,balance) values (?, ?, ?, ?, ?, ?,?,?)"; // Without Public Key and Balance
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, accountNumber);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, pin);
            preparedStatement.setInt(5, generatedKey);
            preparedStatement.setString(6, publicKeyClient);
            preparedStatement.setString(7, salt);
            String balance = "BQjdH92SGs5DHaGF1CSD6kY/F5PaqOg6wDwwRsvWxuty+et78YuVRk4gpUbhRHJm8MaJLQ4uiVkgL7YmqN4SR578YMY4DX2wlIuY5u1ThN/3jr5LjTGIuUDxn0zE8kc55wMz4QiqGOTXEZ1uvDk9tXuzGrOw7zU01G8F9v6jlzc=";
            preparedStatement.setString(8,balance);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error Insert : " + e);
            return "FALSE";
        }
        return "TRUE\nYour Account Number is : " + accountNumber;
    }
}
