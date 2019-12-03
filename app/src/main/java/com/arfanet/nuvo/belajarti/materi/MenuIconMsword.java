package com.arfanet.nuvo.belajarti.materi;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.arfanet.nuvo.belajarti.R;
import com.arfanet.nuvo.belajarti.download_pdf.DownloadTask;
import com.github.barteksc.pdfviewer.PDFView;


/**
 * Created by Nuvo on 30/07/2017.
 */

public class MenuIconMsword extends Activity {

    private Button btnDownload;
    String URL = "https://sinauit.000webhostapp.com/pdf/07. Menu dan Ikon pada Microsoft Word 2007.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        btnDownload = (Button) findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTask(MenuIconMsword.this, URL);
            }
        });
    }
}