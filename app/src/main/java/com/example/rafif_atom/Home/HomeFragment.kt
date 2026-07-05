package com.example.rafif_atom.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import com.example.rafif_atom.BantuanActivity
import com.example.rafif_atom.Pertemuan2.KalkulatorActivity
import com.example.rafif_atom.Pertemuan3.LoginActivity
import com.example.rafif_atom.Pertemuan4.GetStartedActivity
import com.example.rafif_atom.Pertemuan4.ProfileActivity
import com.example.rafif_atom.Pertemuan5.WebViewActivity
import com.example.rafif_atom.Pertemuan10.UmkmCatalogActivity
import com.example.rafif_atom.data.api.BeritaApiClient
import com.example.rafif_atom.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as androidx.appcompat.app.AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as androidx.appcompat.app.AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        binding.btnMenuKalkulator.setOnClickListener {
            startActivity(Intent(requireContext(), KalkulatorActivity::class.java))
        }

        binding.btnMenuCustom1.setOnClickListener {
            startActivity(Intent(requireContext(), GetStartedActivity::class.java))
        }

        binding.btnMenuCustom2.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        binding.btnMenuBinaDesa.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.btnMenuBantuan.setOnClickListener {
            startActivity(Intent(requireContext(), BantuanActivity::class.java))
        }

        binding.btnMenuProduk.setOnClickListener {
            startActivity(Intent(requireContext(), UmkmCatalogActivity::class.java))
        }

        binding.btnMenuPertemuan13.setOnClickListener {
            val intent = Intent(requireContext(), com.example.rafif_atom.pertemuan_13.ThirteenthActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener { viewLogout ->
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Sign Out")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Logout") { dialog, _ ->
                    dialog.dismiss()

                    val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit {
                        clear()
                    }

                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(viewLogout, "Batal keluar", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }

        // Panggil fungsi API saat fragment dibuat
        loadBerita()
    }

    private fun loadBerita() {
        lifecycleScope.launch {
            try {
                val response = BeritaApiClient.apiService.getBeritaUMKM()

                // Cek apakah data tidak null
                response.data?.let { listBerita ->
                    // Ambil 10 berita saja agar tidak terlalu panjang
                    val adapter = BeritaAdapter(listBerita.take(10))
                    binding.rvBerita.adapter = adapter
                    binding.rvBerita.layoutManager = LinearLayoutManager(requireContext())
                }

            } catch (e: Exception) {
                // Menampilkan pesan error asli jika terjadi kendala jaringan/parsing
                Toast.makeText(requireContext(), "Gagal memuat berita: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}