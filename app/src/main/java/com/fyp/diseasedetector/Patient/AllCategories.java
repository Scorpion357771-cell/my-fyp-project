package com.fyp.diseasedetector.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.fyp.diseasedetector.R;

import static android.view.WindowManager.*;

public class AllCategories extends AppCompatActivity {
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_all_categories);

        //Hooks
        backBtn = findViewById(R.id.back_pressed);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllCategories.super.onBackPressed();
            }
        });


    }
}