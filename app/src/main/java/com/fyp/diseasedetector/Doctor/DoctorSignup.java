package com.fyp.diseasedetector.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fyp.diseasedetector.R;

public class DoctorSignup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        Spinner category = (Spinner) findViewById(R.id.cate_spinner);

        ArrayAdapter<String> arr = new ArrayAdapter<String>(DoctorSignup.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.categories));
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(arr);
    }


    public void callDocLogin(View view){
        startActivity(new Intent(getApplicationContext(), DoctorLogin.class));
        finish();
    }

}