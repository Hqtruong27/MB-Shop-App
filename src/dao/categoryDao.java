/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import DB.Ketnoi;
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
 * @author mrtvh
 */
public class categoryDao {

    public boolean InsertCategory(Category c) {
        boolean bl = false;
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            pt = con.prepareCall("insert into Category values(?)");
            pt.setString(1, c.getName());
            int i = pt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }

        return bl;
    }

    public List<Category> GetAllCategory() {
        List<Category> list = new ArrayList<>();
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            pt = con.prepareCall("{ call pCategory }");
            rs = pt.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }
        return list;
    }

    public boolean UpdateCategory(Category c) {
        boolean bl = false;
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();

        try {
            pt = con.prepareCall("update Category set name=? where id=?");
            pt.setString(1, c.getName());
            pt.setInt(2, c.getId());
            int i = pt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }

        return bl;
    }

    public boolean DeleteCategory(int id) {
        boolean bl = false;
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            pt = con.prepareStatement("delete Category where id=?");
            pt.setInt(1, id);
            int i = pt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }

        return bl;
    }

    public List<Category> searchCategory(String sql) {
        List<Category> list = new ArrayList<>();
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            pt = con.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }
        return list;
    }

}
