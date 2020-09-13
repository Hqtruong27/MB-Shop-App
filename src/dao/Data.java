/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author hqtru
 */
public class Data {
    public static int id;
    public static int setId(int userId){
        id = userId;
        return id;
    }
    public static int getId(){
        return id;
    }
}
