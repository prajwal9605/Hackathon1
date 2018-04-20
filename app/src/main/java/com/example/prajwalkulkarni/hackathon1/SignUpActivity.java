package com.example.prajwalkulkarni.hackathon1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText email,password;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);

        /*if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }*/

        Button b1 = (Button)findViewById(R.id.loginSignUp);
        Button b2 = (Button)findViewById(R.id.signupSignUp);
        email=(EditText)findViewById(R.id.userEmailSignUp);
        password=(EditText)findViewById(R.id.userPasswordSignUp);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserEmail=email.getText().toString();
                String UserPassword=password.getText().toString();
                if(TextUtils.isEmpty(UserEmail)){
                    Toast.makeText(SignUpActivity.this, "Enter Email address!!", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(UserPassword)){
                    Toast.makeText(SignUpActivity.this, "Enter Password!!", Toast.LENGTH_SHORT).show();
                }
                progressDialog.setMessage("Logging In");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(UserEmail,UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Incorrect Email or Password!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
