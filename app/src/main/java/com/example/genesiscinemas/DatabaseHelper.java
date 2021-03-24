package com.example.genesiscinemas;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper {
   public static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean result;



//    collections
   public static  final String User_Collection = "Users";
    public static  final  String Bookings_Colletions = "Bookings";

//    Documents
     public static  final String Users =  firebaseAuth.getCurrentUser().getEmail();

//     User_Collection fields
        public static  final  String UserFullname = "Fullname";
        public static  final  String UserPhoneNumber = "Phonenumber";
        public static  final  String UserEmail = "Email";
        public static  final  String UserPassword = "Password";


     public boolean RegisterUser(String fullname,String phoneNumber,String email,String password){

      Task<AuthResult> tasks = firebaseAuth.createUserWithEmailAndPassword(email,password);

      boolean result1 = tasks.isSuccessful();
                 if (result1 == true){
                     Map<String,Object> userDetails = new HashMap<>();
                     userDetails.put(UserFullname,fullname);
                     userDetails.put(UserPhoneNumber,phoneNumber);
                     userDetails.put(UserPassword,password);
                   DocumentReference reference = db.collection(User_Collection).document(Users);
                 result = reference.set(userDetails).isSuccessful();


                 }
                 if (result){
                     return true;
                 }else {
                     return false;
                 }


     }
}
