package com.arfanet.nuvo.belajarti.materi;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.arfanet.nuvo.belajarti.R;
import com.arfanet.nuvo.belajarti.download_pdf.DownloadTask;
import com.github.barteksc.pdfviewer.PDFView;


/**
 * Created by Nuvo on 30/07/2017.
 */

public class TIK extends AppCompatActivity {

    private Button btnDownload;
    String URL = "https://sinauit.000webhostapp.com/pdf/01. Teknologi  Informasi dan Komunikasi.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        btnDownload = (Button) findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTask(TIK.this, URL);
            }
        });
   }
}
