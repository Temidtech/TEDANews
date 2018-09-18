package com.swiftsynq.tedanews.Model;

import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("id")
    private String Id;

    @SerializedName("name")
    private String Name;

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }
}
