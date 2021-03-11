package com.example.genesiscinemas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genesiscinemas.models.Result;

import java.util.ArrayList;
import java.util.List;

public class Grid2Adapter extends RecyclerView.Adapter<Grid2Adapter.ListItemHolder> {

    List<Result> NowPlaying = new ArrayList<>();
    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seconditemview,parent,false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListItemHolder holder, int position) {

        Result movies = NowPlaying.get(position);
        int id = movies.getId();
        float rating = movies.getVoteAverage().floatValue() / 2;
        holder.ratingBar.setRating(rating);
                holder.txt_movieTittle.setText(movies.getTitle());
        Glide.with(holder.movieImage).load("https://image.tmdb.org/t/p/w500"+movies.getPosterPath())
                .into(holder.movieImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),DetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("rating",rating);
                holder.itemView.getContext().startActivity(intent);
            }
        });

//
//      holder.itemView.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//              Intent intent = new Intent(holder.itemView.getContext(),DetailsActivity.class);
//              intent.putExtra("image",items.movieimage);
//              intent.putExtra("name",items.movietittle);
//              holder.itemView.getContext().startActivity(intent);
//          }
//      });

    }

    @Override
    public int getItemCount() {
        return NowPlaying.size();
    }

    public  class ListItemHolder extends RecyclerView.ViewHolder{
        private TextView txt_movieTittle;
        private ImageView movieImage;
        private RatingBar ratingBar;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            txt_movieTittle= itemView.findViewById(R.id.txt_movietittle);
            movieImage = itemView.findViewById(R.id.img_movieimage);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    public  void setNowPlaying(List<Result> nowPlaying){
        this.NowPlaying = nowPlaying;
        notifyDataSetChanged();
    }
//    --------------------------initial Dump Data Class-------------------
//
//    static class data {
//      String movietittle;
//      int movieimage;
//
//      public  data(    String movietittle,int movieimage ){
//          this.movietittle = movietittle;
//          this.movieimage = movieimage;
//      }
//      public static ArrayList<data> getdata() {
//          ArrayList<data> dataItems = new ArrayList<>();
//          dataItems.add(new data("adama my love",R.drawable.dd));
//          dataItems.add(new data("adama my love",R.drawable.ii));
//          dataItems.add(new data("adama my love",R.drawable.ll));
//          dataItems.add(new data("adama my love",R.drawable.cc));
//          dataItems.add(new data("adama my love",R.drawable.ee));
//          dataItems.add(new data("adama my love",R.drawable.gg));
//          dataItems.add(new data("adama my love",R.drawable.dd));
//          dataItems.add(new data("adama my love",R.drawable.aa));
//          dataItems.add(new data("adama my love",R.drawable.bb));
//          return dataItems;
//      }
//    } i
}
