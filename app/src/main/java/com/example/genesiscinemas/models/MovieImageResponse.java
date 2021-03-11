package com.example.genesiscinemas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieImageResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrops")
    @Expose
    private List<Object> backdrops = null;
    @SerializedName("posters")
    @Expose
    private List<Object> posters = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Object> backdrops) {
        this.backdrops = backdrops;
    }

    public List<Object> getPosters() {
        return posters;
    }

    public void setPosters(List<Object> posters) {
        this.posters = posters;
    }

}