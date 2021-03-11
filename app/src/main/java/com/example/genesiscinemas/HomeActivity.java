package com.example.genesiscinemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.MovieResponse;
import com.example.genesiscinemas.models.NowPlayingMovieResponse;
import com.example.genesiscinemas.models.NowPlayingResult;
import com.example.genesiscinemas.models.Result;
import com.example.genesiscinemas.models.UpcomingMovieResponse;
import com.example.genesiscinemas.models.UpcomingResults;
import com.google.android.material.navigation.NavigationView;
import com.pollux.widget.DualProgressView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    RecyclerView PopularList;
    RecyclerView UpcomingRecycler;
    RecyclerView MoviesInCinemaRecycler;
    ImageView navicon;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView popular;
    TextView viewpopular;
    TextView viewMovieInCinema;
    FrameLayout progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView edt_search = findViewById(R.id.edt_Search);
//        DualProgressView progressView = findViewById(R.id.popularRecycler);
        PopularList = findViewById(R.id.popularRecycler);
        UpcomingRecycler = findViewById(R.id.UpcomingRecycler);
        MoviesInCinemaRecycler =findViewById(R.id.MoviesInCinemaRecycle);
        final GridAdapter adapter =  new GridAdapter();
        Grid2Adapter adapter1 = new Grid2Adapter();
        UpcomigAdapter upcomigAdapter = new UpcomigAdapter();
        MoviesInCinemaRecycler.setAdapter(adapter1);
        PopularList.setAdapter(adapter);
//        ActorsRecycler.setAdapter(new Grid3Adapter());
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawer);
        popular = findViewById(R.id.txt_popular);
        navicon = findViewById(R.id.navicon);
        viewpopular = findViewById(R.id.viewPorpularMovies);
        viewMovieInCinema = findViewById(R.id.txt_viewMoviesInCinema);
        progressDialog = findViewById(R.id.progressDialog);






//        --------------------Api Requests-------------------------

        Api_Service service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<MovieResponse> responseCall = service.getMovies("71abb56b703300c3b3b627872f42db03");
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                if (response.isSuccessful())
                {
                    progressDialog.setVisibility(View.INVISIBLE);
                    List<Result> movies = response.body().getResults();
                    adapter.setMovies(movies);
                Log.i(TAG, "onResponse: Numbers o movies fetched is=" + movies.get(4).getVoteCount());
           }else {
                    progressDialog.setVisibility(View.VISIBLE);
                    Log.i(TAG, "onResponse: error"+response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


//                      ------------Movies in cinema Api Request---------------

        Api_Service api_service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<NowPlayingMovieResponse> nowPlayingMovieResponseCall = api_service.getNowPlaying("71abb56b703300c3b3b627872f42db03");
        nowPlayingMovieResponseCall.enqueue(new Callback<NowPlayingMovieResponse>() {
            @Override
            public void onResponse(Call<NowPlayingMovieResponse> call, Response<NowPlayingMovieResponse> response) {
                progressDialog.setVisibility(View.INVISIBLE);
                List<Result> results = response.body().getResults();
                if (response.isSuccessful()){
                    adapter1.setNowPlaying(results);
                    Log.i(TAG, "onResponse: "+results.size());
                }else{
                    Log.i(TAG, "onResponse: error");
                    progressDialog.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<NowPlayingMovieResponse> call, Throwable t) {

                Log.i(TAG, "onResponse: error");
            }
        });

//        ---------------------Upcoming movies Request---------------------------------

        Api_Service apiService = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<UpcomingMovieResponse> upcomingMovieResponseCall = api_service.getUpcomingMovies("71abb56b703300c3b3b627872f42db03");
        upcomingMovieResponseCall.enqueue(new Callback<UpcomingMovieResponse>() {
            @Override
            public void onResponse(Call<UpcomingMovieResponse> call, Response<UpcomingMovieResponse> response) {
                UpcomingMovieResponse movies = response.body();

              if (response.isSuccessful())
                {
                    progressDialog.setVisibility(View.GONE);
                    List<Result> list = movies.getResults();
                UpcomingRecycler.setAdapter(upcomigAdapter);
                upcomigAdapter.setUpcomingMovies(list);
                    Log.i(TAG, "onResponse: "+list.get(1).getTitle());
                }else{
                  progressDialog.setVisibility(View.VISIBLE);
                  Log.i(TAG, "onResponse: error"+response.code());
              }

            }

            @Override
            public void onFailure(Call<UpcomingMovieResponse> call, Throwable t) {
                Log.i(TAG, "onResponse: error");
            }
        });






//       ------------------- OnclickListeners-------------------------------------
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(HomeActivity.this,profile_Activity.class));
                        break;
                    case R.id.trendingMovies:
                        startActivity(new Intent(HomeActivity.this,TendingMoviesActivity.class));                        break;
                    case R.id.moviesInCinema:
                        startActivity(new Intent(HomeActivity.this,MoviesInCinemaActivity.class));
                        break;
                    case R.id.bookedTickets:
                        startActivity(new Intent(HomeActivity.this,TicketsActivity.class));
                        break;
                    case R.id.AdminPanel:
                        startActivity(new Intent(HomeActivity.this,Admin_Activity.class));
                        break;
                    case R.id.home:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
                return true;
            }
        });


        edt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,SearchActivity.class));
            }
        });

        viewpopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,TendingMoviesActivity.class));
            }
        });
        viewMovieInCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MoviesInCinemaActivity.class));
            }
        });



        navicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }
}