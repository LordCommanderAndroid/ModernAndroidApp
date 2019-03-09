package com.hacker.modernapparch.repository.apiinterface;

import com.hacker.modernapparch.model.Result;

import androidx.annotation.Nullable;

public interface CloudCRUDListener {

    public void onSuccess(@Nullable  String ID, @Nullable Result result);
    public void onFailure (Exception e);
}
