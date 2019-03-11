package com.hacker.modernapparch.viewmodel;

import android.app.Application;
import android.util.Log;

import com.hacker.modernapparch.Utils.DisplayUtils;
import com.hacker.modernapparch.model.Result;
import com.hacker.modernapparch.model.UserModel;
import com.hacker.modernapparch.repository.UserRepository;
import com.hacker.modernapparch.repository.apiinterface.getUserListener;
import com.hacker.modernapparch.view.MainActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {

    MutableLiveData<Result> resultMutableLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAndBindData() {
        UserRepository userRepository = new UserRepository();


        userRepository.getUser(new getUserListener() {
            @Override
            public void onUserRetrived(UserModel userModel) {
                List<Result> results = userModel.getResults();
                resultMutableLiveData.setValue( results.get(0));


                DisplayUtils.dismissProgress();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("ERROR",t.getMessage(),t);

            }
        });
    }

    public MutableLiveData<Result> getResultMutableLiveData()
    {
        if (resultMutableLiveData == null) {
            resultMutableLiveData = new MutableLiveData<>();
            getAndBindData();
        }
        return resultMutableLiveData;
    }



}
