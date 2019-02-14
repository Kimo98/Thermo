package com.example.karimmahmoud.thermo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;

public class MainActivity extends AppCompatActivity{
EditText Email, Password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=(Button)findViewById(R.id.loginbtn);
        Button b2=(Button)findViewById(R.id.register);
        Email=(EditText)findViewById(R.id.Email);
        Password=(EditText) findViewById(R.id.Password);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin();



            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
    public void UserLogin(){
        String Email1=Email.getText().toString().trim();
        String Password1=Password.getText().toString().trim();
        if(Email1.isEmpty()){
            Email.setError("The Full Name is required");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email1).matches()) //it's a valid e-mail address
        {
            Email.setError("please enter a valid e-mail address");
            Email.requestFocus();
            return;
        }


        if(Password1.isEmpty()) {
            Password.setError("Password is Required");
            Password.requestFocus();
            return;
        }

        if(Password.length()<6){
            Password.setError("Please Enter a bigger password");
            return;
        }
        mAuth.signInWithEmailAndPassword(Email1, Password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "User is now logged in",
                            Toast.LENGTH_SHORT).show();
                    Intent n=new Intent(MainActivity.this, ListActivity.class);
                    startActivity(n);
                    finish();
                }
                else{

                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

}

