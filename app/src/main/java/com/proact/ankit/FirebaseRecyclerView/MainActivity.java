package com.proact.ankit.FirebaseRecyclerView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView emailEditText;
    TextView passwordEditText;
    Button signUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUp = findViewById(R.id.button);
        auth= FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){

            finish();
            Intent intent = new Intent(MainActivity.this,Profile_Acttivity.class);
            startActivity(intent);



        }


    }

    public void signUp(View view){
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    Log.e("ankit", "onComplete: Failed=" + task.getException().getMessage());
                }else{


                    Intent intent = new Intent(MainActivity.this,Profile_Acttivity.class);
                    startActivity(intent);
                    finish();

                }
//                if(task.isSuccessful()){
//                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
////
//                }else{
//                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//                }

            }
        });


    }
}
