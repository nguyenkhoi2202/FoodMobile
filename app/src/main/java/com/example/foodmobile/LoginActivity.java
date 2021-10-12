package com.example.foodmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodmobile.config.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, pass;
    Button Register, Signin;
    FirebaseAuth FAuth;
    String em;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            email = (EditText) findViewById(R.id.login_email_et);
            pass = (EditText) findViewById(R.id.login_password_et);
            Signin = (Button) findViewById(R.id.login_btn);
            Register = (Button) findViewById(R.id.need_new_acc_btn);

            FAuth = FirebaseAuth.getInstance();
            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    em = email.getText().toString().trim();
                    pwd = pass.getText().toString().trim();
                    if (isValid()) {

                        final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Logging in...");
                        mDialog.show();
                        FAuth.signInWithEmailAndPassword(em, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    mDialog.dismiss();
                                    if (FAuth.getCurrentUser().isEmailVerified()) {
                                        mDialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                                        Intent z = new Intent(LoginActivity.this, FoodMenuActivity.class);
                                        startActivity(z);
                                        finish();


                                    } else {
                                        ReusableCodeForAll.ShowAlert(LoginActivity.this, "", "Please Verify your Email");
                                    }

                                } else {

                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(LoginActivity.this, "Error", task.getException().getMessage());
                                }
                            }
                        });

                    }
                }
            });

            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent Register = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(Register);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                   //finish();

                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        email.setError("");
        pass.setError("");

        boolean isvalidemail = false, isvalidpassword = false, isvalid = false;
        if (TextUtils.isEmpty(em)) {
            email.setError("Email is required");
        } else {
            if (em.matches(emailpattern)) {
                isvalidemail = true;
            } else {
                email.setError("Enter a valid Email Address");
            }

        }
        if (TextUtils.isEmpty(pwd)) {
            pass.setError("Password is required");
        } else {
            isvalidpassword = true;
        }
        isvalid = (isvalidemail && isvalidpassword) ? true : false;
        return isvalid;
    }
}