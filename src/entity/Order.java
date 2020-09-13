/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author hqtru
 */
public class Order {
    int  maDH;
    int users_id;
    Date  orderDate;
    String nameUser;
    String email;
    int  phone;
    String address;
    int status;

    public Order() {
    }

    public Order(int maDH, int users_id, Date orderDate, String nameUser, String email, int phone, String address, int status) {
        this.maDH = maDH;
        this.users_id = users_id;
        this.orderDate = orderDate;
        this.nameUser = nameUser;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

    
     

    
    
}
