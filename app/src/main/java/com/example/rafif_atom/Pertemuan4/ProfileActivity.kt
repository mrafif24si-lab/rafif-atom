package com.example.rafif_atom.Pertemuan4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.rafif_atom.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        // Blok ViewCompat yang mencari R.id.main SUDAH DIHAPUS.
        // Halaman profil sekarang aman dari NullPointerException!
    }
}