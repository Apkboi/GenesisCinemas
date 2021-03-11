package com.example.genesiscinemas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Grid6Adapter extends RecyclerView.Adapter<Grid6Adapter.ListItemHolder> {


    ArrayList<information> texts = new ArrayList<>();
    public Grid6Adapter(){
        texts=information.getinfo();
    }



    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticketlayout,parent,false);

        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        information infos  = texts.get(position);
        holder.txt_movieName.setText(infos.txt_movieName);
        holder.txt_Time.setText(infos.txt_Time);
        holder.txt_Date.setText(infos.txt_Date);
        holder.txt_Seat.setText(infos.txt_Seat);
    }

    @Override
    public int getItemCount() {
        return texts.size();
    }

    public  class ListItemHolder extends RecyclerView.ViewHolder{
            private TextView txt_movieName,txt_Date,txt_Time,txt_Seat;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            txt_movieName = itemView.findViewById(R.id.txtMovieName);
            txt_Date = itemView.findViewById(R.id.txt_Date);
            txt_Seat = itemView.findViewById(R.id.txt_Seat);
            txt_Time = itemView.findViewById(R.id.txt_Time);
        }
    }

    static  class information{
        String  txt_movieName, txt_Date,txt_Seat,txt_Time;
        public information( String  txt_movieName, String txt_Date,String txt_Seat,String txt_Time){
            this.txt_movieName = txt_movieName;
            this.txt_Date =txt_Date;
            this.txt_Seat = txt_Seat;
            this.txt_Time = txt_Time;
        }


        public static ArrayList<information> getinfo(){
            ArrayList<information> info = new ArrayList<>();
            info.add(new information("Achalugo my love","April 1st","A6","08:00AM" ));
            info.add(new information("The Blade","December 1st","A6","10:00AM" ));
            info.add(new information("419","February 14th","A6","12:00AM" ));
            info.add(new information("The Blade","December 1st","A6","10:00AM" ));
            info.add(new information("419","February 14th","A6","12:00AM" ));

            return info;

        }
    }

}
