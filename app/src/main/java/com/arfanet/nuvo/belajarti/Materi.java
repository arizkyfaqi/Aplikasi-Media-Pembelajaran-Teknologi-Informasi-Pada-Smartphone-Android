package com.arfanet.nuvo.belajarti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arfanet.nuvo.belajarti.materi.BekerjaDenganMsExcel;
import com.arfanet.nuvo.belajarti.materi.FungsiIkonMsExcel;
import com.arfanet.nuvo.belajarti.materi.MenggunakanIkonPadaMsWord;
import com.arfanet.nuvo.belajarti.materi.MenuIconMsword;
import com.arfanet.nuvo.belajarti.materi.PenggunaanMenuIkonMsExcel;
import com.arfanet.nuvo.belajarti.materi.SOWinXP;
import com.arfanet.nuvo.belajarti.materi.Software;
import com.arfanet.nuvo.belajarti.materi.Hardware;
import com.arfanet.nuvo.belajarti.materi.PDTIK;
import com.arfanet.nuvo.belajarti.materi.SejarahTIK;
import com.arfanet.nuvo.belajarti.materi.TIK;

public class Materi extends AppCompatActivity {

    ListView list1;
    String[] menu = new String[] {  "01. Teknologi Informasi dan Komunikasi","02. Sejarah Perkembangan TIK", "03. Peran dan Dampak TIK", "04. Sistem Operasi Windows XP", "05. Perangkat Keras (Hardware) Komputer",
                                    "06. Perangkat Lunak (Software) Komputer","07. Menu dan Ikon pada Microsoft Word 2007", "08. Menggunakan Menu dan Ikon pada Microsoft Word 2007",
                                    "09. Fungsi Menu dan Ikon pada Pengolah Angka (Ms Excel)", "10. Penggunaan Menu dan Ikon pada Perangkat Lunak Pengolah Angka (Ms Excel)", "11. Mulai Bekerja dengan MS. Excel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        list1 = (ListView)findViewById(R.id.list);
        list1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                Intent i = null;
                if (position == 0){
                    i = new Intent(Materi.this, TIK.class);}
                else if(position == 1){
                    i = new Intent(Materi.this, SejarahTIK.class);}
                else if(position == 2){
                    i = new Intent(Materi.this, PDTIK.class);}
                else if(position == 3){
                    i = new Intent(Materi.this, Hardware.class);}
                else if(position == 4){
                    i = new Intent(Materi.this, Software.class);}
                else if(position == 5){
                    i = new Intent(Materi.this, SOWinXP.class);}
                else if(position == 6){
                    i = new Intent(Materi.this, MenuIconMsword.class);}
                else if(position == 7){
                    i = new Intent(Materi.this, MenggunakanIkonPadaMsWord.class);}
                else if(position == 8){
                    i = new Intent(Materi.this, FungsiIkonMsExcel.class);}
                else if(position == 9){
                    i = new Intent(Materi.this, PenggunaanMenuIkonMsExcel.class);}
                else if(position == 10){
                    i = new Intent(Materi.this, BekerjaDenganMsExcel.class);}
                else if(position == 11){
                    finish();}
                else{}

                startActivity(i);
            }
        });}
    public void onBackPressed(){
        finish();
        System.exit(0);
    }
    }


