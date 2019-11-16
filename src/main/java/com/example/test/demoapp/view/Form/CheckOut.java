package com.example.test.demoapp.view.Form;

import com.example.test.demoapp.controller.UserController;
import com.example.test.demoapp.object.*;
import com.example.test.demoapp.view.MenuForm;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CheckOut extends javax.swing.JFrame {
    
    private UserController connection;
    
    public CheckOut() {
        connection = new UserController();
        initComponents();
        this.hienThiDanhSachHoaDon();
    }
    
    private ArrayList<Bill> layDanhSachHoaDon() {
        ArrayList<Bill> listBills = new ArrayList<Bill>();
        
        try {
            listBills = (ArrayList<Bill>) connection.connect().listBills();
        } catch (SQLException ex) {
        }
        
        return listBills;
    }
    
    private ArrayList<Customer> laydanhsachkhachhang(){
        ArrayList<Customer> listCustomers = new ArrayList<>();
        
        try {
            listCustomers = 
                    (ArrayList<Customer>) connection.connect().listCustomer();
        } catch (SQLException ex) {
            Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listCustomers;
    }
    
    public void hienThiDanhSachHoaDon() {
        String colTieuDe1[] = new String[]{"Tên", "Tuổi", "CMND", "Địa chỉ", 
            "Thời gian", "Thanh toán"};
        
        ArrayList<Bill> listBills = this.layDanhSachHoaDon();
        ArrayList<Customer> listCustomers = this.laydanhsachkhachhang();
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);

        Object[] row;

        for (int i = 0; i < listBills.size(); i++) {
            for(int j = 0; j < listCustomers.size(); j++){
                if(listBills.get(i).getId_Bill().equalsIgnoreCase(
                        listCustomers.get(j).getId())){
                    row = new Object[6];

                    row[0] = listCustomers.get(j).getName();
                    row[1] = listCustomers.get(j).getAge();
                    row[2] = listCustomers.get(j).getId();
                    row[3] = listCustomers.get(j).getAddress();
                    row[4] = listBills.get(i).getDate_Bill();
                    row[5] = listBills.get(i).getMoney_Bill();

                    model.addRow(row);    
                }
            }
        }
        jTableBill.setModel(model);

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBill = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thanh toán hóa đơn");

        jLabel2.setText("Tên KH:");

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "        Tên", "       Tuổi", "      CMND", "      Địa chỉ", "    Thời gian", "     Hóa đơn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableBill);
        if (jTableBill.getColumnModel().getColumnCount() > 0) {
            jTableBill.getColumnModel().getColumn(0).setResizable(false);
            jTableBill.getColumnModel().getColumn(1).setResizable(false);
            jTableBill.getColumnModel().getColumn(2).setResizable(false);
            jTableBill.getColumnModel().getColumn(3).setResizable(false);
            jTableBill.getColumnModel().getColumn(4).setResizable(false);
            jTableBill.getColumnModel().getColumn(5).setResizable(false);
        }

        jButton2.setText("Thoát");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Thanh toán");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jButton2)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String colTieuDe1[] = new String[]{"Tên", "Tuổi", "CMND", "Địa chỉ", "Thời gian",
        "Thanh toán"};
        
        ArrayList<Bill> listBills = this.layDanhSachHoaDon();
        ArrayList<Customer> listCustomers = this.laydanhsachkhachhang();
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);

        Object[] row;

        for (int i = 0; i < listBills.size(); i++) {
            for(int j = 0; j < listCustomers.size(); j++){
                if(listBills.get(i).getId_Bill().equalsIgnoreCase(
                        listCustomers.get(j).getId()) && 
                        listCustomers.get(j).getName().contains(
                                jTextField1.getText())){
                    row = new Object[6];

                    row[0] = listCustomers.get(j).getName();
                    row[1] = listCustomers.get(j).getAge();
                    row[2] = listCustomers.get(j).getId();
                    row[3] = listCustomers.get(j).getAddress();
                    row[4] = listBills.get(i).getDate_Bill();
                    row[5] = listBills.get(i).getMoney_Bill();

                    model.addRow(row);    
                }
            }
        }

        jTableBill.setModel(model);
        this.hienThiDanhSachHoaDon();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MenuForm menuForm = new MenuForm();
        menuForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String id = (String) jTableBill.getValueAt(jTableBill.getSelectedRow(), 2);
        connection.connect().deleteBooking(id);
        this.hienThiDanhSachHoaDon();
        JOptionPane.showConfirmDialog(this, "Thành công!");
    }//GEN-LAST:event_jButton3ActionPerformed


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
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBill;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
