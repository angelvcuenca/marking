/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.*;
import static views.PlanAcumulativo.*;

public class producto_new extends javax.swing.JInternalFrame {

    /**
     * Creates new form producto_new
     */
    conexion_bd con = new conexion_bd();

    public producto_new() {
        initComponents();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_codigocomun_pro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_talla = new javax.swing.JTextField();
        txt_color = new javax.swing.JTextField();
        txt_marca = new javax.swing.JTextField();
        btn_guardar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_pvp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_guardar2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NUEVO  PRODUCTO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 12), new java.awt.Color(0, 102, 102))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("Marca");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setText("Cod. Comun");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 51));
        jLabel8.setText("talla");

        txt_codigocomun_pro.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 51));
        jLabel6.setText("Color");

        txt_talla.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        txt_color.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        txt_marca.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        btn_guardar.setBackground(new java.awt.Color(0, 102, 102));
        btn_guardar.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus-symbol-in-a-rounded-black-square.png"))); // NOI18N
        btn_guardar.setText(" Producto");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 51));
        jLabel11.setText("PVP ");

        txt_pvp.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/file.png"))); // NOI18N

        btn_guardar2.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btn_guardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rubbish-bin.png"))); // NOI18N
        btn_guardar2.setText("Limpiar");
        btn_guardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_guardar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_guardar))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel6)
                                .addComponent(jLabel11))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addComponent(txt_codigocomun_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_talla, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pvp, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_codigocomun_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_talla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_pvp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar)
                    .addComponent(btn_guardar2))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        Connection c = con.conexion();
        try {

            String tx_marca = txt_marca.getText();
            String tx_codigo = txt_codigocomun_pro.getText();
            String tx_color = txt_color.getText();
            String tx_talla = txt_talla.getText();
            String tx_precio = txt_pvp.getText();
            if (tx_marca.equals("") || tx_codigo.equals("") || tx_color.equals("") || tx_precio.equals("")) {
                JOptionPane.showMessageDialog(null, "LLENE POR FAVOR TODOS LOS CAMPOS MARCADOS POR ASTERISCO ROJO");
            } else {

//                PreparedStatement user = c.prepareStatement("select codigo  from billing_producto  order by codigo desc limit 1");
//                ResultSet s_user = user.executeQuery();
//                int user_id = 0;
//                String contpro = null;
//                while (s_user.next()) {
//                    user_id = s_user.getInt("codigo");
//                    int cont_pro = user_id + 1;
//                    contpro = String.valueOf(cont_pro);
//
//                }
                String descrip = tx_marca + "/" +tx_color + "/" + tx_talla;
                PreparedStatement se = c.prepareStatement("INSERT INTO billing_producto(codigo2,nombreUnico,descripcion,productogrupo_codigo,marca_id,productotipo_id,costo_inventario)\n"
                        + "VALUES(?,?,?,?,?,?,?)");
                se.setString(1, tx_codigo);
                se.setString(2, descrip);
                se.setString(3, descrip);
                se.setString(4, "12");
                se.setString(5, "26");
                se.setString(6, "1");
                se.setString(7, tx_precio);
                se.execute();

                PreparedStatement proutl = c.prepareStatement("select codigo  from billing_producto  order by codigo desc limit 1");
                ResultSet s_prod = proutl.executeQuery();
                int cod_id = 0;
                String cont_pro = null;
                while (s_prod.next()) {
                    cod_id = s_prod.getInt("codigo");
                    cont_pro = String.valueOf(cod_id);
                }

                PreparedStatement ses = c.prepareStatement("INSERT INTO producto_precio(valor,id_precio,id_producto,id_tipo)\n"
                        + "VALUES(?,?,?,?)");
                ses.setString(1, tx_precio);
                ses.setString(2, "4");
                ses.setString(3, cont_pro);
                ses.setString(4, "pA");
                ses.execute();

                PreparedStatement odpss = c.prepareStatement("SELECT p.codigo codp, p.codigo2, p.nombreUnico, p.descripcion, p.estado, m.nombre marca,gp.nombre grupo,pp.valor pvp\n"
                        + "FROM billing_producto p\n"
                        + "LEFT JOIN producto_precio pp ON p.codigo = pp.id_producto AND pp.id_precio =4\n"
                        + "LEFT JOIN billing_marca m ON p.marca_id = m.id\n"
                        + "LEFT JOIN billing_productogrupo gp ON gp.codigo = p.productogrupo_codigo\n"
                        + "WHERE p.codigo = " + cont_pro);

                ResultSet rss_cod = odpss.executeQuery();
                String codigo2=null,lb_productoa=null;
               
                while (rss_cod.next()) {
                    String[] parts = rss_cod.getString("nombreUnico").split("/");
                    String p_marca = parts[0]; // 123
                    String p_color = parts[1];
                    String p_talla = parts[2];
                    lb_productoa  = rss_cod.getString("nombreUnico");
                    codigo2=rss_cod.getString("codigo2");
                    lb_producto.setText(p_marca);
                    lb_codigo2.setText(codigo2);
                    lb_color.setText(p_color);
                    lb_talla.setText(p_talla);
                    pvp_sin_iva.setText(rss_cod.getString("pvp"));
                    valor_producto.setText(rss_cod.getString("pvp"));

                }
                this.dispose();
                product_id_hidden_ac.setText(cont_pro);
                txt_abono.setText("0");
                //lb_producto.setText(lb_productoa);
                lb_codigo2.setText(codigo2);

                btn_search2.setEnabled(true);
                btn_agrega.setEnabled(true);
                txt_abono.setEnabled(true);
                btn_anular_todo.setEnabled(true);
                //btn_anular_pago.setEnabled(true);
                btn_abonar.setEnabled(false);
                valor_producto.setEnabled(true);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_guardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar2ActionPerformed
        txt_codigocomun_pro.setText("");
        txt_color.setText("");
        txt_marca.setText("");
        txt_pvp.setText("");
        txt_talla.setText("");
    }//GEN-LAST:event_btn_guardar2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_guardar;
    public javax.swing.JButton btn_guardar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField txt_codigocomun_pro;
    public javax.swing.JTextField txt_color;
    public javax.swing.JTextField txt_marca;
    public javax.swing.JTextField txt_pvp;
    public javax.swing.JTextField txt_talla;
    // End of variables declaration//GEN-END:variables
}
