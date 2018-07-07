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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.*;
import views.*;

/**
 *
 * @author AngelCM <angelvcuenca@gmail.com>
 */
public class ctrl_empleado_permisos implements ActionListener, KeyListener {

    views_empleado_permisos vista = new views_empleado_permisos();
    usuariosDAO model = new usuariosDAO();
   // departamento_slq model_combo = new departamento_slq();

    public ctrl_empleado_permisos(views_empleado_permisos vista, usuariosDAO model) {
        this.vista = vista;
        this.model = model;
       // this.model_combo = model_com;

        this.vista.btn_listarDatos.addActionListener(this);
        this.vista.btn_newDato.addActionListener(this);
        this.vista.btn_eliminar.addActionListener(this);
        
        this.vista.txt_buscar.addKeyListener(this);

    }

    public ctrl_empleado_permisos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void llenartabla_departa_filial(JTable jt_horarios, String nomDepar) {
        DefaultTableModel modelT = new DefaultTableModel();
        jt_horarios.setModel(modelT);

        modelT.addColumn("Nro. Registro");
        modelT.addColumn("Roles");

      
         Object[] columna = new Object[2];
        int numreg = model.listaUsuarios_view(nomDepar).size();
        for (int i = 0; i < numreg; i++) {
            columna[0] = model.listaUsuarios_view(nomDepar).get(i).getId();
            columna[1] = model.listaUsuarios_view(nomDepar).get(i).getNombres();

            modelT.addRow(columna);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btn_newDato) {
            usuarios modela = (usuarios) this.vista.comb_filial.getSelectedItem();
            String id_empleado = modela.getId();
            int[] selectedrows = vista.tablaRoles.getSelectedRows();
            for (int i = 0; i < selectedrows.length; i++) {
                String roles = String.valueOf(vista.tablaRoles.getValueAt(selectedrows[i], 1));
                model.insertarEmpleadoRoles(id_empleado, roles);
            }
            llenartabla_departa_filial(vista.tabla_resultado, id_empleado);

        }
        if (e.getSource() == vista.btn_listarDatos) {
            usuarios modela = (usuarios) this.vista.comb_filial.getSelectedItem();
            String id_filial = modela.getId();
            String name_filial = modela.getNombres();
            vista.label_filial.setText(name_filial);
            llenartabla_departa_filial(vista.tabla_resultado, id_filial);
           // llenartabla_departa(vista.tablaRoles);
     
        }
        
        if (e.getSource() == vista.btn_eliminar) {
            usuarios modela = (usuarios) this.vista.comb_filial.getSelectedItem();
            String id_empleado = modela.getId();
            int filaInicio = vista.tabla_resultado.getSelectedRow();
            int numFS = vista.tabla_resultado.getSelectedRowCount();
            
            ArrayList<String> listaId = new ArrayList();
            String idhele = null;
            if (numFS > 0) {
                    idhele = String.valueOf(vista.tabla_resultado.getValueAt(filaInicio, 0));
                    int rtp_usu = JOptionPane.showConfirmDialog(null, "DESEA ELIMINAR EL REGISTRO SELECCIONADO");
                    if (rtp_usu == 0) {
                      model.eliminarEmpleadoRoles(idhele);
                    }
                
                llenartabla_departa_filial(vista.tabla_resultado, id_empleado);

            } else {
                JOptionPane.showMessageDialog(null, "DEBE AL MENOS SELECCIONA UNA FILA PARA ELIMINAR");
            }
            
            
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        if (e.getSource() == vista.txt_buscar) {
//            String nomDepar = vista.txt_buscar.getText();
//            DefaultTableModel modelT = new DefaultTableModel();
//            vista.tablaRoles.setModel(modelT);
//
//            modelT.addColumn("Nro. Registro");
//            modelT.addColumn("Nombre Departamento");
//
//            Object[] columna = new Object[2];
//            int numreg = model.searchDepartamento(nomDepar).size();
//            for (int i = 0; i < numreg; i++) {
//                columna[0] = model.searchDepartamento(nomDepar).get(i).getDep_id();
//                columna[1] = model.searchDepartamento(nomDepar).get(i).getDep_nombre();
//
//                modelT.addRow(columna);
//            }
//        }
    }

}
