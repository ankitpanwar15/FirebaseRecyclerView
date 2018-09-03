package com.proact.ankit.FirebaseRecyclerView;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class PdfWebView extends AppCompatActivity {
    private Bundle b;
    WebView myWebView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_web_view);

        progressBar = (ProgressBar) findViewById(R.id.pro);
        myWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        b = getIntent().getExtras();
        String pdfURL = b.getString("url");
        Log.i("pdf",pdfURL);
        myWebView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" +pdfURL);
        myWebView.setVisibility(View.INVISIBLE);

        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);

                setTitle("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                myWebView.loadUrl("javascript:(function() { " +
//                        "document.getElementsByClassName('ndfHFb-c4YZDc-GSQQnc-LgbsSe ndfHFb-c4YZDc-to915-LgbsSe VIpgJd-TzA9Ye-eEGnhe ndfHFb-c4YZDc-LgbsSe')[0].style.display='none'; })()");

                myWebView.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
                myWebView.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);
                setTitle(view.getTitle());
            }
        });
    }
//    private class MyBrowser extends WebViewClient {
//
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//
//
//            super.onPageFinished(view, url);
//            myWebView.loadUrl("javascript:(function() { " +
//                    "document.getElementsByClassName('ndfHFb-c4YZDc-GSQQnc-LgbsSe ndfHFb-c4YZDc-to915-LgbsSe VIpgJd-TzA9Ye-eEGnhe ndfHFb-c4YZDc-LgbsSe')[0].style.display='none'; })()");
////        }
//
//    }
}

