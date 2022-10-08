package org.meicode.mymovie.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.mymovie.API.RetrofitClient;
import org.meicode.mymovie.Activity.SecondActivity;
import org.meicode.mymovie.Adapter.ListMovieAdapter;
import org.meicode.mymovie.DetailMovie.DetailsMovieResponse;
import org.meicode.mymovie.ItemClickListener;
import org.meicode.mymovie.LoadingDialogBar;
import org.meicode.mymovie.Model.Movie;
import org.meicode.mymovie.R;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListMovieFragment extends Fragment {
    LoadingDialogBar loadingDialogBar;

    public static ListMovieFragment newInstance(String typeMovie) {
        ListMovieFragment fragment = new ListMovieFragment();

        Bundle bundle = new Bundle();
        bundle.putString("typeMovie", typeMovie);
        fragment.setArguments(bundle);

        return fragment;
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView recyclerView;
    List<Movie> list;
    ListMovieAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_now_playing, container, false);

        recyclerView = root.findViewById(R.id.rec_nowPlaying);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        adapter = new ListMovieAdapter();
        loadingDialogBar = new LoadingDialogBar(getContext());
        adapter.setItemClickListener(new ItemClickListener<Movie>() {
            @Override
            public void onClick(View view, int position, Movie data) {
                Intent intent = SecondActivity.newIntentToSecondActivity(view.getContext(), data);
                loadingDialogBar.showDialog("SecondActivty");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String type = getArguments().getString("typeMovie");
        RetrofitClient retrofitClient = new RetrofitClient();

//
        switch (type) {
            case "NowPlaying":
                loadingDialogBar.showDialog("NowPlaying");
                Disposable disposableNowplaying = retrofitClient.getApiService().getNowPlaying().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<DetailsMovieResponse>() {
                            @Override
                            public void accept(DetailsMovieResponse detailsMovieResponse) throws Throwable {
                                Log.d("Success nowplaying", detailsMovieResponse.getMovieList().get(1).getTitle());
                                Log.d("Success URl", detailsMovieResponse.getMovieList().get(0).getPoster_path());
                                adapter.updateList(detailsMovieResponse.getMovieList());
                                loadingDialogBar.hideDialog();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.e("Error", throwable.getMessage());

                            }
                        });

                compositeDisposable.add(disposableNowplaying);
                break;
            case "Popular":
                loadingDialogBar.showDialog("Popular");
                Disposable disposablePopular = retrofitClient.getApiService().getPopular().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<DetailsMovieResponse>() {
                            @Override
                            public void accept(DetailsMovieResponse detailsMovieResponse) throws Throwable {
                                Log.d("Success nowplaying", detailsMovieResponse.getMovieList().get(1).getTitle());

                                adapter.updateList(detailsMovieResponse.getMovieList());
                                loadingDialogBar.hideDialog();


                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.e("Error", throwable.getMessage());

                            }
                        });
                compositeDisposable.add(disposablePopular);
                break;
            case "TopRated":
                loadingDialogBar.showDialog("Top Rated");
                Disposable disposableTopRated = retrofitClient.getApiService().getTopRated().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<DetailsMovieResponse>() {
                            @Override
                            public void accept(DetailsMovieResponse detailsMovieResponse) throws Throwable {
                                Log.d("Success nowplaying", detailsMovieResponse.getMovieList().get(1).getTitle());

                                adapter.updateList(detailsMovieResponse.getMovieList());
                                loadingDialogBar.hideDialog();


                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.e("Error", throwable.getMessage());

                            }
                        });
                compositeDisposable.add(disposableTopRated);
                break;
            case "Upcoming":
                loadingDialogBar.showDialog("Upcoming");
                Disposable disposableUpcoming = retrofitClient.getApiService().getUpcoming().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<DetailsMovieResponse>() {
                            @Override
                            public void accept(DetailsMovieResponse detailsMovieResponse) throws Throwable {
                                Log.d("Success nowplaying", detailsMovieResponse.getMovieList().get(1).getTitle());
                                adapter.updateList(detailsMovieResponse.getMovieList());
                                loadingDialogBar.hideDialog();


                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.e("Error", throwable.getMessage());

                            }
                        });
                compositeDisposable.add(disposableUpcoming);
                break;
        }


    }

}