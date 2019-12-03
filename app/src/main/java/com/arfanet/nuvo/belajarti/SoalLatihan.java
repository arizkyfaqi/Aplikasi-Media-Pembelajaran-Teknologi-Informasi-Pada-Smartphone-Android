package com.arfanet.nuvo.belajarti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arfanet.nuvo.belajarti.latihanSoal.SoalBekerjaDenganMsExcel;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalFungsiIkonMsExcel;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalHardware;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalMenggunakanIkonPadaMsWord;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalMenuIkonMsword;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalPDTIK;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalPenggunaanMenuIkonMsExcel;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalSOWinXP;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalSejarahTIK;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalSoftware;
import com.arfanet.nuvo.belajarti.latihanSoal.SoalTIK;

public class SoalLatihan extends AppCompatActivity {
    ListView list1;
    String[] menu = new String[] {  "01. Teknologi Informasi dan Komunikasi","02. Sejarah Perkembangan TIK", "03. Peran dan Dampak TIK", "04. Sistem Operasi Windows XP", "05. Perangkat Keras (Hardware) Komputer",
                                    "06. Perangkat Lunak (Software) Komputer","07. Menu dan Ikon pada Microsoft Word 2007", "08. Menggunakan Menu dan Ikon pada Microsoft Word 2007",
                                    "09. Fungsi Menu dan Ikon pada Pengolah Angka (Ms Excel)", "10. Penggunaan Menu dan Ikon pada Perangkat Lunak Pengolah Angka (Ms Excel)", "11. Mulai Bekerja dengan MS. Excel"};

    // Create an ArrayAdapter, that will actually make the Strings above
    // appear in the ListView
    // Menset nilai array ke dalam list adapater sehingga data pada array
    // akan dimunculkan dalam list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latihan_soal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list1 = (ListView)findViewById(R.id.list);
        list1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                Intent i = null;
                if (position == 0){
                    i = new Intent(SoalLatihan.this, SoalTIK.class);}
                else if(position == 1){
                    i = new Intent(SoalLatihan.this, SoalSejarahTIK.class);}
                else if(position == 2){
                    i = new Intent(SoalLatihan.this, SoalPDTIK.class);}
                else if(position == 3){
                    i = new Intent(SoalLatihan.this, SoalSOWinXP.class);}
                else if(position == 4){
                    i = new Intent(SoalLatihan.this, SoalHardware.class);}
                else if(position == 5){
                    i = new Intent(SoalLatihan.this, SoalSoftware.class);}
                else if(position == 6){
                    i = new Intent(SoalLatihan.this, SoalMenuIkonMsword.class);}
                else if(position == 7){
                    i = new Intent(SoalLatihan.this, SoalMenggunakanIkonPadaMsWord.class);}
                else if(position == 8){
                    i = new Intent(SoalLatihan.this, SoalFungsiIkonMsExcel.class);}
                else if(position == 9){
                    i = new Intent(SoalLatihan.this, SoalPenggunaanMenuIkonMsExcel.class);}
                else if(position == 10){
                    i = new Intent(SoalLatihan.this, SoalBekerjaDenganMsExcel.class);
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


