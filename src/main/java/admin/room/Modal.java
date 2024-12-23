/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package admin.room;

import java.awt.Frame;
import services.admin.Room;
import shared.FormHelper;

/**
 *
 * @author maker
 */
public class Modal extends javax.swing.JDialog {

    public boolean isSubmitted = false;
    public boolean isDeleted = false;
    public Room room = null;

    private boolean editMode = false;
    private int id;
    private Room.Inputs original;

    public Modal(Frame parent) {
        super(parent, true);
        this.init();
        this.setVisible(true);
    }

    public Modal(Frame parent, int id, Room.Inputs input) {
        super(parent, true);
        init();

        this.inputRoomNumber.setText(input.roomNumber);
        this.inputRoomType.setSelectedItem(input.roomType);

        this.buttonDelete.setVisible(true);
        this.labelTitle.setText("Edit Room");

        this.editMode = true;
        this.id = id;
        this.original = input;

        this.setTitle("Edit Room");
        this.setVisible(true);
    }

    private void init() {
        initComponents();

        Runnable submitAction = () -> performSubmitAction();

        FormHelper.jComponentOnEnterSumit(inputRoomNumber, submitAction);
        FormHelper.jComponentOnEnterSumit(inputRoomType, submitAction);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inputRoomNumber = new javax.swing.JTextField();
        rnErrorMessage = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rtErrorMessage = new javax.swing.JLabel();
        inputRoomType = new javax.swing.JComboBox<>();
        buttonSubmit = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Room");
        setResizable(false);

        labelTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Add Room");

        jLabel2.setText("Room Number");

        inputRoomNumber.setPreferredSize(new java.awt.Dimension(350, 22));

        rnErrorMessage.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(rnErrorMessage))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rnErrorMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Room Type");

        rtErrorMessage.setForeground(new java.awt.Color(255, 0, 0));

        inputRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Single Bed", "Double Bed", "Triple Bed" }));
        inputRoomType.setPreferredSize(new java.awt.Dimension(350, 22));
        inputRoomType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputRoomTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputRoomType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(rtErrorMessage))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rtErrorMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonSubmit.setText("Submit");
        buttonSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonSubmitMouseClicked(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCancelMouseClicked(evt);
            }
        });

        buttonDelete.setText("Delete");
        buttonDelete.setVisible(false);
        buttonDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonDeleteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCancel)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSubmit))
                    .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(labelTitle)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSubmit)
                    .addComponent(buttonCancel)
                    .addComponent(buttonDelete))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCancelMouseClicked
        dispose();
    }//GEN-LAST:event_buttonCancelMouseClicked

    private void buttonSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSubmitMouseClicked
        performSubmitAction();
    }//GEN-LAST:event_buttonSubmitMouseClicked

    private void buttonDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDeleteMouseClicked
        if (Room.deleteFromId(id)) {
            isDeleted = true;
        }

        dispose();
    }//GEN-LAST:event_buttonDeleteMouseClicked

    private void inputRoomTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputRoomTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputRoomTypeActionPerformed

    private void performSubmitAction() {
        rnErrorMessage.setText("");
        rtErrorMessage.setText("");

        Room.Inputs inputs = new Room.Inputs(
                inputRoomNumber.getText(),
                inputRoomType.getSelectedItem().toString()
        );

        boolean roomTaken = false;
        
        if (original != null && !original.roomNumber.equals(inputs.roomNumber)) {
            roomTaken = Room.fromRoomNumber(inputs.roomNumber) != null;
        }

        if (inputs.roomNumber.trim().isBlank()) {
            rnErrorMessage.setText("Field is required");
            inputRoomNumber.grabFocus();
        } else if (inputs.roomType.equals("None")) {
            rtErrorMessage.setText("Field is required");
            inputRoomType.grabFocus();
        } else if (roomTaken) {
            rnErrorMessage.setText("Room number already exist");
            inputRoomNumber.grabFocus();
        } else {
            if (editMode) {
                room = Room.updateFromTo(id, inputs);
            } else {
                room = Room.createFrom(inputs);
            }

            isSubmitted = true;
            dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonSubmit;
    private javax.swing.JTextField inputRoomNumber;
    private javax.swing.JComboBox<String> inputRoomType;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel rnErrorMessage;
    private javax.swing.JLabel rtErrorMessage;
    // End of variables declaration//GEN-END:variables
}
