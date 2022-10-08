package org.meicode.mymovie.Genres;

import android.net.wifi.p2p.WifiP2pManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.meicode.mymovie.Movie.InfoMovie;
import org.meicode.mymovie.R;

import java.util.ArrayList;
import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<InfoMovie> list = new ArrayList<>();

    private boolean isGridLayout = false;


    public void setIsGridLayout(boolean gridLayout) {
        isGridLayout = gridLayout;
    }

    public GenresAdapter() {
    }

    public List<InfoMovie> getList() {
        return list;
    }

    public void setList(List<InfoMovie> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isGridLayout) {
            return new ViewHolderGridLayout(LayoutInflater.from(parent.getContext()).inflate(R.layout.now_playing_item, parent, false));
        } else {
            return new ViewHolderLinearLayout(LayoutInflater.from(parent.getContext()).inflate(R.layout.info_movie_item, parent, false));

        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (isGridLayout) {
            ViewHolderGridLayout viewHolderGrid = (ViewHolderGridLayout) holder;
            viewHolderGrid.txt_title.setText(list.get(position).getName());
            viewHolderGrid.txt_rate.setText(list.get(position).getList_type());
            String url = "";
            Glide.with(((ViewHolderGridLayout) holder).imageView.getContext())
                    .load(url)
                    .placeholder(R.drawable.thor)
                    .into(((ViewHolderGridLayout) holder).imageView);


        } else {
            ViewHolderLinearLayout viewHolderLinear = (ViewHolderLinearLayout) holder;

            viewHolderLinear.txt_titleMovie.setText(String.valueOf(list.get(position).getId()));
            viewHolderLinear.txt_overviewMovie.setText(list.get(position).getName());
            viewHolderLinear.txt_releaseMovie.setText(list.get(position).getList_type());
            String url = "";
            Glide.with(((ViewHolderLinearLayout) holder).img_titleFavorite.getContext())
                    .load(url)
                    .placeholder(R.drawable.thor)
                    .into(((ViewHolderLinearLayout) holder).img_titleFavorite);
        }


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderGridLayout extends RecyclerView.ViewHolder {

        TextView txt_title;
        TextView txt_rate;
        ImageView imageView;


        public ViewHolderGridLayout(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_rate = itemView.findViewById(R.id.txt_rating);
            imageView = itemView.findViewById(R.id.imageTitle);

        }
    }

    public class ViewHolderLinearLayout extends RecyclerView.ViewHolder {

        TextView txt_titleMovie;
        TextView txt_overviewMovie;
        TextView txt_releaseMovie;
        ImageView img_titleFavorite;



        public ViewHolderLinearLayout(@NonNull View itemView) {

            super(itemView);
            txt_titleMovie = itemView.findViewById(R.id.txt_titleMovie);
            txt_overviewMovie = itemView.findViewById(R.id.txt_overviewMovie);
            txt_releaseMovie = itemView.findViewById(R.id.txt_releaseMovie);
            img_titleFavorite = itemView.findViewById(R.id.img_titleFavorite);
        }
    }
}
