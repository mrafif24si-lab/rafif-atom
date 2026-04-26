package com.example.rafif_atom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.rafif_atom.Pertemuan3.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pastikan kamu memiliki file layout activity_splash_screen.xml
        setContentView(R.layout.activity_splash_screen)

        // Jalankan coroutine untuk delay 2 detik
        lifecycleScope.launch {
            delay(2000)

            // Cek status login di SharedPreferences
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            if (isLogin) {
                // Jika sudah login, langsung ke MainActivity
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Jika belum login, ke LoginActivity
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
            }

            // Hancurkan halaman splash agar tidak bisa kembali dengan tombol back
            finish()
        }
    }
}