package com.example.tyler.finalproject;

/**
 * Created by Tyler on 12/2/2015.
 */
public class Beverage {
    private int id;
    private String name;
    private String type;
    private double average_rating;
    private String description;

    public Beverage(int id, String name, String type, double average_rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.average_rating = average_rating;
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(double average_rating) {
        this.average_rating = average_rating;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toJson() {
        return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"type\":" +
                type + ", \"average_rating\":\"" + average_rating + "\"}";
    }
}
