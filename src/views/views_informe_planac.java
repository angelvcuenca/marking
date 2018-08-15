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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
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
public class views_informe_planac extends javax.swing.JInternalFrame {

    /**
     * Creates new form views_cumple
     */
    conexion_bd con = new conexion_bd();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public views_informe_planac() {
        initComponents();

        modeloTabla.addColumn("Cedula");
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Telefonos");
        modeloTabla.addColumn("Fecha Emision");
        modeloTabla.addColumn("Valor Total");
        modeloTabla.addColumn("Fecha ult. Abono");
        modeloTabla.addColumn("Valor Abono");
        modeloTabla.addColumn("Saldo Total");
        
        Calendar c2 = new GregorianCalendar();
        date_inicio.setCalendar(c2);
        date_fin.setCalendar(c2);
        //   llena_tabla();
    }

    public void llena_tabla(String f_ini, String f_fin, String cedula) {
        while (modeloTabla.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }
        try {
            Connection c = con.conexion();
            String where_data = "cc.estado = 1";
            if (!"".equals(f_ini) && !"".equals(f_fin)) {
                where_data += " AND cc.fecha_cobro >= '" + f_ini+"'";
                where_data += " AND cc.fecha_cobro <='" + f_fin+"'";
            }
            if (!"".equals(cedula)) {
                where_data += " AND cc.client_id =" + cedula;
            }

            PreparedStatement pss = c.prepareStatement("SELECT cc.id idcxc,cc.client_id, CONCAT(cl.nombres,' ',cl.apellidos) cliente, cl.direccion, cl.telefonos,\n"
                    + "cl.celular,cl.telefono2,cc.fecha_cobro fecha_reg, sl.saldo saldo_total\n"
                    + "FROM bill_cxc_ac cc \n"
                    + "JOIN billing_cliente cl ON cl.PersonaComercio_cedulaRuc = cc.client_id\n"
                    + "JOIN bill_cxc_saldos_ac sl ON sl.client_id = cc.client_id\n"
                    + "WHERE "+where_data);

            ResultSet rss = pss.executeQuery();
            int cxc_id = 0;
            while (rss.next()) {
                cxc_id = rss.getInt("idcxc");
                PreparedStatement abono = c.prepareStatement("SELECT SUM(total_neto) total\n"
                        + "FROM bill_cxc_ac_det\n"
                        + "WHERE estado=1 AND tipotransaccion_cod = 1 AND cxc_id =" + cxc_id);
                ResultSet r_abon = abono.executeQuery();
                Double total = null;
                while (r_abon.next()) {
                    total = r_abon.getDouble("total");
                }
                PreparedStatement abo = c.prepareStatement("SELECT cuota_neto,fecha_cobro\n"
                        + "FROM bill_cxc_ac_det\n"
                        + "WHERE estado=1 AND  tipotransaccion_cod = 2 AND cxc_id =" + cxc_id);
                ResultSet rab = abo.executeQuery();
                Double total_ab = 0.00;
                String fecha_ult_abono = "0.00";
                while (rab.next()) {
                    total_ab = rab.getDouble("cuota_neto");
                    fecha_ult_abono = rab.getString("fecha_cobro");

                }
                 String celular;
                String telefono2;
                String telefonos;
                
                if(rss.getString("telefonos") == null){
                    telefonos = "**";
                }else{
                    telefonos = rss.getString("telefonos");
                }
                /*-------------*/
                if(rss.getString("celular") == null){
                    celular = "**";
                }else{
                    celular = rss.getString("celular");
                }
                /*-------------*/
                if(rss.getString("telefono2") == null){
                    telefono2 = "**";
                }else{
                    telefono2 = rss.getString("telefono2");
                }
                String tlfo = telefonos+"/"+celular+"/"+telefono2;

                modeloTabla.addRow(new Object[]{
                    rss.getString("client_id"),
                    rss.getString("cliente"),
                    rss.getString("direccion"),
                    tlfo,
                    rss.getString("fecha_reg"),
                    total,
                    fecha_ult_abono,
                    total_ab,
                    rss.getString("saldo_total")
                });

            }

            jTable1.setModel(modeloTabla);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_cedula = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date_inicio = new com.toedter.calendar.JDateChooser();
        date_fin = new com.toedter.calendar.JDateChooser();

        setClosable(true);
        setTitle("MODULO INFORMES - PLANES ACUMULATIVOS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(51, 153, 0));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/magnifier (1).png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/printer (1).png"))); // NOI18N
        jButton2.setText("Imprimir Reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 0));
        jLabel1.setText("INFORME PLAN ACUMULATIVO");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("CEDULA/PASS DEL CLIENTE");

        txt_cedula.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("FECHA FIN");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("FECHA INICIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(37, 37, 37))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(953, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(date_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(28, 28, 28))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date fecha_ini = date_inicio.getDate();
        Date fecha_fin = date_fin.getDate();
        String f_ini = "";
        String f_fin = "";
        if (fecha_ini != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            f_ini = formato.format(fecha_ini);
        }

        if (fecha_fin != null) {
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");

            f_fin = formato2.format(fecha_fin);
        }
        String cedula = txt_cedula.getText();
        llena_tabla(f_ini, f_fin, cedula);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         int i = 0;
        String datos = "";
        List result = new ArrayList();
        model_informe_cxc listainforme_cxc;
        result.clear();
        
        Date fecha_ini = date_inicio.getDate();
        Date fecha_fin = date_fin.getDate();
        String f_ini = "";
        String f_fin = "";
        if (fecha_ini != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            f_ini = formato.format(fecha_ini);
        }

        if (fecha_fin != null) {
            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");

            f_fin = formato2.format(fecha_fin);
        }
        try {
            for (i = 0; i < jTable1.getRowCount(); i++) {
                String cedula = String.valueOf(jTable1.getValueAt(i, 0));
                String cliente = String.valueOf(jTable1.getValueAt(i, 1));
                String direccion = String.valueOf(jTable1.getValueAt(i, 2));
                String telefonos = String.valueOf(jTable1.getValueAt(i, 3));
                String fecha_emision = String.valueOf(jTable1.getValueAt(i, 4));
                String valor_total = String.valueOf(jTable1.getValueAt(i, 5));
                String fecha_ult_abono = String.valueOf(jTable1.getValueAt(i, 6));
                String valor_abono = String.valueOf(jTable1.getValueAt(i, 7));
                String saldo_total = String.valueOf(jTable1.getValueAt(i, 8));
                listainforme_cxc = new model_informe_cxc(cedula, cliente, direccion, telefonos, fecha_emision,valor_total, fecha_ult_abono, valor_abono, saldo_total);
                result.add(listainforme_cxc);

            }

            Map map = new HashMap();
            JasperPrint jPrint;
            JDialog report = new JDialog();
            report.setSize(900, 700);
            report.setLocationRelativeTo(null);
            report.setTitle("INFORME DE PLAN ACUMULATIVO");

            map.put("titulo", "INFORME DE PLAN ACUMULATIVO");
            map.put("fecha_inicio", f_ini);
            map.put("fecha_fin", f_fin);
            Connection c = con.conexion();
            //JasperReport reporte =  (JasperReport) JRLoader.loadObject("informe_cxc.jasper");
            //jPrint = JasperFillManager.fillReport(reporte, map,new JRBeanCollectionDataSource(result));
            jPrint = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reports/informe_plan_ac.jasper"), map, new JRBeanCollectionDataSource(result));
            JRViewer jv = new JRViewer(jPrint);
            report.getContentPane().add(jv);
            report.setVisible(true);
           

        } catch (JRException ex) {
            Logger.getLogger(views_informecxc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser date_fin;
    private com.toedter.calendar.JDateChooser date_inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_cedula;
    // End of variables declaration//GEN-END:variables
}
