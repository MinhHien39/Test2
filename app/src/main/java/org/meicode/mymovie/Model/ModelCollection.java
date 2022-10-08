package org.meicode.mymovie.Model;

import com.google.gson.annotations.SerializedName;

public class ModelCollection {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("poster_path")
    public String poster_path;

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


    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
}
