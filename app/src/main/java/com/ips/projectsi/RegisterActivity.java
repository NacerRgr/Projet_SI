package com.ips.projectsi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText registerEmailEditText;
    private EditText registerNomEditText;
    private EditText registerPrenomEditText;
    private EditText registerMdpEditText;
    private EditText registerMdpConfirmEditText;
    private EditText registerTelephoneEditText;
    private Button registerBtn;
    private ProgressBar registerProgressBar;

    private FirebaseAuth auth; //la classe qui va communiquer avec Firbase
    @Override
    //instancier les views existantes dans l'activité (.xml)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();// pour initialiser les views
        auth=FirebaseAuth.getInstance();//pour instancier Firebaseauth
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerProgressBar.setVisibility(View.VISIBLE);
                String email=registerEmailEditText.getText().toString();
                String password=registerMdpEditText.getText().toString();
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                registerProgressBar.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "Succès", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else
                            {
                                registerProgressBar.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "Failude", Toast.LENGTH_SHORT).show();

                            }
                            }
                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });

    }

    private void initViews() {
        registerEmailEditText=findViewById(R.id.editTextEmail);//Pour récuperer View ou il existe la case email
        registerNomEditText=findViewById(R.id.editTextSignInNom);
        registerPrenomEditText=findViewById(R.id.editTextSignInPrenom);
        registerTelephoneEditText=findViewById(R.id.editTextSignInNumTele);
        registerMdpEditText=findViewById(R.id.editTextSignInMdp);
        registerMdpConfirmEditText=findViewById(R.id.editTextSignInMdpConfirm);
        registerBtn=findViewById(R.id.buttonSingIn);
        registerProgressBar=findViewById(R.id.progressBarRegister);

    }
}