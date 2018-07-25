/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Elizabeth
 */
public class model_informe_cxc {
    String cedula;
    String cliente;
    String direccion;
    String telefonos;
    String fecha_emision;
    String valor_total;
    String fecha_ult_abono;
    String valor_abono;
    String saldo_total; 

    public model_informe_cxc(String cedula, String cliente, String direccion, String telefonos, String fecha_emision, String valor_total, String fecha_ult_abono, String valor_abono, String saldo_total) {
        this.cedula = cedula;
        this.cliente = cliente;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.fecha_emision = fecha_emision;
        this.valor_total = valor_total;
        this.fecha_ult_abono = fecha_ult_abono;
        this.valor_abono = valor_abono;
        this.saldo_total = saldo_total;
    }
    
    public String getcedula() {
        return cedula;
    }

    public void setcedula(String cedula) {
        this.cedula = cedula;
    }

    public String getcliente() {
        return cliente;
    }

    public void setcliente(String cliente) {
        this.cliente = cliente;
    }

    public String getdireccion() {
        return direccion;
    }

    public void setdireccion(String direccion) {
        this.direccion = direccion;
    }

    public String gettelefonos() {
        return telefonos;
    }

    public void settelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getfecha_emision() {
        return fecha_emision;
    }

    public void setfecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getvalor_total() {
        return valor_total;
    }

    public void setvalor_total(String valor_total) {
        this.valor_total = valor_total;
    }

    public String getfecha_ult_abono() {
        return fecha_ult_abono;
    }

    public void setfecha_ult_abono(String fecha_ult_abono) {
        this.fecha_ult_abono = fecha_ult_abono;
    }

    public String getvalor_abono() {
        return valor_abono;
    }

    public void setvalor_abono(String valor_abono) {
        this.valor_abono = valor_abono;
    }

    public String getsaldo_total() {
        return saldo_total;
    }

    public void setsaldo_total(String saldo_total) {
        this.saldo_total = saldo_total;
    }
    
}
