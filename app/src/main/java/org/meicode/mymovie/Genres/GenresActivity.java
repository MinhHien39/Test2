package org.meicode.mymovie.Genres;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.mymovie.API.RetrofitClient;
import org.meicode.mymovie.Activity.SecondActivity;
import org.meicode.mymovie.Model.Movie;
import org.meicode.mymovie.Movie.ListMovieResponse;
import org.meicode.mymovie.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GenresActivity extends AppCompatActivity {
    private List<Genres> listGenres = new ArrayList<>();
    Toolbar toolbar;
    Spinner spinner;
    ConstraintLayout constraintLayout;

    TextView txtDescrip;
    GenresAdapter adapter;

    RecyclerView rec_listGenres;

    Button btnGrid;
    Button btnLinear;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public static Intent newIntentToGenresActivity(Context context){
        Intent intent = new Intent(context , GenresActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        toolbar = findViewById(R.id.toolbarGenres);
        setSupportActionBar(toolbar);
        rec_listGenres = findViewById(R.id.rec_listGenres);
        txtDescrip = findViewById(R.id.txt_overviewMovie);
        constraintLayout = findViewById(R.id.parenConstraints);
        btnGrid = findViewById(R.id.btnGrid);
        btnLinear = findViewById(R.id.btnLinear);

        adapter = new GenresAdapter();
        rec_listGenres.setAdapter(adapter);

        //rec_listGenres.setLayoutManager(new GridLayoutManager(GenresActivity.this , 3));

        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApi();
                adapter = new GenresAdapter();
                rec_listGenres.setLayoutManager(new GridLayoutManager(GenresActivity.this, 3));
                adapter.setIsGridLayout(true);
                rec_listGenres.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }
        });
        btnLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getApi();
                rec_listGenres.setLayoutManager(new LinearLayoutManager(GenresActivity.this, RecyclerView.VERTICAL, false));
                adapter = new GenresAdapter();
                adapter.setIsGridLayout(false);
                rec_listGenres.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.d("Change Adapter", "Ok");

            }
        });

        spinner = findViewById(R.id.spinner);

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

    }

    public void getApi() {
        RetrofitClient retrofitClient = new RetrofitClient();
        Disposable disposable = retrofitClient.getApiService().getGenresRepsonse().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GenresResponse>() {
                    @Override
                    public void accept(GenresResponse genresResponse) throws Throwable {
                        Log.d("GetAPIGenres", genresResponse.getListModel().get(0).getName());
                        listGenres = genresResponse.getListModel();
                        List<String> listNameGenres = new ArrayList<>();
                        for (int i = 0; i < listGenres.size(); i++) {
                            listNameGenres.add(listGenres.get(i).getName());
                        }
                        Log.d("SizeListGenres", String.valueOf(listGenres.size()));

                        ArrayAdapter genresArrayAdapter = new ArrayAdapter(GenresActivity.this, android.R.layout.simple_spinner_item, listNameGenres);

                        genresArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinner.setAdapter(genresArrayAdapter);

                        clickSpinner(genresResponse);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("ErrorGenres", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void clickSpinner(GenresResponse genresResponse) {

        RetrofitClient retrofitClient = new RetrofitClient();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();
                if ("Action".equals(selectedItem)) {
                    Log.d("Clicked", "Have Click Spinner");
                    retrofitClient.getApiService().getListMovieResponse(genresResponse.getListModel().get(position).getId()).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ListMovieResponse>() {
                                @Override
                                public void accept(ListMovieResponse listMovieResponse) throws Throwable {
                                    Log.d("ok", String.valueOf(listMovieResponse.getInfoMovieList()));

                                    adapter.setList(listMovieResponse.getInfoMovieList());

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {

                                }
                            });

                } else {
                    retrofitClient.getApiService().getListMovieResponse(genresResponse.getListModel().get(position).getId()).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ListMovieResponse>() {
                                @Override
                                public void accept(ListMovieResponse listMovieResponse) throws Throwable {
                                    Log.d("ok", String.valueOf(listMovieResponse.getInfoMovieList()));

                                    adapter.setList(listMovieResponse.getInfoMovieList());

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Throwable {

                                }
                            });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}