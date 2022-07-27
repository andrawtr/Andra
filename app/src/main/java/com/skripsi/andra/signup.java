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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    EditText etnama,etmail,etpass,etphone;

    String buatcekemail;
    CheckBox checkBox;
    boolean bolmail,bolnama,bolpass, bolhp;
    ProgressDialog pd;
    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        ceklogin();
        mAuth = FirebaseAuth.getInstance();
        etnama = findViewById(R.id.etnama);
        etmail = findViewById(R.id.etmail);
        etpass = findViewById(R.id.etpass);
        etphone = findViewById(R.id.etphone);
        checkBox = findViewById(R.id.checkBox);
        buatcekemail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    etpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        ceklogin();
    }
    private void ceklogin(){
        pd = new ProgressDialog(signup.this);
        pd.setMessage("Memeriksa User ...");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        FirebaseUser sudahlogin = mAuth.getCurrentUser();
        if (sudahlogin!=null ){
            String email =  mAuth.getCurrentUser().getEmail();
            if (email.equals("andrawitantra.aw@gmail.com")){
                signup.this.finish();
                startActivity(new Intent(signup.this, Admin.class));
            }else{
                signup.this.finish();
                startActivity(new Intent(signup.this, main.class));
            }
        }else{
            pd.dismiss();
            Log.d("Lognya","Blm Login");
        }
    }
    public void prosesdaftar(View v) {
        ceknama();
        cekemail();
        cekpass();
        cekphone();
        if (bolnama && bolmail && bolpass && bolhp) {
            pd = new ProgressDialog(signup.this);
            pd.setMessage("Mendaftarkan ...");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.show();
            signup();
        } else {
            Log.d("Lognya","masih ada error");
        }
    }
    private void cekemail() {
        String hmail = cekvalid.valemail(etmail.getText().toString());
        Log.d("TAG", "cekemail: "+hmail);
        if(hmail.equals("true")){
            bolmail = true;
        }else{
            etmail.setError(hmail);
        }
    }

    private void cekpass() {
        String hpass = cekvalid.valpass(etpass.getText().toString());
        if (hpass.equals("true")) {
            bolpass = true;
        } else {
            etpass.setError(hpass);
        }
    }

    private void ceknama() {
        String hnama = cekvalid.valnama(etnama.getText().toString());
        if (hnama.length() < 1) {
            etnama.setError(hnama);
        } else {
            bolnama = true;
        }
    }
    private void cekphone() {
        String hp = cekvalid.valphone(etphone.getText().toString());
        if (hp.length() < 1) {
            etphone.setError(hp);
        } else {
            bolhp = true;
        }
    }
    private void signup() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(etmail.getText().toString(), etpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            String uuid = user.getUid();
                            mdatabase = FirebaseDatabase.getInstance().getReference();
                            mdatabase.child("user").child(uuid).child("username").setValue(etnama.getText().toString());
                            mdatabase.child("user").child(uuid).child("nope").setValue(etphone.getText().toString());
                            signup.this.finish();
                            startActivity(new Intent(signup.this, main.class));
                        } else {
                            Log.w("signup", "createUserWithEmail:failure", task.getException());
                            pd.dismiss();
                            new AlertDialog.Builder(signup.this)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setCancelable(false)
                                    .setTitle("Maaf !")
                                    .setMessage("Pendaftaran Gagal, Mohon Periksa Data Yang Anda Berikan")
                                    .setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                                        @Override public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); }})
                                    .show();
                        }
                    }
                });
    }
    public void login(View v) {
        startActivity(new Intent(signup.this, Signin.class));
        signup.this.finish();
    }
    public void lupapass(View v) {
        startActivity(new Intent(signup.this, lupapass.class));
        signup.this.finish();
    }

}