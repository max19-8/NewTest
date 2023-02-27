package com.example.newtest.data.api.model;

public class Locality{

    public Locality(int id, String name) {
        this.id = id;
        this.name = name;
    }
    private final int id;
    public int getId() {
        return this.id;
    }
    private final String name;
    public String getName() {return this.name;}
}