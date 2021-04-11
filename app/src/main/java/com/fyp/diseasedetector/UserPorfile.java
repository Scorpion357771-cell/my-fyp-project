package com.fyp.diseasedetector;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fyp.diseasedetector.Patient.PatientDashboard;
import com.fyp.diseasedetector.Patient.PatientProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;

public class UserPorfile extends AppCompatActivity {

    TextView fusername, femail, fcnic, fage, fphone;
    FirebaseAuth fAuth;
    ImageView profileImg;
    FirebaseFirestore fStore;
    FirebaseUser user;
    String userID;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_porfile);
        fusername = findViewById(R.id.uname);
        femail = findViewById(R.id.uemail);
        fcnic = findViewById(R.id.ucnic);
        fage = findViewById(R.id.uage);
        fphone = findViewById(R.id.uphone);
        profileImg = findViewById(R.id.profilepic);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        user = fAuth.getCurrentUser();
        userID = fAuth.getCurrentUser().getUid();

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImg);
            }
        });


        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                fusername.setText(documentSnapshot.getString("fName"));
                fusername.setText(documentSnapshot.getString("fName"));
                femail.setText(documentSnapshot.getString("fEmail"));
                fcnic.setText(documentSnapshot.getString("fCnic"));
                fage.setText(documentSnapshot.getString("fAge"));
                fphone.setText(documentSnapshot.getString("fPhone"));

                Log.d(TAG,"onCreate: " + fusername+" "+femail+" "+fage+" "+fcnic+" "+fphone+" ");
            }
        });


        profileImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profileImg.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);
            }
        }

    }

    private void uploadImageToFirebase(Uri imageUri){
        //uploading image to database
        StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImg);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failded to upload image",Toast.LENGTH_SHORT).show();

            }
        });


    }










    public void callHomeScreen(View view){
        startActivity(new Intent(getApplicationContext(), PatientDashboard.class));
        finish();
    }
    public void gotoEdit(View view){
        startActivity(new Intent(getApplicationContext(), PatientProfile.class));
        finish();
    }

//    public void action(View view){
//        Toast.makeText(getApplicationContext(), "Profile image pressed", Toast.LENGTH_SHORT).show();
//    }

}