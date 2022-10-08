package org.meicode.mymovie.Movie;

import com.google.gson.annotations.SerializedName;

public class InfoMovie {
    @SerializedName("description")
    public String description;
    @SerializedName("favorite_count")
    public int favorite_count;
    @SerializedName("id")
    public int id;
    @SerializedName("item_count")
    public int item_count;
    @SerializedName("iso_639_1")
    public String iso_639_1;
    @SerializedName("list_type")
    public String list_type;
    @SerializedName("name")
    public String name;
    @SerializedName("poster_path")
    public String posterPath;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getList_type() {
        return list_type;
    }

    public void setList_type(String list_type) {
        this.list_type = list_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
