package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.MovieResponse;
import com.example.genesiscinemas.models.Result;
import com.google.android.material.appbar.MaterialToolbar;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.awt.font.TextAttribute;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TendingMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tending_movies);
        TextView search = findViewById(R.id.search);
        MaterialToolbar toolbar = findViewById(R.id.porpularToolbar);
        RecyclerView TrendingRecycler = findViewById(R.id.TrendingRecycler);
        Grid5Adapter adapter = new Grid5Adapter();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TendingMoviesActivity.this,SearchActivity.class));
                finish();
            }
        });

        final int[] mImages = new int[]{ R.drawable.bb,R.drawable.cc,R.drawable.dd,R.drawable.ee};
        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Api_Service service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<MovieResponse> responseCall =service.getMovies("71abb56b703300c3b3b627872f42db03");
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                List<Result> movies =movieResponse.getResults();
                TrendingRecycler.setAdapter(adapter);
                adapter.setPopularMovies(movies);


            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

//        carouselView.setFillColor(R.color.darkorange);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
    }
}