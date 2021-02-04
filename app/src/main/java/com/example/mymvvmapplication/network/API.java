package com.example.mymvvmapplication.network;

import com.example.mymvvmapplication.model.Actor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("data.php")
    Call<List<Actor>> getAllActor();
}
