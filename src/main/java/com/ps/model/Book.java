package com.ps.model;

public class Book {
    private int id;
    private String title;

    private int rating;

    public int getId() {return id;}

    public String getTitle() {return title;}

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
