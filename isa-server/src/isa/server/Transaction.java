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

    String keyAES = "Ha^#Sdb$18Lg1+_~J=_is8g$21a12";
    String publicKeyClient = "MIIBKzANBgkqhkiG9w0BAQEFAAOCARgAMIIBEwKCAQoSBpIc3Y8I2cnHdZhnx1WDSJX4/eV9lUf6keNEEPVM2U0ylkosam+CDqua6I53YQdjnjsQVbzEtz+2XGRR6mOg8ckUKH471y6DnnWEZ9n4ni8Y8Y9lQXfq0kbSULTwGMQRQusn8Ac6iHAXo//m7K/pSGBhjIEIdycFZIQEjBRr7Gbtuo0n0AD/rBu7Fuct6/sf2KyOvHA+wD3/KZqxAA7DyWMkMJ6HBh9lbavczxi3j61iRqvLAM5O6Ia2WzLI3YofwsuCx1d/yTH0pcYePkHVL2+Viws64PgPe6N/rPwaeNh94F7ipGEEhJEMHerSgqThf08d1pWu7QNrRPkWCJ6zjaljoLTsKeUKgwIDAQAB";
    String privateKeyServer = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";
    String publicKeyServer = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";

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

            //account_number = Security.Decrypt2(account_number, keyAES, privateKeyServer);
            myBalance = Security.Decrypt2(myBalance, keyAES, privateKeyServer);

            String myBalanceNow = String.valueOf(Integer.parseInt(myBalance) - Integer.parseInt(nominal));
            

            sql = "SELECT * FROM accounts WHERE account_number = ?";
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, destination);
            result = preparedStatement.executeQuery();
            if (result.next()) {
                destBalance = result.getString("balance");
            }

            sql = "SELECT MAX(idtransaction) as id FROM transactions";
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            
            int angka = 0;
            result = preparedStatement.executeQuery();
            if (result.next()) {
                angka = Integer.valueOf(result.getString("id"));
                angka += 1;
            }
           
            String idTransac = String.valueOf(angka);

            destBalance = Security.Decrypt2(destBalance, keyAES, privateKeyServer);

            String destBalanceNow = String.valueOf(Integer.parseInt(destBalance) + Integer.parseInt(nominal));
            

            //account_number = Security.EncryptAES(account_number, keyAES);
            //destination = Security.EncryptAES(destination, keyAES);
            nominal = Security.Encrypt2(nominal, keyAES, publicKeyServer);
            news = Security.Encrypt2(news, keyAES, publicKeyServer);
            myBalanceNow = Security.Encrypt2(myBalanceNow, keyAES, publicKeyServer);
            destBalanceNow = Security.Encrypt2(destBalanceNow, keyAES, publicKeyServer);

            sql = "INSERT INTO transactions (account_number, dest_account_number, nominal, news, timestamp, idtransaction) values (?, ?, ?, ?, ?, ?)";
            preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
            preparedStatement.setString(1, account_number);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, nominal);
            preparedStatement.setString(4, news);
            preparedStatement.setString(5, timestamp);
            preparedStatement.setString(6, idTransac);
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
