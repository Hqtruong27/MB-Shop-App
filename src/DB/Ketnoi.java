/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hqtru
 *
 * coded by Hqtruong27
 */
public class Ketnoi {

    public static Connection open(){
        Connection con = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Mobile_Shop", "sa", "1234$");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ketnoi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ketnoi.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public static void main(String[] args) {
        System.out.println(open());
    }
     public static void closeAll(Connection con, PreparedStatement ps, ResultSet rs){
         if (con != null) {           
             try {
                 con.close();
             } catch (SQLException ex) {
                 Logger.getLogger(Ketnoi.class.getName()).log(Level.SEVERE, null, ex);
             }           
         }
        if (ps != null) {           
             try {
                 ps.close();
             } catch (SQLException ex) {
                 Logger.getLogger(Ketnoi.class.getName()).log(Level.SEVERE, null, ex);
             }           
         }
        if (rs != null) {           
             try {
                 rs.close();
             } catch (SQLException ex) {
                 Logger.getLogger(Ketnoi.class.getName()).log(Level.SEVERE, null, ex);
             }           
         }
     }
}
