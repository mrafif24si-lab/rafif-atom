package com.example.rafif_atom.Pertemuan5

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.rafif_atom.R
import com.google.android.material.appbar.AppBarLayout

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarWebView)
        val appBarLayout = findViewById<AppBarLayout>(R.id.appBarLayout)
        val webView = findViewById<WebView>(R.id.webViewBinaDesa)

        // 1. Setup Toolbar & Tombol Back
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Web UMKM Bina Desa"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 2. Konfigurasi WebView
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://umkm-guest.alwaysdata.net")

        webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                appBarLayout.setExpanded(false, true) // Sembunyikan saat scroll ke bawah
            } else if (scrollY < oldScrollY) {
                appBarLayout.setExpanded(true, true) // Tampilkan saat scroll ke atas
            }
        }
    }


    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.webViewBinaDesa)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }


    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}