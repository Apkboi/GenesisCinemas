package com.example.genesiscinemas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.MovieDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;
import java.util.Map;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout extends AppCompatActivity {


    private static final String TAG = "Checkout";
    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String public_key = "pk_test_2442c1c75c79a8cbd1fdd8cba558a68ea1dd8524";

    //    collections
    public static  final String User_Collection = "Users";
    public static  final  String Bookings_Colletions = "Bookings";
    public static  final  String Reservation_Colletions = "Reservations";

    //    Documents
    public static  final String User =  firebaseAuth.getCurrentUser().getEmail();

    //     User_Collection fields
    public static  final  String UserFullname = "Fullname";
    public static  final  String UserPhoneNumber = "Phonenumber";
    public static  final  String UserEmail = "Email";
    public static  final  String UserPassword = "Password";

    //     Bookings_Collection fields
    public static  final  String MovieName = "Movie name";
    public static  final  String Date = "Date and Time";
    public static  final  String Time = "Time";
    public static  final  String Price = "Price";
    public static  final  String SeatNumber = "Seat Number";



    int Total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        PaystackSdk.setPublicKey(public_key);
        PaystackSdk.initialize(getApplicationContext());
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        final TextView txt_date = findViewById(R.id.txt_Date);
       TextView txt_total =  findViewById(R.id.txt_total);
       TextView txt_director = findViewById(R.id.txt_director);
        TextView txtTittle = findViewById(R.id.Tittle);
        TextView txt_duration =findViewById(R.id.txt_duration);
        TextView txt_cardNo = findViewById(R.id.txt_cardNo);
        TextView txt_seats = findViewById(R.id.txt_seats);
        ImageView movieImg = findViewById(R.id.movieImg);
        ImageView imgCard = findViewById(R.id.img_card);
        MaterialButton btn_paynow = findViewById(R.id.btn_paynow);
        MaterialButton btn_reserve = findViewById(R.id.btn_reserve);
        FrameLayout progressDialog = findViewById(R.id.progressDialog);

//        ButtomSheet Variables
        BottomSheetDialog dialog1 = new BottomSheetDialog(Checkout.this);
        dialog1.setContentView(R.layout.buttomsheet_layout);
        TextView Ddate = dialog1.findViewById(R.id.Ddate);
        TextView movieTittle = dialog1.findViewById(R.id.movieTittle);
        TextView Lprice = dialog1.findViewById(R.id.txt_Lprice);
        ImageView img_barcode = dialog1.findViewById(R.id.img_barcode);

        progressDialog.setVisibility(View.INVISIBLE);
        final Intent intent = getIntent();
       final int image = intent.getIntExtra("Image",0);
//       final String tittle = intent.getStringExtra("Name");
       String duration = intent.getStringExtra("duration");
       final String total = intent.getStringExtra("total");
       String cardNo = intent.getStringExtra("cardNumber");
       String cardType = intent.getStringExtra("cardType");
        String seat = intent.getStringExtra("seats");

        if (cardType.equals("visa")) {
           imgCard.setImageResource(R.drawable.visacard);
       }else {
           imgCard.setImageResource(R.drawable.master);
       }
       txt_cardNo.setText(cardNo);
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
        int length = total.length();
        Total = Integer.parseInt(txt_total.getText().toString().substring(1,length));


       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                finish();
           }
       });


//       txt_duration.setText(duration);

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> reservation = new HashMap<>();
                reservation.put(MovieName,txtTittle.getText().toString());
                reservation.put(Date,Ddate.getText().toString());
//                       booking.put(Time,movieTittle.getText().toString());
                reservation.put(Price,Lprice.getText().toString());
                reservation.put(SeatNumber,txt_seats.getText().toString());
                reservation.put(UserEmail,User);
                db.collection(Reservation_Colletions).add(reservation).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        
                        if (task.isSuccessful()){
                            Toast.makeText(Checkout.this, "Reserved", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Checkout.this,HomeActivity.class));
                            finish();
                        }
                    }
                });
            }
        });


       btn_paynow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               performCharge();

               CountDownTimer timer = new CountDownTimer(2000,20) {
                   @Override
                   public void onTick(long l) {
                        progressDialog.setVisibility(View.VISIBLE);
                   }

                   @Override
                   public void onFinish() {
                       progressDialog.setVisibility(View.INVISIBLE);

                      
//                       Database Creation
                       Map<String, Object> booking = new HashMap<>();
                       booking.put(MovieName,txtTittle.getText().toString());
                       booking.put(Date,Ddate.getText().toString());
//                       booking.put(Time,movieTittle.getText().toString());
                       booking.put(Price,Lprice.getText().toString());
                       booking.put(SeatNumber,txt_seats.getText().toString());
                       booking.put(UserEmail,User);

                       db.collection(Bookings_Colletions).add(booking).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                           @Override
                           public void onComplete(@NonNull Task<DocumentReference> task) {
                               if (task.isSuccessful()){
                                   Log.i(TAG, "onComplete: " +task.getResult().getId());
                                   MultiFormatWriter writer = new MultiFormatWriter();
                                   try {
                                       BitMatrix matrix = writer.encode( task.getResult().getId(), BarcodeFormat.QR_CODE,300,100);
                                       BarcodeEncoder encoder = new BarcodeEncoder();
                                       Bitmap bitmap = encoder.createBitmap(matrix);
                                       img_barcode.setImageBitmap(bitmap);
                                   } catch (WriterException e) {
                                       e.printStackTrace();
                                   }
                                   CountDownTimer timer1 = new CountDownTimer(1000,50) {
                                       @Override
                                       public void onTick(long l) {

                                       }

                                       @Override
                                       public void onFinish() {
                                           dialog1.hide();
                                           startActivity(new Intent(Checkout.this,HomeActivity.class));
                                           finish();

                                       }
                                   }.start();
                               }
                           }
                       });


                       Ddate.setText(txt_date.getText());
                       movieTittle.setText(txtTittle.getText());
                       Lprice.setText(total);
                       dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                       dialog1.show();
                   }
               }.start();

           }
       });
    }

    public void performCharge (){
        String cardNumber = "4084084084084081";
        int expiryMonth = 11; //any month in the future
        int expiryYear = 21; // any year in the future. '2018' would work also!
        String cvv = "408";  // cvv of the test card

        Card card = new Card(cardNumber, expiryMonth, expiryYear, cvv);
        Charge charge = new Charge();
        charge.setCard(card);
        charge.setCurrency("NGN");
        charge.setAmount(Total);
        charge.setEmail(firebaseAuth.getCurrentUser().getEmail());//sets the card to charge

        PaystackSdk.chargeCard(Checkout.this, charge, new Paystack.TransactionCallback() {
            @Override
            public void onSuccess(Transaction transaction) {
                // This is called only after transaction is deemed successful.
                // Retrieve the transaction, and send its reference to your server
                // for verification.
            }

            @Override
            public void beforeValidate(Transaction transaction) {
                // This is called only before requesting OTP.
                // Save reference so you may send to server. If
                // error occurs with OTP, you should still verify on server.
            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                //handle error here
            }


        });
    }
}