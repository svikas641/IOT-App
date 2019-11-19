package com.example.hibreed2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private EditText reg_username;
    private EditText reg_email;
    private EditText reg_password;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        reg_username = findViewById(R.id.editTextUsernameLogin);
        reg_email = findViewById(R.id.editTextEmail);
        reg_password = findViewById(R.id.editTextPasswordLogin);
        Button buttonSignup = findViewById(R.id.buttonSignup);
        TextView textViewLogin = findViewById(R.id.textViewLogin);

        mAuth = FirebaseAuth.getInstance();


        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reg_email.getText().toString();
                String password = reg_password.getText().toString();
                createAccount(email,password);

            }
        });
    }


    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(signup.this, "User created successfully.",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),login.class);
                            startActivity(i);

                        }
                        else
                            Toast.makeText(signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();



                    }
                });
    }
    private boolean validateForm(){
        boolean valid = true;

        String email = reg_email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            reg_email.setError("Required.");
            valid = false;
        } else {
            reg_email.setError(null);
        }

        String password = reg_password.getText().toString();
        if (TextUtils.isEmpty(password)) {
            reg_password.setError("Required.");
            valid = false;
        } else {
            reg_password.setError(null);
        }

        return valid;
    }
}
