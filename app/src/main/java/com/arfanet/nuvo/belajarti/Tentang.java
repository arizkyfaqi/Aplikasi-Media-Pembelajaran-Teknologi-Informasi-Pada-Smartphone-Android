package com.arfanet.nuvo.belajarti;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;


/**
 * Created by Nuvo on 27/09/2017.
 */

public class Tentang extends Activity {

    WebView webview;
    String url = "file:///android_asset/tentang.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        webview = (WebView)findViewById(R.id.webview1);

        webview.loadUrl(url);



    }


}



