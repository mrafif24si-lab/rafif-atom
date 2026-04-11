package com.example.rafif_atom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rafif_atom.Pertemuan2.KalkulatorActivity
import com.example.rafif_atom.Pertemuan3.LoginActivity
import com.example.rafif_atom.Pertemuan4.GetStartedActivity
import com.example.rafif_atom.Pertemuan4.ProfileActivity
import com.google.android.material.card.MaterialCardView // Import CardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ubah dari Button ke MaterialCardView agar sesuai dengan XML terbaru
        val btnKalkulator = findViewById<MaterialCardView>(R.id.btnMenuKalkulator)
        val btnCustom1 = findViewById<MaterialCardView>(R.id.btnMenuCustom1)
        val btnCustom2 = findViewById<MaterialCardView>(R.id.btnMenuCustom2)
        val btnLogout = findViewById<MaterialCardView>(R.id.btnLogout)

        // 1. Ke Halaman Kalkulator
        btnKalkulator.setOnClickListener {
            val intent = Intent(this, KalkulatorActivity::class.java)
            // Mengirim data "Titipan" (Intent Extra)
            intent.putExtra("JUDUL", "Calculator Tools")
            intent.putExtra("DESKRIPSI", "Hitung rumus bangun ruang dengan mudah")
            startActivity(intent)
        }

        // 2. Ke Halaman Get Started (Custom 1)
        btnCustom1.setOnClickListener {
            val intent = Intent(this, GetStartedActivity::class.java)
            intent.putExtra("JUDUL", "Let's Cooking!")
            intent.putExtra("DESKRIPSI", "Pelajari resep makanan sehat di sini.")
            startActivity(intent)
        }

        // 3. Ke Halaman Profile (Custom 2)
        btnCustom2.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("JUDUL", "Account Info")
            intent.putExtra("DESKRIPSI", "Detail profil dan riwayat memasak")
            startActivity(intent)
        }

        // 4. Logout dengan Dialog Konfirmasi
        btnLogout.setOnClickListener { view ->
            MaterialAlertDialogBuilder(this)
                .setTitle("Sign Out")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Logout") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    // FLAG ini menghapus semua history halaman, sangat aman!
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish() // Memastikan MainActivity benar-benar dihancurkan
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(view, "Batal keluar", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}