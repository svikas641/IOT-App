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

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText reg_email;
    private EditText reg_password;
    private static final String TAG = "EmailPassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logincheck();
        setContentView(R.layout.login);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        TextView login = findViewById(R.id.textViewLogin);
         reg_email = findViewById(R.id.editTextUsernameLogin);
         reg_password = findViewById(R.id.editTextPasswordLogin);
        Button loginButton = findViewById(R.id.buttonLogin);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reg_email.getText().toString();
                String password = reg_password.getText().toString();
                signIn(email,password);
            }


        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),signup.class);
                startActivity(i);
            }
        });

    }


    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }
        else
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(login.this, "User LoggedIn successfully.",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),operationActivity.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(login.this, "Username or Password is incorrect.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            if (!task.isSuccessful()) {

                            }
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

    private void logincheck(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            Intent i = new Intent(getApplicationContext(),operationActivity.class);
            startActivity(i);
        }

    }


}
