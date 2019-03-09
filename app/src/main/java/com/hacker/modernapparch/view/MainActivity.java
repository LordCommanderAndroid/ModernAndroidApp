package com.hacker.modernapparch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.hacker.modernapparch.R;
import com.hacker.modernapparch.Utils.DisplayUtils;
import com.hacker.modernapparch.Utils.OnSwipeTouchListener;
import com.hacker.modernapparch.Utils.SlideAnimationUtil;
import com.hacker.modernapparch.databinding.ActivityMainBinding;
import com.hacker.modernapparch.model.Result;
import com.hacker.modernapparch.model.UserModel;
import com.hacker.modernapparch.repository.UserRepository;
import com.hacker.modernapparch.repository.apiinterface.getUserListener;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getAndBindData();
        initListeners();

    }

    private void initListeners() {
        binding.activityProfile.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                // your swipe left here.
                Toast.makeText(MainActivity.this, "Swipe Left", Toast.LENGTH_SHORT).show();
                SlideAnimationUtil.slideOutToLeft(MainActivity.this,binding.activityProfile);
                getAndBindData();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                // your swipe right here.
                Toast.makeText(MainActivity.this, "Swipe Right", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getAndBindData() {
        UserRepository userRepository = new UserRepository();

        DisplayUtils.showProgressDialog(this);

        userRepository.getUser(new getUserListener() {
            @Override
            public void onUserRetrived(UserModel userModel) {
                List<Result> results = userModel.getResults();
                binding.setUser(results.get(0)); // 8 textviews setText-ed in a single line
                DisplayUtils.dismissProgress();
            }

            @Override
            public void onFailure(Throwable t) {
                DisplayUtils.showAlert(MainActivity.this,"Error",t.getMessage());


            }
        });
    }
}
