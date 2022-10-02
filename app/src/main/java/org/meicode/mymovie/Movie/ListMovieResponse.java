package org.meicode.mymovie.Movie;

import com.google.gson.annotations.SerializedName;

import org.meicode.mymovie.Movie.InfoMovie;

import java.util.List;

public class ListMovieResponse {
    @SerializedName("id")
    public int id;
    @SerializedName("page")
    public String page;
    @SerializedName("results")
    public List<InfoMovie> infoMovieList;

    public List<InfoMovie> getInfoMovieList() {
        return infoMovieList;
    }

    public void setInfoMovieList(List<InfoMovie> infoMovieList) {
        this.infoMovieList = infoMovieList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
