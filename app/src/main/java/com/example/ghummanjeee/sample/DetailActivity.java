package com.example.ghummanjeee.sample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {
 ImageView ig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
ig=(ImageView)findViewById(R.id.img);
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent is=new Intent(DetailActivity.this,Login.class);
                startActivity(is);
            }
        });
    }


    private void showCompleteDialog() {
        AlertDialog.Builder alt=new AlertDialog.Builder(this);
        alt.setTitle("Inbox");
       alt.setCancelable(true);
        alt.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alt.create();
alt.show();
    }


}
