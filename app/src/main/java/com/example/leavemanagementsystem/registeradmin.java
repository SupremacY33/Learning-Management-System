package com.example.leavemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class registeradmin extends AppCompatActivity {

    TextInputEditText admin_email, admin_password, admin_conform_password;
    MaterialButton button1;
    DatabaseReference db;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    ProgressDialog dialog;
    private Object Users;
    ImageView imageView;
    Uri imageUri;
    StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeradmin);


        initilization();

    }

    private void initilization(){
            admin_email.findViewById(R.id.adminemail);
            admin_password.findViewById(R.id.adminpassword);
            admin_conform_password.findViewById(R.id.admin_conform_password);

        db = FirebaseDatabase.getInstance().getReference().child("admin");
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("User Account Created..!!");
        dialog.setMessage("Please Wait Creating Your Account");
        dialog.setCancelable(false);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri != null){
                    uploadToFirebase(imageUri,v);
                }
                else{
                    Toast.makeText(registeradmin.this,"Wrong Input!!!!!",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
    }





    private void uploadToFirebase(Uri uri, View view){

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        validations(view,uri);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registeradmin.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }


    private void validations(View view, Uri uri){
        dialog.show();

        DocumentReference db1 = firebaseFirestore.collection("admin").document();

        String semail = admin_email.getText().toString();
        String spassword = admin_password.getText().toString();
        String sconform_password = admin_conform_password.getText().toString();

        if(semail.equals("")){
            Snackbar.make(view, "Please Enter Your Email",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(admin_email);
            dialog.dismiss();
        }
        else if(spassword.equals("")){
            Snackbar.make(view, "Please Enter Your Password",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(admin_password);
            dialog.dismiss();
        }
        else if(sconform_password.equals("")){
            Snackbar.make(view, "Please Enter Your Conform Password",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(admin_conform_password);
            dialog.dismiss();
        }
        else{
            mAuth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Admin admin = new Admin(semail,spassword,sconform_password);
                        db.push().setValue(admin);
                        db1.set(admin);
                        Snackbar.make(view,"User Account Has Been Created",Snackbar.LENGTH_SHORT).show();
                        cleartext();
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),adminlogin.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(registeradmin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void cleartext(){
        admin_email.setText("");
        admin_password.setText("");
        admin_conform_password.setText("");
    }
}