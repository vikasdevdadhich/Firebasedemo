package com.example.vikas.splesho;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class first extends AppCompatActivity {
    EditText username;


    EditText password;
    Button button;
    Firebase firebase;
    DatabaseReference ref;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(first.this,new1.class);
            startActivity(intent);
            finish();
        }
        button = findViewById(R.id.button);
        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        progressDialog=new ProgressDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                if (valid(user)) {
                    progressDialog.setTitle("Registraion");
                    progressDialog.setMessage("Connecting..");
                    progressDialog.show();

                    create();
                }
                else
                {
                    Toast.makeText(first.this, "Please! enter a valid email address", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void create() {

        firebaseAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(first.this, new1.class);
                    startActivity(intent);
                    finish();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(first.this, "Something! went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public boolean valid(CharSequence email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        boolean bwn = matcher.matches();
        return bwn;
    }
}


