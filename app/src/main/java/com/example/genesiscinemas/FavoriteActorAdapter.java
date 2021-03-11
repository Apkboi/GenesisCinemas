package com.example.genesiscinemas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoriteActorAdapter extends RecyclerView.Adapter<FavoriteActorAdapter.ListItemHolder> {

    ArrayList<favoriteActors> actors = new ArrayList<>();
    public FavoriteActorAdapter() {
        actors = favoriteActors.getFavoriteActor();
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favouriteactorlayout,parent,false);

        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        favoriteActors actors1 = actors.get(position);
        holder.actorsImage.setImageResource(actors1.actorsImage);
        holder.actorsName.setText(actors1.actorsName);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{
        private ImageView actorsImage,imgDel;
        private TextView actorsName;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            actorsName = itemView.findViewById(R.id.actorsname);
            imgDel = itemView.findViewById(R.id.imgDel);
            actorsImage = itemView.findViewById(R.id.actorsImg);
        }
    }

    static class favoriteActors {
        int actorsImage;
        String actorsName;

        public favoriteActors(int actorsImage, String actorsName) {
            this.actorsImage = actorsImage;
            this.actorsName = actorsName;
        }
       public static ArrayList<favoriteActors> getFavoriteActor(){
            ArrayList<favoriteActors> actors = new ArrayList<>();
            actors.add(new favoriteActors(R.drawable.hh,"Ebube Roderick"));
            actors.add(new favoriteActors(R.drawable.gg,"Williams cherechi williams"));
           actors.add(new favoriteActors(R.drawable.hh,"Ebube Roderick"));
           actors.add(new favoriteActors(R.drawable.gg,"Williams cherechi williams"));
           actors.add(new favoriteActors(R.drawable.hh,"Ebube Roderick"));
           actors.add(new favoriteActors(R.drawable.gg,"Williams cherechi williams"));
           actors.add(new favoriteActors(R.drawable.hh,"Ebube Roderick"));
           actors.add(new favoriteActors(R.drawable.gg,"Williams cherechi williams"));

           return actors;

       }
    }
}
