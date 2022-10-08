package org.meicode.mymovie.Model;

import android.graphics.ColorSpace;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.meicode.mymovie.Genres.Genres;

import java.util.List;

public class Movie implements Parcelable {
    @SerializedName("poster_path")
    public String poster_path;
    @SerializedName("backdrop_path")
    public String backdrop_path;
    @SerializedName("overview")
    public String overview;
    @SerializedName("genres")
    public List<Genres> genres;
    @SerializedName("original_title")
    public String original_title;
    @SerializedName("release_date")
    public String release_date;
    @SerializedName("belongs_to_collection")
    public ModelCollection modelCollections;



    public ModelCollection getModelCollections() {
        return modelCollections;
    }

    public void setModelCollections(ModelCollection modelCollections) {
        this.modelCollections = modelCollections;
    }

    protected Movie(Parcel in) {
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        original_title = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_average = in.readDouble();
        id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @SerializedName("title")
    public String title;
    @SerializedName("vote_average")
    public double vote_average;
    @SerializedName("id")
    public int id;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeString(original_title);
        parcel.writeString(release_date);
        parcel.writeString(title);
        parcel.writeDouble(vote_average);
        parcel.writeInt(id);
    }
}
