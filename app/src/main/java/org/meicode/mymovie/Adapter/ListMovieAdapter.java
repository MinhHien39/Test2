package org.meicode.mymovie.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.meicode.mymovie.ItemClickListener;
import org.meicode.mymovie.Model.Movie;
import org.meicode.mymovie.R;

import java.util.ArrayList;
import java.util.List;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ViewHolder> {

    List<Movie> list = new ArrayList<>();
    ItemClickListener itemClickListener;

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ListMovieAdapter() {
    }

    public List<Movie> getList() {
        return list;
    }

    public void updateList(List<Movie> list) {

        this.list = list;
        notifyDataSetChanged();
        Log.d("Success Adapter" , "Have Data In Adapter");
        Log.d("Size List" , String.valueOf(list.size()));
    }
    public void filterList(ArrayList<Movie> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        list = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public ListMovieAdapter(List<Movie> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.now_playing_item, parent, false);

//        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) itemView.getLayoutParams();
//        layoutParams.height = parent.getMeasuredHeight() / 4;
//        layoutParams.width = parent.getMeasuredWidth() / 4;
//
//        Log.d("Sucesss LayoutParams" , String.valueOf(layoutParams.height));
//        Log.d("Sucesss LayoutParams" , String.valueOf(layoutParams.width));
//
//        itemView.setLayoutParams(layoutParams);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_title.setText(list.get(position).getTitle());
        holder.txt_rating.setText(String.valueOf(list.get(position).getVote_average()));
        String url = "http://image.tmdb.org/t/p/w500" + list.get(position).getPoster_path();
        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.imageTitle);

        //holder.imageTitle.setImageResource(R.drawable.anh1);



        holder.imageStar.setImageResource(R.drawable.ic_baseline_star_rate_24);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(holder.itemView.getContext(), SecondActivity.class);
//                holder.itemView.getContext().startActivity(intent);
               int position = holder.getAdapterPosition();
               itemClickListener.onClick(view , position , list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title;
        TextView txt_rating;
        ImageView imageTitle;
        ImageView imageStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_rating = itemView.findViewById(R.id.txt_rating);
            imageTitle = itemView.findViewById(R.id.imageTitle);
            imageStar  = itemView.findViewById(R.id.imageStar);



        }
    }
}
