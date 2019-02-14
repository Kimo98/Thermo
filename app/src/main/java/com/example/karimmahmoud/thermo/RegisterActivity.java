package com.example.karimmahmoud.thermo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {
    EditText Email1, UserName1, Password1, ConfirmPassword1;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button logn=(Button) findViewById(R.id.loginBut);
        logn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n=new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(n);
            }
        });
        Button register=(Button)findViewById(R.id.registration);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterUser();
            }
        });
//This is reference to the EditTexts in XML to be used in java.
        Email1=(EditText)findViewById(R.id.Email);
         UserName1=(EditText)findViewById(R.id.UserName);
        Password1=(EditText)findViewById(R.id.Password);
         ConfirmPassword1=(EditText)findViewById(R.id.ConfirmPassword);
        mAuth = FirebaseAuth.getInstance();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);


    }



    public void RegisterUser(){ //validation for user information before registration
        String Email= Email1.getText().toString().trim();
        String UserName=UserName1.getText().toString().trim();
        String Password=Password1.getText().toString().trim();
        String ConfirmPassword=ConfirmPassword1.getText().toString().trim();
        if(Email.isEmpty()){
            Email1.setError("The Full Name is required");
            Email1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) //it's a valid e-mail address
        {
            Email1.setError("please enter a valid e-mail address");
            Email1.requestFocus();
            return;
        }

        if(UserName.isEmpty()){
            UserName1.setError("UserName is required");
            UserName1.requestFocus();
            return;
        }

        if(Password.isEmpty()) {
            Password1.setError("Password is Required");
            Password1.requestFocus();
            return;
        }
        if(ConfirmPassword.isEmpty()){
            ConfirmPassword1.setError("Please confirm your password");
            ConfirmPassword1.requestFocus();
            return;
        }
        if(Password.length()<6){
            Password1.setError("Please Enter a bigger password");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterActivity.this, "User registered successfully",
                                    Toast.LENGTH_SHORT).show();

                        }
                            else{
                             if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                 Toast.makeText(getApplicationContext(), "User is already registered",Toast.LENGTH_SHORT).show();
                             else{
                                 Toast.makeText(getApplicationContext(),"An Error has occured",Toast.LENGTH_SHORT).show();
                             }
                            }


                        // ...
                    }
                });

    }
}
