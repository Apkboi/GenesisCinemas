package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.ActorsResponse;
import com.example.genesiscinemas.models.Cast;
import com.example.genesiscinemas.models.MovieCastResponse;
import com.example.genesiscinemas.models.MovieDetails;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorProfile_Activity extends AppCompatActivity {
    private static final String TAG = "ActorProfile_Activity";
    FrameLayout progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_profile_);
        ImageView app_bar = findViewById(R.id.appbar_image);
        MaterialToolbar toolbar = findViewById(R.id.Actorstoolbar);
//        RecyclerView recyclerView = findViewById(R.id.RecentMoviesRecycler);
        TextView biography = findViewById(R.id.txt_biography);
        TextView txt_name = findViewById(R.id.txt_name);
        TextView txt_gender = findViewById(R.id.txt_gender);
        TextView txt_department = findViewById(R.id.txt_work);
        TextView txt_birthday = findViewById(R.id.txt_birthday);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);

        progressDialog = findViewById(R.id.progressDialog);
//        recyclerView.setAdapter(new ActorsMovieAdapter());

//            ---------INTENT DATAS------
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
//        int mId = intent.getIntExtra("Mid",0);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        Api_Service service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<ActorsResponse> responseCall = service.getActorDetails(id,"71abb56b703300c3b3b627872f42db03");
        responseCall.enqueue(new Callback<ActorsResponse>() {
            @Override
            public void onResponse(Call<ActorsResponse> call, Response<ActorsResponse> response) {

                if (response.isSuccessful()){ progressDialog.setVisibility(View.GONE);}else{progressDialog.setVisibility(View.VISIBLE);}
                ActorsResponse response1 = response.body();
                biography.setText(response1.getBiography());
               txt_name.setText( response1.getName());
               collapsingToolbarLayout.setTitle(response1.getName());
               txt_birthday.setText(response1.getBirthday());
               txt_department.setText(response1.getKnownForDepartment());
                Log.i(TAG, "onResponse: ID="+response1.getId());
               Glide.with(ActorProfile_Activity.this).load("https://image.tmdb.org/t/p/w500"+response1.getProfilePath())
                       .into(app_bar);
               int gender = response1.getGender();
                if (gender == 2){
                    txt_gender.setText("male");
                }else if (gender == 1){
                    txt_gender.setText("female");
                }



            }

            @Override
            public void onFailure(Call<ActorsResponse> call, Throwable t) {

            }
        });
//               ------------------------------Request Using Movie Credits-----------------------------

//
//        Api_Service service1 = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
//        Call<MovieCastResponse> CastresponseCall = service1.getCast(mId,"71abb56b703300c3b3b627872f42db03");
//        CastresponseCall.enqueue(new Callback<MovieCastResponse>() {
//            @Override
//            public void onResponse(Call<MovieCastResponse> call, Response<MovieCastResponse> response) {
//                int position = intent.getIntExtra("position",0);
//                MovieCastResponse castResponse = response.body();
//                List<Cast> cast = castResponse.getCast();
//                if (response.isSuccessful()){
//
//
//                    Glide.with(ActorProfile_Activity.this).load("https://image.tmdb.org/t/p/w500"+cast.get(position).getProfilePath())
//                            .into(app_bar);
//                    txt_department.setText(cast.get(position).getKnownForDepartment());
//                    int gender =  cast.get(position).getGender();
//                    if (gender == 2){
//                        txt_gender.setText("male");
//                    }else if (gender == 1){
//                        txt_gender.setText("female");
//                    }
//
//
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieCastResponse> call, Throwable t) {
//
//            }
//        });

//        Api_Service service1 = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
//        Call<MovieDetails> movieDetailsCall = service1.getMovieDetails(mId,"71abb56b703300c3b3b627872f42db03");
//        movieDetailsCall.enqueue(new Callback<MovieDetails>() {
//            @Override
//            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieDetails> call, Throwable t) {
//
//            }
//        });
    }
}