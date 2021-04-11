package com.fyp.diseasedetector.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fyp.diseasedetector.HelperClasses.SliderAdapter;
import com.fyp.diseasedetector.LoginActivity;
import com.fyp.diseasedetector.LoginSelection;
import com.fyp.diseasedetector.Patient.PatientDashboard;
import com.fyp.diseasedetector.R;
import com.fyp.diseasedetector.UserPorfile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Onboarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button getStarted;
    Animation animation;
    int currentPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //REMOVING THE STATUS BAR
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);



        //Hooks
        viewPager= findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        getStarted = findViewById(R.id.get_started_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(this, LoginSelection.class));

    }
    public void next(View view){
        viewPager.setCurrentItem(currentPosition + 1);
    }

    public void get_started_btn(View view){
        startActivity(new Intent(this, LoginSelection.class));
    }


    private void addDots(int position) {
    dots = new TextView[4];
    dotsLayout.removeAllViews();
    for(int i = 0 ; i < dots.length ; i++){
        dots[i] = new TextView(this);
        dots[i].setText(Html.fromHtml("&#8226;"));
        dots[i].setTextSize(35);

        dotsLayout.addView(dots[i]);
    }

    if(dots.length>0){
        dots[position].setTextColor(getResources().getColor(R.color.blue));
    }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPosition) {

    };
    @Override
    public void onPageSelected(int position){
    addDots(position);
    currentPosition = position;
    if(position ==0 ){
        getStarted.setVisibility(View.INVISIBLE);
    }
    else if(position ==1){
        getStarted.setVisibility(View.INVISIBLE);
    }
    else if( position ==2){
        getStarted.setVisibility(View.INVISIBLE);
    }
    else {
        animation = AnimationUtils.loadAnimation(Onboarding.this,R.anim.bottom_anim);
        getStarted.setAnimation(animation);
        getStarted.setVisibility(View.VISIBLE);
    }

    };

    public void gotoSel(View view){
        startActivity(new Intent(getApplicationContext(), LoginSelection.class));
    }


    @Override
    public void onPageScrollStateChanged(int state){

        };
    };
}