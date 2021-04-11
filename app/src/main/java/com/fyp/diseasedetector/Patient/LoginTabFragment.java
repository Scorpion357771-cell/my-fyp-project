package com.fyp.diseasedetector.Patient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fyp.diseasedetector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {

    TextView email, password, forgetPass;
    Button loginbtn;
    float v =0;
    FirebaseAuth fAuth;
    private Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.pass);
        forgetPass = root.findViewById(R.id.forget_pass);
        fAuth = FirebaseAuth.getInstance();
        loginbtn = root.findViewById(R.id.loginBtn);
        btn = root.findViewById(R.id.loginBtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fEmail = email.getText().toString().trim();
                String fPassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(fEmail)){
                    email.setError("EMAIL IS REQUIRED");
                    return;
                }
                if(TextUtils.isEmpty(fPassword)){
                    password.setError("This field is required");
                    return;
                }
                fAuth.signInWithEmailAndPassword(fEmail,fPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(getContext(),"Logged In",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(),PatientDashboard.class));
                        }
                        else{
                            Toast.makeText(getContext(), "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText resetPass = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password? ");
                passwordResetDialog.setMessage("Enter Your Email to get reset Link. ");
                passwordResetDialog.setView(resetPass);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetPass.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(), "Link sent to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Failed to send link "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });



        return root;
    }




}
