package com.arfanet.nuvo.belajarti.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.arfanet.nuvo.belajarti.R;

public class DetailBerita extends AppCompatActivity {
    ImageView img;
    TextView tvV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img  = (ImageView)findViewById(R.id.imgDetail);
        tvV  =(TextView) findViewById(R.id.textDetail);

        tvV.setText(getIntent().getStringExtra("isi_berita"));
        Glide.with(getApplicationContext())
                .load("https://sinauit.000webhostapp.com/foto_berita/"+
                        getIntent().getStringExtra("gambar"))
                .crossFade()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(img);
        setTitle(getIntent().getStringExtra("judul"));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
