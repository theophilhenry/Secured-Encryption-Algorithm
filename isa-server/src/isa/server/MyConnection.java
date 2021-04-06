/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.server;

import java.sql.*;

/**
 *
 * @author User
 */
public class MyConnection {

    protected Connection connect;

    public Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connect = DriverManager.getConnection("jdbc:mysql://localhost/uts_isa", "root", "mysql");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connect;
    }
}
