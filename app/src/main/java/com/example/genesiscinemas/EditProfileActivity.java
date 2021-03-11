package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        EditText fullname = findViewById(R.id.edt_fullname);
        EditText email = findViewById(R.id.edt_email);
        EditText phonenumber = findViewById(R.id.edt_phonenumber);
        EditText Password = findViewById(R.id.edt_password);
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
    }
}