package com.fyp.diseasedetector.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fyp.diseasedetector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class SignupTabFragment extends Fragment {

    TextView email, password, username, age, cnic, phone;
    Button signup;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    float v =0;
    private Button btn;
    String userID;


  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.pass);
        username = root.findViewById(R.id.username);
        age = root.findViewById(R.id.age);
        cnic = root.findViewById(R.id.cnic);
        phone = root.findViewById(R.id.phoneno);
        signup = root.findViewById(R.id.signupBtn);

        btn = root.findViewById(R.id.signupBtn);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getActivity(),PatientDashboard.class));

        }

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String fEmail = email.getText().toString().trim();
                String fPassword = password.getText().toString().trim();
                String fUsername = username.getText().toString().trim();
                String fAge = age.getText().toString().trim();
                String fCnic = cnic.getText().toString().trim();
                String fPhone = phone.getText().toString().trim();

                if(TextUtils.isEmpty(fEmail)){
                    email.setError("EMAIL IS REQUIRED");
                    return;
                }if(TextUtils.isEmpty(fPassword)){
                    password.setError("PASSWORD IS REQUIRED");
                    return;
                }if(password.length() < 6){
                    password.setError("Password must be greater then 6 Character");
                }
                if(TextUtils.isEmpty(fUsername)){
                    username.setError("USERNAME IS REQUIRED");
                    return;
                }if(TextUtils.isEmpty(fAge)){
                    age.setError("AGE IS REQUIRED");
                    return;
                }if(TextUtils.isEmpty(fCnic)){
                    cnic.setError("CNIC IS REQUIRED");
                    return;
                }if(TextUtils.isEmpty(fPhone)){
                    phone.setError("PLEASE TYPE PASSWORD AGAIN");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(fEmail,fPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"Registered",Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fUsername);
                            user.put("fEmail",fEmail);
                            user.put("fAge",fAge);
                            user.put("fCnic",fCnic);
                            user.put("fPhone",fPhone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: user Profile is created for " + userID);
                                }
                            });
                            startActivity(new Intent(getActivity(),PatientDashboard.class));
                        }
                        else{
                            Toast.makeText(getContext(), "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginActivity();
//            }
//        });

        return root;
    }
//    public void loginActivity() {
//        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        startActivity(intent);
//
//    }




}
