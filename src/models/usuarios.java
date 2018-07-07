/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author AngelCm <angelvcuenca@gmail.com>
 */
public class usuarios {

    String id;
    String PersonaComercio_cedulaRuc;
    String username;
    String clave;
    String nombres;
    String apellidos;
    String  id_tipo_rol;
    String fecha_ult_conexion;
    String tipo_usuario;
    String correo;
    String celular;
    String estado;
    String tipo_almacen;

    public usuarios() {
        id = "";
        fecha_ult_conexion = "";
        PersonaComercio_cedulaRuc = "";
        username = "";
        clave = "";
        nombres = "";
        apellidos = "";
        id_tipo_rol = "";
        tipo_usuario = "";
        correo = "";
        celular = "";
        tipo_almacen = "";
        estado = "";
        

    }

    

    public usuarios(String id, String nombres) {
        this.id = id;
        this.nombres = nombres;
    }
    
    public String toString(){
        return this.nombres;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getFecha_ult_conexion() {
        return fecha_ult_conexion;
    }

    public void setFecha_ult_conexion(String fecha_ult_conexion) {
        this.fecha_ult_conexion = fecha_ult_conexion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonaComercio_cedulaRuc() {
        return PersonaComercio_cedulaRuc;
    }

    public void setPersonaComercio_cedulaRuc(String PersonaComercio_cedulaRuc) {
        this.PersonaComercio_cedulaRuc = PersonaComercio_cedulaRuc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getId_tipo_rol() {
        return id_tipo_rol;
    }

    public void setId_tipo_rol(String id_tipo_rol) {
        this.id_tipo_rol = id_tipo_rol;
    }
    
    public String getTipo_almacen() {
        return tipo_almacen;
    }

    public void setTipo_almacen(String tipo_almacen) {
        this.tipo_almacen = tipo_almacen;
    }
   
}
