package com.fyp.diseasedetector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fyp.diseasedetector.Doctor.DoctorLogin;
import com.fyp.diseasedetector.Patient.PatientDashboard;

public class doctorprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorprofile);
    }

    public void callHomeScreen(View view){
        startActivity(new Intent(getApplicationContext(), PatientDashboard.class));
        finish();
    }
}