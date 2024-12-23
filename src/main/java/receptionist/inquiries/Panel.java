/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package receptionist.inquiries;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import services.receptionist.Inquiry;
import shared.Database;
import shared.Defs;

/**
 *
 * @author maker
 */
public class Panel extends javax.swing.JPanel {

    public boolean loaded;

    private JFrame absoluteParent = null;

    public Panel() {
        this.initComponents();
    }

    public Panel(JFrame parent) {
        this.initComponents();
        this.absoluteParent = parent;
    }

    public void lazyLoadTable() {
        try {
            for (Inquiry inquiry : Inquiry.getAll()) {
                addRow(inquiry);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Could not load staff table!", ex);
        }

        loaded = true;
    }

    private void addRow(Inquiry inquiry) {
        DefaultTableModel modal = (DefaultTableModel) table.getModel();

        modal.addRow(new Object[]{
            inquiry.id,
            inquiry.clientName,
            inquiry.clientContact,
            inquiry.roomType,
            inquiry.startDate + " - " + inquiry.endDate
        });
    }

    private void deleteRow(int row) {
        ((DefaultTableModel) table.getModel()).removeRow(row);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Inquiries");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Client Name", "Client Contact", "Room Type", "Period"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getColumnModel().getColumn(0).setCellRenderer(Defs.centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(Defs.centerRenderer);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setText("Refresh");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1093, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1256, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();

            DefaultTableModel modal = (DefaultTableModel) table.getModel();
            int selectedRow = table.getSelectedRow();

            String period[] = modal.getValueAt(selectedRow, 4).toString().split(" - ");

            Modal checkInquiry = new Modal(
                    absoluteParent,
                    (int) modal.getValueAt(selectedRow, 0),
                    new Inquiry.Inputs(
                            modal.getValueAt(selectedRow, 1).toString(),
                            modal.getValueAt(selectedRow, 2).toString(),
                            modal.getValueAt(selectedRow, 3).toString(),
                            period[0],
                            period[1]
                    )
            );

            if (checkInquiry.isDone) {
                deleteRow(selectedRow);
            }

//            if (editStaff.isSubmitted && editStaff.staff != null) {
//                rerenderAtRow(selectedRow, editStaff.staff);
//            } else if (editStaff.isDeleted) {
//                deleteRow(selectedRow);
//            }
        }
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        DefaultTableModel modal = (DefaultTableModel) table.getModel();
        modal.setRowCount(0);
        lazyLoadTable();
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}