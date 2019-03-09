package com.hacker.modernapparch.repository;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacker.modernapparch.model.Result;
import com.hacker.modernapparch.repository.apiinterface.CloudCRUDListener;

import androidx.annotation.NonNull;

public class CloudRepository {
    private  final String TAG = this.getClass().getSimpleName();
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db ;

    public CloudRepository()
    {
        db = FirebaseFirestore.getInstance();
    }

    public void addData(Result userModel, final CloudCRUDListener cloudCRUDListener)
    {


// Add a new document with a generated ID
        db.collection("users")
                .add(userModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        cloudCRUDListener.onSuccess(documentReference.getId(),null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "DocumentSnapshot Error adding document", e);
                        cloudCRUDListener.onFailure(e);
                    }
                });
    }
}
