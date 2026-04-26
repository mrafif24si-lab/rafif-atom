package com.example.rafif_atom.Pertemuan3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit // Penting: Import ini agar fungsi edit{} berjalan
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rafif_atom.MainActivity
import com.example.rafif_atom.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val sidePadding = (24 * resources.displayMetrics.density).toInt()
            val bottomPadding = (32 * resources.displayMetrics.density).toInt()
            v.setPadding(
                systemBars.left + sidePadding,
                systemBars.top + sidePadding,
                systemBars.right + sidePadding,
                systemBars.bottom + bottomPadding
            )
            insets
        }

        binding.btnLogin.setOnClickListener {
            // 1. Simpan sesi login ke SharedPreferences menggunakan KTX (edit {})
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            sharedPref.edit {
                putBoolean("isLogin", true)
                putString("username", "Rafif") // Menyimpan data user
            }

            // 2. Arahkan langsung ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // 3. Tutup LoginActivity
            finish()
        }
    }
}