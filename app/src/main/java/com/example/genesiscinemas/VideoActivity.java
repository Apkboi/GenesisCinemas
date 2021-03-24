package com.example.genesiscinemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.genesiscinemas.api_service.Api_Service;
import com.example.genesiscinemas.api_service.RetrofitClient;
import com.example.genesiscinemas.models.VideoResponse;
import com.example.genesiscinemas.models.VideoResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends YouTubeBaseActivity {


    @Override
    public void onBackPressed() {
       finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        FloatingActionButton btn_play = findViewById(R.id.btn_play);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.videoPlayer);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);



//        --------Api Request-------------
        Api_Service service = RetrofitClient.getRetrofitInstance().create(Api_Service.class);
       Call<VideoResponse> responseCall = service.getVideos(id,"71abb56b703300c3b3b627872f42db03");
       responseCall.enqueue(new Callback<VideoResponse>() {
           @Override
           public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
               if (response.isSuccessful()){
                   VideoResponse videoResponse = response.body();
                  List<VideoResult> videos = videoResponse.getResults();
                String key = String.valueOf(videos.get(0).getKey());
                   btn_play.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           btn_play.setVisibility(View.GONE);
                           youTubePlayerView.initialize("AIzaSyBAR_s1I1N_tT0AVckjXciGRO2Yqnyrf0c", new YouTubePlayer.OnInitializedListener() {
                               @Override
                               public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                                   if (key != null) {
                                       youTubePlayer.loadVideo(key);
                                   }else{
                                       Toast.makeText(VideoActivity.this, "trailer not Available", Toast.LENGTH_SHORT).show();
                                   }
                               }
                               @Override
                               public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                               }
                           });
                       }
                   });
               }
           }

           @Override
           public void onFailure(Call<VideoResponse> call, Throwable t) {

           }
       });
    }
}