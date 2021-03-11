package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;

public class FavoriteActorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_actor);
        RecyclerView favoriteActorsRecycler = findViewById(R.id.favoriteActorsRecycler);
        MaterialToolbar toolbar = findViewById(R.id.Favoritetoolbar);
        favoriteActorsRecycler.setAdapter(new FavoriteActorAdapter());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}