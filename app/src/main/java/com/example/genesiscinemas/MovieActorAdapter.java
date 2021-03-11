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
import com.example.genesiscinemas.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MovieActorAdapter extends RecyclerView.Adapter<MovieActorAdapter.ListItemHolder> {

    List<Cast> actorsList = new ArrayList<>();
    int Mid ;

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_buttomsheet_layout,parent,false);
      return  new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        int id = actorsList.get(position).getId();
        holder.MactorsName.setText(actorsList.get(position).getCharacter());
        Glide.with(holder.MactorsImage).load("https://image.tmdb.org/t/p/w500"+actorsList.get(position).getProfilePath())
                .into(holder.MactorsImage);
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
        return actorsList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{
        private ImageView MactorsImage;
        private TextView MactorsName;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            MactorsImage = itemView.findViewById(R.id.MactorsImg);
            MactorsName = itemView.findViewById(R.id.Mactorsname);
        }


    }
    public void setactorList(List<Cast> actorList){
        this.actorsList = actorList;
    }
    public  void setId(int id){
        this.Mid = id;

    }
//   public static  class MovieActors {
//      int MactorsImage;
//      String MactorsName;
//
//        public MovieActors(int mactorsImage, String mactorsName) {
//            MactorsImage = mactorsImage;
//            MactorsName = mactorsName;
//        }
//        public static ArrayList<MovieActors> getMovieActors(){
//          ArrayList<MovieActors> MovieActor = new ArrayList<>();
//           MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//            MovieActor.add(new MovieActors(R.drawable.aa,"Williams cherechi"));
//
//
//            return MovieActor;
//        }
//    }
}
