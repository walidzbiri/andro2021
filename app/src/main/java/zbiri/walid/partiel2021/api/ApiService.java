package zbiri.walid.partiel2021.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    public static final String ENDPOINT="https://my-json-server.typicode.com/walidzbiri/demo/";

    @GET("db")
    Call<TeamsResponse> getAllTeams();

    @GET("db")
    Call<DriversResponse> getTeamDrivers();
/*
    @GET("/3/movie/upcoming")
    Call<PopularMovieResponse> getUpcomingMovies(@Query("api_key") String key,@Query("page") int page,@Query("language") String lang);

    @GET("/3/movie/{movie_id}")
    Call<DetailsMovieResponse> getMovieDetail(@Path("movie_id") int movie_id, @Query("api_key") String key, @Query("page") int page, @Query("language") String lang);
*/
}

