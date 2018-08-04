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
public class model_renumeracion {

    String id;
    String cliente;
    String num_new;
    String num_old;

    public model_renumeracion(String id, String cliente, String num_new, String num_old) {
        this.id = id;
        this.cliente = cliente;
        this.num_new = num_new;
        this.num_old = num_old;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNum_new() {
        return num_new;
    }

    public void setNum_new(String num_new) {
        this.num_new = num_new;
    }

    public String getNum_old() {
        return num_old;
    }

    public void setNum_old(String num_old) {
        this.num_old = num_old;
    }
    

}
