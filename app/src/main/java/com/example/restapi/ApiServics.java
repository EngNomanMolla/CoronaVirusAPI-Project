package com.example.restapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServics {
    @GET("/countries/bangladesh")
    Call<ModelClass> getData();


}
