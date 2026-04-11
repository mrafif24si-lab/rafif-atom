package com.example.rafif_atom.Pertemuan4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rafif_atom.R

class GetStartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)

        // 1. Inisialisasi ulang View (Gunakan ID yang ada di activity_get_started.xml)
        val tvJudul = findViewById<TextView>(R.id.textView3) // Sesuaikan ID-nya
        val btnStart = findViewById<Button>(R.id.btnGoToMain)

        // 2. Menangkap data dari Intent
//        val judul = intent.getStringExtra("JUDUL")
//        val deskripsi = intent.getStringExtra("DESKRIPSI")

        // 3. Tampilkan kembali ke layar
//        tvJudul.text = judul
        // Jika ada TextView deskripsi, tambahkan juga di sini

        btnStart.setOnClickListener {
            finish()
        }
    }
}