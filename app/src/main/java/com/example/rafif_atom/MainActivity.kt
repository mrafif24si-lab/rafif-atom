package com.example.rafif_atom

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Deklarasi Komponen Segitiga
        val inputAlasSegitiga = findViewById<EditText>(R.id.inputAlasSegitiga)
        val inputTinggiSegitiga = findViewById<EditText>(R.id.inputTinggiSegitiga)
        val btnHitungSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)
        val tvHasilSegitiga = findViewById<TextView>(R.id.tvHasilSegitiga)

        // Deklarasi Komponen Balok
        val inputPanjangBalok = findViewById<EditText>(R.id.inputPanjangBalok)
        val inputLebarBalok = findViewById<EditText>(R.id.inputLebarBalok)
        val inputTinggiBalok = findViewById<EditText>(R.id.inputTinggiBalok)
        val btnHitungBalok = findViewById<Button>(R.id.btnHitungBalok)
        val tvHasilBalok = findViewById<TextView>(R.id.tvHasilBalok)

        // Logika Tombol Hitung Segitiga
        btnHitungSegitiga.setOnClickListener {
            val alasStr = inputAlasSegitiga.text.toString()
            val tinggiStr = inputTinggiSegitiga.text.toString()

            if (alasStr.isNotEmpty() && tinggiStr.isNotEmpty()) {
                val alas = alasStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val luas = 0.5 * alas * tinggi
                tvHasilSegitiga.text = "Hasil Luas: $luas"
            } else {
                Toast.makeText(this, "Mohon isi alas dan tinggi!", Toast.LENGTH_SHORT).show()
            }
        }

        // Logika Tombol Hitung Balok
        btnHitungBalok.setOnClickListener {
            val panjangStr = inputPanjangBalok.text.toString()
            val lebarStr = inputLebarBalok.text.toString()
            val tinggiStr = inputTinggiBalok.text.toString()

            if (panjangStr.isNotEmpty() && lebarStr.isNotEmpty() && tinggiStr.isNotEmpty()) {
                val panjang = panjangStr.toDouble()
                val lebar = lebarStr.toDouble()
                val tinggi = tinggiStr.toDouble()
                val volume = panjang * lebar * tinggi
                tvHasilBalok.text = "Hasil Volume: $volume"
            } else {
                Toast.makeText(this, "Mohon isi panjang, lebar, dan tinggi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}