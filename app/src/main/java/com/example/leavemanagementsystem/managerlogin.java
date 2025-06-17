package com.example.leavemanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class managerlogin extends AppCompatActivity {
    EditText email, password;
    Button loginbtn;
    FirebaseAuth mAuth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerlogin);


        initilizations();

    }

    private void initilizations(){
        email = findViewById(R.id.emailmanager);
        password = findViewById(R.id.passwordmanager);
        loginbtn = findViewById(R.id.loginmanager);

        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please Wait..!!");
        dialog.setMessage("Checking Database For Your Account Info..!!!!");
        dialog.setCancelable(false);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                validationsy(v);
            }
        });

    }

    private void validationsy(View view){

        String ssemail = email.getText().toString();
        String sspassword = password.getText().toString();

        if(ssemail.equals("")){
            Snackbar.make(view, "Please Enter Your Email",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(email);
            dialog.dismiss();
        }
        else if(sspassword.equals("")){
            Snackbar.make(view, "Please Enter Your Password",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(password);
            dialog.dismiss();
        }
        else{
            mAuth.signInWithEmailAndPassword(ssemail,sspassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        startActivity(new Intent(getApplicationContext(), managerhomepage.class));

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}