/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Elizabeth
 */
public class views_renumerar extends javax.swing.JInternalFrame {

    /**
     * Creates new form views_renumerar
     */
    conexion_bd con = new conexion_bd();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public views_renumerar() {
        initComponents();
        modeloTabla.addColumn("id");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("#Paquete Nuevo");
        modeloTabla.addColumn("#Paquete Ant.");
        txt_renumerar.setEnabled(false);
        jButton2.setEnabled(false);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_renumerar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RENUMERAR PAQUETES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12), new java.awt.Color(51, 51, 255))); // NOI18N

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "Nro Funda", "Cliente"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jLabel1.setText("INGRESE EL NUEVO NUMERO PARA EMPEZAR ");

        txt_renumerar.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        jButton2.setBackground(new java.awt.Color(51, 102, 0));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton2.setText("Renumerar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/printer (1).png"))); // NOI18N
        jButton3.setText("Imprimir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_renumerar)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_renumerar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (txt_renumerar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "LLENE POR FAVOR EL CAMPO NUEVO NUMERO");
        } else {
            int n = JOptionPane.showConfirmDialog(
                    null, "Esta seguro de empezar una nueva numeracio",
                    "Eliga una opcion",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                int new_number = Integer.parseInt(txt_renumerar.getText());
                renumerar(new_number);
                llenar_tabla();
                txt_renumerar.setEnabled(false);
                jButton2.setEnabled(false);
            } else if (n == JOptionPane.NO_OPTION) {
                System.out.println("noo");
            }
        }


    }//GEN-LAST:event_jButton2ActionPerformed
    public void renumerar(int new_numero) {
        try {
            Connection c = con.conexion();
            //UPDATE bill_funda_acumulativo SET num_funda_old=num_funda 
            PreparedStatement updt = c.prepareStatement("UPDATE bill_funda_acumulativo SET num_funda_old=num_funda");
            updt.execute();
                
            PreparedStatement pss = c.prepareStatement("SELECT pl.id_funda, pl.num_funda,CONCAT(c.nombres,' ',c.apellidos) cliente\n"
                    + "FROM bill_funda_acumulativo pl\n"
                    + "JOIN billing_cliente c ON c.PersonaComercio_cedulaRuc = pl.cliente_dni\n"
                    + "WHERE estado_funda = 1");

            ResultSet rss = pss.executeQuery();

            while (rss.next()) {
                String id_funda = rss.getString("id_funda");
                String num_funda = rss.getString("num_funda");
                String cliente = rss.getString("cliente");

                PreparedStatement updSaldocc = c.prepareStatement("UPDATE bill_funda_acumulativo set num_funda=? where id_funda=?");
                updSaldocc.setInt(1, new_numero);
                updSaldocc.setString(2, id_funda);
                updSaldocc.execute();

                ///System.out.println(id_funda + "-" + cliente + "-" + num_funda + "**" + new_number);
                //rss.getString("id_funda");
                new_numero++;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        llenar_tabla();
        txt_renumerar.setEnabled(true);
                jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
              int i = 0;
        String datos = "";
        List result = new ArrayList();
        model_renumeracion lista_renu;
        result.clear();
        try {
            for (i = 0; i < jTable1.getRowCount(); i++) {
                String id = String.valueOf(jTable1.getValueAt(i, 0));
                String cliente = String.valueOf(jTable1.getValueAt(i, 1));
                String num_new = String.valueOf(jTable1.getValueAt(i, 2));
                String num_old = String.valueOf(jTable1.getValueAt(i, 3));
                lista_renu = new model_renumeracion(id, cliente, num_new, num_old);
                result.add(lista_renu);

            }

            Map map = new HashMap();

            JasperPrint jPrint;
            JDialog report = new JDialog();
            report.setSize(900, 700);
            report.setLocationRelativeTo(null);
            report.setTitle("INFORME DE RENUMERACION");

            map.put("titulo", "INFORME DE RENUMERACION");
           
            jPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reports/renumercion.jasper"), map, new JRBeanCollectionDataSource(result));
            JRViewer jv = new JRViewer(jPrint);
            report.getContentPane().add(jv);
            report.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(views_informecxc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void llenar_tabla() {
        while (modeloTabla.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }
        try {
            Connection c = con.conexion();
            PreparedStatement pss = c.prepareStatement("SELECT pl.id_funda, pl.num_funda,pl.num_funda_old,CONCAT(c.nombres,' ',c.apellidos) cliente\n"
                    + "FROM bill_funda_acumulativo pl\n"
                    + "JOIN billing_cliente c ON c.PersonaComercio_cedulaRuc = pl.cliente_dni\n"
                    + "WHERE estado_funda = 1");

            ResultSet rss = pss.executeQuery();

            while (rss.next()) {
                modeloTabla.addRow(new Object[]{
                    rss.getString("id_funda"),
                    rss.getString("cliente"),
                    rss.getString("num_funda"),
                    rss.getString("num_funda_old"),
                });

            }

            //txt_renumerar.setEnabled(false);
            //jButton2.setEnabled(false);
            jTable1.setModel(modeloTabla);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_renumerar;
    // End of variables declaration//GEN-END:variables
}
