/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Desarrollo
 */
public class grupo {

    public String codigo;
    public String nombre;

    public grupo() {
      
    }

    public grupo(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
     public String toString(){
        return this.nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
          
    @Override  
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof grupo)) {
            return false;
        }
        grupo gp = (grupo) o;
        if (codigo != null ? !codigo.equals(gp.codigo) : gp.codigo != null) {
            return false;
        }
        if (nombre != null ? !nombre.equals(gp.nombre) : gp.nombre != null) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 89 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
        
        return hash;
    }
}
