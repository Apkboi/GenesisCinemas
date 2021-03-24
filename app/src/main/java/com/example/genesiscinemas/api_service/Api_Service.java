package com.example.genesiscinemas.api_service;

import com.example.genesiscinemas.models.ActorsResponse;
import com.example.genesiscinemas.models.MovieCastResponse;
import com.example.genesiscinemas.models.MovieDetails;
import com.example.genesiscinemas.models.MovieImageResponse;
import com.example.genesiscinemas.models.MovieResponse;
import com.example.genesiscinemas.models.NowPlayingMovieResponse;
import com.example.genesiscinemas.models.SearchResponse;
import com.example.genesiscinemas.models.UpcomingMovieResponse;
import com.example.genesiscinemas.models.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_Service {

    @GET("/3/movie/popular")
   public Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("/3/movie/now_playing")
    public Call<NowPlayingMovieResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("/3/movie/{movie_id}/images")
    public Call<MovieImageResponse> getMovieImage( @Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("/3/movie/{movie_id}")
    public Call<MovieDetails> getMovieDetails(@Path("movie_id") int movieId,@Query("api_key") String apiKey);

    @GET("/3/movie/{movie_id}/credits")
    public Call<MovieCastResponse> getCast (@Path("movie_id") int movieId, @Query("api_key") String apiKey);
    @GET("/3/movie/upcoming" )
         public Call<UpcomingMovieResponse> getUpcomingMovies(@Query("api_key") String apiKey);
    @GET("/3/person/{person_id}")
    public Call<ActorsResponse> getActorDetails(@Path("person_id") int id, @Query("api_key") String apiKey);

   @GET("/3/search/movie")
    public Call<SearchResponse> getSearchResponse (@Query("api_key") String apiKey,@Query("query") String query);

   @GET("/3/movie/{movie_id}/videos")
    public Call<VideoResponse> getVideos (@Path("movie_id") int movieId,@Query("api_key") String apiKey);
}
