package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.SearchView;

import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.Result;
import com.example.genesiscinemas.models.SearchResponse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    SearchView searchQuery;
    RecyclerView searchRecycler;
    SearchAdapter searchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
       searchQuery = findViewById(R.id.searchQuery);
         searchRecycler = findViewById(R.id.SearchRecycler);
        searchAdapter = new SearchAdapter();
        searchRecycler.setAdapter(searchAdapter);

        searchQuery.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!s.isEmpty()){
                makeRequest(s);}
                Log.i(TAG, "onQueryTextChange: Query:"+s);
                return true;
            }
        });
    }
    public void makeRequest(String query){

        Api_Service service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
        Call<SearchResponse> searchResponseCall = service.getSearchResponse("71abb56b703300c3b3b627872f42db03",query);
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                List<Result> results = response.body().getResults();

                    if (response.isSuccessful()) {
                    searchAdapter.setResults(results);

                    Log.i(TAG, "onResponse: "+response.body().getTotalResults());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }
}