

package com.skripsi.andra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class lupapass extends AppCompatActivity {
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupapass);
        email = findViewById(R.id.etmaillupa);

    }
    public void lupapassword(View v){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.setLanguageCode("id");
        auth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    new AlertDialog.Builder(lupapass.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(false)
                            .setMessage("Silahkan Periksa Email Anda untuk mengubah password")
                            .setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    lupapass.this.finish();
                                }
                            })
                            .show();
                }
                else {
                    new AlertDialog.Builder(lupapass.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(false)
                            .setMessage("Email Yang anda Berika Belum terdaftar")
                            .setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    lupapass.this.finish();
                                }
                            })
                            .show();
                }
            }
        });
    }
}