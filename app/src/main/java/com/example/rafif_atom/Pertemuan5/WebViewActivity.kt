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

        // 3. Logika Hide/Show Toolbar saat Web di-scroll
        webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                appBarLayout.setExpanded(false, true) // Sembunyikan saat scroll ke bawah
            } else if (scrollY < oldScrollY) {
                appBarLayout.setExpanded(true, true) // Tampilkan saat scroll ke atas
            }
        }
    }

    // 4. Mengambil alih tombol back bawaan HP agar kembali ke history web, bukan langsung keluar aplikasi
    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.webViewBinaDesa)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    // 5. Mengaktifkan fungsi tombol panah back di Toolbar
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed() // Memanggil fungsi onBackPressed di atas
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}