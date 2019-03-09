package com.hacker.modernapparch.repository.apiinterface;

import com.hacker.modernapparch.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/api")
    Call<UserModel> getUser ();


}
