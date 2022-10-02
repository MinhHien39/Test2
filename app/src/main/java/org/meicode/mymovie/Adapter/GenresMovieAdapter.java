package org.meicode.mymovie.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.mymovie.Genres.Genres;
import org.meicode.mymovie.Model.Movie;
import org.meicode.mymovie.R;

import java.util.ArrayList;
import java.util.List;

public class GenresMovieAdapter extends RecyclerView.Adapter<GenresMovieAdapter.ViewHolder> {
    List<Genres> list = new ArrayList<>();

    public List<Genres> getList() {
        return list;
    }

    public void updaterList(List<Genres> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public GenresMovieAdapter(List<Genres> list) {
        this.list = list;
    }

    public GenresMovieAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.genres_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_name.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_genres);
        }
    }
}
