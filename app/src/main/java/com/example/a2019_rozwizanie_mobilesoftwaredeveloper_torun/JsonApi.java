package com.example.a2019_rozwizanie_mobilesoftwaredeveloper_torun;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("users/allegro/repos?sort=updated")
    Call<List<Data>> getData();

}
