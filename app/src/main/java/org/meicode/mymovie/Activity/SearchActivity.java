package org.meicode.mymovie.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.mymovie.API.RetrofitClient;
import org.meicode.mymovie.Adapter.ListMovieAdapter;
import org.meicode.mymovie.Model.Movie;
import org.meicode.mymovie.R;
import org.meicode.mymovie.RxSearchObservable;
import org.meicode.mymovie.SearchMovie.SearchMovieResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    public static Intent newIntentToSearchActivity(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }


    RecyclerView rec_search_hor;


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView recyclerView;
    List<Movie> list;
    ListMovieAdapter adapter;
    EditText edt;

    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.rec_search_hor);
        RetrofitClient retrofitClient = new RetrofitClient();
        adapter = new ListMovieAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        searchView = findViewById(R.id.edtSearch);

        RxSearchObservable.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String text) {
                        if (text.isEmpty()) {
                            searchView.setQueryHint("Search");
                            adapter.getList().clear();
                            return false;
                        } else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<SearchMovieResponse>>() {
                    @Override
                    public ObservableSource<SearchMovieResponse> apply(String s) throws Throwable {
                        return retrofitClient.getApiService().getMovie(s)
                                .doOnError(throwable -> {
                                    throw new RuntimeException("unexcepted");
                                })
                                .onErrorReturn(throwable -> null);

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchMovieResponse>() {
                    @Override
                    public void accept(SearchMovieResponse searchMovieResponse) throws Throwable {
                        adapter.updateList(searchMovieResponse.getMovieList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("Error", throwable.getMessage());

                    }
                });
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                if (s.isEmpty()) {
//                    searchView.setQueryHint("Enter Movie");
//                    adapter.getList().clear();
//                    adapter.notifyDataSetChanged();
//                } else {
//                    Disposable disposableNowplaying = retrofitClient.getApiService().getMovie(s)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Consumer<SearchMovieResponse>() {
//                                @Override
//                                public void accept(SearchMovieResponse searchMovieResponse) throws Throwable {
//                                    adapter.updateList(searchMovieResponse.getMovieList());
//
//                                }
//                            }, new Consumer<Throwable>() {
//                                @Override
//                                public void accept(Throwable throwable) throws Throwable {
//                                    Log.e("Error", throwable.getMessage());
//
//                                }
//                            });
//                    compositeDisposable.add(disposableNowplaying);
//                }
//
//                return false;
//            }
//    });


    }

}