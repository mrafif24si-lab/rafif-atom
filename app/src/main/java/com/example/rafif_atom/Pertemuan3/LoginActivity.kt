package com.example.rafif_atom.Pertemuan3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rafif_atom.BaseActivity
import com.example.rafif_atom.InputEmailActivity
import com.example.rafif_atom.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
            val inputUsername = binding.inputEmail.text.toString().trim()
            val inputPassword = binding.inputPassword.text.toString().trim()

            val sharedPref = getSharedPreferences("DataUser", MODE_PRIVATE)
            val savedUsername = sharedPref.getString("username", "")
            val savedPassword = sharedPref.getString("password", "")


            val kondisiSatu = inputUsername.isNotEmpty() && (inputUsername == inputPassword)

            // Kondisi 2: username dan password sesuai dengan SharedPreferences
            val kondisiDua = inputUsername.isNotEmpty() && (inputUsername == savedUsername) && (inputPassword == savedPassword)

            if (kondisiSatu || kondisiDua) {
                // ===============================================================
                // TAHAP PERBAIKAN: Gunakan "user_pref" untuk menyimpan sesi login
                // agar sinkron dengan SplashScreen dan tombol Logout
                // ===============================================================
                val sessionPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                sessionPref.edit().putBoolean("isLogin", true).apply()

                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Login Gagal -> Tampilkan MaterialAlertDialog
                MaterialAlertDialogBuilder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username atau password salah!")
                    .setPositiveButton("Coba Lagi") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        // --- SOAL A1: TOMBOL REGISTER WITH GMAIL ---
        binding.btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, InputEmailActivity::class.java)
            startActivity(intent)
        }
    }
}