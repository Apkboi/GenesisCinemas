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

public class Grid3Adapter  extends  RecyclerView.Adapter<Grid3Adapter.ListItemHolder>{

    ArrayList<data> DataItem;
    public  Grid3Adapter(){
        DataItem = data.getdata();

    }
    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actorsitemview,parent,false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListItemHolder holder, int position) {

        final data Item = DataItem.get(position);
        holder.txt_actorsname.setText(Item.actorsname);
        holder.txt_actorsmovies.setText(Item.actorsmovies);
        holder.actors_image.setImageResource(Item.actorsimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),ActorProfile_Activity.class));
            }
        });
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return DataItem.size();
    }

    public  class ListItemHolder extends RecyclerView.ViewHolder{

        private TextView txt_actorsname;
        private TextView txt_actorsmovies;
        ImageView actors_image;
        ImageView favorite;

        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            txt_actorsname = itemView.findViewById(R.id.txt_actorsname);
            txt_actorsmovies = itemView.findViewById(R.id.txtactorMovies);
            actors_image = itemView.findViewById(R.id.actorimage);
            favorite = itemView.findViewById(R.id.imgFavourite);
        }

    }

    static  class data {
        String actorsname ;
        String actorsmovies;
        int actorsimage;

        public  data(String actorsname,String actorsmovies,int actorsimage){
            this.actorsname = actorsname;
            this.actorsmovies = actorsmovies;
            this.actorsimage = actorsimage;
        }

        public static ArrayList<data> getdata() {
            ArrayList<data> dataitems = new ArrayList<>();
            dataitems.add(new data("williams cherechi","44 movies",R.drawable.zz));
            dataitems.add(new data("williams cherechi","44 movies",R.drawable.yy));
            dataitems.add(new data("williams cherechi","44 movies",R.drawable.xx));
            dataitems.add(new data("williams cherechi","44 movies",R.drawable.uu));
            dataitems.add(new data("williams cherechi","44 movies",R.drawable.tt));
            dataitems.add(new data("williams cherechi","44 movies",R.drawable.zz));
       return  dataitems;
        }

        }

}
