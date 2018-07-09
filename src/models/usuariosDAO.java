package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AngelCM <angelvcuenca@gmail.com>
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class usuariosDAO {

    conexion_bd con;

    public usuariosDAO() {
        con = new conexion_bd();
    }

    //public boolean login(String usr, String pass) {
    public boolean login(usuarios usr) {

        Connection acceso = con.conexion();
        try {
            String md = getMD5("novamoda2018@" + usr.getClave());
            PreparedStatement verStmt = acceso.prepareStatement("SELECT u.id, u.PersonaComercio_cedulaRuc, u.username, u.clave, u.nombres, u.apellidos\n"
                    + "FROM billing_empleado u \n"
                    + "WHERE u.username=? AND u.estaActivo = 1");
            verStmt.setString(1, usr.getUsername());
            ResultSet rs = verStmt.executeQuery();
            if (rs.next()) {
                if (md.equals(rs.getString("clave"))) {
                    PreparedStatement guardarStmt = acceso.prepareStatement("UPDATE billing_empleado\n"
                            + "SET f_desde = ? \n"
                            + "WHERE id=?");
                    guardarStmt.setString(1, usr.getFecha_ult_conexion());
                    guardarStmt.setString(2, rs.getString(1));
                    guardarStmt.execute();

                    PreparedStatement se = acceso.prepareStatement("INSERT INTO cat_historial_sesiones(id_user,fecha_hora_sesion)\n"
                            + "VALUES(?,?)");
                    se.setString(1, rs.getString(1));
                    se.setString(2, usr.getFecha_ult_conexion());
                    se.execute();

                    usr.setId(rs.getString("id"));
                    usr.setPersonaComercio_cedulaRuc(rs.getString("PersonaComercio_cedulaRuc"));
                    usr.setNombres(rs.getString("nombres"));
                    usr.setApellidos(rs.getString("apellidos"));
                    //usr.setId_tipo_rol(rs.getString(7));
                    //usr.setTipo_usuario(rs.getString(8));

                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        } finally {
            con.desconectar();
        }
        //return false;

    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<usuarios> listar_Usuarios() {
        ArrayList listaDepar = new ArrayList();
        usuarios usuarios;
        try {
            Connection acceso = con.conexion();
            PreparedStatement ps = acceso.prepareStatement("SELECT * \n"
                    + "FROM billing_empleado \n");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuarios = new usuarios();
                usuarios.setId(rs.getString(1));
                usuarios.setPersonaComercio_cedulaRuc(rs.getString(3));
                usuarios.setNombres(rs.getString(6));
                usuarios.setApellidos(rs.getString(7));
                usuarios.setCelular(rs.getString(10));
                usuarios.setCorreo(rs.getString(8));
                usuarios.setUsername(rs.getString(4));
                usuarios.setEstado(rs.getString(17));
                usuarios.setTipo_almacen(rs.getString(28));

                listaDepar.add(usuarios);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
        return listaDepar;
    }

    public String insertUsuario(String letra, String combo, String estaActivo, String dni, String user_name, String clave, String name, String apellido, String email, String celular) {
        String rpta_horario = null;

        try {
            Connection acceso = con.conexion();
            Date date = new Date();
            DateFormat fomdate = new SimpleDateFormat("yyyy-MM-dd");
            String fecha_ingreso = fomdate.format(date);
            PreparedStatement verStmt = acceso.prepareStatement("SELECT * \n"
                    + "FROM billing_empleado \n"
                    + "WHERE PersonaComercio_cedulaRuc=?");
            verStmt.setString(1, dni);
            ResultSet rs_v = verStmt.executeQuery();

            if (rs_v.last()) {
                JOptionPane.showMessageDialog(null, "EL USUARIO YA EXISTE", "VERIFIQUE POR FAVOR", JOptionPane.ERROR_MESSAGE);

            } else {
                
                String md = getMD5("A3!1VGDDAifLJSRWI0p?gH:y" + clave);

                PreparedStatement guardarStmt = acceso.prepareStatement("INSERT INTO billing_empleado(PersonaComercio_cedulaRuc,username,clave,nombres,apellidos, email, celular, fechaIngreso, observaciones, estaActivo, tipo_almacen) \n"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                guardarStmt.setString(1, dni);
                guardarStmt.setString(2, user_name);
                guardarStmt.setString(3, md);
                guardarStmt.setString(4, name);
                guardarStmt.setString(5, apellido);
                guardarStmt.setString(6, email);
                guardarStmt.setString(7, celular);
                guardarStmt.setString(8, fecha_ingreso);
                guardarStmt.setString(9, clave);
                guardarStmt.setString(10, estaActivo);
                guardarStmt.setString(11, letra);
                
                boolean numFa = guardarStmt.execute();
                PreparedStatement pss = acceso.prepareStatement("select id  as id_user from billing_empleado  order by id desc limit 1");
                ResultSet rss = pss.executeQuery();
                while (rss.next()) {
                    String codigo = rss.getString("id_user");

                    actuliza_id(codigo);
                }

                if (numFa == false) {
                    rpta_horario = "REGISTRO AGREGADO CORRECTAMENTE";
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
        return rpta_horario;
    }

    public void actuliza_id(String id_cod) {
        Connection acceso = con.conexion();
        try {
            PreparedStatement guardarStmt_ult = acceso.prepareStatement("UPDATE billing_empleado set tipo_medic=? where id=?");
            guardarStmt_ult.setString(1, id_cod);
            guardarStmt_ult.setString(2, id_cod);
            guardarStmt_ult.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
    }

    public int editarUsuarios(String combo,String estaActivo, String id, String dni, String user_name, String clave, String name, String apellido, String email, String celular) {

        int rpta_horario = 0;
        try {
            Connection acceso = con.conexion();
            String md = getMD5("novamoda2018@" + clave);

            PreparedStatement guardarStmt = acceso.prepareStatement("UPDATE billing_empleado \n"
                    + "SET  PersonaComercio_cedulaRuc=?,username=?,clave=?,nombres=?,apellidos=?, email=?, celular=?, estaActivo=?, ccostos_id=?, tipo_almacen=?\n"
                    + "WHERE id = ?");
            guardarStmt.setString(1, dni);
            guardarStmt.setString(2, user_name);
            guardarStmt.setString(3, md);
            guardarStmt.setString(4, name);
            guardarStmt.setString(5, apellido);
            guardarStmt.setString(6, email);
            guardarStmt.setString(7, celular);
            guardarStmt.setString(8, estaActivo);
            guardarStmt.setString(9, estaActivo);
            guardarStmt.setString(10, combo);
            
            guardarStmt.setString(11, id);

            boolean numFa = guardarStmt.execute();
            if (numFa == false) {
                rpta_horario = 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
        return rpta_horario;
    }

    public int eliminarUsuario(String id) {
        int rpta_horario = 0;
        try {
            Connection acceso = con.conexion();
            PreparedStatement guardarStmt = acceso.prepareStatement("UPDATE billing_empleado \n"
                    + "SET estaActivo = 0\n"
                    + "WHERE id=?");
            guardarStmt.setString(1, id);

            boolean numFa = guardarStmt.execute();
            if (numFa == false) {
                rpta_horario = 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
        return rpta_horario;
    }

    public ArrayList<usuarios> searchUsuario(String nombreFilial) {
        ArrayList<usuarios> lista = new ArrayList();
        usuarios usuarios;

        try {
            Connection acceso = con.conexion();
            //select * from cat_horarios where inicio_h like  ('12:%');
            PreparedStatement ps = acceso.prepareStatement("SELECT * FROM billing_empleado WHERE estaActivo = 1 AND UPPER( CONCAT(nombres, apellidos))LIKE '%" + nombreFilial + "%'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuarios = new usuarios();
                usuarios.setId(rs.getString(1));
                usuarios.setPersonaComercio_cedulaRuc(rs.getString(3));
                usuarios.setNombres(rs.getString(6));
                usuarios.setApellidos(rs.getString(7));
                usuarios.setCelular(rs.getString(9));
                usuarios.setCorreo(rs.getString(8));
                usuarios.setUsername(rs.getString(4));
                lista.add(usuarios);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ArrayList<usuarios> listaUsuarios_view(String id_emp) {
        ArrayList listaDepar = new ArrayList();
        usuarios usuarios;
        try {
            Connection acceso = con.conexion();
            PreparedStatement ps = acceso.prepareStatement("SELECT c.id_rol,c.tipo_usuario,\n"
                    + "	CONCAT(d.nombres, ' ', d.apellidos) empleado \n"
                    + "FROM cat_roles c\n"
                    + "JOIN billing_empleado d ON c.id_empleado = d.id\n"
                    + "WHERE c.id_empleado = ?");
            ps.setString(1, id_emp);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usuarios = new usuarios();
                usuarios.setId(rs.getString(1));
                usuarios.setNombres(rs.getString(2));

                listaDepar.add(usuarios);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
        return listaDepar;
    }

    public String insertarEmpleadoRoles(String id_empleado, String roles) {
        String rpta_horario = null;

        try {
            Connection acceso = con.conexion();
            PreparedStatement verStmt = acceso.prepareStatement("SELECT * \n"
                    + "FROM cat_roles \n"
                    + "WHERE tipo_usuario=? AND id_empleado=?");
            verStmt.setString(1, roles);
            verStmt.setString(2, id_empleado);

            ResultSet rs_v = verStmt.executeQuery();

            if (rs_v.last()) {
                JOptionPane.showMessageDialog(null, "EL EMPLEADO TIENE YA EL ROL ASIGNADO", "VERIFIQUE POR FAVOR", JOptionPane.ERROR_MESSAGE);

            } else {

                PreparedStatement guardarStmt = acceso.prepareStatement("INSERT INTO cat_roles(tipo_usuario,id_empleado) VALUES(?,?)");
                guardarStmt.setString(1, roles);
                guardarStmt.setString(2, id_empleado);

                boolean numFa = guardarStmt.execute();
                if (numFa == false) {
                    rpta_horario = "REGISTRO AGREGADO CORRECTAMENTE";
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
        return rpta_horario;
    }

    public int eliminarEmpleadoRoles(String id_rol) {
        int rpta_horario = 0;
        try {
            Connection acceso = con.conexion();
            PreparedStatement guardarStmt = acceso.prepareStatement("DELETE FROM cat_roles WHERE id_rol=?");
            guardarStmt.setString(1, id_rol);
            boolean numFa = guardarStmt.execute();
            if (numFa == false) {
                rpta_horario = 1;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();
        }
        return rpta_horario;
    }

    public void listar_usuario(JComboBox box) {

        DefaultComboBoxModel value;
        try {
            value = new DefaultComboBoxModel();
            box.setModel(value);

            Connection acceso = con.conexion();
            PreparedStatement ps = acceso.prepareStatement("SELECT id, CONCAT(nombres,' ',apellidos) empleado \n"
                    + "FROM billing_empleado \n"
                    + "WHERE estaActivo = 1");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                value.addElement(new usuarios(rs.getString(1), rs.getString(2)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.desconectar();

        }
    }
}
