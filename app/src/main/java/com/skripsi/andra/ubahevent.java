package com.skripsi.andra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ubahevent extends AppCompatActivity {
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView tvnamau, tvlokasiu, tvtamuu, tvtglu, tvjamu, tvtlpu, tvstatusu, tvnoevente, tvuuide, tvtglbaru, tvjambaru;
    String jam, menit, namabaru, loksibaru, tamubaru, tanggalbaru,jambaru,nopebaru,statusbaru ;
    EditText etnamabaru, ettempatbaru,ettamubaru,ettlpbaru,etstatusbaru;
    private DatabaseReference mdatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahevent);
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        String nama = getIntent().getStringExtra("nama");
        String lokasi = getIntent().getStringExtra("lokasi");
        String tanggal = getIntent().getStringExtra("tanggal");
        String nope = getIntent().getStringExtra("nope");
        String tamu = getIntent().getStringExtra("tamu");
        String jam = getIntent().getStringExtra("jam");
        String status = getIntent().getStringExtra("status");
        String noevent = getIntent().getStringExtra("noevent");
        String uuid = getIntent().getStringExtra("uuid");
        tvlokasiu = findViewById(R.id.tvlokasiu);
        tvtamuu = findViewById(R.id.tvtamuu);
        tvtglu = findViewById(R.id.tvtanggalu);
        tvjamu = findViewById(R.id.tvjamu);
        tvtlpu = findViewById(R.id.tvtlpu);
        tvstatusu = findViewById(R.id.tvstatusu);
        tvnoevente = findViewById(R.id.tvnoevente);
        tvuuide = findViewById(R.id.tvuuide);
        tvtglbaru = findViewById(R.id.tvtglbaru);
        tvjambaru = findViewById(R.id.tvjambaru);
        tvnamau = findViewById(R.id.tvnamau);
        tvnamau.setText(nama);
        tvlokasiu.setText(lokasi);
        tvtamuu.setText(tamu);
        tvtglu.setText(tanggal);
        tvjamu.setText(jam);
        tvtlpu.setText(nope);
        tvstatusu.setText(status);
        tvnoevente.setText(noevent);
        tvuuide.setText(uuid);
        etnamabaru = findViewById(R.id.etnamabaru);
        ettempatbaru = findViewById(R.id.ettempatbaru);
        ettamubaru = findViewById(R.id.ettamubaru);
        ettlpbaru =findViewById(R.id.ettlpbaru);
        etstatusbaru = findViewById(R.id.etstatusbaru);

    }

    public void dp(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        tvtglbaru.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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
                        tvjambaru.setText(jam + ":" + menit);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }
    public void getstatus(){
        if(etstatusbaru.getText().toString().length()>1){
            statusbaru = etstatusbaru.getText().toString();
        }else{
            statusbaru = tvstatusu.getText().toString();
        }
    }
    public void update(View v){
        getstatus();
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase.child("user").child(tvuuide.getText().toString()).child("userevent").child(tvnoevente.getText().toString()).child("status").setValue(statusbaru);
        mdatabase.child("Event").child(tvnoevente.getText().toString()).child("status").setValue(statusbaru);
        ubahevent.this.finish();
    }



}