package com.example.rafif_atom // Sesuaikan jika ada sub-package

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.ChipGroup

class BantuanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bantuan)

        val listViewBantuan = findViewById<ListView>(R.id.listViewBantuan)
        val btnKirim = findViewById<MaterialButton>(R.id.btnKirimPertanyaan)
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroupFilter)

        // 1. Siapkan Data Dummy khas Bina Desa UMKM
        val listData = ArrayList<HashMap<String, String>>()

        val item1 = HashMap<String, String>()
        item1["judul"] = "Cara Daftar Mitra Bina Desa"
        item1["deskripsi"] = "Syarat dan ketentuan untuk bergabung menjadi UMKM binaan."
        listData.add(item1)

        val item2 = HashMap<String, String>()
        item2["judul"] = "Panduan Foto Produk"
        item2["deskripsi"] = "Tips mengambil foto produk agar menarik minat pembeli di katalog."
        listData.add(item2)

        val item3 = HashMap<String, String>()
        item3["judul"] = "Sistem Pencairan Dana"
        item3["deskripsi"] = "Berapa lama proses pencairan dana hasil penjualan UMKM ke rekening."
        listData.add(item3)

        // 2. Mapping data ke layout item_bantuan.xml
        val from = arrayOf("judul", "deskripsi")
        val to = intArrayOf(R.id.tvJudulBantuan, R.id.tvDeskripsiBantuan)

        // 3. Terapkan SimpleAdapter
        val adapter = SimpleAdapter(this, listData, R.layout.item_bantuan, from, to)
        listViewBantuan.adapter = adapter

        // Interaksi
        listViewBantuan.setOnItemClickListener { _, _, position, _ ->
            val judul = listData[position]["judul"]
            Toast.makeText(this, "Membuka: $judul", Toast.LENGTH_SHORT).show()
        }

        btnKirim.setOnClickListener {
            Toast.makeText(this, "Pertanyaan berhasil dikirim ke tim Bina Desa!", Toast.LENGTH_SHORT).show()
        }
    }
}