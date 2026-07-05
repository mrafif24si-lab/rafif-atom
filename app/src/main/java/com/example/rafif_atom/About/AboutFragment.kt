package com.example.rafif_atom.About

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rafif_atom.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Menggunakan ViewBinding untuk menyambungkan layout fragment_about.xml
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar untuk halaman About
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "About UMKM" // Judul di Toolbar
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Ikon back dialihkan ke menu Home di BottomNavigationView agar tidak menutup aplikasi
        binding.toolbar.setNavigationOnClickListener {
            val bottomNav = requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(com.example.rafif_atom.R.id.bottom_navigation)
            bottomNav?.selectedItemId = com.example.rafif_atom.R.id.nav_home
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}