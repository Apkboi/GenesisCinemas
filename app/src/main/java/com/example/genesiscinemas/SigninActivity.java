package com.example.genesiscinemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {



    private static final String TAG = "SigninActivity";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //    collections
    public static  final String User_Collection = "Users";
    public static  final  String Bookings_Colletions = "Bookings";



    //     User_Collection fields
    public static  final  String UserFullname = "Fullname";
    public static  final  String UserPhoneNumber = "Phonenumber";
    public static  final  String UserEmail = "Email";
    public static  final  String UserPassword = "Password";
    public static  final  String UserTYPE= "userType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

//        variables
         final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

//         refference
        TextView txt_login = findViewById(R.id.txt_login);
        MaterialButton btnsignup = findViewById(R.id.btnsignup);
        TextInputEditText edt_fullname = findViewById(R.id.edt_fullname);
        TextInputEditText edt_phonenumber = findViewById(R.id.edt_phoneNumber);
        TextInputEditText edt_email = findViewById(R.id.edt_email);
        TextInputEditText edt_password = findViewById(R.id.edt_password);

//        occlicks
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname = edt_fullname.getText().toString();
                String phonenumber = edt_phonenumber.getText().toString();
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String UserId= task.getResult().getUser().getEmail();
                            Map<String,Object> userDetails = new HashMap<>();
                            userDetails.put(UserFullname,fullname);
                            userDetails.put(UserPhoneNumber,phonenumber);
                            userDetails.put(UserPassword,password);
                            userDetails.put(UserEmail,email);
                            userDetails.put(UserTYPE,"user");
                            db.collection(User_Collection).document(UserId).set(userDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SigninActivity.this, "succesful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SigninActivity.this,LoginActivity.class));
                                        finish();
                                    }else {
                                        Log.i(TAG, "onComplete: " + task.getException());
                                    }
                                }
                            });
                        }
                    }
                });

            }
        });

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(SigninActivity.this,LoginActivity.class));
            }
        });
    }
}