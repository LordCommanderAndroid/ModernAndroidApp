package com.hacker.modernapparch.repository.apiinterface;

import com.hacker.modernapparch.model.UserModel;

public interface getUserListener {

    public void onUserRetrived(UserModel userModel);
    public void onFailure(Throwable t);
}
