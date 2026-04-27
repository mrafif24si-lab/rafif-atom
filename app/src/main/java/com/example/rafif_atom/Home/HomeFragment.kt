package com.example.rafif_atom.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.rafif_atom.Pertemuan2.KalkulatorActivity
import com.example.rafif_atom.Pertemuan3.LoginActivity
import com.example.rafif_atom.Pertemuan4.GetStartedActivity
import com.example.rafif_atom.Pertemuan4.ProfileActivity
import com.example.rafif_atom.Pertemuan5.WebViewActivity
import com.example.rafif_atom.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using ViewBinding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as androidx.appcompat.app.AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as androidx.appcompat.app.AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }
        // 1. Navigasi ke Kalkulator
        binding.btnMenuKalkulator.setOnClickListener {
            startActivity(Intent(requireContext(), KalkulatorActivity::class.java))
        }

        // 2. Navigasi ke Get Started
        binding.btnMenuCustom1.setOnClickListener {
            startActivity(Intent(requireContext(), GetStartedActivity::class.java))
        }

        // 3. Navigasi ke Profile Activity (Tugas lama)
        binding.btnMenuCustom2.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        // 4. Navigasi ke Bina Desa (WebView)
        binding.btnMenuBinaDesa.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // 5. Logout dan Hapus Sesi SharedPreferences
        binding.btnLogout.setOnClickListener { viewLogout ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Sign Out")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Logout") { dialog, _ ->
                    dialog.dismiss()

                    // --- MENGHAPUS SESI LOGIN MENGGUNAKAN KTX ---
                    val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit {
                        clear() // Hapus semua data (isLogin & username)
                    }
                    // ---------------------------------------------

                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish() // Hancurkan Activity host (BaseActivity)
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(viewLogout, "Batal keluar", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Mencegah memory leak
    }
}