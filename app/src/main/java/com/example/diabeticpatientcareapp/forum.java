package com.example.diabeticpatientcareapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class forum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        WebView webview = new WebView(this);
        setContentView(webview);
       webview.loadUrl("https://www.diabetes.co.uk/forum");
        //webview.loadUrl("https://www.diabetesdaily.com/");
    }
}
