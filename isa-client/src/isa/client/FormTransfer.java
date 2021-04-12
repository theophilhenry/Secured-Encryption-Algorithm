/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isa.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FormTransfer extends javax.swing.JFrame {

     String saltDefault = "hzVZ09jPyqbBUGPW6cPtSw==";
    String keyAES = "Ha^#Sdb$18Lg1+_~J=_is8g$21a12";
    String publicKeyServer = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
    String privateKeyClient = "MIIE5gIBADANBgkqhkiG9w0BAQEFAASCBNAwggTMAgEAAoIBChIGkhzdjwjZycd1mGfHVYNIlfj95X2VR/qR40QQ9UzZTTKWSixqb4IOq5rojndhB2OeOxBVvMS3P7ZcZFHqY6DxyRQofjvXLoOedYRn2fieLxjxj2VBd+rSRtJQtPAYxBFC6yfwBzqIcBej/+bsr+lIYGGMgQh3JwVkhASMFGvsZu26jSfQAP+sG7sW5y3r+x/YrI68cD7APf8pmrEADsPJYyQwnocGH2Vtq9zPGLePrWJGq8sAzk7ohrZbMsjdih/Cy4LHV3/JMfSlxh4+QdUvb5WLCzrg+A97o3+s/Bp42H3gXuKkYQSEkQwd6tKCpOF/Tx3Wla7tA2tE+RYInrONqWOgtOwp5QqDAgMBAAECggEKEASixt/kAVt3wbWwT1iFlvpjuzX0P1ru8a2+pXfkY/wea7u+AiY7HPnoaOoL3+3rYQz1M5jc/HpTNbx4sYp2KklyBGXPd2HKxc+EYrreqd33wFS4tqQs6hEeOjrZ7xoy8Zvj2EOmSM+BvKBSAWWHFOjqLQVsjJdFp7cg0eGhLB6RgOPsF08SfGhhIftkYBQ+R4NGhwB5n2BTqW1b15vOBzkZWk9Mjc8egx0smUAw9FMPkI73o9QB9YyqafdmTBkmc/mzoVvshjF/PG5/Y+rhXAyPEuwPaUgWtivfl6yzQyjg/M55Q9iwxG6eRiFnp48mKp/Zbx4610R9QkCxb2v/Vtu5bMWqnWDIrOECgYV2cH47yCH4rzTjgzu0oBKIN8Tr4vGkqfP8Z3haOnoy5t9bOlHE0836qm3VLs4Ls5FdFXATkcmFqtlEC8Q5pgTvwjVGvhXvFdhhU5gh/LcxbUHXV1/6d65J8L3vIzUIugHJC5D6co0t6FEmJCjyUcMMNhPt0UN1g8+0r2rHg4FPSLZ/HoHLAoGFJvYhBLqxz6N4CpOjZK8MOxxDvE5rpUCdHsbjB7Kj/g51G+iFpJKTpgK/e180LAXvuNldpYtWGH6pwOLLrfePX9WitveBbZaDDz0jYbXctblp+/34nLmMP8lLPFDaCVnXZbWgIf1HOiZIU8hoaoJUrpDrU+0ajZEtsBD+9vCLNPwfQFqjKQKBhQMZcIQ5+YGbfCPUBFT2t2VvKC0QFEi91c+uGX0q+JWqav/lJ8yhyksb+KHShrvRqCUqcXNhpkdkMClSsYDcslZf26sNQF+wT6hEWr/Q8C5P8KAk/jL8vMfcj+vZHQMscu2C6vlS1BT2dmEsIxIsR5FjPORFTKGfoGjXClFMRK8BdR0gfD8CgYUfFLAIbsrUQqrLfTWWZ1JRgV3Dxad3/9CXsb+A75CFZpEjv12FiOzCPWT1jWA4UlAHXVeobTw0dC8FUad8U4YLxIh2f4G/o5dPu80S8JpuWchjnxoKJLbuV61uI22ckhAT8k0ZcEy25b1DIRezB994FuG0ZOyOzKbu0tAivIfAr/RaSTPpAoGFELDjlCwkhRfWFP0+icBDJ4iqrJ900uOuH3wQmy7Yfmily/+C50FDQ+ZWnMVcu33i0xvU7g103rLkB4rWpzckUACf2x1sVJ+d3g75/E5lfTu8QaB2q5niiENWMfI52YhBPboDT9G+7OTPuSIE9T4BW326/6gQwFJUxYv1CYBGficKqyhROw==";
    
    
    Socket socket;

    BufferedReader in;
    DataOutputStream out;
    
    String username;
    
    public FormTransfer() {
        initComponents();
    }

    public FormTransfer(Socket socket, String loginName) {
        initComponents();
        
        this.username = loginName;

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
        txtNominal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        txtDestinationAccountNumber = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNews = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Nominal");

        jLabel2.setText("Destination Account Number");

        jLabel1.setText("TRANSFER");

        btnSubmit.setText("SUBMIT");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        jLabel4.setText("News");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(txtNews, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnClose)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(140, 140, 140))
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(59, 59, 59)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDestinationAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDestinationAccountNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnClose))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String destination = txtDestinationAccountNumber.getText();
        String nominal = txtNominal.getText();
        String news = txtNews.getText();
        Timestamp timeNow = new Timestamp(new Date().getTime());

        // Encrypt and Hash here
        // ...
        
        
        destination = Security.Encrypt2(destination, keyAES, publicKeyServer);
        nominal = Security.Encrypt2(nominal, keyAES, publicKeyServer);
        username = Security.Encrypt2(username, keyAES, publicKeyServer);
        news = Security.Encrypt2(news, keyAES, publicKeyServer);
        
        String hashDestination = Security.Encrypt3(destination, keyAES,saltDefault, publicKeyServer);
        String hashNominal = Security.Encrypt3(nominal, keyAES,saltDefault, publicKeyServer);
        String hashUsername = Security.Encrypt3(username, keyAES,saltDefault, publicKeyServer);
        String hashNews = Security.Encrypt3(news, keyAES,saltDefault, publicKeyServer);
        
        String command = "TRANSFER"
                + "[_]" + username
                + "[_]" + destination
                + "[_]" + nominal
                + "[_]" + news
                + "[_]" + timeNow
                + "[_]" + hashUsername
                + "[_]" + hashDestination
                + "[_]" + hashNominal
                + "[_]" + hashNews;
        

        try {
            out.writeBytes(command + "\n");

            String output = in.readLine();
            output = Security.Decrypt2(output, keyAES, privateKeyClient);
            JOptionPane.showMessageDialog(rootPane, output);
        } catch (Exception e) {
            System.out.println("Error Transfer : " + e);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

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
            java.util.logging.Logger.getLogger(FormTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTransfer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtDestinationAccountNumber;
    private javax.swing.JTextField txtNews;
    private javax.swing.JTextField txtNominal;
    // End of variables declaration//GEN-END:variables
}
