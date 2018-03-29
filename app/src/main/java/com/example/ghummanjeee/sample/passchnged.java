package com.example.ghummanjeee.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class passchnged extends AppCompatActivity {
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
 EditText pass;
    Button btnchnged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passchnged);
    pass=(EditText)findViewById(R.id.passchnge);
        btnchnged=(Button)findViewById(R.id.btn_login);
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        btnchnged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.updatePassword(pass.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(passchnged.this, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
                                            signOut();
                                        } else {
                                            Toast.makeText(passchnged.this, "Failed to update password!", Toast.LENGTH_SHORT).show();

                                        }
                                    }

                                    private void signOut() {
                                        FirebaseAuth.getInstance().signOut();
                                        Intent intent = new Intent(passchnged.this, Login.class);
                                        startActivity(intent);
                                        finish();
                                        return;
                                    }
                                });
                    }



        });
    }
}
