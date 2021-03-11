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


public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.ListItemHolder> {

    List<Result> results = new ArrayList<>();
    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seconditemview,parent,false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {

        int id = results.get(position).getId();
        float rating = results.get(position).getVoteAverage().floatValue() / 2;
        holder.movieName.setText(results.get(position).getTitle());
        holder.ratingBar.setRating(rating);
        Glide.with(holder.movieImage).load("https://image.tmdb.org/t/p/w500"+results.get(position).getPosterPath())
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
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder {
        private ImageView movieImage;
        private TextView movieName;
        private RatingBar ratingBar;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.img_movieimage);
            movieName = itemView.findViewById(R.id.txt_movietittle);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    public void setResults(List<Result> resultList){
        this.results = resultList;
           notifyDataSetChanged();
    }
}
