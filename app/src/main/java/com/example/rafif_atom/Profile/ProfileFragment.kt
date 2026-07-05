package com.example.rafif_atom.Profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rafif_atom.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        binding.toolbar.title = "Profil Pengembang"
        binding.toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        binding.toolbar.setNavigationOnClickListener {
            val bottomNav = requireActivity().findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(com.example.rafif_atom.R.id.bottom_navigation)
            bottomNav?.selectedItemId = com.example.rafif_atom.R.id.nav_home
        }

        // Logika Klik Sosial Media
        binding.btnLinkedin.setOnClickListener { openUrl("https://linkedin.com/in/rafifzidane") }
        binding.btnGithub.setOnClickListener { openUrl("https://github.com/mrafif24si-lab/UMKM-guest.git") }
        binding.btnInstagram.setOnClickListener { openUrl("https://instagram.com/raffzdne") }
        binding.btnFacebook.setOnClickListener { openUrl("https://facebook.com/rafifzidane") }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}