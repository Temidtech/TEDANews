package com.swiftsynq.tedanews.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsSources {

    @SerializedName("status")
    private String status;
    @SerializedName("sources")
    private List<Sources> sources;

    public String getStatus() {
        return status;
    }

    public List<Sources> getArticles() {
        return sources;
    }
}
