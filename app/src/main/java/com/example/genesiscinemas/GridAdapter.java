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

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ListItemHolder>  {

    List<Result> MovieList = new ArrayList<>();

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListItemHolder holder, int position) {

    Result result = MovieList.get(position);
    int id = result.getId();
    float rating = result.getVoteAverage().floatValue() / 2;
    holder.txt_raing.setText(String.valueOf(rating));
    holder.ratingBar.setRating(rating);
    holder.txt_movietittle.setText(result.getTitle());

        Glide.with(holder.movieimage).load("https://image.tmdb.org/t/p/w500"+result.getPosterPath())
                .into(holder.movieimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),DetailsActivity.class);
               intent.putExtra("id",id);
               intent.putExtra("rating",rating);
                holder.itemView.getContext().startActivity(intent);
            }
        });



//                  ------------initial holder OnclickListener---------
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(),DetailsActivity.class);
//                intent.putExtra("image",items.movieimage);
//                intent.putExtra("name",items.txt_momietittle);
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return MovieList.size();
    }

    public  class ListItemHolder extends RecyclerView.ViewHolder{
        private TextView txt_movietittle;
        private TextView txt_raing;
        private ImageView movieimage;
        private RatingBar ratingBar;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
        txt_movietittle =    itemView.findViewById(R.id.txt_movietittle);
         movieimage =   itemView.findViewById(R.id.img_movieimage);
         ratingBar = itemView.findViewById(R.id.ratingBar);
         txt_raing = itemView.findViewById(R.id.txt_rating);
        }
    }


    public void setMovies(List<Result> movies){
        this.MovieList = movies;
        notifyDataSetChanged();

    }

//    initial dummy data
//   public static class data{
//        String txt_momietittle;
//        int movieimage;
//
//       public data(String txtView, int image) {
//           this.txt_momietittle = txtView;
//           this.movieimage = image;
//       }
//       public static ArrayList<data> getdata(){
//
//           ArrayList<data> dataItems = new ArrayList<>();
//           dataItems.add(new data("iron monkey",R.drawable.aa));
//           dataItems.add(new data("iron monkey",R.drawable.bb));
//           dataItems.add(new data("iron monkey",R.drawable.cc));
//           dataItems.add(new data("iron monkey",R.drawable.dd));
//           dataItems.add(new data("iron monkey",R.drawable.ee));
//           dataItems.add(new data("iron monkey",R.drawable.ee));
//           dataItems.add(new data("iron monkey",R.drawable.aa));
//           dataItems.add(new data("iron monkey",R.drawable.aa));
//           dataItems.add(new data("iron monkey",R.drawable.ii));
//           return dataItems;
//       }
//    }
}
