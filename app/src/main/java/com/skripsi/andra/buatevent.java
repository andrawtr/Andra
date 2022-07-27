package com.skripsi.andra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class buatevent extends AppCompatActivity {
    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView tvtanggal, tvjam, tvnope;
    String jam, menit, uuid;
    EditText jenis, lokasi, tamu;
    Boolean bjenis, blokasi, btamu, btanggal, bjam;
    int no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buatevent);
        mAuth = FirebaseAuth.getInstance();
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        tvtanggal = findViewById(R.id.tglevent);
        tvjam = findViewById(R.id.jamevent);
        jenis = findViewById(R.id.jenisevent);
        lokasi = findViewById(R.id.lokasievent);
        tamu = findViewById(R.id.jumlahtamu);
        tvnope = findViewById(R.id.tvnopeb);
        bjenis = false;
        blokasi = false;
        btamu = false;
        btanggal = false;
        bjam = false;
        FirebaseUser user = mAuth.getCurrentUser();
        uuid  = user.getUid();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("no");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                no = Integer.parseInt(dataSnapshot.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void buatevent(View v) {
        cekjenis();
        ceklokasi();
        cektamu();
        cektanggal();
        cekjam();
        if(bjenis && blokasi && btamu && btanggal && bjam){
            prosesevent();
        }

    }

    public void dp(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        tvtanggal.setText(dayOfMonth + "-" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void tp(View v) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (hourOfDay == 0) {
                            jam = "00";
                        } else {
                            jam = String.valueOf(hourOfDay);
                        }
                        if (minute == 0) {
                            menit = "00";
                        } else {
                            menit = String.valueOf(minute);
                        }
                        tvjam.setText(jam + ":" + menit);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    public void cekjenis(){
        if (jenis.getText().toString().length() < 1) {
            jenis.setError("Jenis Event Harus Diisi");
        } else {
            bjenis = true;
        }
    }
    public void ceklokasi(){
        if (lokasi.getText().toString().length() < 1) {
            lokasi.setError("Lokasi Event Harus Diisi");
        } else {
            blokasi = true;
        }
    }

    public void cektamu(){
        if (tamu.getText().toString().length() < 1) {
            tamu.setError("Tamu Event Harus Diisi");
        } else {
            btamu = true;
        }
    }

    public void cektanggal(){
        if (tvtanggal.getText().toString().length() < 1) {
            tvtanggal.setError("Tanggal Event Harus Diisi");
        } else {
            btanggal = true;
        }
    }
    public void cekjam(){
        if (tvjam.getText().toString().length() < 1) {
            tvjam.setError("Jam Event Harus Diisi");
        } else {
            bjam = true;
        }
    }

    public void prosesevent(){
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase.child("no").setValue(String.valueOf(no+1));
        mdatabase.child("Event").child(String.valueOf(no+1)).child("uuid").setValue(uuid);
        mdatabase.child("Event").child(String.valueOf(no+1)).child("nope").setValue(tvnope.getText().toString());
        mdatabase.child("Event").child(String.valueOf(no+1)).child("noevent").setValue(String.valueOf(no+1));
        mdatabase.child("Event").child(String.valueOf(no+1)).child("jenis").setValue(jenis.getText().toString());
        mdatabase.child("Event").child(String.valueOf(no+1)).child("lokasi").setValue(lokasi.getText().toString());
        mdatabase.child("Event").child(String.valueOf(no+1)).child("tamu").setValue(tamu.getText().toString());
        mdatabase.child("Event").child(String.valueOf(no+1)).child("tanggal").setValue(tvtanggal.getText().toString());
        mdatabase.child("Event").child(String.valueOf(no+1)).child("jam").setValue(tvjam.getText().toString());
        mdatabase.child("Event").child(String.valueOf(no+1)).child("status").setValue("Event sedang diproses");
        mdatabase.child("user").child(uuid).child("userevent").child(String.valueOf(no+1)).child("jenis").setValue(jenis.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child(String.valueOf(no+1)).child("lokasi").setValue(lokasi.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child(String.valueOf(no+1)).child("tamu").setValue(tamu.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child(String.valueOf(no+1)).child("tanggal").setValue(tvtanggal.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child(String.valueOf(no+1)).child("jam").setValue(tvjam.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child(String.valueOf(no+1)).child("status").setValue("Event sedang diproses");
        this.finish();
        startActivity(new Intent(buatevent.this, main.class));
    }

}