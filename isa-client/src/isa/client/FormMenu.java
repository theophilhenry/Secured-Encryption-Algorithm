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
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class FormMenu extends javax.swing.JFrame {

    Socket socket;
    String saltDefault = "hzVZ09jPyqbBUGPW6cPtSw==";
    String keyAES = "Ha^#Sdb$18Lg1+_~J=_is8g$21a12";
    String publicKeyServer = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB";
    String privateKeyClient = "MIIE5gIBADANBgkqhkiG9w0BAQEFAASCBNAwggTMAgEAAoIBChIGkhzdjwjZycd1mGfHVYNIlfj95X2VR/qR40QQ9UzZTTKWSixqb4IOq5rojndhB2OeOxBVvMS3P7ZcZFHqY6DxyRQofjvXLoOedYRn2fieLxjxj2VBd+rSRtJQtPAYxBFC6yfwBzqIcBej/+bsr+lIYGGMgQh3JwVkhASMFGvsZu26jSfQAP+sG7sW5y3r+x/YrI68cD7APf8pmrEADsPJYyQwnocGH2Vtq9zPGLePrWJGq8sAzk7ohrZbMsjdih/Cy4LHV3/JMfSlxh4+QdUvb5WLCzrg+A97o3+s/Bp42H3gXuKkYQSEkQwd6tKCpOF/Tx3Wla7tA2tE+RYInrONqWOgtOwp5QqDAgMBAAECggEKEASixt/kAVt3wbWwT1iFlvpjuzX0P1ru8a2+pXfkY/wea7u+AiY7HPnoaOoL3+3rYQz1M5jc/HpTNbx4sYp2KklyBGXPd2HKxc+EYrreqd33wFS4tqQs6hEeOjrZ7xoy8Zvj2EOmSM+BvKBSAWWHFOjqLQVsjJdFp7cg0eGhLB6RgOPsF08SfGhhIftkYBQ+R4NGhwB5n2BTqW1b15vOBzkZWk9Mjc8egx0smUAw9FMPkI73o9QB9YyqafdmTBkmc/mzoVvshjF/PG5/Y+rhXAyPEuwPaUgWtivfl6yzQyjg/M55Q9iwxG6eRiFnp48mKp/Zbx4610R9QkCxb2v/Vtu5bMWqnWDIrOECgYV2cH47yCH4rzTjgzu0oBKIN8Tr4vGkqfP8Z3haOnoy5t9bOlHE0836qm3VLs4Ls5FdFXATkcmFqtlEC8Q5pgTvwjVGvhXvFdhhU5gh/LcxbUHXV1/6d65J8L3vIzUIugHJC5D6co0t6FEmJCjyUcMMNhPt0UN1g8+0r2rHg4FPSLZ/HoHLAoGFJvYhBLqxz6N4CpOjZK8MOxxDvE5rpUCdHsbjB7Kj/g51G+iFpJKTpgK/e180LAXvuNldpYtWGH6pwOLLrfePX9WitveBbZaDDz0jYbXctblp+/34nLmMP8lLPFDaCVnXZbWgIf1HOiZIU8hoaoJUrpDrU+0ajZEtsBD+9vCLNPwfQFqjKQKBhQMZcIQ5+YGbfCPUBFT2t2VvKC0QFEi91c+uGX0q+JWqav/lJ8yhyksb+KHShrvRqCUqcXNhpkdkMClSsYDcslZf26sNQF+wT6hEWr/Q8C5P8KAk/jL8vMfcj+vZHQMscu2C6vlS1BT2dmEsIxIsR5FjPORFTKGfoGjXClFMRK8BdR0gfD8CgYUfFLAIbsrUQqrLfTWWZ1JRgV3Dxad3/9CXsb+A75CFZpEjv12FiOzCPWT1jWA4UlAHXVeobTw0dC8FUad8U4YLxIh2f4G/o5dPu80S8JpuWchjnxoKJLbuV61uI22ckhAT8k0ZcEy25b1DIRezB994FuG0ZOyOzKbu0tAivIfAr/RaSTPpAoGFELDjlCwkhRfWFP0+icBDJ4iqrJ900uOuH3wQmy7Yfmily/+C50FDQ+ZWnMVcu33i0xvU7g103rLkB4rWpzckUACf2x1sVJ+d3g75/E5lfTu8QaB2q5niiENWMfI52YhBPboDT9G+7OTPuSIE9T4BW326/6gQwFJUxYv1CYBGficKqyhROw==";
    

    BufferedReader in;
    DataOutputStream out;

    String userLogin;

    public FormMenu() {
        initComponents();
    }

    public FormMenu(Socket socket, String userLogin) {
        initComponents();

        this.socket = socket;
        this.userLogin = userLogin;
        
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

        jLabel1 = new javax.swing.JLabel();
        btnMenuTransfer = new javax.swing.JButton();
        btnMenuInfoSaldo = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("MENU");

        btnMenuTransfer.setText("TRANSFER");
        btnMenuTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuTransferActionPerformed(evt);
            }
        });

        btnMenuInfoSaldo.setText("INFO SALDO");
        btnMenuInfoSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuInfoSaldoActionPerformed(evt);
            }
        });

        buttonExit.setText("Exit");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenuInfoSaldo)
                    .addComponent(btnMenuTransfer)
                    .addComponent(jLabel1))
                .addContainerGap(229, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonExit)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnMenuTransfer)
                .addGap(18, 18, 18)
                .addComponent(btnMenuInfoSaldo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(buttonExit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuTransferActionPerformed
        // TODO add your handling code here:
        FormTransfer f = new FormTransfer(this.socket, this.userLogin);
        f.setVisible(true);
    }//GEN-LAST:event_btnMenuTransferActionPerformed

    private void btnMenuInfoSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuInfoSaldoActionPerformed
        // TODO add your handling code here:
         
        
        String usernameSaldo = Security.Encrypt2(userLogin, keyAES, publicKeyServer);
        String hashUsernameSaldo = Security.Encrypt3(userLogin, keyAES,saltDefault, publicKeyServer);
        
        System.out.println("\nINFO SALDO -------------------------------------------------------------------------------------");
        System.out.println("DATA ASLI : ");
        System.out.println("Username : " + userLogin);
        System.out.println("\n");
        System.out.println("DATA ENCRYPT2(AES-RSA) : ");
        System.out.println("Username : " + usernameSaldo);
        System.out.println("\n");
        System.out.println("DATA ENCRYPT3(HASH-RSA-AES) : ");
        System.out.println("Username : " + hashUsernameSaldo);
       
        
        
        
        
        
        
        
        String command = "INFOSALDO" + "[_]" + usernameSaldo + "[_]" + hashUsernameSaldo;

        try {
            out.writeBytes(command + "\n");

            String output = in.readLine();
           
            
            
            String[] serverInput = output.split("\\[_\\]");
            
            String result = serverInput[0];
            String hashResult = serverInput[1];
            
           
            
            result = Security.Decrypt2(result, keyAES, privateKeyClient);
            String testResult = Security.MakeHash(result, saltDefault);
            hashResult = Security.Decrypt3(hashResult, keyAES, saltDefault, privateKeyClient);
            
             System.out.println("\nINFO SALDO DARI SERVER -------------------------------------------------------------------------------------");
            System.out.println("DATA ASLI : ");
            System.out.println("Saldo : " + result);
            System.out.println("\n");
            System.out.println("DATA ENCRYPT2(AES-RSA) : ");
            System.out.println("Saldo : " + serverInput[0]);
            System.out.println("\n");
           
            
            if(testResult.equals(hashResult))
            {
                JOptionPane.showMessageDialog(rootPane, result);
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Sorry the data is corrupted, please try again.");
            }
            
            
        } catch (Exception e) {
            System.out.println("Error Info Saldo : " + e);
        }
    }//GEN-LAST:event_btnMenuInfoSaldoActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonExitActionPerformed

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
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenuInfoSaldo;
    private javax.swing.JButton btnMenuTransfer;
    private javax.swing.JButton buttonExit;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
