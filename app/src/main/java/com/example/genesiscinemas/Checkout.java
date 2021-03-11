package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.MovieDetails;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        final TextView txt_date = findViewById(R.id.txt_Date);
       TextView txt_total =  findViewById(R.id.txt_total);
       TextView txt_director = findViewById(R.id.txt_director);
        TextView txtTittle = findViewById(R.id.Tittle);
        TextView txt_duration =findViewById(R.id.txt_duration);
        ImageView movieImg = findViewById(R.id.movieImg);
        MaterialButton btn_paynow = findViewById(R.id.btn_paynow);
        FrameLayout progressDialog = findViewById(R.id.progressDialog);
        progressDialog.setVisibility(View.INVISIBLE);
        final Intent intent = getIntent();
       final int image = intent.getIntExtra("Image",0);
//       final String tittle = intent.getStringExtra("Name");
       String duration = intent.getStringExtra("duration");
       final String total = intent.getStringExtra("total");
        BottomSheetDialog dialog = new BottomSheetDialog(Checkout.this);
        if (dialog.isShowing()){ dialog.hide();}

        int id = intent.getIntExtra("id",0);

        Api_Service service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<MovieDetails> movieDetailsCall = service.getMovieDetails(id,"71abb56b703300c3b3b627872f42db03");
                movieDetailsCall.enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                        MovieDetails details = response.body();
                        txtTittle.setText(details.getTitle());
                        txt_director.setText(intent.getStringExtra("Name"));
                        Glide.with(Checkout.this).load("https://image.tmdb.org/t/p/w500"+details.getPosterPath())
                                .into(movieImg);
                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                    }
                });
//        txtTittle.setText(tittle);
        txt_total.setText(total);


       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                finish();
           }
       });


//       txt_duration.setText(duration);


       btn_paynow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               CountDownTimer timer = new CountDownTimer(2000,20) {
                   @Override
                   public void onTick(long l) {
                        progressDialog.setVisibility(View.VISIBLE);
                   }

                   @Override
                   public void onFinish() {
                       progressDialog.setVisibility(View.INVISIBLE);
                       BottomSheetDialog dialog = new BottomSheetDialog(Checkout.this);
                       dialog.setContentView(R.layout.buttomsheet_layout);
                       TextView Ddate = dialog.findViewById(R.id.Ddate);
                       TextView movieTittle = dialog.findViewById(R.id.movieTittle);
                       TextView Lprice = dialog.findViewById(R.id.txt_Lprice);
                       Ddate.setText(txt_date.getText());
                       movieTittle.setText(txtTittle.getText());
                       Lprice.setText(total);
                       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                       dialog.show();
                   }
               }.start();

           }
       });
    }
}