package com.example.rafif_atom.Pertemuan3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// Import diubah dari MainActivity menjadi BaseActivity
import com.example.rafif_atom.BaseActivity
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
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            sharedPref.edit {
                putBoolean("isLogin", true)
                putString("username", "Rafif")
            }

            // Arahkan langsung ke BaseActivity
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}