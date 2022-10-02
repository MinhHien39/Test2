package org.meicode.mymovie.Genres;

import com.google.gson.annotations.SerializedName;

import org.meicode.mymovie.Genres.Genres;

import java.util.List;

public class GenresResponse {
    @SerializedName("genres")
    private List<Genres> listModel;

    public List<Genres> getListModel() {
        return listModel;
    }

    public void setListModel(List<Genres> listModel) {
        this.listModel = listModel;
    }

    }

