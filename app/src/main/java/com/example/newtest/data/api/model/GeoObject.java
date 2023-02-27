package com.example.newtest.data.api.model;

public class GeoObject{
    public GeoObject(Locality locality) {
        this.locality = locality;
    }
    private final Locality locality;
    public Locality getLocality() {
        return this.locality;
    }

}