/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import DB.Ketnoi;
import entity.Order;
import entity.orderDetail;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hqtru
 */
public class orderDAO {

    public boolean inserts(Order od, int userid) {
        boolean bl = false;
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ps = con.prepareStatement(" insert into [Order] values(?,?,?,?,?,?,?) ");
            ps.setInt(1, userid);
            ps.setDate(2, new Date(od.getOrderDate().getTime()));
            ps.setString(3, od.getNameUser());
            ps.setString(4, od.getEmail());
            ps.setInt(5, od.getPhone());
            ps.setString(6, od.getAddress());
            ps.setString(6, od.getAddress());
            ps.setInt(7, od.getStatus());
            int i = ps.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ps, rs);
        }
        return bl;
    }

    public int getmaDathang() {
        int maDH = 0;
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ps = con.prepareStatement(" select top 1 * from [Order] order by id desc ");
            rs = ps.executeQuery();
            if (rs.next()) {
                maDH = rs.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ps, rs);
        }
        return maDH;
    }

    public boolean insertOrderDetail(orderDetail od) {
        boolean bl = false;
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ps = con.prepareStatement("insert into Order_detail values (?,?,?,?,?) ");
            ps.setInt(1, od.getOrderId());
            ps.setString(2, od.getProductName());
            ps.setInt(3, od.getQty());
            ps.setFloat(4, od.getPrice());
            ps.setFloat(5, od.getTotalPrice());
            int i = ps.executeUpdate();
            if (i > 0) {
                bl = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ps, rs);
        }
        return bl;
    }

    public List<orderDetail> getAllOrderDetail() {
        List<orderDetail> ListO = new ArrayList<>();
        Connection con;
        CallableStatement ca = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ca = con.prepareCall("{ call orderdetail }");
            rs = ca.executeQuery();
            while (rs.next()) {
                orderDetail orderDetail = new orderDetail();
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setProductName(rs.getString("productName"));
                orderDetail.setQty(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getFloat("price"));
                orderDetail.setTotalPrice(rs.getInt("totalprice"));
                ListO.add(orderDetail);
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ca, rs);
        }
        return ListO;
    }

    public List<Order> getOrderId(int userId) {
        List<Order> ListH = new ArrayList<>();
        Connection con;
        CallableStatement ca = null;
        ResultSet rs = null;
        con = Ketnoi.open();
        try {
            ca = con.prepareCall("{ call getOrderId  (?)}");
            ca.setInt(1, userId);
            rs = ca.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setMaDH(rs.getInt("id"));
                order.setUsers_id(rs.getInt("users_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setNameUser(rs.getString("fullname"));
                order.setEmail(rs.getString("email"));
                order.setPhone(rs.getInt("phone"));
                order.setAddress(rs.getString("address"));
                ListH.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ca, rs);
        }
        return ListH;
    }

    public List<Order> getOrder() {
        List<Order> ListH = new ArrayList<>();
        Connection con;
        CallableStatement ca = null;
        ResultSet rs = null;
        con = Ketnoi.open();
        try {
            ca = con.prepareCall("{ call getOrder }");
            rs = ca.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setMaDH(rs.getInt("id"));
                order.setUsers_id(rs.getInt("users_id"));
                order.setOrderDate(rs.getTimestamp("order_date"));
                order.setNameUser(rs.getString("fullname"));
                order.setEmail(rs.getString("email"));
                order.setPhone(rs.getInt("phone"));
                order.setAddress(rs.getString("address"));
                ListH.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ca, rs);
        }
        return ListH;
    }

    public List<orderDetail> getAllOrderdetail(int orderId) {
        List<orderDetail> ListOD = new ArrayList<>();
        Connection con;
        CallableStatement ca = null;
        ResultSet rs = null;

        con = Ketnoi.open();
        try {
            ca = con.prepareCall("{ call orderdetailbyOrderId (?) }");
            ca.setInt(1, orderId);
            rs = ca.executeQuery();
            while (rs.next()) {
                orderDetail or = new orderDetail();
                or.setOrderId(rs.getInt("order_id"));
                or.setProductName(rs.getString("productName"));
                or.setQty(rs.getInt("quantity"));
                or.setPrice(rs.getFloat("price"));
                or.setTotalPrice(rs.getFloat("totalprice"));
                ListOD.add(or);
            }

        } catch (SQLException ex) {
            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Ketnoi.closeAll(con, ca, rs);
        }
        return ListOD;
    }
    
    
    
//    public boolean updateStautusOrder(int status) {
//        boolean bl = false;
//        Connection con;
//        CallableStatement ca = null;
//        ResultSet rs = null;
//
//        con = Ketnoi.open();
//        try {
//            ca = con.prepareCall(" {  getStatusOrder  } ");
//            int i = ca.executeUpdate();
//            if (i > 0) {
//                bl = true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(categoryDao.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            Ketnoi.closeAll(con, ca, rs);
//        }
//        return bl;
//    }
}
