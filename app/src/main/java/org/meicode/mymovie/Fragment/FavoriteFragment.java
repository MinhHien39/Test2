package org.meicode.mymovie.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.mymovie.Database.DatabaseClass;
import org.meicode.mymovie.Database.FavoriteAdapter;
import org.meicode.mymovie.Database.InfoDetailMovie;
import org.meicode.mymovie.ItemClickListener;
import org.meicode.mymovie.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavoriteFragment extends Fragment {
    List<InfoDetailMovie> list;
    FavoriteAdapter adapter;
    RecyclerView recyclerView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = root.findViewById(R.id.rec_favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FavoriteAdapter();
        recyclerView.setAdapter(adapter);

        return root;
    }
    public void deleteItem(InfoDetailMovie item){

        Disposable disposable = DatabaseClass.getDatabase(getContext()).getDao().deleteAllData(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("Success" , "Ok");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("Error" , throwable.getMessage());

                    }
                });
        compositeDisposable.add(disposable);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Disposable disposable = DatabaseClass.getDatabase(getContext()).getDao().getAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<InfoDetailMovie>>() {
                    @Override
                    public void accept(List<InfoDetailMovie> infoDetailMovies) throws Throwable {
                        adapter.updateListMovie(infoDetailMovies);
                        Log.d("Overview" , infoDetailMovies.get(0).getOverview());
                        adapter.setItemClickListener(new ItemClickListener<InfoDetailMovie>() {
                            @Override
                            public void onClick(View view, int position, InfoDetailMovie data) {
                                deleteItem(data);
                                infoDetailMovies.remove(position);
                                adapter.notifyDataSetChanged();

                            }
                        });

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("Error" , throwable.getMessage());

                    }
                });

        compositeDisposable.add(disposable);
    }
}