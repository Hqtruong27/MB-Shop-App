/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB.Ketnoi;
import entity.Category;
import entity.Login;
import entity.LoginRegister;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIEN NGUYEN
 */
public class LoginRegisterDAO {

    public boolean insertLoginRegister(LoginRegister lr) {
        boolean bl = false;
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Ketnoi.open();

        try {
            pstmt = con.prepareStatement("insert into Users(name,email,password,phone,address) values (?,?,?,?,?)");
            pstmt.setString(1, lr.getName());
            pstmt.setString(2, lr.getEmail());
            pstmt.setString(3, lr.getPassword());
            pstmt.setString(4, lr.getPhone());
            pstmt.setString(5, lr.getAddress());
            int i = pstmt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginRegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pstmt, rs);
        }

        return bl;
    }

    public List<Login> getAllUser() {
        List<Login> list = new ArrayList<>();
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            pt = con.prepareCall("{ call LoginUser }");
            rs = pt.executeQuery();
            while (rs.next()) {
                Login login = new Login();
                login.setId(rs.getInt("id"));
                login.setName(rs.getString("name"));
                login.setEmail(rs.getString("email"));
                login.setPhone(rs.getInt("phone"));
                login.setPassword(rs.getString("password"));
                login.setAddress(rs.getString("address"));
                login.setGender(rs.getBoolean("gender"));
                login.setBirthDay(rs.getDate("birthday"));
                list.add(login);
         
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }
        return list;
    }
}
