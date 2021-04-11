package com.fyp.diseasedetector.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fyp.diseasedetector.R;

public class DoctorLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
    }

    public void callSelectionScreen(View view){
        startActivity(new Intent(getApplicationContext(), com.fyp.diseasedetector.LoginSelection.class));
        finish();
    }
    public void gotoDocSignup(View view){
        startActivity(new Intent(getApplicationContext(), DoctorSignup.class));
    }
}