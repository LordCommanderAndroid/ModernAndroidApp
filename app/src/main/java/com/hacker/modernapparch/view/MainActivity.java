package com.hacker.modernapparch.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hacker.modernapparch.R;
import com.hacker.modernapparch.Utils.DisplayUtils;
import com.hacker.modernapparch.Utils.OnSwipeTouchListener;
import com.hacker.modernapparch.Utils.SlideAnimationUtil;
import com.hacker.modernapparch.databinding.ActivityMainBinding;
import com.hacker.modernapparch.model.Result;
import com.hacker.modernapparch.model.UserModel;
import com.hacker.modernapparch.repository.CloudRepository;
import com.hacker.modernapparch.repository.UserRepository;
import com.hacker.modernapparch.repository.apiinterface.CloudCRUDListener;
import com.hacker.modernapparch.repository.apiinterface.getUserListener;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private Result result;

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

        });

    }

    private void getAndBindData() {
        UserRepository userRepository = new UserRepository();

        DisplayUtils.showProgressDialog(this);

        userRepository.getUser(new getUserListener() {
            @Override
            public void onUserRetrived(UserModel userModel) {
                List<Result> results = userModel.getResults();
                result = results.get(0);
                binding.setUser(result); // 8 textviews setText-ed in a single line

                DisplayUtils.dismissProgress();
            }

            @Override
            public void onFailure(Throwable t) {
                DisplayUtils.showAlert(MainActivity.this,"Error",t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.add: {
            //add the function to perform here
            new CloudRepository().addData(result, new CloudCRUDListener() {
                @Override
                public void onSuccess(@Nullable String ID, @Nullable Result result) {
                    DisplayUtils.showAlert(MainActivity.this, "Added to cloud ", "The ID is " + ID);
                }

                @Override
                public void onFailure(Exception e) {
                    DisplayUtils.showAlert(MainActivity.this, "Failed to cloud ", "The error is " + e.getMessage());

                }
            });

            return (true);
        }
        case R.id.reset:
            //add the function to perform here
            getAndBindData();
            return(true);
        case R.id.about:
            //add the function to perform here
            return(true);
        case R.id.exit:
            //add the function to perform here
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }


}
