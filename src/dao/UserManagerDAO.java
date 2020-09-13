/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB.Ketnoi;
import entity.UserManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN NGUYEN
 */
public class UserManagerDAO {
     public boolean insertUserManager(UserManager um){
        boolean bl = false;
        
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Ketnoi.open();
        try {
            pstmt = con.prepareStatement("insert into Users(name,email,phone,password,address) values (?,?,?,?,?)");
            pstmt.setString(1, um.getName());
            pstmt.setString(2, um.getEmail());
            pstmt.setString(3, um.getPhone());
            pstmt.setString(4, um.getPassword());
            pstmt.setString(5, um.getAddress());
            
            int i = pstmt.executeUpdate();
            if(i>0)
                bl = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Ketnoi.closeAll(con, pstmt, rs);
        }
        
        return bl;
    }
    public UserManager findByName(String name){
        UserManager um = null;
        
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Ketnoi.open();
        try {
            pstmt = con.prepareStatement("select * from Users where name = ?");
            pstmt.setString(1, name);
            
            rs = pstmt.executeQuery();
            if(rs.next()){
                um = new UserManager();
                um.setId(rs.getInt("id"));
                um.setName(rs.getString("name"));
                um.setEmail(rs.getString("email"));
                um.setPhone(rs.getString("phone"));
                um.setPassword(rs.getString("password"));
                um.setAddress(rs.getString("address"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Ketnoi.closeAll(con, pstmt, rs);
        }
        
        return um;
    }
   public boolean deleteUserManager(String id){
       boolean bl = false;
       
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Ketnoi.open();
        try {
            pstmt = con.prepareStatement("delete from Users where id = ?");
            pstmt.setString(1, id);
           int i = pstmt.executeUpdate();
           
           if(i>0)
               bl = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Ketnoi.closeAll(con, pstmt, rs);
        }
       
       return bl;
   }
}
