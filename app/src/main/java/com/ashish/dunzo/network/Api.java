package com.ashish.dunzo.network;

import com.ashish.dunzo.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/services/rest/")
    Call<Root> getResponse(@Query("method") String method,@Query("api_key") String api_key,@Query("text") String text,@Query("format") String format,@Query("nojsoncallback") int nojsoncallback,@Query("per_page") int per_page,@Query("page") int page);
}