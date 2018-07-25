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
public class model_cxc_ac {

    String nro;
    String fecha;
    String codigo;
    String descripcion;
    String valor;
    String abono;
    String saldo;
   // String estado;

    public model_cxc_ac(String nro, String fecha, String codigo, String descripcion, String valor, String abono, String saldo) {
        this.nro = nro;
        this.fecha = fecha;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.valor = valor;
        this.abono = abono;
        this.saldo = saldo;
       // this.estado = estado;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAbono() {
        return abono;
    }

    public void setAbono(String abono) {
        this.abono = abono;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

//    public String getEstado() {
//        return estado;
//    }
//
//    public void setEstado(String estado) {
//        this.estado = estado;
//    }
}
