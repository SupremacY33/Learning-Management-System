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

public class adminlogin extends AppCompatActivity {
    EditText email, password;
    Button loginbtn;
    Button button;
    FirebaseAuth mAuth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);


        initilization();

    }

    private void initilization(){
        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        loginbtn = findViewById(R.id.login123);
        button = findViewById(R.id.registeradmin);

        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Please Wait..!!");
        dialog.setMessage("Checking Database For Your Account Info..!!!!");
        dialog.setCancelable(false);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminlogin.this, registeradmin.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                validations(v);
            }
        });

    }

    private void validations(View view){

        String semail = email.getText().toString();
        String spassword = password.getText().toString();

        if(semail.equals("")){
            Snackbar.make(view, "Please Enter Your Email",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(email);
            dialog.dismiss();
        }
        else if(spassword.equals("")){
            Snackbar.make(view, "Please Enter Your Password",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(password);
            dialog.dismiss();
        }
        else{
            mAuth.signInWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        startActivity(new Intent(getApplicationContext(), adminhomepage.class));

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