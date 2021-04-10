/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.client;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FormRegister extends javax.swing.JFrame {

    String keyAES = "Ha^#Sdb$18Lg1+_~J=_is8g$21a12";
    String publicKeyServer = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
    String privateKeyClient = "MIIE5gIBADANBgkqhkiG9w0BAQEFAASCBNAwggTMAgEAAoIBChIGkhzdjwjZycd1mGfHVYNIlfj95X2VR/qR40QQ9UzZTTKWSixqb4IOq5rojndhB2OeOxBVvMS3P7ZcZFHqY6DxyRQofjvXLoOedYRn2fieLxjxj2VBd+rSRtJQtPAYxBFC6yfwBzqIcBej/+bsr+lIYGGMgQh3JwVkhASMFGvsZu26jSfQAP+sG7sW5y3r+x/YrI68cD7APf8pmrEADsPJYyQwnocGH2Vtq9zPGLePrWJGq8sAzk7ohrZbMsjdih/Cy4LHV3/JMfSlxh4+QdUvb5WLCzrg+A97o3+s/Bp42H3gXuKkYQSEkQwd6tKCpOF/Tx3Wla7tA2tE+RYInrONqWOgtOwp5QqDAgMBAAECggEKEASixt/kAVt3wbWwT1iFlvpjuzX0P1ru8a2+pXfkY/wea7u+AiY7HPnoaOoL3+3rYQz1M5jc/HpTNbx4sYp2KklyBGXPd2HKxc+EYrreqd33wFS4tqQs6hEeOjrZ7xoy8Zvj2EOmSM+BvKBSAWWHFOjqLQVsjJdFp7cg0eGhLB6RgOPsF08SfGhhIftkYBQ+R4NGhwB5n2BTqW1b15vOBzkZWk9Mjc8egx0smUAw9FMPkI73o9QB9YyqafdmTBkmc/mzoVvshjF/PG5/Y+rhXAyPEuwPaUgWtivfl6yzQyjg/M55Q9iwxG6eRiFnp48mKp/Zbx4610R9QkCxb2v/Vtu5bMWqnWDIrOECgYV2cH47yCH4rzTjgzu0oBKIN8Tr4vGkqfP8Z3haOnoy5t9bOlHE0836qm3VLs4Ls5FdFXATkcmFqtlEC8Q5pgTvwjVGvhXvFdhhU5gh/LcxbUHXV1/6d65J8L3vIzUIugHJC5D6co0t6FEmJCjyUcMMNhPt0UN1g8+0r2rHg4FPSLZ/HoHLAoGFJvYhBLqxz6N4CpOjZK8MOxxDvE5rpUCdHsbjB7Kj/g51G+iFpJKTpgK/e180LAXvuNldpYtWGH6pwOLLrfePX9WitveBbZaDDz0jYbXctblp+/34nLmMP8lLPFDaCVnXZbWgIf1HOiZIU8hoaoJUrpDrU+0ajZEtsBD+9vCLNPwfQFqjKQKBhQMZcIQ5+YGbfCPUBFT2t2VvKC0QFEi91c+uGX0q+JWqav/lJ8yhyksb+KHShrvRqCUqcXNhpkdkMClSsYDcslZf26sNQF+wT6hEWr/Q8C5P8KAk/jL8vMfcj+vZHQMscu2C6vlS1BT2dmEsIxIsR5FjPORFTKGfoGjXClFMRK8BdR0gfD8CgYUfFLAIbsrUQqrLfTWWZ1JRgV3Dxad3/9CXsb+A75CFZpEjv12FiOzCPWT1jWA4UlAHXVeobTw0dC8FUad8U4YLxIh2f4G/o5dPu80S8JpuWchjnxoKJLbuV61uI22ckhAT8k0ZcEy25b1DIRezB994FuG0ZOyOzKbu0tAivIfAr/RaSTPpAoGFELDjlCwkhRfWFP0+icBDJ4iqrJ900uOuH3wQmy7Yfmily/+C50FDQ+ZWnMVcu33i0xvU7g103rLkB4rWpzckUACf2x1sVJ+d3g75/E5lfTu8QaB2q5niiENWMfI52YhBPboDT9G+7OTPuSIE9T4BW326/6gQwFJUxYv1CYBGficKqyhROw==";
    Socket socket;

    BufferedReader in;
    DataOutputStream out;

    public FormRegister() {
        initComponents();
    }

    public FormRegister(Socket socket) {
        initComponents();

        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new DataOutputStream(this.socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error Constructor : " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        txtPin = new javax.swing.JPasswordField();
        btnClose = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Password");

        jLabel2.setText("Username");

        jLabel1.setText("REGISTER");

        jLabel5.setText("Pin");

        btnRegister.setText("SUBMIT");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel6.setText("Name");

        jLabel7.setText("Age");

        jLabel8.setText("Phone Number");

        jLabel9.setText("Account Details");

        jLabel10.setText("User Details");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(262, 262, 262))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnClose)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRegister))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegister)
                    .addComponent(btnClose))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        String name = txtName.getText();
        String age = txtAge.getText();
        String phoneNumber = txtPhoneNumber.getText();
        
        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());
        String pin = String.valueOf(txtPin.getPassword());
        
        
        
        name = Security.Encrypt2(name, keyAES, publicKeyServer);
        age = Security.Encrypt2(age, keyAES, publicKeyServer);
        phoneNumber = Security.Encrypt2(phoneNumber, keyAES, publicKeyServer);
        
        String salt = Security.MakeSalt();
        
        username = Security.Encrypt3(username, keyAES, salt, publicKeyServer);
        password = Security.Encrypt3(password, keyAES, salt, publicKeyServer);
        pin = Security.Encrypt3(pin, keyAES, salt, publicKeyServer);
        
        

        //Encrypt and Hash the username and password here
        String command = "REGISTER"
                + "[_]" + name
                + "[_]" + age
                + "[_]" + phoneNumber
                + "[_]" + username
                + "[_]" + password
                + "[_]" + pin
                + "[_]" + salt;

        try {
            out.writeBytes(command + "\n");

            String output = in.readLine();
            output = Security.Decrypt2(output, keyAES, privateKeyClient);
            JOptionPane.showMessageDialog(rootPane, output);
        } catch (Exception e) {
            System.out.println("Error Register : " + e);
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

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
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JPasswordField txtPin;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
