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
 * 
 * coded by Hqtruong27
 */
public class Login {
    int id;
    String name;
    String email;
    int phone;
    String password;
    boolean group_name;
    String address;
    boolean gender;
    Date birthDay;

    public Login() {
    }

    public Login(int id, String name, String email, int phone, String password, boolean group_name, String address, boolean gender, Date birthDay) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.group_name = group_name;
        this.address = address;
        this.gender = gender;
        this.birthDay = birthDay;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGroup_name() {
        return group_name;
    }

    public void setGroup_name(boolean group_name) {
        this.group_name = group_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
 
      
}
