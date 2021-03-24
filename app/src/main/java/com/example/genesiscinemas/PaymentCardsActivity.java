package com.example.genesiscinemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nullable;

public class PaymentCardsActivity extends AppCompatActivity {



    //    ------------Database Collections---------
    private static  final String CARDS_COLLECTION = "Payment Cards";

    //    ------------Database fields------------
    private static  final String CARDS_NUMBER = "Card Number";
    private static  final String CARDS_CVV = "Card cvv";
    private static  final String CARDS_USER = "Card user";
    private static  final String CARDS_EPIRY_DATE = "Card expiry Dste";
    private static  final String CARD_TYPE = "Card Type";
    private static  final String USER_EMAIL = "User Email";
    private static final String TAG = "PaymentCardsActivity";
//    -------------Reference block------------
//    RadioButton btn_visa = findViewById(R.id.visa);
//    RadioButton btn_master = findViewById(R.id.master);
//    TextInputEditText edt_CardNumber = findViewById(R.id.txt_cardNumber);
//    TextInputEditText edt_CardUser = findViewById(R.id.txt_User_name);
//    TextInputEditText edt_Cvv = findViewById(R.id.txt_cvv);
//    TextInputEditText edt_ExpiryDate = findViewById(R.id.txt_ExpiryDate);
//    MaterialButton btn_add = findViewById(R.id.btn_addCard);

    //    ----------------Variables----------
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String Useremail = firebaseAuth.getCurrentUser().getEmail();


    @Override
    public void onBackPressed() {
        finish();
    }
    AtmAdapter atmAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_cards);
        FloatingActionButton btn_addCard = findViewById(R.id.add_newCard);
        RecyclerView recyclerView = findViewById(R.id.cardsRecycler);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        MaterialButton addC = findViewById(R.id.btn_addC);


        addC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentCardsActivity.this,AddCardActivity.class));
            }
        });

        db.collection(CARDS_COLLECTION).whereEqualTo(USER_EMAIL,Useremail)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    ArrayList<Map>  cards = new ArrayList<>();
                    ArrayList<String> id = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                        cards.add(documentSnapshot.getData());
                        id.add(documentSnapshot.getId());

                    }
                     atmAdapter = new AtmAdapter(cards,id);
                    recyclerView.setAdapter(atmAdapter);
                    int card = atmAdapter.getItemCount();
                    if (card == 0){
                        recyclerView.setVisibility(View.INVISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            });





        btn_addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentCardsActivity.this,AddCardActivity.class));
                finish();
            }
        });

          }
}