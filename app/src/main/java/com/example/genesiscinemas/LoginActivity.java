package com.example.genesiscinemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import javax.annotation.Nullable;

public class LoginActivity extends AppCompatActivity {

    //    collections
    public static  final String User_Collection = "Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        --------Variables-------------
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


//    ---------------Reference--------------
        MaterialButton btn_login = findViewById(R.id.btnlogin);
        TextView txt_signup = findViewById(R.id.txt_signup);
        TextInputEditText edt_Email = findViewById(R.id.edt_Email);
        TextInputEditText edt_Password = findViewById(R.id.edt_Password);

//        -----------------Onclicks------------------
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user !=null){
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_Email.getText().toString();
                String password = edt_Password.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = firebaseAuth.getCurrentUser().getUid();
                          Task<QuerySnapshot> reference =  db.collection(User_Collection).whereEqualTo("userType","admin").get();
                          if (reference != null){
                              startActivity(new Intent(LoginActivity.this,Admin_Activity.class));
                          }


                        }
                    }
                });
            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this,SigninActivity.class));
                finish();
            }
        });
    }
}