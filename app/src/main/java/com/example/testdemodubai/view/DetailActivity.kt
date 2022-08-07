package com.example.testdemodubai.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import com.example.testdemodubai.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initWebview()
    }


    private fun initWebview() {
        val url = intent?.extras?.getString("url", "")
        val webView=findViewById<WebView>(R.id.webView)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        if (url != null) {
            webView.loadUrl(url)
        } //load url to webview

    }


}