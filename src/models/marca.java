/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ANGEL_CUENCA
 */
public class marca {

    public String id;
    public String nombre;

    
    public marca() {
        
    }

    public marca(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public String toString(){
        return this.nombre;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        if (!(o instanceof marca)) {
            return false;
        }
        marca filial = (marca) o;
        if (id != null ? !id.equals(filial.id) : filial.id != null) {
            return false;
        }
        if (nombre != null ? !nombre.equals(filial.nombre) : filial.nombre != null) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        
        return hash;
    }

}
