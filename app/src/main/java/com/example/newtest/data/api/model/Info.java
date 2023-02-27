package com.example.newtest.data.api.model;

public class Info{

    public Info(Tzinfo tzinfo) {
        this.tzinfo = tzinfo;
    }
    private final Tzinfo tzinfo;
    public Tzinfo getTzInfo() {
        return this.tzinfo;
    }
}