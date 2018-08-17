/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.*;
import views.*;

/**
 *
 * @author AngelCM <angelvcuenca@gmail.com>
 */
public class ctrl_usuarios implements ActionListener, KeyListener {

    views_usuarios vista = new views_usuarios();
    usuariosDAO model = new usuariosDAO();
    DefaultTableModel modelT = new DefaultTableModel();
    conexion_bd con = new conexion_bd();

    public ctrl_usuarios(views_usuarios vista, usuariosDAO model) {
        this.vista = vista;
        this.model = model;
        this.vista.btn_newDato.addActionListener(this);
        this.vista.btn_listarDatos.addActionListener(this);
        this.vista.btn_actulizarDatos.addActionListener(this);
        this.vista.btn_eliminarDatos.addActionListener(this);
        this.vista.btn_ok.addActionListener(this);
        this.vista.txt_RucDniPass.addKeyListener(this);
        this.vista.txt_nombreUser.addKeyListener(this);
        this.vista.txt_buscar.addKeyListener(this);

        modelT.addColumn("ID");
        modelT.addColumn("DNI/Ruc/Pass");
        modelT.addColumn("Nombres");
        modelT.addColumn("Apellidos");
        modelT.addColumn("Celular");
        modelT.addColumn("Email");
        modelT.addColumn("Username");
        modelT.addColumn("Almacen");
        modelT.addColumn("Estado");
    }

     public void llenartabla_horarios() {
        while (modelT.getRowCount() > 0) {
            modelT.removeRow(0);
        }
        Connection c = con.conexion();
        try {
            
            
            PreparedStatement psa = c.prepareStatement("SELECT * \n"
                    + "FROM billing_empleado \n");
            ResultSet rsa = psa.executeQuery();

            while (rsa.next()) {
                String act_desc = rsa.getString("estaActivo");
                
                String txt_search = null;
                if (act_desc.equals("1")) {
                    txt_search = "Activo";
                }
                if (act_desc.equals("0")) {
                    txt_search = "Inactivo";
                }
                
                modelT.addRow(new Object[]{
                    rsa.getString(1),
                    rsa.getString(3),
                    rsa.getString(6),
                    rsa.getString(7),
                    rsa.getString(10),
                    rsa.getString(8),
                    rsa.getString(4),
                    rsa.getString(28),
                    txt_search
                });
            }
            this.vista.tablaUsuarios.setModel(modelT);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            con.desconectar();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_newDato) {
            vista.rb_activo.setEnabled(true);
            vista.rb_cedula_ruc.setEnabled(true);
            vista.rb_inactivo.setEnabled(true);
            vista.rb_passport.setEnabled(true);
            vista.txt_RucDniPass.setEnabled(true);
            vista.txt_apellido_user.setEnabled(true);
            vista.txt_buscar.setEnabled(true);
            vista.txt_celular_user.setEnabled(true);
            vista.txt_clave_usr.setEnabled(true);
            vista.txt_correo_user.setEnabled(true);
            vista.txt_id_usuario.setEnabled(true);
            vista.txt_nombreUser.setEnabled(true);
            vista.txt_username_user.setEnabled(true);

            ValidarIdentificacion d = new ValidarIdentificacion();
            try {
                vista.rb_cedula_ruc.setActionCommand("0");
                vista.rb_passport.setActionCommand("1");
                String dni_ruc_pass = vista.group_identificacion.getSelection().getActionCommand();
                String aux_dniRucPass = null;
                if (dni_ruc_pass.equals("0")) {
                    aux_dniRucPass = "0";
                }
                if (dni_ruc_pass.equals("1")) {
                    aux_dniRucPass = "1";
                }

                vista.rb_inactivo.setActionCommand("0");
                vista.rb_activo.setActionCommand("1");
                String estad_pass = vista.group_estado.getSelection().getActionCommand();
                String envia_est = null;
                if (estad_pass.equals("0")) {
                    envia_est = "0";
                }
                if (estad_pass.equals("1")) {
                    envia_est = "1";
                }

                String PersonaComercio_cedulaRuc = vista.txt_RucDniPass.getText();
                String name_user = vista.txt_nombreUser.getText();
                String apellidos_user = vista.txt_apellido_user.getText();
                String celular_user = vista.txt_celular_user.getText();
                String email_user = vista.txt_correo_user.getText();
                String username_user = vista.txt_username_user.getText();
                String clave_user = new String(vista.txt_clave_usr.getPassword());
                String combo = vista.cmb_empleado_comedor.getSelectedItem().toString();
                String letra = null;

                if (PersonaComercio_cedulaRuc.equals("") || name_user.equals("")
                        || vista.txt_apellido_user.getText().equals("") || vista.txt_celular_user.equals("")
                        || vista.txt_correo_user.getText().equals("") || vista.txt_username_user.equals("")
                        || clave_user.equals("") || combo.equals("Seleccionar")) {
                    JOptionPane.showMessageDialog(null, "LLENE POR FAVOR TODOS LOS CAMPOS");
                } else {

                    int[] a = d.get_tipo_doc_id(aux_dniRucPass, PersonaComercio_cedulaRuc);
                    int clienTipo = a[0];
                    int tipoRuc = a[1];
                    if (clienTipo == 0 && tipoRuc == 0) {
                        JOptionPane.showMessageDialog(null, "El documento de identidad ->" + PersonaComercio_cedulaRuc + "<- parece no ser valido.");
                    } else {
                        if (combo.equals("Sucursal_1")) {
                            letra = "Sucursal 1";
                        }
                        if (combo.equals("Sucursal_2")) {
                            letra = "Sucursal 2";
                        }
                        if (combo.equals("Sucursal_3")) {
                            letra = "Sucursal 3";
                        }
                        if (combo.equals("Sucursal_4")) {
                            letra = "Sucursal 4";
                        }
                        if (combo.equals("Sucursal_5")) {
                            letra = "Sucursal 5";
                        }
                        
                        if (combo.equals("Principal")) {
                            letra = "Principal";
                        }
                        String rtp = model.insertUsuario(letra, combo, envia_est, PersonaComercio_cedulaRuc, username_user, clave_user, name_user, apellidos_user, email_user, celular_user);
                        if (rtp != null) {
                            JOptionPane.showMessageDialog(null, rtp);
                            limpiarElementos();
                            llenartabla_horarios();
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == vista.btn_listarDatos) {
            llenartabla_horarios();
            vista.btn_actulizarDatos.setEnabled(true);
            vista.btn_eliminarDatos.setEnabled(true);
          
        }

        if (e.getSource() == vista.btn_actulizarDatos) {
            int filaEditar = vista.tablaUsuarios.getSelectedRow();
            int numfilas = vista.tablaUsuarios.getSelectedRowCount();
            if (filaEditar >= 0 && numfilas == 1) {
                String idDep = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 0));
                String dni = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 1));
                String name = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 2));
                String apellido = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 3));
                String celular = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 4));
                String email = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 5));
                String username = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 6));
                String almacen_h = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 7));
                String estado_h = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 8));
                if (almacen_h.equals("Sucursal 1")) {
                    vista.cmb_empleado_comedor.setSelectedItem("Sucursal_1");
                }
                if (almacen_h.equals("Sucursal 2")) {
                    vista.cmb_empleado_comedor.setSelectedItem("Sucursal_2");
                }
                if (almacen_h.equals("Sucursal 3")) {
                    vista.cmb_empleado_comedor.setSelectedItem("Sucursal_3");
                }
                if (almacen_h.equals("Sucursal 4")) {
                    vista.cmb_empleado_comedor.setSelectedItem("Sucursal_4");
                }
                if (almacen_h.equals("Sucursal 5")) {
                    vista.cmb_empleado_comedor.setSelectedItem("Sucursal_5");
                }
                
                if (almacen_h.equals("Principal")) {
                    vista.cmb_empleado_comedor.setSelectedItem("Principal");
                    
                }
                if (estado_h.equals("Activo")) {
                    vista.rb_activo.setSelected(true);
                }
                if (estado_h.equals("Inactivo")) {
                    vista.rb_inactivo.setSelected(true);
                }
                activa_campos();
                vista.txt_id_usuario.setText(idDep);
                vista.txt_RucDniPass.setText(dni);
                vista.txt_nombreUser.setText(name);
                vista.txt_apellido_user.setText(apellido);
                vista.txt_celular_user.setText(celular);
                vista.txt_correo_user.setText(email);
                vista.txt_username_user.setText(username);

                vista.btn_newDato.setEnabled(false);
                vista.btn_listarDatos.setEnabled(false);
                vista.btn_ok.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR AL MENOS UN DATO PARA EDITAR");
            }
        }

        if (e.getSource() == vista.btn_ok) {

            String idDep = vista.txt_id_usuario.getText();
            String dni = vista.txt_RucDniPass.getText();
            String name = vista.txt_nombreUser.getText();
            String apellido = vista.txt_apellido_user.getText();
            String celular = vista.txt_celular_user.getText();
            String email = vista.txt_correo_user.getText();
            String username = vista.txt_username_user.getText();
            String clave_user = new String(vista.txt_clave_usr.getPassword());
            String combo = vista.cmb_empleado_comedor.getSelectedItem().toString();

            int filaEditar = vista.tablaUsuarios.getSelectedRow();
            int numfilas = vista.tablaUsuarios.getSelectedRowCount();
            String idDe = null;

            if (filaEditar >= 0 && numfilas == 1) {
                idDe = String.valueOf(vista.tablaUsuarios.getValueAt(filaEditar, 0));

            } else {
                JOptionPane.showMessageDialog(null, "DEBE DE SELECCIONAR AL MENOS UN DATO PARA EDITAR");
            }

            ValidarIdentificacion d = new ValidarIdentificacion();
            try {
                vista.rb_cedula_ruc.setActionCommand("0");
                vista.rb_passport.setActionCommand("1");
                String dni_ruc_pass = vista.group_identificacion.getSelection().getActionCommand();
                String aux_dniRucPass = null;
                if (dni_ruc_pass.equals("0")) {
                    aux_dniRucPass = "0";
                }
                if (dni_ruc_pass.equals("1")) {
                    aux_dniRucPass = "1";
                }
                vista.rb_inactivo.setActionCommand("0");
                vista.rb_activo.setActionCommand("1");
                String estad_pass = vista.group_estado.getSelection().getActionCommand();
                String envia_est = null;
                if (estad_pass.equals("0")) {
                    envia_est = "0";
                }
                if (estad_pass.equals("1")) {
                    envia_est = "1";
                }
                if (dni.equals("") || name.equals("") || vista.txt_apellido_user.getText().equals("") || vista.txt_celular_user.equals("")
                        || vista.txt_correo_user.getText().equals("") || vista.txt_username_user.equals("")
                        || clave_user.equals("")) {
                    JOptionPane.showMessageDialog(null, "LLENE POR FAVOR TODOS LOS CAMPOS");
                } else {
                    int[] a = d.get_tipo_doc_id(aux_dniRucPass, dni);
                    int clienTipo = a[0];
                    int tipoRuc = a[1];
                    if (clienTipo == 0 && tipoRuc == 0) {
                        JOptionPane.showMessageDialog(null, "El documento de identidad ->" + dni + "<- parece no ser valido.");
                    } else {

                        int rpt_edit = model.editarUsuarios(combo, envia_est, idDe, dni, username, clave_user, name, apellido, email, celular);

                        if (rpt_edit > 0) {
                            JOptionPane.showMessageDialog(null, "REGISTRO EDITADO");
                            llenartabla_horarios();
                            limpiarElementos();
                        }
                    }
                    vista.btn_eliminarDatos.setEnabled(true);
                    vista.btn_newDato.setEnabled(true);
                    vista.btn_actulizarDatos.setEnabled(true);
                    vista.btn_listarDatos.setEnabled(true);
                    vista.btn_ok.setEnabled(false);

                }
            } catch (Exception ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (e.getSource() == vista.btn_eliminarDatos) {
            int filaInicio = vista.tablaUsuarios.getSelectedRow();
            int numFS = vista.tablaUsuarios.getSelectedRowCount();
            ArrayList<String> listaId = new ArrayList();
            String idhele = null;
            if (numFS > 0) {
                for (int i = 0; i < numFS; i++) {
                    idhele = String.valueOf(vista.tablaUsuarios.getValueAt(i + filaInicio, 0));
                    listaId.add(idhele);
                }
                for (int j = 0; j < numFS; j++) {
                    int rtp_usu = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR EL o LOS REGISTROs SELECCIONADOS");
                    if (rtp_usu == 0) {
                        model.eliminarUsuario(idhele);
                    }
                }
                llenartabla_horarios();

            } else {
                JOptionPane.showMessageDialog(null, "DEBE AL MENOS SELECCIONA UNA FILA PARA ELIMINAR");
            }

        }

    }

    public void limpiarElementos() {
        vista.txt_RucDniPass.setText("");
        vista.txt_nombreUser.setText("");
        vista.txt_apellido_user.setText("");
        vista.txt_celular_user.setText("");
        vista.txt_clave_usr.setText("");
        vista.txt_username_user.setText("");
        vista.txt_correo_user.setText("");
        vista.txt_id_usuario.setText("");
        vista.rb_cedula_ruc.setSelected(true);
        vista.rb_passport.setSelected(false);
        vista.txt_buscar.setText("");

    }

    public void activa_campos() {
        vista.rb_activo.setEnabled(true);
        vista.rb_cedula_ruc.setEnabled(true);
        vista.rb_inactivo.setEnabled(true);
        vista.rb_passport.setEnabled(true);
        vista.txt_RucDniPass.setEnabled(true);
        vista.txt_apellido_user.setEnabled(true);
        vista.txt_buscar.setEnabled(true);
        vista.txt_celular_user.setEnabled(true);
        vista.txt_clave_usr.setEnabled(true);
        vista.txt_correo_user.setEnabled(true);
        vista.txt_id_usuario.setEnabled(true);
        vista.txt_nombreUser.setEnabled(true);
        vista.txt_username_user.setEnabled(true);
        vista.cmb_empleado_comedor.setEnabled(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txt_buscar) {
            String nomDepar = vista.txt_buscar.getText();
            DefaultTableModel modelT = new DefaultTableModel();
            vista.tablaUsuarios.setModel(modelT);

            modelT.addColumn("ID");
            modelT.addColumn("DNI/Ruc/Pass");
            modelT.addColumn("Nombres");
            modelT.addColumn("Apellidos");
            modelT.addColumn("Celular");
            modelT.addColumn("Email");
            modelT.addColumn("Username");

            Object[] columna = new Object[7];
            int numreg = model.searchUsuario(nomDepar).size();
            for (int i = 0; i < numreg; i++) {
                columna[0] = model.searchUsuario(nomDepar).get(i).getId();
                columna[1] = model.searchUsuario(nomDepar).get(i).getPersonaComercio_cedulaRuc();
                columna[2] = model.searchUsuario(nomDepar).get(i).getNombres();
                columna[3] = model.searchUsuario(nomDepar).get(i).getApellidos();
                columna[4] = model.searchUsuario(nomDepar).get(i).getCelular();
                columna[5] = model.searchUsuario(nomDepar).get(i).getCorreo();
                columna[6] = model.searchUsuario(nomDepar).get(i).getUsername();

                modelT.addRow(columna);
            }
        }
    }

}
