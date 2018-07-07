/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author TOSHIBA
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conexion_bd {

    Connection conn;
    static Statement st;
    static ResultSet rt;
    public String puerto = "3306";
    public String nameservidor = "localhost";
    public String db = "bill_novamoda";
    public String user = "root";
    public String pass = "Masterpc%%16";
    public String ruta = "jdbc:mysql://";
    public String servidor = nameservidor + ":" + puerto + "/";

    public Connection conexion() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(ruta + servidor + db, user, pass);
            if (conn != null) {
                System.out.println("Conectado...");
            } else if (conn == null) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: " + e.getMessage());
        } finally {
            return conn;
        }

    }


    public void desconectar() {
        //conn = null;
        
        try {
            if(conn!=null)
            {
                conn.close();
            }
            System.out.println("Desconectado...");
        } catch (SQLException ex) {
            Logger.getLogger(conexion_bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
