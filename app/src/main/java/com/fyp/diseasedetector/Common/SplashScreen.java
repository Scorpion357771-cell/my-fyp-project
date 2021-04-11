package com.fyp.diseasedetector.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyp.diseasedetector.LoginActivity;
import com.fyp.diseasedetector.LoginSelection;
import com.fyp.diseasedetector.Patient.PatientDashboard;
import com.fyp.diseasedetector.R;

public class SplashScreen extends AppCompatActivity {

    private static int Splash_Timer = 3000;
    //Variables
    ImageView backgroundImage;
    TextView powered;

    //Animation
    Animation sideAnim;
    Animation bottomAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Hooks
        backgroundImage = findViewById(R.id.background_image);
        powered = findViewById(R.id.powered_by_line);


        //Animatioms

        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);


        //set Animation

        backgroundImage.setAnimation(sideAnim);
        powered.setAnimation(bottomAnim);

        new Handler().postDelayed(() ->{
            onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
            boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);


            if(isFirstTime) {

                SharedPreferences.Editor editor = onBoardingScreen.edit();
                editor.putBoolean("firstTime",false);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), Onboarding.class);
                startActivity(intent);
                finish();
            }
            else{
                Intent intent = new Intent(getApplicationContext(), LoginSelection.class);
                startActivity(intent);
                finish();
            }
        },Splash_Timer);

    }
}