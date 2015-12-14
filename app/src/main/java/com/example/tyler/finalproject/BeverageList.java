package com.example.tyler.finalproject;

import java.util.ArrayList;

/**
 * Created by Tyler on 12/2/2015.
 */
public class BeverageList {
    private ArrayList<Beverage> beverageArrayList;

    public BeverageList() {
        beverageArrayList = new ArrayList<>();
    }

    public String toJson() {
        String json = "[";
        for(int i = 0; i < beverageArrayList.size(); i++) {
            json += beverageArrayList.get(i).toJson();
            if(i+1 != beverageArrayList.size()) {
                json += ",";
            }
        }
        json += "]";
        return json;
    }

    public void add(Beverage beverage) {
        beverageArrayList.add(beverage);
    }

    public int getSize() { return beverageArrayList.size(); }

    public String[] getNames() {
        String[] names = new String[beverageArrayList.size()];
        for(int i = 0; i < beverageArrayList.size(); i++) {
            names[i] = beverageArrayList.get(i).getName();
        }
        return names;
    }

    public ArrayList<Beverage> getList() {
        return beverageArrayList;
    }
}
