package com.arfanet.nuvo.belajarti.menu_materi;

import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arfanet.nuvo.belajarti.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;


public class PDFActivity extends AppCompatActivity {

    public PDFView pdfView;
    public float zoomValue=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        //PDFVIEW SHALL DISPLAY OUR PDFS
        pdfView = (PDFView) findViewById(R.id.PDFView);

        //SCROLLBAR TO ENABLE SCROLLING

        //VERTICAL SCROLLING

        //SERVICE MEMORY FOR QUALITY
        //pdfView.useBseQuality(true);

        //UNPACK DOUR DATA FROM INTENT
        Intent i=this.getIntent();
        String path=i.getExtras().getString("PATH");

        //GET THE PDF FLIE
        File file=new File(path);

        if (file.canRead())
        {
            //LOAD IT
            pdfView.fromFile(file).defaultPage(0).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(PDFActivity.this, String.valueOf(nbPages), Toast.LENGTH_LONG).show();
                }

            }).load();
        }

    }

    public void  nextPage(View view){
        pdfView.jumpTo(pdfView.getCurrentPage()+1,true);
    }

    public void  prevPage(View view){
        pdfView.jumpTo(pdfView.getCurrentPage()-1,true);
    }

    public void zoomIn(View view){
        ++zoomValue;
        pdfView.zoomTo(zoomValue);
        pdfView.loadPages();
    }

    public void zoomOut(View view){

        if (zoomValue!=1){
            --zoomValue;
            pdfView.zoomTo(zoomValue);
            pdfView.loadPages();
        }

    }

}
