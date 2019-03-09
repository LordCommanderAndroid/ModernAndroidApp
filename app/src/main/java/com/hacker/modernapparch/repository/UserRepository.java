package com.hacker.modernapparch.repository;

import com.hacker.modernapparch.model.UserModel;
import com.hacker.modernapparch.repository.apiinterface.ApiInterface;
import com.hacker.modernapparch.repository.apiinterface.getUserListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    ApiInterface apiInterface;

    public void getUser(final getUserListener getUserListener)
    {
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        Call<UserModel> userModelCall = apiInterface.getUser();

        userModelCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                getUserListener.onUserRetrived(userModel);
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                getUserListener.onFailure(t);
            }
        });
    }



}
