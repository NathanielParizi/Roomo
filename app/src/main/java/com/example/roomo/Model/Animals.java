package com.example.roomo.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Animals {
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

    public String getSpeices() {
        return speices;
    }

    public void setSpeices(String speices) {
        this.speices = speices;
    }

    private int id;
    private String name;
    private String speices;

    public Animals(String name, String speices) {
        this.name = name;
        this.speices = speices;
    }

    public Animals(int id, String name, String speices) {
        this.id = id;
        this.name = name;
        this.speices = speices;
    }

    public Animals() {
        super();
    }


}
