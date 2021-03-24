package com.example.genesiscinemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nullable;

import static com.example.genesiscinemas.Checkout.User;

public class profile_Activity extends AppCompatActivity {
    // ---------collections----------
    public static  final String User_Collection = "Users";
    public static  final  String Bookings_Colletions = "Bookings";

    //     -------------User_Collection fields-------
    public static  final  String UserFullname = "Fullname";
    public static  final  String UserPhoneNumber = "Phonenumber";
    public static  final  String UserEmail = "Email";
    public static  final  String UserPassword = "Password";


//    ---------Documents----------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

//        ------------Variables-----------
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String Useremail = firebaseAuth.getCurrentUser().getEmail();


//        --------------Reference Block-----------
        TextView fullname = findViewById(R.id.Fullname);
        TextView email = findViewById(R.id.txt_Email);
        TextView phonenumber = findViewById(R.id.txt_phonenumber);
        TextView txt_booked = findViewById(R.id.txt_bookedTickets);
        TextView password = findViewById(R.id.txt_password);
        MaterialButton edit = findViewById(R.id.editProfile);
        MaterialToolbar toolbar = findViewById(R.id.Profiletoolbar);
        FrameLayout dialog = findViewById(R.id.progressDialog);
        FloatingActionButton actionButton = findViewById(R.id.actionbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar);


//        ----------Onclicks-------------
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(profile_Activity.this,EditProfileActivity.class);
                intent.putExtra("Fullname",fullname.getText().toString());
                intent.putExtra("Email",email.getText().toString());
                intent.putExtra("phonenumber",phonenumber.getText().toString());
                intent.putExtra("password",password.getText().toString());
                startActivity(intent);

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        ---------Firebase profile Request-----------
       db.collection(User_Collection).whereEqualTo(UserEmail,Useremail)
               .get()
               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                       if (task.isSuccessful()){
                                dialog.setVisibility(View.INVISIBLE);
                                actionButton.setVisibility(View.VISIBLE);
                         for (QueryDocumentSnapshot documentSnapshot :task.getResult()){
                             Map<String,Object> items = documentSnapshot.getData();
                           String fulname =  documentSnapshot.getData().get(UserFullname).toString();
                           String Unumber = documentSnapshot.getData().get(UserPhoneNumber).toString();
                           String Upassword = documentSnapshot.getData().get(UserPassword).toString();
                           String Uemail = documentSnapshot.getData().get(UserEmail).toString();

                           phonenumber.setText(Unumber);
                           password.setText(Upassword);
                           email.setText(Uemail);
                           fullname.setText(fulname);
                           toolbarLayout.setTitle(fulname);

                         }
                       }
                   }
               });
        db.collection(Bookings_Colletions).whereEqualTo(UserEmail,User).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<Map> booking = new ArrayList<>();
                ArrayList<String> id = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                    booking.add(documentSnapshot.getData());

                }
                int booked = booking.size();
                txt_booked.setText(String.valueOf(booked));
            }
        });
    }
}