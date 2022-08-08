package com.skripsi.andra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class main<ImageAdapter> extends AppCompatActivity {
    TextView tv, tvnamae, tvlokasie, tvtamue, tvtanggale, tvjame, tvstatuse,textView24;
    ProgressDialog pd;
    String usernya, uuid;
    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase,databaseReference;
    CardView cv;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        tvnamae = findViewById(R.id.tvnamae);
        tvlokasie = findViewById(R.id.tvlokasie);
        tvtamue = findViewById(R.id.tvtamue);
        tvtanggale = findViewById(R.id.tvtanggale);
        tvjame = findViewById(R.id.tvjame);
        tvstatuse = findViewById(R.id.tvstatuse);
        cv = findViewById(R.id.cv);
        button2 = findViewById(R.id.button2);
        textView24 = findViewById(R.id.textView24);
        pd = new ProgressDialog(main.this);
        pd.setMessage("Memeriksa Username ...");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uuid = user.getUid();
        cekdata();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("user").child(uuid).child("username");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pd.dismiss();
                usernya = dataSnapshot.getValue().toString();
                tv.setText(usernya);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void buatevent(View v) {
        this.finish();
        startActivity(new Intent(main.this, buatevent.class));
    }
    private void cekdata() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(uuid).child("userevent");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("jenis")) {
                    getdata();
                    cv.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    textView24.setText("Event Anda Sedang Kami Kerjakan\nSilahkan Hubungi Admin Untuk Mengubah Event");
                }else{
                    textView24.setText("Anda Belum Mempunyai Event\nKlik Tombol Di Atas Untuk Membuat Event Anda");
                    cv.setVisibility(View.GONE);
                    button2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }


    private void getdata() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(uuid).child("userevent");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pd.dismiss();
                Model post = dataSnapshot.getValue(Model.class);
                tvnamae.setText(post.getJenis());
                tvlokasie.setText(post.getLokasi());
                tvtamue.setText(post.getTamu());
                tvtanggale.setText(post.getTanggal());
                tvjame.setText(post.getJam());
                tvstatuse.setText(post.getStatus());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void adm(View v) {
        this.finish();
        startActivity(new Intent(main.this, Admin.class));
    }

}
