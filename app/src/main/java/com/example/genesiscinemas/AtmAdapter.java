package com.example.genesiscinemas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;


public class AtmAdapter extends  RecyclerView.Adapter<AtmAdapter.ListItemHolder> {

    private static  final String CARDS_COLLECTION = "Payment Cards";

    //    ------------Database fields------------
    private static  final String CARDS_NUMBER = "Card Number";
    private static  final String CARDS_CVV = "Card cvv";
    private static  final String CARDS_USER = "Card user";
    private static  final String CARDS_EPIRY_DATE = "Card expiry Dste";
    private static  final String CARD_TYPE = "Card Type";
    private static  final String USER_EMAIL = "User Email";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<Map> cards = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();
    public AtmAdapter(ArrayList<Map> cards,ArrayList<String> id) {
        this.cards = cards;
        this.id = id;
    }





    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout,parent,false);
       return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Map<String,Object> cardDetails = cards.get(position);
        String expiryDate =  "Expires on " + cardDetails.get(CARDS_EPIRY_DATE).toString();
        String cardNumber = cardDetails.get(CARD_TYPE).toString() + "******"+cardDetails.get(CARDS_NUMBER).toString().substring(0,0);

        if (cardDetails.size() != 0) {
            holder.txt_cardInfo.setText(cardNumber);
            holder.txt_expiryDate.setText(expiryDate);
            if (cardDetails.get(CARD_TYPE).toString().equals("visa")) {
                holder.card_img.setImageResource(R.drawable.visa1);
            } else {
                holder.card_img.setImageResource(R.drawable.master);
            }
            holder.txt_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());

                    builder.setTitle("Confirm Delete");
                    builder.setMessage("Are you sure you want to delete");
                    builder.setNegativeButton("NO", null);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            db.collection(CARDS_COLLECTION).document(id.get(position)).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(holder.itemView.getContext(), "deleted", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            });

                        }
                    });
                    builder.show();

                }
            });
        }else {
            Toast.makeText(holder.itemView.getContext(), "", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder{
        private TextView txt_cardInfo,txt_expiryDate,txt_remove;
        private ImageView card_img;
        public ListItemHolder(@NonNull View itemView) {
            super(itemView);


            txt_cardInfo = itemView.findViewById(R.id.cardInfo);
            card_img = itemView.findViewById(R.id.card_img);
            txt_expiryDate = itemView.findViewById(R.id.txt_expiryDate);
            txt_remove = itemView.findViewById(R.id.txt_remove);

        }
    }


}
