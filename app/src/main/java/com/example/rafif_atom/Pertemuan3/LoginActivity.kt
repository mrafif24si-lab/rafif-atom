package com.example.rafif_atom.Pertemuan3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rafif_atom.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur padding untuk insets DAN mengembalikan jarak aman desain
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Konversi nilai 24dp dan 32dp ke ukuran Pixel layar HP
            val sidePadding = (24 * resources.displayMetrics.density).toInt()
            val bottomPadding = (32 * resources.displayMetrics.density).toInt()

            // Tambahkan jarak aman ke dalam insets system bar
            v.setPadding(
                systemBars.left + sidePadding,
                systemBars.top + sidePadding,
                systemBars.right + sidePadding,
                systemBars.bottom + bottomPadding
            )
            insets
        }

        binding.btnLogin.setOnClickListener {
            // 1. Buat Intent untuk pindah ke WelcomeActivity
            val intent = Intent(this, WelcomeActivity::class.java)

            // 2. Jalankan Intent
            startActivity(intent)

            // 3. (PENTING) Tutup LoginActivity agar tidak bisa di-"Back" oleh user
            // Ini membuat alur aplikasi terasa lebih profesional
            finish()
        }
    }
}