package com.example.bwhsm.bramsmit_pset4;

/**
 * Created by bwhsm on 25-9-2017.
 */

public class Item {
    private int id;
    private String title;
    private Boolean completed = false;

    public Item() {}

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}

