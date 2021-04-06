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
        String account_number;
        int balance;
        
        while (true) {
            try {
                String rawInput = in.readLine();
                txtConversation.append(rawInput);

                String[] clientInput = rawInput.split("\\[\\_\\]");
                String respond = "";

                User __user = new User();
                switch (clientInput[0]) {
                    case "LOGIN":
                        username = clientInput[1];
                        password = clientInput[2];
                        respond = __user.Login(username, password); //return TRUE or FALSE
                        break;
                    case "REGISTER":
                        username = clientInput[1];
                        password = clientInput[2];
                        name = clientInput[3];
                        pin = clientInput[4];
                        
                        respond = __user.Register(username, password, name, pin); //return TRUE or FALSE
                        break;
                    case "INFOSALDO":
                        respond = "Info Saldo";
                        break;
                    case "TRANSFER":
                        respond = "Transfer";
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
