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

public class Grid5Adapter extends RecyclerView.Adapter<Grid5Adapter.ListItemHolder>{

    List<Result> PopularMovies = new ArrayList<>();
    public Grid5Adapter(){

    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seconditemview,parent,false);
      return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListItemHolder holder, int position) {
        int id = PopularMovies.get(position).getId();
        float rating = PopularMovies.get(position).getVoteAverage().floatValue() / 2;
        holder.ratingBar.setRating(rating);
        holder.txt_movieTittle.setText(PopularMovies.get(position).getTitle());
        Glide.with(holder.movieImage).load("https://image.tmdb.org/t/p/w500"+PopularMovies.get(position).getPosterPath())
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

//    final Tdata items = DataItems.get(position);
//        holder.txt_movieTittle.setText(items.movietittle);
//        holder.movieImage.setImageResource(items.movieimage);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(),DetailsActivity.class);
//                intent.putExtra("image",items.movieimage);
//                intent.putExtra("name",items.movietittle);
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return PopularMovies.size();
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

    public  void setPopularMovies (List<Result> popularMovies){
        this.PopularMovies=popularMovies;

    }

//    static  class Tdata {
//
//        String movietittle;
//        int movieimage;
//        public  Tdata(    String movietittle,int movieimage ){
//            this.movietittle = movietittle;
//            this.movieimage = movieimage;
//        }
//        public static ArrayList<Tdata> getdata() {
//            ArrayList<Tdata> TdataItems = new ArrayList<>();
//            TdataItems.add(new Tdata("adama my love",R.drawable.dd));
//            TdataItems.add(new Tdata("adama my love",R.drawable.ii));
//            TdataItems.add(new Tdata("adama my love",R.drawable.ll));
//            TdataItems.add(new Tdata("adama my love",R.drawable.bb));
//            TdataItems.add(new Tdata("adama my love",R.drawable.ee));
//            TdataItems.add(new Tdata("adama my love",R.drawable.aa));
//            TdataItems.add(new Tdata("adama my love",R.drawable.cc));
//            TdataItems.add(new Tdata("adama my love",R.drawable.gg));
//            TdataItems.add(new Tdata("adama my love",R.drawable.tt));
//            return TdataItems;
//        }
//    }
}
