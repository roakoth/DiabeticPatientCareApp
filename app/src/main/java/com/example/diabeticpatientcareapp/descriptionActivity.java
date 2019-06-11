package com.example.diabeticpatientcareapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class descriptionActivity extends AppCompatActivity {
    private static final String TAG = "descriptionactivity";
    private Context mContext;
    private Bundle extras;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);



        mContext = descriptionActivity.this;

        webView = (WebView) findViewById(R.id.simpleWebView);
        extras = getIntent().getExtras();
        if(!extras.equals(null)){

            String data = extras.getString("titles");
            Log.d(TAG,"onCreate: the coming data is "+data);

            String url = "file:///android_asset/"+data+".html";
            webView.loadUrl(url);

            WebSettings webSettings = webView.getSettings();
            webSettings.setBuiltInZoomControls(true);
            webSettings.setDisplayZoomControls(false);
            webSettings.setJavaScriptEnabled(true);

        }
    }
}
