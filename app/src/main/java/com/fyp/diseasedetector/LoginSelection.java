package com.fyp.diseasedetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fyp.diseasedetector.Doctor.DoctorLogin;
import com.fyp.diseasedetector.Patient.LoginTabFragment;

public class LoginSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_selection);
    }

    public void gotoPatLogin(View view){
        startActivity(new Intent(getApplicationContext(), com.fyp.diseasedetector.LoginActivity.class));
    }
    public void gotoDocLogin(View view){
        startActivity(new Intent(getApplicationContext(), DoctorLogin.class));
    }


}