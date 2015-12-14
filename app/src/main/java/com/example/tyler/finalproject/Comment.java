package com.example.tyler.finalproject;

/**
 * Created by Tyler on 12/8/2015.
 */
public class Comment {
    private int comment_id;
    private int beverage_id;
    private String beverage_name;
    private String user_fname;
    private String comment_descrip;
    private double rating;

    public Comment(int comment_id, int beverage_id, String beverage_name, String user_fname, String comment_descrip, double rating) {
        this.comment_id = comment_id;
        this.beverage_id = beverage_id;
        this.beverage_name = beverage_name;
        this.user_fname = user_fname;
        this.comment_descrip = comment_descrip;
        this.rating = rating;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getBeverage_id() {
        return beverage_id;
    }

    public void setBeverage_id(int beverage_id) {
        this.beverage_id = beverage_id;
    }

    public String getBeverage_name() {
        return beverage_name;
    }

    public void setBeverage_name(String beverage_name) {
        this.beverage_name = beverage_name;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public String getComment_descrip() {
        return comment_descrip;
    }

    public void setComment_descrip(String comment_descrip) {
        this.comment_descrip = comment_descrip;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", beverage_id=" + beverage_id +
                ", beverage_name='" + beverage_name + '\'' +
                ", user_fname='" + user_fname + '\'' +
                ", comment_descrip='" + comment_descrip + '\'' +
                ", rating=" + rating +
                '}';
    }
}
