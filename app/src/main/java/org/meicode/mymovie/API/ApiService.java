package org.meicode.mymovie.API;

import org.meicode.mymovie.DetailMovie.DetailsMovieResponse;
import org.meicode.mymovie.Genres.GenresResponse;
import org.meicode.mymovie.Model.Movie;
import org.meicode.mymovie.Movie.ListMovieResponse;
import org.meicode.mymovie.SearchMovie.SearchMovieResponse;
import org.meicode.mymovie.User.DetailUserResponse;
import org.meicode.mymovie.User.SessionIdResponse;
import org.meicode.mymovie.User.SessionRequestLoginResponse;
import org.meicode.mymovie.User.RequestTokenResponse;
import org.meicode.mymovie.User.SessionRequest;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("genre/movie/list?api_key=c29a024e152bfd1ad3d4d0dc8cb48019")
    Observable<GenresResponse> getGenresRepsonse();

    @GET("movie/{movie_id}/lists?api_key=c29a024e152bfd1ad3d4d0dc8cb48019&language=en-US&page=1")
    Observable<ListMovieResponse> getListMovieResponse(@Path("movie_id") Integer id);

    @GET("movie/{movie_id}?api_key=c29a024e152bfd1ad3d4d0dc8cb48019&language=en-US")
    Observable<Movie> getDetailMovieResponse(@Path("movie_id") Integer id);

    @GET("movie/now_playing?api_key=c29a024e152bfd1ad3d4d0dc8cb48019&language=en-US&page=1")
    Observable<DetailsMovieResponse> getNowPlaying();

    @GET("movie/popular?api_key=c29a024e152bfd1ad3d4d0dc8cb48019&language=en-US&page=1")
    Observable<DetailsMovieResponse> getPopular();

    @GET("movie/top_rated?api_key=c29a024e152bfd1ad3d4d0dc8cb48019&language=en-US&page=1")
    Observable<DetailsMovieResponse> getTopRated();

    @GET("movie/upcoming?api_key=c29a024e152bfd1ad3d4d0dc8cb48019&language=en-US&page=1")
    Observable<DetailsMovieResponse> getUpcoming();

    @GET("authentication/token/new?api_key=c29a024e152bfd1ad3d4d0dc8cb48019")
    Observable<RequestTokenResponse> getRequestToken();

    @POST("authentication/token/validate_with_login?api_key=c29a024e152bfd1ad3d4d0dc8cb48019")
    Observable<SessionRequestLoginResponse> createSessionRequest(@Body SessionRequest sessionRequest);

    @POST("authentication/session/new?api_key=c29a024e152bfd1ad3d4d0dc8cb48019")
    Observable<SessionIdResponse> createSessionIdRequest(@Body SessionRequestLoginResponse sessionRequestLoginResponse);

    @GET("account?api_key=c29a024e152bfd1ad3d4d0dc8cb48019")
    Observable<DetailUserResponse> getDetailUser(@Query("session_id") String sessionIdResponse);

    @GET("search/movie?api_key=c29a024e152bfd1ad3d4d0dc8cb48019&language=en-US&page=1&include_adult=false")
    Observable<SearchMovieResponse> getMovie(@Query("query") String query);









}
