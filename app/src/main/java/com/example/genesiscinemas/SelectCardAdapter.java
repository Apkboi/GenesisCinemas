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
import java.util.Map;


public class SelectCardAdapter extends  RecyclerView.Adapter<SelectCardAdapter.ListItemHolder> {

    //    ------------Database fields------------
    private static  final String CARDS_NUMBER = "Card Number";
    private static  final String CARDS_CVV = "Card cvv";
    private static  final String CARDS_USER = "Card user";
    private static  final String CARDS_EPIRY_DATE = "Card expiry Dste";
    private static  final String CARD_TYPE = "Card Type";
    private static  final String USER_EMAIL = "User Email";


    ArrayList<Map> cards = new ArrayList<>();
    int movieId;
    String MovieName;
    String MovieDuration;
    String total;

    public SelectCardAdapter(ArrayList<Map> cards, int movieId, String movieName, String movieDuration, String total) {
        this.cards = cards;
        this.movieId = movieId;
        MovieName = movieName;
        MovieDuration = movieDuration;
        this.total = total;
    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_card_layout,parent,false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Map<String,Object> cardDetails = cards.get(position);
        String expiryDate =  "Expires on " + cardDetails.get(CARDS_EPIRY_DATE).toString();
        String cardNumber = cardDetails.get(CARD_TYPE).toString() + "******"+cardDetails.get(CARDS_NUMBER).toString().substring(12,16);
        holder.txt_cardInfo.setText(cardNumber);
        holder.txt_expiryDate.setText(expiryDate);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),Checkout.class);
                intent.putExtra("id",movieId);
                intent.putExtra("Name",MovieName);
                intent.putExtra("duration",MovieDuration);
                intent.putExtra("total",total);
                intent.putExtra("cardNumber",cardNumber);
                intent.putExtra("cardType",cardDetails.get(CARD_TYPE).toString());


              holder.itemView.getContext().startActivity(intent);

            }
        });


        if (cardDetails.get(CARD_TYPE).toString().equals("visa")){
            holder.card_img.setImageResource(R.drawable.visa1);

        }else{
            holder.card_img.setImageResource(R.drawable.master);
        }

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{
        private TextView txt_cardInfo,txt_expiryDate;
        private ImageView card_img;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);


            txt_cardInfo = itemView.findViewById(R.id.cardInfo);
            card_img = itemView.findViewById(R.id.card_img);
            txt_expiryDate = itemView.findViewById(R.id.txt_expiryDate);

        }
    }


}
