/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.*;
import models.*;

/**
 *
 * @author Angel CM <angelvcuenca@gmail.com>
 */
public class ctrl_producto implements ActionListener, KeyListener {

    Producto vista = new Producto();
    conexion_bd con = new conexion_bd();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public ctrl_producto(Producto vista) {
        this.vista = vista;
        //botones
        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_nuevo.addActionListener(this);
        this.vista.btn_search.addActionListener(this);
        this.vista.btn_modificar.addActionListener(this);
        this.vista.btn_inactivar.addActionListener(this);

        modeloTabla.addColumn("Cod.");
        modeloTabla.addColumn("Cod. Comun");
        modeloTabla.addColumn("Producto");
        modeloTabla.addColumn("Descrip.");
        modeloTabla.addColumn("pvp(-iva)/pvp(+iva)");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Grupo");
        modeloTabla.addColumn("Estado");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_nuevo) {
            vista.hidden_update_new.setText("0");
            Connection c = con.conexion();
            try {
                //vista.txt_codigo_pro.setEnabled(true);
                PreparedStatement user = c.prepareStatement("select codigo  from billing_producto  order by codigo desc limit 1");
                ResultSet s_user = user.executeQuery();
                int user_id = 0;
                while (s_user.next()) {
                    user_id = s_user.getInt("codigo");
                    int cont_pro = user_id+1;
                    String contpro = String.valueOf(cont_pro);
                    vista.txt_codigo_pro.setText(contpro);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());

            } finally {
                con.desconectar();
            }

            activar_campos();

        }
        if (e.getSource() == vista.btn_guardar) {
            ValidarIdentificacion d = new ValidarIdentificacion();
            try {
                vista.rb_inactivo.setActionCommand("0");
                vista.rb_activo.setActionCommand("1");
                String estad_pass = vista.estado_cliente.getSelection().getActionCommand();
                String envia_est = null;
                if (estad_pass.equals("0")) {
                    envia_est = "0";
                }
                if (estad_pass.equals("1")) {
                    envia_est = "1";
                }

                String codigo_pro = vista.txt_codigo_pro.getText();
                String codigo_comun = vista.txt_codigocomun_pro.getText();
                String nombre_producto = vista.txt_nombre_producto.getText();
                String descripcion = vista.txt_descripcion.getText();
                String pvp = vista.txt_pvp.getText();
                
                marca model = (marca) vista.cbm_marca.getSelectedItem();
                String marca = model.getId();
                
                grupo mod = (grupo) vista.cbm_producto.getSelectedItem();
                String grupo = mod.getCodigo();
        
                if (codigo_pro.equals("")
                        || nombre_producto.equals("")
                        || codigo_comun.equals("")) {
                    JOptionPane.showMessageDialog(null, "LLENE POR FAVOR TODOS LOS CAMPOS MARCADOS POR ASTERISCO ROJO");
                } else {
                    Connection c = con.conexion();
                    String hidden_select_update_new = vista.hidden_update_new.getText();
                    try {
                        if (hidden_select_update_new.equals("1")) {
                            /*UPDATE CLIENTE*/
                            PreparedStatement act = c.prepareStatement("UPDATE billing_producto SET codigo2=?,nombreUnico=?,descripcion=?,productogrupo_codigo=?,marca_id=?,estado=?\n"
                                    + "WHERE codigo=?");
                            act.setString(1, codigo_comun);
                            act.setString(2, nombre_producto);
                            act.setString(3, descripcion);
                            act.setString(4, grupo);
                            act.setString(5, marca);
                            act.setString(6, envia_est);
                            act.setString(7, codigo_pro);
                            act.execute();
                            
                            PreparedStatement pvp_act = c.prepareStatement("UPDATE producto_precio SET valor=?\n"
                                    + "WHERE id_producto=?");
                            pvp_act.setString(1, pvp);
                            pvp_act.setString(2, codigo_pro);
                            pvp_act.execute();
                            
                            JOptionPane.showMessageDialog(null, "PRODUCTO MODIFICADO CORRECTAMENTE", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                        }
                        if (hidden_select_update_new.equals("0")) {
                            /* NEW CLIENTE*/
                            PreparedStatement verStmt = c.prepareStatement("SELECT * \n"
                                    + "FROM billing_producto \n"
                                    + "WHERE nombreUnico=?");
                            verStmt.setString(1, nombre_producto);
                            ResultSet rs_v = verStmt.executeQuery();

                            if (rs_v.last()) {
                                JOptionPane.showMessageDialog(null, "EL PRODUCTO QUE INTENTA AGREGAR YA SE ENCUENTRA REGISTRADO", "VERIFIQUE POR FAVOR", JOptionPane.ERROR_MESSAGE);
                            } else {
                                PreparedStatement se = c.prepareStatement("INSERT INTO billing_producto(codigo,codigo2,nombreUnico,descripcion,productogrupo_codigo,marca_id,productotipo_id,costo_inventario)\n"
                                        + "VALUES(?,?,?,?,?,?,?,?)");
                                se.setString(1, codigo_pro);
                                se.setString(2, codigo_comun);
                                se.setString(3, nombre_producto);
                                se.setString(4, descripcion);
                                se.setString(5, grupo);
                                se.setString(6, marca);
                                se.setString(7, "1");
                                se.setString(8, pvp);
                                
                                
                                se.execute();
                                
                                PreparedStatement ses = c.prepareStatement("INSERT INTO producto_precio(valor,id_precio,id_producto,id_tipo)\n"
                                        + "VALUES(?,?,?,?)");
                                ses.setString(1, pvp);
                                ses.setString(2, "4");
                                ses.setString(3, codigo_pro);
                                ses.setString(4, "pA");
                                ses.execute();
                                
                                JOptionPane.showMessageDialog(null, "CLIENTE AGREGADO CORRECTAMENTE", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                            }

                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

                    } finally {
                        con.desconectar();
                    }

                }

            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == vista.btn_search) {
            vista.btn_inactivar.setEnabled(true);
            vista.btn_modificar.setEnabled(true);

            Connection c = con.conexion();
            while (modeloTabla.getRowCount() > 0) {
                modeloTabla.removeRow(0);
            }

            try {
                vista.rb_nombre.setActionCommand("0");
                vista.rb_codigo.setActionCommand("1");
                vista.rb_comun.setActionCommand("2");

                String search_cliente = vista.grupo_search_cliente.getSelection().getActionCommand();
                String searchName = vista.txt_search_cliente.getText();
                PreparedStatement pss = null;
                if (search_cliente.equals("0")) {
                    pss = c.prepareStatement("SELECT p.codigo codp, p.codigo2, p.nombreUnico, p.descripcion, p.estado, m.nombre marca,gp.nombre grupo,	pp.valor pvp\n"
                            + "FROM billing_producto p\n"
                            + "LEFT JOIN producto_precio pp ON p.codigo = pp.id_producto AND pp.id_precio =4\n"
                            + "LEFT JOIN billing_marca m ON p.marca_id = m.id\n"
                            + "LEFT JOIN billing_productogrupo gp ON gp.codigo = p.productogrupo_codigo\n"
                            + "WHERE LOWER(p.nombreUnico)LIKE '%" + searchName + "%' \n"
                            + "OR UPPER(p.nombreUnico)LIKE '%" + searchName + "%'");
                }
                if (search_cliente.equals("1")) {
                    pss = c.prepareStatement("SELECT p.codigo codp, p.codigo2, p.nombreUnico, p.descripcion, p.estado, m.nombre marca,gp.nombre grupo,pp.valor pvp\n"
                            + "FROM billing_producto p\n"
                            + "LEFT JOIN producto_precio pp ON p.codigo = pp.id_producto AND pp.id_precio =4\n"
                            + "LEFT JOIN billing_marca m ON p.marca_id = m.id\n"
                            + "LEFT JOIN billing_productogrupo gp ON gp.codigo = p.productogrupo_codigo\n"
                            + "WHERE p.codigo = " + searchName);
                }
                if (search_cliente.equals("2")) {
                    pss = c.prepareStatement("SELECT p.codigo codp, p.codigo2, p.nombreUnico, p.descripcion, p.estado, m.nombre marca,gp.nombre grupo,pp.valor pvp\n"
                            + "FROM billing_producto p\n"
                            + "LEFT JOIN producto_precio pp ON p.codigo = pp.id_producto AND pp.id_precio =4\n"
                            + "LEFT JOIN billing_marca m ON p.marca_id = m.id\n"
                            + "LEFT JOIN billing_productogrupo gp ON gp.codigo = p.productogrupo_codigo\n"
                            + "WHERE LOWER(p.codigo2)LIKE '%" + searchName + "%' \n"
                            + "OR UPPER(p.codigo2)LIKE '%" + searchName + "%'");

                }

                ResultSet rss = pss.executeQuery();
                while (rss.next()) {
                    String act_desc = rss.getString("estado");
                    String txt_search = null;
                    if (act_desc.equals("1")) {
                        txt_search = "Activo";
                    }
                    if (act_desc.equals("0")) {
                        txt_search = "Inactivo";
                    }
                    Double pvp_siniva = rss.getDouble("pvp");

                    Double val = pvp_siniva * 1.12;
                    Double iva = Redondear(val);
                    Double siniva = Redondear(pvp_siniva);
                    modeloTabla.addRow(new Object[]{
                        rss.getString("codp"),
                        rss.getString("codigo2"),
                        rss.getString("nombreUnico"),
                        rss.getString("descripcion"),
                        siniva + " / " + iva,
                        rss.getString("marca"),
                        rss.getString("grupo"),
                        txt_search

                    });
                }

                this.vista.tabla_clientes.setModel(modeloTabla);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                con.desconectar();
            }
        }
        if (e.getSource() == vista.btn_modificar) {
            String id_mar = null;
            String n_marca = null;

            String id_grp = null;
            String n_grp = null;

            int filaEditar = vista.tabla_clientes.getSelectedRow();
            int numfilas = vista.tabla_clientes.getSelectedRowCount();
            if (filaEditar >= 0 && numfilas == 1) {
                String codigo_pro = String.valueOf(vista.tabla_clientes.getValueAt(filaEditar, 0));
                vista.btn_guardar.setText("Modificar Producto");
                vista.hidden_update_new.setText("1");
                Connection c = con.conexion();
                try {
                    PreparedStatement cli_pss = c.prepareStatement("SELECT p.codigo cod,p.codigo2,p.nombreUnico,p.descripcion,p.estado,	m.nombre marca,m.id id_marca,gp.nombre grupo, gp.codigo cod_grp,pp.valor pvp\n"
                            + "FROM billing_producto p\n"
                            + "LEFT JOIN producto_precio pp ON p.codigo = pp.id_producto AND pp.id_precio =4\n"
                            + "LEFT JOIN billing_marca m ON p.marca_id = m.id\n"
                            + "LEFT JOIN billing_productogrupo gp ON gp.codigo = p.productogrupo_codigo\n"
                            + "WHERE p.codigo = " + codigo_pro);
                    ResultSet cli_rss = cli_pss.executeQuery();
                    if (cli_rss.next()) {
                        vista.txt_codigo_pro.setText(cli_rss.getString("cod"));
                        vista.txt_nombre_producto.setText(cli_rss.getString("nombreUnico"));
                        vista.txt_codigocomun_pro.setText(cli_rss.getString("codigo2"));
                        vista.txt_descripcion.setText(cli_rss.getString("descripcion"));
                        Double pvp_siniva = cli_rss.getDouble("pvp");
                        Double val = pvp_siniva * 1.12;
                        Double iva = Redondear(val);
                        vista.txt_pvp.setText(cli_rss.getString("pvp"));
                        vista.txt_pvp_iva.setText(iva.toString());

                        String esAct = cli_rss.getString("estado");
                        if (esAct.equals("1")) {
                            vista.rb_activo.setSelected(true);
                        }
                        if (esAct.equals("0")) {
                            vista.rb_inactivo.setSelected(true);
                        }

                        id_mar = cli_rss.getString("id_marca");
                        PreparedStatement smtmarca = c.prepareStatement("SELECT * FROM billing_marca where id=?");
                        smtmarca.setString(1, id_mar);
                        ResultSet filial_rs = smtmarca.executeQuery();
                        if (filial_rs.next()) {
                            n_marca = filial_rs.getString("nombre");
                            marca f = new marca();
                            f.id = id_mar;
                            f.nombre = n_marca;
                            vista.cbm_marca.setSelectedItem(f);
                        }

                        id_grp = cli_rss.getString("cod_grp");
                        System.out.println("++" + id_grp);
                        PreparedStatement smtgrp = c.prepareStatement("SELECT * FROM billing_productogrupo where codigo=?");
                        smtgrp.setString(1, id_grp);
                        ResultSet grp_rs = smtgrp.executeQuery();
                        if (grp_rs.next()) {
                            n_grp = grp_rs.getString("nombre");
                            System.out.println("/////" + n_grp);

                            grupo fg = new grupo();
                            fg.codigo = id_grp;
                            fg.nombre = n_grp;
                            vista.cbm_producto.setSelectedItem(fg);
                        }

                    }
                    activar_campos();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    con.desconectar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR AL MENOS UN DATO PARA EDITAR");
            }

        }
        if (e.getSource() == vista.btn_inactivar) {
            int filaEditar = vista.tabla_clientes.getSelectedRow();
            int numfilas = vista.tabla_clientes.getSelectedRowCount();
            if (filaEditar >= 0 && numfilas == 1) {
                String cedula_selec = String.valueOf(vista.tabla_clientes.getValueAt(filaEditar, 0));
                Connection c = con.conexion();
                try {
                    int rtp_usu = JOptionPane.showConfirmDialog(null, "DESEA INACTIVAR EL SELECCIONADO");
                    if (rtp_usu == 0) {
                        PreparedStatement update_cli = c.prepareStatement("UPDATE billing_cliente SET estaActivo=0 where PersonaComercio_cedulaRuc=?");
                        update_cli.setString(1, cedula_selec);
                        update_cli.execute();
                        JOptionPane.showMessageDialog(null, "CLIENTE INACTIVADO CORRECTAMENTE");
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    con.desconectar();
                }
            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR AL MENOS UN DATO PARA INACTIVAR");
            }

        }

    }

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    public void activar_campos() {
        vista.txt_codigocomun_pro.setEnabled(true);
        vista.txt_descripcion.setEnabled(true);
        vista.txt_nombre_producto.setEnabled(true);
        vista.txt_pvp.setEnabled(true);
        vista.rb_activo.setEnabled(true);
        vista.rb_inactivo.setEnabled(true);
        vista.btn_guardar.setEnabled(true);
        vista.cbm_marca.setEnabled(true);
        vista.cbm_producto.setEnabled(true);
        vista.btn_nuevo.setEnabled(false);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
