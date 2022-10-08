package org.meicode.mymovie.Database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import org.meicode.mymovie.ItemClickListener;
import org.meicode.mymovie.R;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    List<InfoDetailMovie> listMovie = new ArrayList<>();

    ItemClickListener itemClickListener;

    public FavoriteAdapter() {
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FavoriteAdapter(List<InfoDetailMovie> listMovie) {
        this.listMovie = listMovie;
    }

    public void updateListMovie(List<InfoDetailMovie> list) {
        this.listMovie = list;
        Log.d("Success Adapter" , "Have Data In Adapter");
        Log.d("Size ListMovie" , String.valueOf(list.size()));
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.info_movie_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.txt_idMovie.setText(String.valueOf(listMovie.get(position).getId()));
        holder.txt_overviewMovie.setText(listMovie.get(position).getOverview());
        holder.txt_titleMovie.setText(listMovie.get(position).getTitle());
        holder.txt_releaseMovie.setText(listMovie.get(position).getRelease_date());
        holder.txt_rating.setText(String.valueOf(listMovie.get(position).getVote_average()));
        String url = "http://image.tmdb.org/t/p/w500" + listMovie.get(position).getPoster_path();
        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.img_titleFavorite);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                itemClickListener.onClick(view , position , listMovie.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_idMovie;
        TextView txt_titleMovie;
        TextView txt_overviewMovie;
        TextView txt_releaseMovie;
        TextView txt_rating;
        ImageView img_titleFavorite;
        Button btnDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //txt_idMovie = itemView.findViewById(R.id.txt_idMovie);
            txt_titleMovie = itemView.findViewById(R.id.txt_titleMovie);
            txt_overviewMovie = itemView.findViewById(R.id.txt_overviewMovie);
            txt_releaseMovie = itemView.findViewById(R.id.txt_releaseMovie);
            img_titleFavorite = itemView.findViewById(R.id.img_titleFavorite);
            txt_rating = itemView.findViewById(R.id.txt_ratingMovie);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
