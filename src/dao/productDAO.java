/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB.Ketnoi;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.product;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hqtru
 */
public class productDAO {

    public boolean InsertCategory(product pp) {
        boolean bl = false;
        Connection con;
        CallableStatement ca = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ca = con.prepareCall("{ call pProduct (?,?,?,?,?,?,?,?)}");
            ca.setString(1, pp.getName());
            ca.setFloat(2, pp.getPrice());
            ca.setFloat(3, pp.getSalePrice());
            ca.setString(4, pp.getImages());
            ca.setString(5, pp.getContent());
            ca.setInt(6, pp.getCategory_id());
            ca.setString(7, pp.getGuarantee());
            ca.setBoolean(8, pp.isStatus());
            int i = ca.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ca, rs);
        }

        return bl;
    }

    public List<product> GetAllCategory() {
        List<product> _listP = new ArrayList<>();
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            pt = con.prepareCall("{ call _selectProduct }");
            rs = pt.executeQuery();
            while (rs.next()) {
                product _p = new product();
                _p.setId(rs.getInt("id"));
                _p.setName(rs.getString("name"));
                _p.setPrice(rs.getFloat("price"));
                _p.setSalePrice(rs.getFloat("saleprice"));
                _p.setImages(rs.getString("images"));
                _p.setContent(rs.getString("content"));
                _p.setCategory_id(rs.getInt("category_id"));
                _p.setGuarantee(rs.getString("guarantee"));
                _p.setStatus(rs.getBoolean("status"));
                _p.setNameCategory(rs.getString("categoryName"));
                _listP.add(_p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }
        return _listP;
    }

    public boolean DeleteCategory(int id) {
        boolean bl = false;
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();

        try {
            pt = con.prepareStatement("delete Product where id=?");
            pt.setInt(1, id);
            int i = pt.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(productDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }

        return bl;
    }

    public boolean UpdateProduct(product pp) {
        boolean bl = false;
        Connection con;
        CallableStatement ca = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ca = con.prepareCall("{call UpdateProduct(?,?,?,?,?,?,?,?,?)}");
            ca.setInt(1, pp.getId());
            ca.setString(2, pp.getName());
            ca.setFloat(3, pp.getPrice());
            ca.setFloat(4, pp.getSalePrice());
            ca.setString(5, pp.getImages());
            ca.setString(6, pp.getContent());
            ca.setInt(7, pp.getCategory_id());
            ca.setString(8, pp.getGuarantee());
            ca.setBoolean(9, pp.isStatus());
            int i = ca.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ca, rs);
        }

        return bl;
    }

    public List<product> search_product(String sql) {
        List<product> list = new ArrayList<>();
        Connection con;
        PreparedStatement pt = null;
        ResultSet rs = null;

        con = Ketnoi.open();

        try {
            pt = con.prepareStatement(sql);
            rs = pt.executeQuery();
            while (rs.next()) {
                product p = new product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));
                p.setSalePrice(rs.getFloat("saleprice"));
                p.setImages(rs.getString("images"));
                p.setContent(rs.getString("content"));
                p.setCategory_id(rs.getInt("category_id"));
                p.setGuarantee(rs.getString("guarantee"));
                p.setStatus(rs.getBoolean("status"));
                p.setNameCategory(rs.getString("CategoryName"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(productDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, pt, rs);
        }
        return list;
    }
    public List<product> GetAllCategorybyId(int id) {
        List<product> _listP = new ArrayList<>();
        Connection con;
        CallableStatement ca = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ca = con.prepareCall("{ call _ProductforCategory (?) }");
            ca.setInt(1, id);
            rs = ca.executeQuery();
            while (rs.next()) {
                product _p = new product();
                _p.setId(rs.getInt("id"));
                _p.setName(rs.getString("name"));
                _p.setPrice(rs.getFloat("price"));
                _p.setSalePrice(rs.getFloat("saleprice"));
                _p.setImages(rs.getString("images"));
                _p.setContent(rs.getString("content"));
                _p.setCategory_id(rs.getInt("category_id"));
                _p.setGuarantee(rs.getString("guarantee"));
                _p.setStatus(rs.getBoolean("status"));
                _listP.add(_p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ca, rs);
        }
        return _listP;
    }
    public static product getCategorybyId(int id) {
        product _p = null;
        Connection con;
        PreparedStatement ca = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ca = con.prepareStatement("select * from Product where id = ? ");
            ca.setInt(1, id);
            rs = ca.executeQuery();
            while (rs.next()) {
                _p = new product();
//                _p.setId(id);
                _p.setName(rs.getString("name"));
                _p.setPrice(rs.getFloat("price"));
                _p.setSalePrice(rs.getFloat("saleprice"));
                _p.setImages(rs.getString("images"));
                _p.setContent(rs.getString("content"));
                _p.setCategory_id(rs.getInt("category_id"));
                _p.setGuarantee(rs.getString("guarantee"));
                _p.setStatus(rs.getBoolean("status"));
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ca, rs);
        }
        return _p;
    }
}
