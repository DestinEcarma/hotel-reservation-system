package main;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import services.admin.Staff;
import shared.Database;
import shared.Defs;
import shared.FormHelper;
import shared.PasswordUtil;
import shared.Session;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author maker
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        this.initComponents();

        Runnable submitAction = () -> this.performSubmitAction();

        FormHelper.jComponentOnEnterSumit(this.puInput, submitAction);
        FormHelper.jComponentOnEnterSumit(this.ppInput, submitAction);
    }

    private void performSubmitAction() {
        String username = puInput.getText();
        String password = String.valueOf(ppInput.getPassword());

        if (Defs.dotenv.get("ADMIN_USER").equals(username) && Defs.dotenv.get("ADMIN_USER").equals(password)) {
            new admin.Dashboard().setVisible(true);

            dispose();
        } else {
            try {
                Staff staff = Staff.fromUsername(username);

                if (staff != null && PasswordUtil.passwordVerify(password, staff.passwordHash)) {
                    Session.userId = staff.id;
                    new receptionist.Dashboard().setVisible(true);
                    dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Could not get staff!", ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        panelPassword = new javax.swing.JPanel();
        ppLabel = new javax.swing.JLabel();
        ppInput = new javax.swing.JPasswordField();
        ppErrorMessage = new javax.swing.JLabel();
        panelUsername = new javax.swing.JPanel();
        puLabel = new javax.swing.JLabel();
        puInput = new javax.swing.JTextField();
        puErrorMessage = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        buttonSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel Reservation System | Login");
        setPreferredSize(new java.awt.Dimension(520, 720));
        setResizable(false);

        panelPassword.setPreferredSize(new java.awt.Dimension(350, 56));

        ppLabel.setText("Password");

        ppInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppInputActionPerformed(evt);
            }
        });

        ppErrorMessage.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout panelPasswordLayout = new javax.swing.GroupLayout(panelPassword);
        panelPassword.setLayout(panelPasswordLayout);
        panelPasswordLayout.setHorizontalGroup(
            panelPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ppInput)
                    .addGroup(panelPasswordLayout.createSequentialGroup()
                        .addGroup(panelPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ppLabel)
                            .addComponent(ppErrorMessage))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelPasswordLayout.setVerticalGroup(
            panelPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ppLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ppInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ppErrorMessage)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        panelUsername.setPreferredSize(new java.awt.Dimension(350, 56));

        puLabel.setText("Username");

        puInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puInputActionPerformed(evt);
            }
        });

        puErrorMessage.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout panelUsernameLayout = new javax.swing.GroupLayout(panelUsername);
        panelUsername.setLayout(panelUsernameLayout);
        panelUsernameLayout.setHorizontalGroup(
            panelUsernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsernameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUsernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(puInput, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                    .addGroup(panelUsernameLayout.createSequentialGroup()
                        .addGroup(panelUsernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(puLabel)
                            .addComponent(puErrorMessage))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelUsernameLayout.setVerticalGroup(
            panelUsernameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUsernameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(puLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(puInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(puErrorMessage)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        labelTitle.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Login");

        buttonSubmit.setText("Submit");
        buttonSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSubmitMouseClicked(evt);
            }
        });
        buttonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSubmit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(panelUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(buttonSubmit)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void puInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puInputActionPerformed

    private void ppInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ppInputActionPerformed

    private void buttonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSubmitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSubmitActionPerformed

    private void buttonSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSubmitMouseClicked

    }//GEN-LAST:event_buttonSubmitMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());

            java.awt.EventQueue.invokeLater(() -> {
                new Login().setVisible(true);
            });
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSubmit;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPanel panelPassword;
    private javax.swing.JPanel panelUsername;
    private javax.swing.JLabel ppErrorMessage;
    private javax.swing.JPasswordField ppInput;
    private javax.swing.JLabel ppLabel;
    private javax.swing.JLabel puErrorMessage;
    private javax.swing.JTextField puInput;
    private javax.swing.JLabel puLabel;
    // End of variables declaration//GEN-END:variables
}