package com.example.newtest.data.api.model;

import java.util.Date;
import java.util.List;

public class RootWeather {

    public RootWeather(Date now_dt, Fact fact, GeoObject geo_object, Info info, List<Forecast> forecasts) {
        this.now_dt = now_dt;
        this.fact = fact;
        this.geo_object = geo_object;
        this.info = info;
        this.forecasts = forecasts;
    }

    private final Date now_dt;
    public Date getNow_dt() {
        return this.now_dt;
    }

    private final Fact fact;
    public Fact getFact() {
        return this.fact;
    }
    private final GeoObject geo_object;
    public GeoObject getGeoObject() {
        return this.geo_object;
    }

    private final Info info;
    public Info getInfo() {
        return this.info;
    }
    private final List<Forecast> forecasts;
    public List<Forecast> getForecasts() {
        return forecasts;
    }
}

