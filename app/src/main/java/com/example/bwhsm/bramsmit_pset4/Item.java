package com.example.bwhsm.bramsmit_pset4;

/**
 * Created by bwhsm on 25-9-2017.
 */

public class Item {
    private int id;
    private String title;

    public Item() {
    }

    public Item(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
