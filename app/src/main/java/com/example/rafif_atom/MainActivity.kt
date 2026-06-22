package com.example.rafif_atom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit // Penting: Import ini untuk fungsi edit{}
import com.example.rafif_atom.Pertemuan2.KalkulatorActivity
import com.example.rafif_atom.Pertemuan3.LoginActivity
import com.example.rafif_atom.Pertemuan4.GetStartedActivity
import com.example.rafif_atom.Pertemuan4.ProfileActivity
import com.example.rafif_atom.Pertemuan5.WebViewActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnKalkulator = findViewById<MaterialCardView>(R.id.btnMenuKalkulator)
        val btnCustom1 = findViewById<MaterialCardView>(R.id.btnMenuCustom1)
        val btnCustom2 = findViewById<MaterialCardView>(R.id.btnMenuCustom2)
        val btnBinaDesa = findViewById<MaterialCardView>(R.id.btnMenuBinaDesa)
        val btnLogout = findViewById<MaterialCardView>(R.id.btnLogout)

        btnKalkulator.setOnClickListener {
            startActivity(Intent(this, KalkulatorActivity::class.java))
        }

        btnCustom1.setOnClickListener {
            startActivity(Intent(this, GetStartedActivity::class.java))
        }

        btnCustom2.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnBinaDesa.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        btnLogout.setOnClickListener { view ->
            MaterialAlertDialogBuilder(this)
                .setTitle("Sign Out")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Logout") { dialog, _ ->
                    dialog.dismiss()

                    // --- MENGHAPUS SESI LOGIN MENGGUNAKAN KTX ---
                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    sharedPref.edit {
                        clear() // Hapus semua data (isLogin & username)
                    }
                    // ---------------------------------------------

                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(view, "Batal keluar", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}