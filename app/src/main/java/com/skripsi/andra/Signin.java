package com.skripsi.andra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Signin extends AppCompatActivity {
    CheckBox checkBox2;
    EditText etpass, etmaill;
    String buatcekemail;
    boolean bolmail, bolpass;
    ProgressDialog pd;
    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;
    private String username;
    private cekvalid cv = new cekvalid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        checkBox2 = findViewById(R.id.checkBox2);
        etpass = findViewById(R.id.etplogin);
        etmaill = findViewById(R.id.etmaill);
        buatcekemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    etpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    public void proseslogin(View v) {
        cekemail();
        cekpass();
        if (bolmail && bolpass) {
            pd = new ProgressDialog(Signin.this);
            pd.setMessage("Login ...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.show();
            prosessignin();
        } else {
            Log.d("Lognya", "masih ada error");
        }
    }

    // cek formatemail pass nama
    private void cekemail() {
        String hmail = cv.valemail(etmaill.getText().toString());
        if(hmail.equals("true")){
            bolmail = true;
        }else{
            etmaill.setError(hmail);
        }
    }


    private void cekpass() {
        String hpass = cv.valpass(etpass.getText().toString());
        if (hpass.equals("true")) {
            bolpass = true;
        } else {
            etpass.setError(hpass);
        }
    }

    private void prosessignin() {
        mAuth.signInWithEmailAndPassword(etmaill.getText().toString(), etpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Signin.this.finish();
                            startActivity(new Intent(Signin.this, main.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            new AlertDialog.Builder(Signin.this)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setCancelable(false)
                                    .setTitle("Maaf !")
                                    .setMessage("Gagal Masuk, Mohon Periksa Data Yang Anda Berikan")
                                    .setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }

                        // ...
                    }
                });
    }
}
