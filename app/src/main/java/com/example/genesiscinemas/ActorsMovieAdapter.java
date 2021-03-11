package com.example.genesiscinemas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActorsMovieAdapter extends RecyclerView.Adapter<ActorsMovieAdapter.ListItemHolder>{

    ArrayList<ActorMovies> actorMovies = new ArrayList<>();
    public ActorsMovieAdapter() {
        actorMovies=ActorMovies.getActorMovie();
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seconditemview,parent,false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListItemHolder holder, int position) {

        final ActorMovies actorMovies1 = actorMovies.get(position);
        holder.MovieImage.setImageResource(actorMovies1.movieImage);
        holder.MovieName.setText(actorMovies1.movieTittle);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),DetailsActivity.class);
                intent.putExtra("image",actorMovies1.movieImage);
                intent.putExtra("name",actorMovies1.movieTittle);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return actorMovies.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{
            private ImageView MovieImage;
            private TextView MovieName;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);

            MovieImage = itemView.findViewById(R.id.img_movieimage);
            MovieName = itemView.findViewById(R.id.txt_movietittle);


        }
    }

    static  class ActorMovies{
        int movieImage;
        String movieTittle;

        public ActorMovies(int movieImage, String movieTittle) {
            this.movieImage = movieImage;
            this.movieTittle = movieTittle;
        }

        static ArrayList<ActorMovies> getActorMovie(){
            ArrayList<ActorMovies> ActorMovie = new ArrayList<>();
            ActorMovie.add(new ActorMovies(R.drawable.aa,"Ebube Roderick"));
            ActorMovie.add(new ActorMovies(R.drawable.aa,"Ebube Roderick"));
            ActorMovie.add(new ActorMovies(R.drawable.aa,"Ebube Roderick"));
            ActorMovie.add(new ActorMovies(R.drawable.aa,"Ebube Roderick"));
            ActorMovie.add(new ActorMovies(R.drawable.aa,"Ebube Roderick"));
            ActorMovie.add(new ActorMovies(R.drawable.aa,"Ebube Roderick"));
            ActorMovie.add(new ActorMovies(R.drawable.aa,"Ebube Roderick"));

            return ActorMovie;
        }
    }
}
