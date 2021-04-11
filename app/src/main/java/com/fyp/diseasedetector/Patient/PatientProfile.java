package com.fyp.diseasedetector.Patient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import static android.content.ContentValues.TAG;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.diseasedetector.R;
import com.fyp.diseasedetector.UserPorfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class   PatientProfile extends AppCompatActivity {
    TextView fusername, femail, fcnic, fage, fdesc, fphone;
    Button fupdate;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    FirebaseUser user;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        fusername = findViewById(R.id.p_name);

        fupdate = findViewById(R.id.updatebtn);
        femail = findViewById(R.id.p_email);
        fcnic = findViewById(R.id.p_cnic);
        fage = findViewById(R.id.p_age);
        fphone = findViewById(R.id.p_phone);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                fusername.setText(documentSnapshot.getString("fName"));
                femail.setText(documentSnapshot.getString("fEmail"));
                fcnic.setText(documentSnapshot.getString("fCnic"));
                fage.setText(documentSnapshot.getString("fAge"));
                fphone.setText(documentSnapshot.getString("fPhone"));

                Log.d(TAG,"onCreate: "+" "+femail+" "+fage+" "+fcnic+" "+fphone+" ");
            }
        });

        fupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fusername.getText().toString().isEmpty() || femail.getText().toString().isEmpty() || fage.getText().toString().isEmpty() || fcnic.getText().toString().isEmpty() || fphone.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Some Fields are Empty.", Toast.LENGTH_SHORT).show();

            }
                final String email = femail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference documentReference1 = fStore.collection("users").document(user.getUid());
                        Map<String,Object> edited = new HashMap<>();
                        edited.put("fEmail",femail);
                        edited.put("fAge",fage);
                        edited.put("fCnic",fcnic);
                        edited.put("fPhone",fphone);
                        documentReference1.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Profile updated", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage() , Toast.LENGTH_SHORT).show();

                    }
                });
            }


        });


    }

    public void callHomeScreen(View view){
        startActivity(new Intent(getApplicationContext(), UserPorfile.class));
        finish();
    }

}