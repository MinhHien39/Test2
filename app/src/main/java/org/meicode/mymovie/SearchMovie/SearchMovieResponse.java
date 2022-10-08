package org.meicode.mymovie.SearchMovie;

import com.google.gson.annotations.SerializedName;

import org.meicode.mymovie.Model.Movie;
import org.meicode.mymovie.Movie.InfoMovie;

import java.util.List;

public class SearchMovieResponse {
    @SerializedName("page")
    public int  page;
    @SerializedName("results")
    public List<Movie> movieList;


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
