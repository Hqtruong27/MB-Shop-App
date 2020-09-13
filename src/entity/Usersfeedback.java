/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author TIEN NGUYEN
 */
public class Usersfeedback {
    int Id;
    boolean rate;
    String comment;

    public Usersfeedback() {
    }

    public Usersfeedback(int Id, boolean rate, String comment) {
        this.Id = Id;
        this.rate = rate;
        this.comment = comment;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public boolean isRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    
    
}
