package com.fyp.diseasedetector;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.fyp.diseasedetector.Common.LoginSignup.LoginAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class LoginActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton help, rate, report;
    float v=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        rate = findViewById(R.id.fab_rate);
        help = findViewById(R.id.fab_help);
        report = findViewById(R.id.fab_report);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        rate.setTranslationY(300);
        help.setTranslationY(300);
        report.setTranslationY(300);

        rate.setAlpha(v);
        help.setAlpha(v);
        report.setAlpha(v);
        tabLayout.setAlpha(v);

        report.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        rate.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        help.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();




    }
}
