package org.dkit.logued.myapplication

import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient

class WebviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        var fullURL =
            "https://en.wikipedia.org/wiki/List_of_presidents_of_the_United_States"

        // DL - this line is missing from the tutorial
        // it loads the WebView created by the XML
        val webView = findViewById<WebView>(R.id.wiki_webview)

        webView.webViewClient = WebViewClient()  // may fail
//        webView.webViewClient = USNAWebViewClient()

        var president = intent.getStringExtra("president")

        // replace all spaces with the underscore character
        president = president?.replace(" ", "_");

        // DL - changed to https from "http://" to prevent ERR_CLEARTEXT_NOT_PERMITTED error
        fullURL = "https://en.m.wikipedia.org/wiki/$president"

        webView.loadUrl(fullURL)


    }
}

// This code may be unnecessary - so do NOT worry about
// how it works.
//extend WebViewClient class
private class USNAWebViewClient :  WebViewClient() {

    //these errors might be sent due to missing CA
    override fun onReceivedSslError(
        view: WebView?,
        handler: SslErrorHandler?,
        error: SslError?
    ) {
        Log.e("IT472", "SSL error: " + error.toString())
        //not recommended, but needed due to missing CA on AVDs
        handler?.proceed()
        //super.onReceivedSslError(view, handler, error)
    }

}