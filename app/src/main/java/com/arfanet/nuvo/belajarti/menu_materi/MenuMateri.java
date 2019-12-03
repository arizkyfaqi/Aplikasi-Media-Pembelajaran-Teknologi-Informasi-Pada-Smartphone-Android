package com.arfanet.nuvo.belajarti.menu_materi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.arfanet.nuvo.belajarti.R;
import com.arfanet.nuvo.belajarti.download_pdf.DaftarDownload;

import java.io.File;
import java.util.ArrayList;

public class MenuMateri extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_materi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView lv = (ListView) findViewById(R.id.lv);

        lv.setAdapter(new CostumAdapter(MenuMateri.this,getPDFs()));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(MenuMateri.this, DaftarDownload.class);
                startActivity(c);
            }
        });
    }

    private ArrayList<PDFDoc> getPDFs()
    {
        ArrayList<PDFDoc> pdfDocs=new ArrayList<>();
        //TARGET FOLDER
        File downloadsFolder = new File (Environment.getExternalStorageDirectory() + "/" + "BelajarTI");

        PDFDoc pdfDoc;

        if (downloadsFolder.exists())
        {
            //GET ALL FILE IN DOWNLOAD FOLDER
            File[] files=downloadsFolder.listFiles();

            //LOOP THRU THOSE FILES GETTING NAME AND URI
            for (int i=0; i<files.length; i++)
            {
                File file=files[i];

                if (file.getPath().endsWith("pdf"))
                {
                    pdfDoc=new PDFDoc();
                    pdfDoc.setName(file.getName());
                    pdfDoc.setPath(file.getPath());

                    pdfDocs.add(pdfDoc);
                }
            }
        }

        return pdfDocs;
    }


}