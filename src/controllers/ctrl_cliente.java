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
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ctrl_cliente implements ActionListener, KeyListener {

    Cliente vista = new Cliente();
    conexion_bd con = new conexion_bd();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public ctrl_cliente(Cliente vista) {
        this.vista = vista;
        //botones
        this.vista.btn_guardar.addActionListener(this);
        this.vista.btn_nuevo.addActionListener(this);
        this.vista.btn_search.addActionListener(this);
        this.vista.btn_modificar.addActionListener(this);
        this.vista.btn_inactivar.addActionListener(this);

        modeloTabla.addColumn("Nro.Ced");
        modeloTabla.addColumn("Nombres");
        modeloTabla.addColumn("Apellidos");
        modeloTabla.addColumn("Celular");
        modeloTabla.addColumn("Tfno. Casa");
        modeloTabla.addColumn("E-mail");
        modeloTabla.addColumn("Dir.Casa");
        modeloTabla.addColumn("Nro.Casa");
        modeloTabla.addColumn("Tfno. Trabajp");
        modeloTabla.addColumn("Dir. Trabajo");
        modeloTabla.addColumn("Fec. Nacimiento");
        modeloTabla.addColumn("Estado");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_nuevo) {
            activar_campos();
            vista.btn_guardar.setText("Nuevo Cliente");
            vista.hidden_update_new.setText("2");


        }
        if (e.getSource() == vista.btn_guardar) {
            ValidarIdentificacion d = new ValidarIdentificacion();
            try {
                vista.rb_cedula_ruc.setActionCommand("0");
                vista.rb_passport.setActionCommand("1");
                String dni_ruc_pass = vista.grupo_dni_pass.getSelection().getActionCommand();
                String aux_dniRucPass = null;
                if (dni_ruc_pass.equals("0")) {
                    aux_dniRucPass = "0";
                }
                if (dni_ruc_pass.equals("1")) {
                    aux_dniRucPass = "1";
                }

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

                String RucDniPass_cliente = vista.txt_RucDniPass_cliente.getText();
                String name_cliente = vista.txt_names_cliente.getText();
                String apellido_cliente = vista.txt_apellido_cliente.getText();
                String celular_cliente = vista.txt_celular_cliente.getText();
                String telefonos_cliente = vista.txt_telefonos_cliente.getText();
                String correo_cliente = vista.txt_correo_cliente.getText();
                String direccion_cliente = vista.txt_direccion_cliente.getText();
                String nro_casa = vista.txt_nro_casa.getText();
                String fono_trabajo = vista.txt_fono_trabajo.getText();
                String dir_trabajo = vista.txt_dir_trabajo.getText();
                Date fecha = vista.date_fechanacimiento.getDate();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                 String fe = formato.format(fecha);
                if (RucDniPass_cliente.equals("")
                        || apellido_cliente.equals("")
                        //|| celular_cliente.equals("")
                        //|| correo_cliente.equals("")
                        //|| cupo_credito.equals("")
                        //|| dias_credito.equals("")
                        //|| direccion_cliente.equals("")
                        || name_cliente.equals("")) {
                    //|| porcentaje_desc.equals("")
                    //|| razonSocial_cliente.equals("")
                    //|| search_cliente.equals("")
                    //|| telefonos_cliente.equals("")) {
                    JOptionPane.showMessageDialog(null, "LLENE POR FAVOR TODOS LOS CAMPOS MARCADOS POR ASTERISCO ROJO");
                } else {

                    int[] a = d.get_tipo_doc_id(aux_dniRucPass, RucDniPass_cliente);
                    int clienTipo = a[0];
                    int tipoRuc = a[1];
                    if (clienTipo == 0 && tipoRuc == 0) {
                        JOptionPane.showMessageDialog(null, "El documento de identidad ->" + RucDniPass_cliente + "<- parece no ser valido.");
                    } else {
                        Connection c = con.conexion();
                        String hidden_select_update_new = vista.hidden_update_new.getText();
                        try {

                            if (hidden_select_update_new.equals("1")) {
                                /*UPDATE CLIENTE*/
                                PreparedStatement act = c.prepareStatement("UPDATE billing_cliente SET PersonaComercio_cedulaRuc=?,nombres=?,apellidos=?,celular=?,telefonos=?,email=?,direccion=?,id_nro_poste=?,telefono2=?,direccion2=?, estaActivo=?, fecha_nacimiento_cli=?\n"
                                        + "WHERE PersonaComercio_cedulaRuc=?");
                                act.setString(1, RucDniPass_cliente);
                                act.setString(2, name_cliente);
                                act.setString(3, apellido_cliente);
                                act.setString(4, celular_cliente);
                                act.setString(5, telefonos_cliente);
                                act.setString(6, correo_cliente);
                                act.setString(7, direccion_cliente);
                                act.setString(8, nro_casa);
                                act.setString(9, fono_trabajo);
                                act.setString(10, dir_trabajo);
                                act.setString(11, estad_pass);
                                act.setDate(12, java.sql.Date.valueOf(fe));
                                act.setString(13, RucDniPass_cliente);
                                
                                act.execute();
                                
                                
                                JOptionPane.showMessageDialog(null, "CLIENTE MODIFICADO CORRECTAMENTE", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                                limpiar_data();
                                list_cliente(RucDniPass_cliente);
                                
                            }
                            if (hidden_select_update_new.equals("2")) {
                                /* NEW CLIENTE*/
                                PreparedStatement verStmt = c.prepareStatement("SELECT * \n"
                                        + "FROM billing_cliente \n"
                                        + "WHERE PersonaComercio_cedulaRuc=?");
                                verStmt.setString(1, RucDniPass_cliente);
                                ResultSet rs_v = verStmt.executeQuery();

                                if (rs_v.last()) {
                                    JOptionPane.showMessageDialog(null, "EL CLIENTE QUE INTENTA AGREGAR YA SE ENCUENTRA REGISTRADO", "VERIFIQUE POR FAVOR", JOptionPane.ERROR_MESSAGE);
                                    //limpiar_data();
                                    list_cliente(RucDniPass_cliente);
                                } else {
                                    PreparedStatement se = c.prepareStatement("INSERT INTO billing_cliente(PersonaComercio_cedulaRuc,nombres,apellidos,celular,telefonos,email,direccion,id_nro_poste,telefono2,direccion2,clientetipo_idclientetipo, fecha_nacimiento_cli\n"
                                            + ",billing_cliente_id,es_pasaporte,tipo_ruc)\n"
                                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    se.setString(1, RucDniPass_cliente);
                                    se.setString(2, name_cliente);
                                    se.setString(3, apellido_cliente);
                                    se.setString(4, celular_cliente);
                                    se.setString(5, telefonos_cliente);
                                    se.setString(6, correo_cliente);
                                    se.setString(7, direccion_cliente);
                                    se.setString(8, nro_casa);
                                    se.setString(9, fono_trabajo);
                                    se.setString(10, dir_trabajo);
                                    se.setInt(11, 1);
                                    se.setString(12, "0");
                                    se.setString(13, aux_dniRucPass);
                                    se.setInt(14, tipoRuc);
                                    se.setDate(15, java.sql.Date.valueOf(fe));
                                    se.execute();
                                    JOptionPane.showMessageDialog(null, "CLIENTE AGREGADO CORRECTAMENTE", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                                }
                                limpiar_data();
                                list_cliente(RucDniPass_cliente);

                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());

                        } finally {
                            con.desconectar();
                        }
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
                vista.rb_search_nombres.setActionCommand("0");
                vista.rb_search_dni.setActionCommand("1");
                String search_cliente = vista.grupo_search_cliente.getSelection().getActionCommand();
                String searchName = vista.txt_search_cliente.getText();
                PreparedStatement pss = null;
                if (search_cliente.equals("0")) {
                    System.out.println("nombres y apelldiso" + searchName);
                    pss = c.prepareStatement("SELECT *\n"
                            + "FROM billing_cliente \n"
                            + "WHERE LOWER(CONCAT(apellidos, nombres))LIKE '%" + searchName + "%' \n"
                            + "OR UPPER(CONCAT(apellidos, nombres))LIKE '%" + searchName + "%'");
                }
                if (search_cliente.equals("1")) {
                    System.out.println("cedula" + searchName);
                    pss = c.prepareStatement("SELECT *\n"
                            + "FROM billing_cliente \n"
                            + "WHERE PersonaComercio_cedulaRuc =" + searchName);

                }

                ResultSet rss = pss.executeQuery();
                while (rss.next()) {
                    String act_desc = rss.getString("estaActivo");
                    String txt_search = null;
                    if (act_desc.equals("1")) {
                        txt_search = "Activo";
                    }
                    if (act_desc.equals("0")) {
                        txt_search = "Inactivo";
                    }
                    modeloTabla.addRow(new Object[]{
                        rss.getString("PersonaComercio_cedulaRuc"),
                        rss.getString("nombres"),
                        rss.getString("apellidos"),
                        rss.getString("celular"),
                        rss.getString("telefonos"),
                        rss.getString("email"),
                        rss.getString("direccion"),
                        rss.getString("id_nro_poste"),
                        rss.getString("telefono2"),
                        rss.getString("direccion2"),
                        rss.getString("fecha_nacimiento_cli"),
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
            int filaEditar = vista.tabla_clientes.getSelectedRow();
            int numfilas = vista.tabla_clientes.getSelectedRowCount();
            if (filaEditar >= 0 && numfilas == 1) {
                String cedula_selec = String.valueOf(vista.tabla_clientes.getValueAt(filaEditar, 0));
                vista.btn_guardar.setText("Modificar Cliente");
                vista.hidden_update_new.setText("1");
                Connection c = con.conexion();
                try {
                    PreparedStatement cli_pss = c.prepareStatement("SELECT *\n"
                            + "FROM billing_cliente \n"
                            + "WHERE PersonaComercio_cedulaRuc =" + cedula_selec);
                    ResultSet cli_rss = cli_pss.executeQuery();
                    while (cli_rss.next()) {
                        vista.txt_RucDniPass_cliente.setText(cli_rss.getString("PersonaComercio_cedulaRuc"));
                        vista.txt_names_cliente.setText(cli_rss.getString("nombres"));
                        vista.txt_apellido_cliente.setText(cli_rss.getString("apellidos"));
                        vista.txt_celular_cliente.setText(cli_rss.getString("celular"));
                        vista.txt_telefonos_cliente.setText(cli_rss.getString("telefonos"));
                        vista.txt_correo_cliente.setText(cli_rss.getString("email"));
                        vista.txt_direccion_cliente.setText(cli_rss.getString("direccion"));
                        vista.txt_nro_casa.setText(cli_rss.getString("id_nro_poste"));
                        vista.txt_fono_trabajo.setText(cli_rss.getString("telefono2"));
                        vista.txt_dir_trabajo.setText(cli_rss.getString("direccion2"));
                        vista.date_fechanacimiento.setDate(cli_rss.getDate("fecha_nacimiento_cli"));
                        String esPa = cli_rss.getString("es_pasaporte");
                        if (esPa.equals("0")) {
                            vista.rb_cedula_ruc.setSelected(true);
                        }
                        if (esPa.equals("1")) {
                            vista.rb_passport.setSelected(true);
                        }

                        String esAct = cli_rss.getString("estaActivo");
                        if (esAct.equals("1")) {
                            vista.rb_activo.setSelected(true);
                        }
                        if (esAct.equals("0")) {
                            vista.rb_inactivo.setSelected(true);
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

    public void activar_campos() {
        vista.txt_RucDniPass_cliente.setEnabled(true);
        vista.txt_names_cliente.setEnabled(true);
        vista.txt_celular_cliente.setEnabled(true);
        vista.txt_correo_cliente.setEnabled(true);
        vista.txt_fono_trabajo.setEnabled(true);
        vista.txt_direccion_cliente.setEnabled(true);
        vista.txt_apellido_cliente.setEnabled(true);
        vista.txt_nro_casa.setEnabled(true);
        vista.txt_telefonos_cliente.setEnabled(true);
        vista.txt_dir_trabajo.setEnabled(true);
        vista.date_fechanacimiento.setEnabled(true);
        vista.rb_activo.setEnabled(true);
        vista.rb_cedula_ruc.setEnabled(true);
        vista.rb_inactivo.setEnabled(true);
        vista.rb_passport.setEnabled(true);
        vista.btn_guardar.setEnabled(true);
        vista.btn_nuevo.setEnabled(false);
        
    
    
    }

    public void limpiar_data() {
        /*BLOQUEAR*/
        vista.txt_RucDniPass_cliente.setEnabled(false);
        vista.txt_names_cliente.setEnabled(false);
        vista.txt_celular_cliente.setEnabled(false);
        vista.txt_correo_cliente.setEnabled(false);
        vista.txt_fono_trabajo.setEnabled(false);
        vista.txt_direccion_cliente.setEnabled(false);
        vista.txt_apellido_cliente.setEnabled(false);
        vista.txt_nro_casa.setEnabled(false);
        vista.txt_telefonos_cliente.setEnabled(false);
        vista.txt_dir_trabajo.setEnabled(false);
        vista.date_fechanacimiento.setEnabled(false);
        
        vista.rb_activo.setEnabled(false);
        vista.rb_cedula_ruc.setEnabled(false);
        vista.rb_inactivo.setEnabled(false);
        vista.rb_passport.setEnabled(false);
        vista.btn_guardar.setEnabled(false);
        vista.btn_inactivar.setEnabled(true);
        vista.btn_modificar.setEnabled(true);
        

        /*LIMPIA DATOS*/
        vista.txt_RucDniPass_cliente.setText("");
        vista.date_fechanacimiento.setDate(new Date());
        vista.txt_names_cliente.setText("");
        vista.txt_celular_cliente.setText("");
        vista.txt_correo_cliente.setText("");
        vista.txt_fono_trabajo.setText("");
        vista.txt_direccion_cliente.setText("");
        vista.txt_apellido_cliente.setText("");
        vista.txt_nro_casa.setText("");
        vista.txt_telefonos_cliente.setText("");
        vista.txt_dir_trabajo.setText("");
        vista.rb_cedula_ruc.setSelected(true);
        vista.rb_passport.setSelected(false);
        vista.rb_activo.setSelected(true);
        vista.rb_inactivo.setSelected(false);
        vista.btn_nuevo.setEnabled(true);

    }

    public void list_cliente(String searchName) {
        Connection c = con.conexion();
        while (modeloTabla.getRowCount() > 0) {
            modeloTabla.removeRow(0);
        }

        try {
            PreparedStatement pss = null;
            pss = c.prepareStatement("SELECT *\n"
                    + "FROM billing_cliente \n"
                    + "WHERE PersonaComercio_cedulaRuc =" + searchName);

            ResultSet rss = pss.executeQuery();
            while (rss.next()) {
                String act_desc = rss.getString("estaActivo");
                String txt_search = null;
                if (act_desc.equals("1")) {
                    txt_search = "Activo";
                }
                if (act_desc.equals("0")) {
                    txt_search = "Inactivo";
                }
                modeloTabla.addRow(new Object[]{
                    rss.getString("PersonaComercio_cedulaRuc"),
                    rss.getString("nombres"),
                    rss.getString("apellidos"),
                    rss.getString("celular"),
                    rss.getString("telefonos"),
                    rss.getString("email"),
                    rss.getString("direccion"),
                    rss.getString("id_nro_poste"),
                    rss.getString("telefono2"),
                    rss.getString("direccion2"),
                    rss.getString("fecha_nacimiento_cli"),
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
