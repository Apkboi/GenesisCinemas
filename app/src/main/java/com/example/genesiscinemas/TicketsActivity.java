package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nullable;

public class TicketsActivity extends AppCompatActivity {

    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        RecyclerView TicketsRecycler = findViewById(R.id.TicketsRecycler);
        MaterialToolbar toolbar = findViewById(R.id.Ticketstoolbar);

        db.collection(Bookings_Colletions).whereEqualTo(UserEmail,User).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<Map> booking = new ArrayList<>();
                ArrayList<String> id = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                         booking.add(documentSnapshot.getData());
                         id.add(documentSnapshot.getId());

                }
                TicketsRecycler.setAdapter(new Grid6Adapter(booking,id));
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