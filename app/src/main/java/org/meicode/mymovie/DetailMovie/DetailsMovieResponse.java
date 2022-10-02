package org.meicode.mymovie.DetailMovie;

import com.google.gson.annotations.SerializedName;

import org.meicode.mymovie.Model.Movie;

import java.util.List;

public class DetailsMovieResponse {
    @SerializedName("page")
    public int  page;
    @SerializedName("results")
    public List<Movie> movieList;

    public DetailsMovieResponse() {
    }

    public DetailsMovieResponse(int page) {
        this.page = page;

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}



