package com.swiftsynq.tedanews.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model of News.
 */
public class News {

    @SerializedName("articles")
    private List<Articles> articles;
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private int totalResults;

    public List<Articles> getArticlesResponse() {
        return articles;
    }
    public String getStatusResponse() {
        return status;
    }
    public int getTotalResultResponse() {
        return totalResults;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public String toString() {
        return articles.toString();
    }

}
