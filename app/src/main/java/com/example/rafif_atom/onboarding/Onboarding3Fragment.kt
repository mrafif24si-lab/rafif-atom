package com.example.rafif_atom.onboarding // Sesuaikan dengan nama packagemu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.rafif_atom.Pertemuan3.LoginActivity // Sesuaikan import LoginActivity
import com.example.rafif_atom.R

class Onboarding3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // PASTIKAN BARIS INI MENGARAH KE R.layout.fragment_onboarding3
        return inflater.inflate(R.layout.fragment_onboarding3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Logika untuk tombol "Ayo Mulai"
        val btnAyoMulai = view.findViewById<Button>(R.id.btnAyoMulai)
        btnAyoMulai.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Tutup halaman onboarding
        }
    }
}