package com.example.rafif_atom.Pertemuan10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rafif_atom.databinding.ActivityUmkmCatalogBinding
import com.google.android.material.tabs.TabLayoutMediator

class UmkmCatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUmkmCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUmkmCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. SETTING TOOLBAR SEBAGAI ACTION BAR
        setSupportActionBar(binding.toolbarKatalog)

        // 2. AKTIFKAN TOMBOL BACK (PANAH KEMBALI)
        supportActionBar?.apply {
            title = "Katalog UMKM" // Judul halaman di Toolbar
            setDisplayHomeAsUpEnabled(true) // Memunculkan tombol panah kembali
            setDisplayShowHomeEnabled(true)
        }

        // 3. PASANG ADAPTER KE VIEWPAGER2 (Agar isi fragment muncul kembali)
        val adapter = UmkmTabsAdapter(this)
        binding.viewPager.adapter = adapter

        // 4. HUBUNGKAN TABLAYOUT DENGAN VIEWPAGER2 (Agar nama tab muncul kembali)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Katalog Produk"
                1 -> tab.text = "Info UMKM"
            }
        }.attach()
    }

    // 5. FUNGSI LOGIKA KETIKA TOMBOL PANAH BACK DIKLIK
    override fun onSupportNavigateUp(): Boolean {
        finish() // Menutup halaman katalog ini dan otomatis kembali ke HomeFragment
        return true
    }
}