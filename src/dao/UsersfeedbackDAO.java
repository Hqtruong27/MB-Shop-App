/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB.Ketnoi;
import entity.Usersfeedback;
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
public class UsersfeedbackDAO {
    public boolean insertfeedback(Usersfeedback fb) {
        boolean bl = false;
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Ketnoi.open();

        try {
            pstmt = con.prepareStatement("insert into feedback values (?,?)");
            pstmt.setBoolean(1, fb.isRate());
            pstmt.setString(2, fb.getComment());
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
    public List<Usersfeedback> getFeeback(){
        List<Usersfeedback> ListUser = new ArrayList<>();
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        con = Ketnoi.open();
        try {
            pstmt = con.prepareStatement(" select * from feedback ");
            rs = pstmt.executeQuery();
            if(rs.next()){
                Usersfeedback ufb = new Usersfeedback();
                ufb.setId(rs.getInt("Id"));
                ufb.setRate(rs.getBoolean("rate"));
                ufb.setComment(rs.getString("comment"));
                ListUser.add(ufb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Ketnoi.closeAll(con, pstmt, rs);
        }
        
        return ListUser;
    }
}
