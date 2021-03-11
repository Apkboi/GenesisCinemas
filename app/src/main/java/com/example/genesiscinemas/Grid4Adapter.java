package com.example.genesiscinemas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.genesiscinemas.models.Cast;

import java.util.ArrayList;
import java.util.List;

public class Grid4Adapter extends  RecyclerView.Adapter<Grid4Adapter.ListItemHolder> {

    List<Cast> castList = new ArrayList<>();

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actsitemviews,parent,false);
        return new Grid4Adapter.ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListItemHolder holder, int position) {

        int id = castList.get(position).getId();
        holder.txtActorsname.setText(castList.get(position).getCharacter());
        Glide.with(holder.imgActorsimage).load("https://image.tmdb.org/t/p/w500"+castList.get(position).getProfilePath())
                .into(holder.imgActorsimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),ActorProfile_Activity.class);
                intent.putExtra("id",id);
                intent.putExtra("position",position);
                holder.itemView.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
       return castList.size();
    }

    public class  ListItemHolder extends RecyclerView.ViewHolder {

        private TextView txtActorsname,txtActorsrole;
        private ImageView imgActorsimage;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            txtActorsname = itemView.findViewById(R.id.actorsname);
//            txtActorsrole = itemView.findViewById(R.id.actorsRole);
            imgActorsimage = itemView.findViewById(R.id.actorsImg);
        }


    }
     public void setCastList(List<Cast> casts){
        this.castList = casts;
        notifyDataSetChanged();
     }


     }
//    static  class data {
//        String Actorsname ;
//        String actorsRole;
//        int actorsimage;
//
//        public  data(String Actorsname,String actorsRole,int actorsimage){
//            this.Actorsname = Actorsname;
//            this.actorsRole = actorsRole;
//            this.actorsimage = actorsimage;
//        }
//
//        public static ArrayList<Grid4Adapter.data> getdata() {
//            ArrayList<Grid4Adapter.data> dataitems = new ArrayList<>();
//            dataitems.add(new Grid4Adapter.data("williams cherechi","44 movies",R.drawable.zz));
//            dataitems.add(new Grid4Adapter.data("williams cherechi","44 movies",R.drawable.yy));
//            dataitems.add(new Grid4Adapter.data("williams cherechi","44 movies",R.drawable.xx));
//            dataitems.add(new Grid4Adapter.data("williams cherechi","44 movies",R.drawable.uu));
//            dataitems.add(new Grid4Adapter.data("williams cherechi","44 movies",R.drawable.tt));
//            dataitems.add(new Grid4Adapter.data("williams cherechi","44 movies",R.drawable.zz));
//            return  dataitems;
//        }
//
//    }


