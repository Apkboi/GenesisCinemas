package com.example.genesiscinemas;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grid6Adapter extends RecyclerView.Adapter<Grid6Adapter.ListItemHolder> {


    ArrayList<Map> bookings = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();
    public Grid6Adapter(ArrayList<Map> bookings,ArrayList<String> id){
        this.bookings = bookings;
        this.id = id;


    }


    //     Bookings_Collection fields
    public static  final  String MovieName = "Movie name";
    public static  final  String Date = "Date and Time";
    public static  final  String Time = "Time";
    public static  final  String Price = "Price";
    public static  final  String SeatNumber = "Seat Number";
    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticketlayout,parent,false);

        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
       Map<String,Object> booking = bookings.get(position);

        holder.txt_movieName.setText(booking.get(MovieName).toString());
        holder.txt_Time.setText("8:00pm");
        holder.txt_Date.setText(booking.get(Date).toString());
        holder.txt_Seat.setText(booking.get(SeatNumber).toString());
        MultiFormatWriter writer = new MultiFormatWriter();


        try {
            BitMatrix matrix =  writer.encode(id.get(position), BarcodeFormat.QR_CODE, 300, 300);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            holder.img_qr.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public  class ListItemHolder extends RecyclerView.ViewHolder{
            private TextView txt_movieName,txt_Date,txt_Time,txt_Seat;
            ImageView img_qr;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);
            txt_movieName = itemView.findViewById(R.id.txtMovieName);
            txt_Date = itemView.findViewById(R.id.txt_Date);
            txt_Seat = itemView.findViewById(R.id.txt_Seat);
            txt_Time = itemView.findViewById(R.id.txt_Time);
            img_qr = itemView.findViewById(R.id.img_qr);
        }
    }
}
