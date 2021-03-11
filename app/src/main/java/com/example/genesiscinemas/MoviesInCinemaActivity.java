package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.NowPlayingMovieResponse;
import com.example.genesiscinemas.models.Result;
import com.google.android.material.appbar.MaterialToolbar;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesInCinemaActivity extends AppCompatActivity {

    private static final String TAG = "MoviesInCinemaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_in_cinema);
        TextView search = findViewById(R.id.search);
        RecyclerView MoviesInCinemaRecycler = findViewById(R.id.MoviesInCinemaRecycler);
        CarouselView carouselView = findViewById(R.id.carousel);
        MaterialToolbar toolbar = findViewById(R.id.MoviesToolbar);
        Grid2Adapter adapter = new Grid2Adapter();
        MoviesInCinemaRecycler.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoviesInCinemaActivity.this,SearchActivity.class));
            }
        });
        final int[] mImages = {R.drawable.ee,R.drawable.aa,R.drawable.cc};
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Api_Service api_service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<NowPlayingMovieResponse> nowPlayingMovieResponseCall = api_service.getNowPlaying("71abb56b703300c3b3b627872f42db03");
        nowPlayingMovieResponseCall.enqueue(new Callback<NowPlayingMovieResponse>() {
            @Override
            public void onResponse(Call<NowPlayingMovieResponse> call, Response<NowPlayingMovieResponse> response) {
                List<Result> results = response.body().getResults();
                if (response.isSuccessful()){
                    adapter.setNowPlaying(results);
                    Log.i(TAG, "onResponse: "+results.size());
                }else{
                    Log.i(TAG, "onResponse: error");
                }
            }

            @Override
            public void onFailure(Call<NowPlayingMovieResponse> call, Throwable t) {

                Log.i(TAG, "onResponse: error");
            }
        });
    }
}