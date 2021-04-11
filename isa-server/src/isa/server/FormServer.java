/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.server;

import java.io.*;
import java.net.*;

/**
 *
 * @author User
 */
public class FormServer extends javax.swing.JFrame implements Runnable {

    Thread thread;
    String keyAES = "Ha^#Sdb$18Lg1+_~J=_is8g$21a12";
    String privateKeyServer = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";
    String publicKeyClient = "MIIBKzANBgkqhkiG9w0BAQEFAAOCARgAMIIBEwKCAQoSBpIc3Y8I2cnHdZhnx1WDSJX4/eV9lUf6keNEEPVM2U0ylkosam+CDqua6I53YQdjnjsQVbzEtz+2XGRR6mOg8ckUKH471y6DnnWEZ9n4ni8Y8Y9lQXfq0kbSULTwGMQRQusn8Ac6iHAXo//m7K/pSGBhjIEIdycFZIQEjBRr7Gbtuo0n0AD/rBu7Fuct6/sf2KyOvHA+wD3/KZqxAA7DyWMkMJ6HBh9lbavczxi3j61iRqvLAM5O6Ia2WzLI3YofwsuCx1d/yTH0pcYePkHVL2+Viws64PgPe6N/rPwaeNh94F7ipGEEhJEMHerSgqThf08d1pWu7QNrRPkWCJ6zjaljoLTsKeUKgwIDAQAB";
    String saltDefault = "hzVZ09jPyqbBUGPW6cPtSw==";
    String publicKeyServer = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
    
    Socket socket;
    ServerSocket serverSocket;

    BufferedReader in;
    DataOutputStream out;

    public FormServer() {
        initComponents();
        try {

            serverSocket = new ServerSocket(6000);

            System.out.println("Waiting for Client...");
            socket = serverSocket.accept();
            System.out.println("Client Accepted!");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            this.start();
        } catch (Exception e) {
            System.out.println("Error Form Server : " + e);
        }
    }

    @Override
    public void run() {
        String username;
        String password;
        String name;
        String pin;
        String age;
        String phoneNumber;
        String destination;
        String nominal;
        String news;
        String account_number;
        int balance;
        String timestamp;
        
        String salt;
        String hashUsername;
        String hashPassword;
        String hashPin;
        String hashAge;
        String hashName;
        String hashPhone;
        
        String testUsername;
        String testPassword;
        String testPin;
        String testAge;
        String testName;
        String testPhone;

        while (true) {
            try {
                String rawInput = in.readLine();
                txtConversation.append(rawInput + "\n");

                String[] clientInput = rawInput.split("\\[_\\]");
                String respond = "";
                
                System.out.println("77 " + clientInput.length);
                
                User __user = new User();
                Transaction __transaction = new Transaction();
                Account __account = new Account();
                System.out.println("82");
                switch (clientInput[0]) {
                    case "LOGIN":
                        
                        // Input into Hash
                        username = clientInput[1];
                        password = clientInput[2];
                        testUsername = Security.DecryptAES(username, keyAES);
                        testPassword = Security.Decrypt2(password, keyAES, privateKeyServer);
                        
                        System.out.println("username : " + testUsername);
                        System.out.println("pass : " + testPassword);
                        System.out.println("+++++++++++++++++++++++++");
                        System.out.println("username : " + username);
                        
                        testUsername = Security.MakeHash(testUsername, saltDefault);
                        testPassword = Security.MakeHash(testPassword, saltDefault);
                        
                        
                        System.out.println(testUsername + "  " + testPassword);
                        
                        // Hash
                        hashPassword = clientInput[3];
                        hashUsername = clientInput[4];
                        hashUsername = Security.Decrypt3(hashUsername, keyAES, saltDefault, privateKeyServer);
                        hashPassword = Security.Decrypt3(hashPassword, keyAES, saltDefault, privateKeyServer);
                        
                        
                        System.out.println(hashUsername + " " + hashPassword);
                        System.out.println("107");
                        if((testUsername + testPassword).equals(hashUsername+hashPassword))
                        {
                            //password = Security.Encrypt3AfterHASH(hashPassword, keyAES, publicKeyServer);
                            password = Security.Decrypt2(password, keyAES, privateKeyServer);
                            System.out.println("Password Akhir : " + password);
                            respond = __user.Login(username, password); //return TRUE or FALSE
                           
                        }
                        else
                        {
                            respond = "Sorry Bambank";
                        }
                        
                        
                        
                        break;
                    case "REGISTER":
                        System.out.println("107");
                        name = clientInput[1];
                        age = clientInput[2];
                       
                        phoneNumber = clientInput[3];
                        username = clientInput[4];
                        password = clientInput[5];
                        pin = clientInput[6];
                        salt = clientInput[7];
                        
                        
                        
                                              
                        testUsername = Security.DecryptAES(username, keyAES);
                        testPassword = Security.Decrypt2(password, keyAES, privateKeyServer);
                        String hasilPasswordDecrypt = testPassword;
                        testPin = Security.Decrypt2(pin, keyAES, privateKeyServer);
                        salt = Security.Decrypt2(salt, keyAES, privateKeyServer);
                        System.out.println("150 : " + salt);
                        testAge = Security.Decrypt2(age, keyAES, privateKeyServer);
                        testPhone = Security.Decrypt2(phoneNumber, keyAES, privateKeyServer);
                        testName = Security.Decrypt2(name, keyAES, privateKeyServer);
                        
                        System.out.println("username : " + testUsername);
                        System.out.println("pass : " + testPassword);
                        System.out.println("pin : " + testPin);
                        System.out.println("age : " + testAge);
                        System.out.println("phone : " + testPhone);
                        System.out.println("name : " + testName);
                        System.out.println("+++++++++++++++++++++++++");
                        System.out.println("username : " + username);
                        System.out.println("pass : " + password);
                        System.out.println("pin : " + pin);
                        System.out.println("age : " + age);
                        System.out.println("phone : " + phoneNumber);
                        System.out.println("name : " + name);
                        
                        testUsername = Security.MakeHash(testUsername,  saltDefault);
                        testPassword = Security.MakeHash(testPassword,  saltDefault);
                        testPin = Security.MakeHash(testPin, saltDefault);
                        testName = Security.MakeHash(testName,  saltDefault);
                        testAge = Security.MakeHash(testAge,  saltDefault);
                        testPhone = Security.MakeHash(testPhone, saltDefault);
                        
                        
                        
                        hashUsername = clientInput[8];
                        hashPassword = clientInput[9];
                        hashPin = clientInput[10];
                        hashAge = clientInput[11];
                        hashName = clientInput[12];
                        hashPhone = clientInput[13];
                        
                        hashUsername = Security.Decrypt3(hashUsername, keyAES, saltDefault, privateKeyServer);
                        hashPassword = Security.Decrypt3(hashPassword, keyAES, saltDefault, privateKeyServer);
                        hashPin = Security.Decrypt3(hashPin, keyAES, saltDefault, privateKeyServer);
                        hashAge = Security.Decrypt3(hashAge, keyAES, saltDefault, privateKeyServer);
                        hashName = Security.Decrypt3(hashName, keyAES, saltDefault, privateKeyServer);
                        hashPhone = Security.Decrypt3(hashPhone, keyAES, saltDefault, privateKeyServer);
                        
                        
                        String allHash = hashUsername + hashPassword + hashPin ;
                        String allTest = testUsername + testPassword + testPin ;
                        
                        
                        if((allTest).equals(allHash))
                        {
                            System.out.println("127");
                            password = Security.Encrypt3(hasilPasswordDecrypt, keyAES, salt, publicKeyServer);
                            pin = Security.Encrypt3AfterHASH(testPin, keyAES, publicKeyServer);
                            respond = __user.Register(name, age, phoneNumber, username, password, pin,salt); //return TRUE or FALSE
                        }
                        else
                        {
                            respond = "Sorry Bambank";
                        }
                        
                        
                        
                        
                        break;
                    case "INFOSALDO":
                        System.out.println("199");
                        
                        username = clientInput[1];
                        hashUsername = clientInput[2];
                        
                        testUsername = Security.Decrypt2(username, keyAES, privateKeyServer);
                        System.out.println("Username Test 1: " + testUsername);
                        String usernameDecrypt2 = testUsername;
                        testUsername = Security.MakeHash(testUsername, saltDefault);
                        hashUsername = Security.Decrypt3(hashUsername, keyAES, saltDefault, privateKeyServer);
                                                                   
                        if (testUsername.equals(hashUsername))
                        {
                            System.out.println("Username 1: " + username);
                            System.out.println("Username Test 2: " + testUsername);
                            //QXm68ly2QwS2RYlpE2zVEw==
                            username = Security.EncryptAES(usernameDecrypt2, keyAES);
                            System.out.println("Username 3: " + username);
                            respond = __account.InfoSaldo(username);
                        }
                        else
                        {
                            respond = "Sorry Bambank";
                        }
                        
                        break;
                    case "TRANSFER":
                        username = clientInput[1];
                        destination = clientInput[2];
                        nominal = clientInput[3];
                        news = clientInput[4];
                        timestamp = clientInput[5];
                        
                        username = Security.Decrypt2(username, keyAES, privateKeyServer);
                        destination = Security.Decrypt2(destination, keyAES, privateKeyServer);
                        news = Security.Decrypt2(news, keyAES, privateKeyServer);
                        nominal = Security.Decrypt2(nominal, keyAES, privateKeyServer);
                        
                        
                        respond = __transaction.Transfer(username, destination, nominal, news, timestamp);
                        break;
                    case "CHECKSALDO":
                        respond = "Try to Check Saldo";
                        break;
                    case "EXIT":
                        respond = "Menutup server";
                        break;
                    default:
                        break;
                }
                
                
                
                System.out.println(respond);
                
                respond = Security.Encrypt2(respond, keyAES, publicKeyClient);
                
                out.writeBytes(respond + "\n");

            } catch (Exception e) {
                System.out.println("Error Multithread : " + e);
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this, "Thread GetChat");
            thread.start();
        }
    }

    public void getChat() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtConversation = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtConversation.setColumns(20);
        txtConversation.setRows(5);
        jScrollPane1.setViewportView(txtConversation);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SERVER");

        btnExit.setText("EXIT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(176, 176, 176))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExit)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtConversation;
    // End of variables declaration//GEN-END:variables

}
