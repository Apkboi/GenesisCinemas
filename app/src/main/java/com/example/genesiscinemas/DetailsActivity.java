package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.Cast;
import com.example.genesiscinemas.models.Genre;
import com.example.genesiscinemas.models.MovieCastResponse;
import com.example.genesiscinemas.models.MovieDetails;
import com.example.genesiscinemas.models.MovieImageResponse;
import com.example.genesiscinemas.models.Result;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    RecyclerView actorsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        TextView movie_details = findViewById(R.id.movie_details);
        TextView viewAll = findViewById(R.id.viewAllMovieActors);
        final TextView txt_duration = findViewById(R.id.txt_duration);
        TextView txt_date = findViewById(R.id.txt_date);
        TextView txt_rating = findViewById(R.id.txt_rating);
        TextView txt_director = findViewById(R.id.txt_director);
        ImageView appbar_image = findViewById(R.id.appbar_image);
        final TextView moie_name = findViewById(R.id.movie_name);
        MaterialButton btn_book = findViewById(R.id.btn_book);
        Grid4Adapter adapter = new Grid4Adapter();
        MovieActorAdapter actorAdapter = new MovieActorAdapter();
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        FloatingActionButton floatingActionButton = findViewById(R.id.actionButton);
        FrameLayout progresDialog = findViewById(R.id.progressDialog);
        AppBarLayout layout = findViewById(R.id.appbar_layout);

        final Dialog dialog = new Dialog(this);
        actorsList = findViewById(R.id.actorsDetail);


        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("id",0);
        float rating = intent1.getFloatExtra("rating",0);




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsActivity.this,HomeActivity.class));
            }
        });
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.dialog_layout);
                dialog.show();
                final int adultPrice = 50;
                final int studentPrice = 50;
                final int childPrice = 50;
                final int seniorPrice = 50;

                final MaterialButton book_now = dialog.findViewById(R.id.book_now);
                TextView txt_adultPrice = dialog.findViewById(R.id.txt_adultPrice);
                TextView txt_studentPrice = dialog.findViewById(R.id.txt_studentPrice);
                TextView txt_childPrice = dialog.findViewById(R.id.txt_childPrice);
                TextView txt_seniorPrice = dialog.findViewById(R.id.txt_seniorPrice);
                final TextView txt_total = dialog.findViewById(R.id.txt_total);

                final TextView txt_adult = dialog.findViewById(R.id.txt_adult);
                final TextView txt_student = dialog.findViewById(R.id.txt_student);
                final TextView txt_child = dialog.findViewById(R.id.txt_child);
                final TextView txt_senior = dialog.findViewById(R.id.txt_senior);
                ImageButton minus_adult = dialog.findViewById(R.id.minus_adult);
                ImageButton add_adult = dialog.findViewById(R.id.add_adult);
                ImageButton minus_student = dialog.findViewById(R.id.minus_student);
                ImageButton add_student = dialog.findViewById(R.id.add_student );
                ImageButton minus_child = dialog.findViewById(R.id.minus_child);
                ImageButton add_child = dialog.findViewById(R.id.add_child);
                ImageButton minus_senior = dialog.findViewById(R.id.minus_senior);
                ImageButton add_senior = dialog.findViewById(R.id.add_senior);


                txt_adult.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        int adultQuantity = Integer.parseInt(txt_adult.getText().toString());
                        int studentQuantity = Integer.parseInt(txt_student.getText().toString());
                        int childQuantity = Integer.parseInt(txt_child.getText().toString());
                        int seniorQuantity = Integer.parseInt(txt_senior.getText().toString());
                        final int adult_result = chekOne(adultPrice,adultQuantity);
                        final int student_result = chekOne(studentPrice,studentQuantity);
                        final int child_result = chekOne(childPrice,childQuantity);
                        final int senior_result = chekOne(seniorPrice,seniorQuantity);

                        int total = checkAll(adult_result,student_result,child_result,senior_result);
                        txt_total.setText("$"+(String.valueOf(total)));
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                    }
                });
                txt_student.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        int adultQuantity = Integer.parseInt(txt_adult.getText().toString());
                        int studentQuantity = Integer.parseInt(txt_student.getText().toString());
                        int childQuantity = Integer.parseInt(txt_child.getText().toString());
                        int seniorQuantity = Integer.parseInt(txt_senior.getText().toString());
                        final int adult_result = chekOne(adultPrice,adultQuantity);
                        final int student_result = chekOne(studentPrice,studentQuantity);
                        final int child_result = chekOne(childPrice,childQuantity);
                        final int senior_result = chekOne(seniorPrice,seniorQuantity);

                        int total = checkAll(adult_result,student_result,child_result,senior_result);
                        txt_total.setText("$"+(String.valueOf(total)));
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                    }
                });
                txt_child.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        int adultQuantity = Integer.parseInt(txt_adult.getText().toString());
                        int studentQuantity = Integer.parseInt(txt_student.getText().toString());
                        int childQuantity = Integer.parseInt(txt_child.getText().toString());
                        int seniorQuantity = Integer.parseInt(txt_senior.getText().toString());
                        final int adult_result = chekOne(adultPrice,adultQuantity);
                        final int student_result = chekOne(studentPrice,studentQuantity);
                        final int child_result = chekOne(childPrice,childQuantity);
                        final int senior_result = chekOne(seniorPrice,seniorQuantity);

                        int total = checkAll(adult_result,student_result,child_result,senior_result);
                        txt_total.setText("$"+(String.valueOf(total)));
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                    }
                });
                txt_senior.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        int adultQuantity = Integer.parseInt(txt_adult.getText().toString());
                        int studentQuantity = Integer.parseInt(txt_student.getText().toString());
                        int childQuantity = Integer.parseInt(txt_child.getText().toString());
                        int seniorQuantity = Integer.parseInt(txt_senior.getText().toString());
                        final int adult_result = chekOne(adultPrice,adultQuantity);
                        final int student_result = chekOne(studentPrice,studentQuantity);
                        final int child_result = chekOne(childPrice,childQuantity);
                        final int senior_result = chekOne(seniorPrice,seniorQuantity);

                        int total = checkAll(adult_result,student_result,child_result,senior_result);
                        txt_total.setText("$"+(String.valueOf(total)));
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {


                    }
                });


//-                 ----- -------------OnclickListeners-----------------------------------



                minus_adult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                       String adult_no = String.valueOf(minus(Integer.parseInt((String) txt_adult.getText().toString())));
                       txt_adult.setText(adult_no);
                    }
                });

                add_adult.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String adult_no = String.valueOf(add(Integer.parseInt((String) txt_adult.getText().toString())));
                        txt_adult.setText(adult_no);
                    }
                });

                minus_student.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String student_no = String.valueOf(minus(Integer.parseInt((String) txt_student.getText().toString())));
                        txt_student.setText(student_no);
                    }
                });
                add_student.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String student_no = String.valueOf(add(Integer.parseInt((String) txt_student.getText().toString())));
                        txt_student.setText(student_no);
                    }
                });

                minus_child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String child_no = String.valueOf(minus(Integer.parseInt((String) txt_child.getText().toString())));
                        txt_child.setText(child_no);
                    }
                });
                add_child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String child_no = String.valueOf(add(Integer.parseInt((String) txt_child.getText().toString())));
                        txt_child.setText(child_no);
                    }
                });
                minus_senior.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String senior_no = String.valueOf(minus(Integer.parseInt((String) txt_senior.getText().toString())));
                        txt_senior.setText(senior_no);
                    }
                });
                add_senior.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String senior_no = String.valueOf(add(Integer.parseInt((String) txt_senior.getText().toString())));
                        txt_senior.setText(senior_no);
                    }
                });
                book_now.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(DetailsActivity.this,Checkout.class);

                        intent1.putExtra("id", id);
                        intent1.putExtra("Name", txt_director.getText());
                        intent1.putExtra("duration",txt_duration.getText());
                        intent1.putExtra("total",txt_total.getText());

                        if (txt_total.getText().toString().equals("$0")){
                            Toast.makeText(DetailsActivity.this, "Select Number Of Seats", Toast.LENGTH_SHORT).show();
                        }else{startActivity(intent1);}

                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        });

//        Api_Service service1 = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
//                Call<MovieImageResponse> imageResponseClass = service1.getMovieImage(id,"71abb56b703300c3b3b627872f42db03");
//                imageResponseClass.enqueue(new Callback<MovieImageResponse>() {
//                    @Override
//                    public void onResponse(Call<MovieImageResponse> call, Response<MovieImageResponse> response) {
//
//
//                        if (response.isSuccessful()) {
//                            List<Object> images = response.body().getPosters();
//
//                            Glide.with(DetailsActivity.this).load("https://image.tmdb.org/t/p/w500"+images.get(2).toString())
//                                    .into(appbar_image);
//
//
//                           Log.i(TAG, "onResponse: susses"+response.body().getPosters().size());
//                       }else {
//                            Log.i(TAG, "onResponse: Error");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MovieImageResponse> call, Throwable t) {
//
//                        Log.i(TAG, "onResponse: Error");
//                    }
//                });

        Api_Service service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<MovieDetails> response= service.getMovieDetails(id,"71abb56b703300c3b3b627872f42db03");
        response.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful()) {
                    progresDialog.setVisibility(View.INVISIBLE);
                    floatingActionButton.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                    MovieDetails details =response.body();
                    ratingBar.setRating(rating);
                    txt_rating.setText(String.valueOf(rating));
                    Glide.with(getBaseContext()).load(("https://image.tmdb.org/t/p/w500"+details.getPosterPath()))
                            .into(appbar_image);
                    moie_name.setText(details.getTitle());
                    movie_details.setText(details.getOverview());


                Log.i(TAG, "onResponse: Posterpath=>"+details.getTitle());

                    Log.i(TAG, "onResponse: succesul");
                }else{
                    progresDialog.setVisibility(View.VISIBLE);
                    Log.i(TAG, "onResponse: error"+response.code());

                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

                Log.i(TAG, "onResponse: error");
            }
        });

        Api_Service service1 = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<MovieCastResponse> responseCall = service1.getCast(id,"71abb56b703300c3b3b627872f42db03");
        responseCall.enqueue(new Callback<MovieCastResponse>() {
            @Override
            public void onResponse(Call<MovieCastResponse> call, Response<MovieCastResponse> response) {

                MovieCastResponse castResponse = response.body();
                List<Cast> cast = response.body().getCast();

                if (response.isSuccessful()){
                    progresDialog.setVisibility(View.INVISIBLE);
                    floatingActionButton.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                    txt_director.setText(castResponse.getCast().get(1).getCharacter());
                    adapter.setCastList(cast);
                    actorsList.setAdapter(adapter);
//                    adapter.setId(id);
                    actorAdapter.setactorList(cast);
                    actorAdapter.setId(id);
                    Log.i(TAG, "onResponse: "+cast.get(1).getId());
                }else { progresDialog.setVisibility(View.VISIBLE);}
            }

            @Override
            public void onFailure(Call<MovieCastResponse> call, Throwable t) {

            }
        });



        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog1 = new BottomSheetDialog(DetailsActivity.this);

                dialog1.setContentView(R.layout.actorbuttomsheet);

                RecyclerView recyclerView = dialog1.findViewById(R.id.MovieActorsRecycler);
                ;
                recyclerView.setAdapter(actorAdapter);
                dialog1.show();



            }
        });
    }
    public int minus(int result){
        int text;
        if (result==0){
            text = 0;
        }else{text = result-1;}
        return text;
    }

    public int add(int result){
        int text;
        text = result+1;
        return text;
    }

    public int chekOne(int price ,int quantity){
        int result = price * quantity;
        return  result;
    }
    public int checkAll(int result1,int result2,int result3, int result4){

        int result = result1 + result2 + result3 + result3 + result4;
        return  result;
    }


}