package org.meicode.mymovie.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.meicode.mymovie.Genres.Genres;
import org.meicode.mymovie.Model.ModelCollection;
import org.meicode.mymovie.Model.Movie;

import java.util.List;
@Entity(tableName = "movieTable")
public class InfoDetailMovie {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "poster_path")
    public String poster_path;
    @ColumnInfo(name = "overview")
    public String overview;
//    @ColumnInfo(name = "genres")
//    public <List>Genres genres;
    @ColumnInfo(name = "release_date")
    public String release_date;
    @ColumnInfo(name = "title")
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ColumnInfo(name ="vote_average")
    public double vote_average;

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public InfoDetailMovie fromMovie(Movie movie){
        this.poster_path = movie.getPoster_path();
        this.overview = movie.getOverview();
        //this.genres = movie.getGenres();
        this.release_date = movie.getRelease_date();
        this.id = movie.getId();
        this.vote_average = movie.getVote_average();
        this.title = movie.getTitle();
        return this;
    }

}
