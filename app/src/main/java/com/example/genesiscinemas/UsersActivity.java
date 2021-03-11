package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class UsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        RecyclerView recyclerView = findViewById(R.id.UserList);
        recyclerView.setAdapter(new UserListAdapter());
    }
}