package com.example.genesiscinemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddCardActivity extends AppCompatActivity {


//    ------------Database Collections---------
    private static  final String CARDS_COLLECTION = "Payment Cards";

//    ------------Database fields------------
    private static  final String CARDS_NUMBER = "Card Number";
    private static  final String CARDS_CVV = "Card cvv";
    private static  final String CARDS_USER = "Card user";
    private static  final String CARDS_EPIRY_DATE = "Card expiry Dste";
    private static  final String CARD_TYPE = "Card Type";
    private static  final String USER_EMAIL = "User Email";
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        //    -------------Reference block------------
       final RadioButton btn_visa = findViewById(R.id.visa);
        RadioButton btn_master = findViewById(R.id.master);
        TextInputEditText edt_CardNumber = findViewById(R.id.txt_cardNumber);
        TextInputEditText edt_CardUser = findViewById(R.id.txt_User_name);
        TextInputEditText edt_Cvv = findViewById(R.id.txt_cvv);
        TextInputEditText edt_ExpiryDate = findViewById(R.id.txt_ExpiryDate);
        MaterialButton btn_add = findViewById(R.id.btn_addCard);
        btn_visa.setChecked(true);






//        ----------Onclicks-------
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardNumber = edt_CardNumber.getText().toString();
                String cardUser = edt_CardUser.getText().toString();
                String expiryDate = edt_ExpiryDate.getText().toString();
                String cvv = edt_Cvv.getText().toString();
                String cardType = getCardType();
                Map<String,Object> cards = new HashMap<>();
                cards.put(CARD_TYPE,cardType);
                cards.put(CARDS_NUMBER,cardNumber);
                cards.put(CARDS_CVV,cvv);
                cards.put(CARDS_EPIRY_DATE,expiryDate);
                cards.put(CARDS_USER,cardUser);
                cards.put(USER_EMAIL,Useremail);
                db.collection(CARDS_COLLECTION).add(cards).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            task.getResult().getId();
                            Toast.makeText(AddCardActivity.this, "Added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddCardActivity.this,PaymentCardsActivity.class));
                            finish();
                        }
                    }
                });
            }
        });



    }



//    --------Getting Atm card Type -----------
    public  String getCardType(){
RadioButton btn_master = findViewById(R.id.master);
        if (btn_master.isChecked()){
            return "master";
        }else{
            return "visa";
        }
    }
}