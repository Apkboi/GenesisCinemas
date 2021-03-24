package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //    collections
    public static  final String User_Collection = "Users";
    public static  final  String Bookings_Colletions = "Bookings";



    //     User_Collection fields
    public static  final  String UserFullname = "Fullname";
    public static  final  String UserPhoneNumber = "Phonenumber";
    public static  final  String UserEmail = "Email";
    public static  final  String UserPassword = "Password";


//        variables

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            EditText fullname = findViewById(R.id.fullname);
        EditText email = findViewById(R.id.email);
        EditText phonenumber = findViewById(R.id.phonenumber);
        EditText Password = findViewById(R.id.password);
        MaterialButton btn_update = findViewById(R.id.btn_Update);
        ImageView close = findViewById(R.id.img_close);
        Intent intent = getIntent();
       String Fullname = intent.getStringExtra("Fullname");
       String txt_Email = intent.getStringExtra("Email");
        String Phonenumber = intent.getStringExtra("phonenumber");
        String password = intent.getStringExtra("password");

       fullname.setText(Fullname);
       email.setText(txt_Email);
       phonenumber.setText(Phonenumber);
       Password.setText(password);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                db.collection(User_Collection).document(firebaseAuth.getUid()).update()

            }
        });
    }
}