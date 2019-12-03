package com.arfanet.nuvo.belajarti;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.arfanet.nuvo.belajarti.menu_materi.MenuMateri;
import com.arfanet.nuvo.belajarti.news.News;

public class MainActivity extends AppCompatActivity {

    ImageButton btn1, btn2, btn3, btn4, btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (ImageButton) findViewById(R.id.btnMateri);
        btn2 = (ImageButton) findViewById(R.id.btnLatihan);
        btn3 = (ImageButton) findViewById(R.id.btnNews);
        btn4 = (ImageButton) findViewById(R.id.btnPetunjuk);
        btn5 = (ImageButton) findViewById(R.id.btnTentang);
        btn6 = (ImageButton) findViewById(R.id.btnKeluar);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MenuMateri.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MainActivity.this, SoalLatihan.class);
                startActivity(x);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(MainActivity.this, News.class);
                startActivity(n);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(MainActivity.this, Petunjuk.class);
                startActivity(b);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(MainActivity.this, Tentang.class);
                startActivity(b);
            }
        });
    }

    public void onBackPressed(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //pesan keluar
        builder.setMessage("Keluar dari Aplikasi ?")
                .setCancelable(false)
                //Button Keluar
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        moveTaskToBack(true);
                        System.exit(0);
                    }
                })
                //Button Batal
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
