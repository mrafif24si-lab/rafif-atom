package com.example.rafif_atom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.rafif_atom.About.AboutFragment
import com.example.rafif_atom.Home.HomeFragment
import com.example.rafif_atom.Profile.ProfileFragment
// Tambahkan import FragmentNote
import com.example.rafif_atom.Note.FragmentNote
import com.example.rafif_atom.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatasi jarak bottom navigation (insets) sesuai panduan materi
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0) // bottom diubah jadi 0
            insets
        }

        // Tampilkan HomeFragment sebagai default
        replaceFragment(HomeFragment())

        // Set listener untuk Bottom Navigation
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                // 👇 TAHAP PENTING: Tambahkan navigasi Note di sini 👇
                R.id.note -> {
                    replaceFragment(FragmentNote())
                    true
                }
                // 👆 ---------------------------------------------- 👆

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}