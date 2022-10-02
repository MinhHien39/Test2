package org.meicode.mymovie.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.meicode.mymovie.API.RetrofitClient;
import org.meicode.mymovie.Adapter.GenresMovieAdapter;
import org.meicode.mymovie.Database.DatabaseClass;
import org.meicode.mymovie.Database.InfoDetailMovie;
import org.meicode.mymovie.Model.Movie;
import org.meicode.mymovie.R;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {

    TextView title;
    TextView txt_overview;
    TextView txt_release;
    TextView txt_vote;
    ImageView img_title;
    ImageView img_back;

    ImageView img_return;
    ImageView img_far;
    RecyclerView rec_genres;
    RelativeLayout relativeLayout;

    GenresMovieAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Toolbar toolbar;
    public static Intent newIntentToSecondActivity(Context context , Movie data){
        Intent intent = new Intent(context , SecondActivity.class);
        intent.putExtra("ModelMovie" , data);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rec_genres = findViewById(R.id.rec_genres);
        rec_genres.setLayoutManager(new LinearLayoutManager(this , RecyclerView.HORIZONTAL , false)) ;
        relativeLayout = findViewById(R.id.parentRelative);



        Movie movie = getIntent().getParcelableExtra("ModelMovie");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        img_return = findViewById(R.id.img_back);
        img_far = findViewById(R.id.img_far);

        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });






        RetrofitClient retrofitClient = new RetrofitClient();
        adapter = new GenresMovieAdapter();


        retrofitClient.getApiService().getDetailMovieResponse(movie.getId()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movie) throws Throwable {
                        title = findViewById(R.id.txt_titleSecond);
                        title.setText(movie.getTitle());
                        txt_overview = findViewById(R.id.txt_overview);
                        txt_overview.setText(movie.getOverview());
                        txt_release = findViewById(R.id.txt_release);
                        txt_release.setText(movie.getRelease_date());
                        img_title = findViewById(R.id.img_title);
                        img_back = findViewById(R.id.img_background);
                        txt_vote = findViewById(R.id.txt_vote);
                        txt_vote.setText(String.valueOf(movie.getVote_average()));
                        if(movie.getModelCollections() != null) {
                            Glide.with(getApplicationContext())
                                    .load("https://image.tmdb.org/t/p/w500" + movie.getModelCollections().getPoster_path())
                                    .into(img_back);
                        }
                        Glide.with(getApplicationContext())
                                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                                .into(img_title);
                        checkIdInRoom(movie);
                        saveDataIntoDatabase(movie);

                        adapter.updaterList(movie.getGenres());
                        rec_genres.setAdapter(adapter);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("Error" , throwable.getMessage());
                    }
                });

    }
    private void saveDataIntoDatabase(Movie movie) {

        Disposable disposable = Completable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                InfoDetailMovie infoDetailMovie = new InfoDetailMovie().fromMovie(movie);
                DatabaseClass.getDatabase(getApplication()).getDao().insertAllData(infoDetailMovie);


            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("Success", "Ok");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("Error", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }
    private void checkIdInRoom(Movie movie){
        int id = DatabaseClass.getDatabase(getApplication()).getDao().checkId(movie.getId());
        if (id > 0 ){
            img_far.setImageResource(R.drawable.favorite);
        }else{
            img_far.setImageResource(R.drawable.ic_baseline_favorite_24);
        }

    }

}