package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class profile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        TextView fullname = findViewById(R.id.Fullname);
        TextView email = findViewById(R.id.txt_Email);
        TextView phonenumber = findViewById(R.id.txt_phonenumber);
        TextView password = findViewById(R.id.txt_password);
        MaterialButton edit = findViewById(R.id.editProfile);
        MaterialToolbar toolbar = findViewById(R.id.Profiletoolbar);
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
    }
}