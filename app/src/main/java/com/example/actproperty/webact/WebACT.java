package com.example.actproperty.webact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.actproperty.R;

public class WebACT extends AppCompatActivity {
WebView webact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_act);
        webact = (WebView)findViewById(R.id.webact);
        WebSettings webSettings = webact.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webact.loadUrl("http://vienthongact.vn/");

    }
}
