package com.skripsi.andra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ubahevent extends AppCompatActivity {
    ProgressDialog pd;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView tvnamau, tvlokasiu, tvtamuu, tvtglu, tvjamu, tvtlpu, tvstatusu, tvuuide, tvtglbaru, tvjambaru;
    String jam, menit,statusbaru,jenisnya,uuid;
    EditText etnamabaru, ettempatbaru,ettamubaru,ettlpbaru;
    private DatabaseReference mdatabase;
    Spinner jenis,etstatusbaru;
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
        String uuid = getIntent().getStringExtra("uuid");
        tvlokasiu = findViewById(R.id.tvlokasiu);
        tvtamuu = findViewById(R.id.tvtamuu);
        tvtglu = findViewById(R.id.tvtanggalu);
        tvjamu = findViewById(R.id.tvjamu);
        tvtlpu = findViewById(R.id.tvtlpu);
        tvstatusu = findViewById(R.id.tvstatusu);
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
        tvuuide.setText(uuid);
        jenis = findViewById(R.id.etnamabaru);
        ettempatbaru = findViewById(R.id.ettempatbaru);
        ettamubaru = findViewById(R.id.ettamubaru);
        ettlpbaru =findViewById(R.id.ettlpbaru);
        etstatusbaru = findViewById(R.id.etstatusbaru);
        ettempatbaru.setText(lokasi);
        ettamubaru.setText(tamu);
        tvtglbaru.setText(tanggal);
        tvjambaru.setText(jam);
        ettlpbaru.setText(nope);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Jenis_Event, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenis.setAdapter(adapter);
        jenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                jenisnya = jenis.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.status_Event, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etstatusbaru.setAdapter(adapter2);
        etstatusbaru.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                statusbaru = etstatusbaru.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
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

    public void update(View v){
        pd = new ProgressDialog(ubahevent.this);
        pd.setMessage("Menyimpan Perubahan ...");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference();
        uuid = tvuuide.getText().toString();
        mdatabase.child("Event").child(uuid).setValue(uuid);
        mdatabase.child("Event").child(uuid).child("uuid").setValue(uuid);
        mdatabase.child("Event").child(uuid).child("nope").setValue(ettlpbaru.getText().toString());
        mdatabase.child("Event").child(uuid).child("jenis").setValue(jenisnya);
        mdatabase.child("Event").child(uuid).child("lokasi").setValue(ettempatbaru.getText().toString());
        mdatabase.child("Event").child(uuid).child("tamu").setValue(ettamubaru.getText().toString());
        mdatabase.child("Event").child(uuid).child("tanggal").setValue(tvtglbaru.getText().toString());
        mdatabase.child("Event").child(uuid).child("jam").setValue(tvjambaru.getText().toString());
        mdatabase.child("Event").child(uuid).child("status").setValue(statusbaru);
        mdatabase.child("user").child(uuid).child("userevent").child("jenis").setValue(jenisnya);
        mdatabase.child("user").child(uuid).child("userevent").child("lokasi").setValue(ettempatbaru.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child("tamu").setValue(ettamubaru.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child("tanggal").setValue(tvtglbaru.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child("jam").setValue(tvjambaru.getText().toString());
        mdatabase.child("user").child(uuid).child("userevent").child("status").setValue(statusbaru)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        pd.dismiss();
                        ubahevent.this.finish();
                        startActivity(new Intent(ubahevent.this, Admin.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                    }
                });

    }

}