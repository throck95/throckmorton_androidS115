package com.example.tyler.finalproject;

import java.util.ArrayList;

/**
 * Created by Tyler on 12/8/2015.
 */
public class CommentList {
    private ArrayList<Comment> commentArrayList;

    public CommentList() {
        commentArrayList = new ArrayList<>();
    }

    public String[] getText() {
        String[] text = new String[commentArrayList.size()];
        for(int i = 0; i < text.length; i++) {
            text[i] = commentArrayList.get(i).getUser_fname() + "   -   " + commentArrayList.get(i).getRating() + "\n\"";
            text[i] += commentArrayList.get(i).getComment_descrip() + "\"";
        }
        return text;
    }

    public void add(Comment comment) {
        commentArrayList.add(comment);
    }
}
