package com.arfanet.nuvo.belajarti;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;


/**
 * Created by Nuvo on 27/09/2017.
 */

public class Petunjuk extends AppCompatActivity {

    WebView webview;
    String url = "file:///android_asset/petunjuk.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        webview = (WebView)findViewById(R.id.webview1);

        webview.loadUrl(url);



    }

}