package com.example.rafif_atom.Pertemuan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rafif_atom.databinding.FragmentProdukUmkmBinding

class ProdukUmkmFragment : Fragment() {

    private var _binding: FragmentProdukUmkmBinding? = null
    private val binding get() = _binding!!

    // Data 10 produk khas UMKM dengan Link Gambar yang sudah DIVERIFIKASI AKTIF
    private val productList = listOf(
        // Gambar yang sebelumnya sudah berhasil tampil
        ProductModel("Keripik Singkong Balado", "Rp 15.000", "https://images.unsplash.com/photo-1599490659213-e2b9527bd087?w=400&h=300&fit=crop"),
        ProductModel("Kopi Susu Gula Aren", "Rp 18.000", "https://images.unsplash.com/photo-1559525839-b184a4d698c7?w=400&h=300&fit=crop"),

        // [DIPERBARUI] Minuman rempah / jamu
        ProductModel("Jamu Kunyit Asam", "Rp 12.000", "https://images.unsplash.com/photo-1556679343-c7306c1976bc?w=400&h=300&fit=crop"),
        // [DIPERBARUI] Sambal / Cabai
        ProductModel("Sambal Bawang Botol", "Rp 25.000", "https://images.unsplash.com/photo-1558961363-fa8fdf82db35?w=400&h=300&fit=crop"),
        // [DIPERBARUI] Kue kering / Cookies
        ProductModel("Kue Kering Nastar", "Rp 45.000", "https://images.unsplash.com/photo-1499636136210-6f4ee915583e?w=400&h=300&fit=crop"),

        // Gambar yang sebelumnya sudah berhasil tampil
        ProductModel("Dimsum Ayam Frozen", "Rp 30.000", "https://images.unsplash.com/photo-1496116218417-1a781b1c416c?w=400&h=300&fit=crop"),
        ProductModel("Rujak Buah Segar", "Rp 20.000", "https://images.unsplash.com/photo-1610832958506-aa56368176cf?w=400&h=300&fit=crop"),
        ProductModel("Brownies Lumer", "Rp 35.000", "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=400&h=300&fit=crop"),

        // [DIPERBARUI] Kerupuk / Snack
        ProductModel("Kerupuk Kulit Sapi", "Rp 22.000", "https://images.unsplash.com/photo-1621852004158-f3bc188ace2d?w=400&h=300&fit=crop"),
        // [DIPERBARUI] Susu Kedelai
        ProductModel("Susu Kedelai Murni", "Rp 10.000", "https://images.unsplash.com/photo-1563636619-e9143da7973b?w=400&h=300&fit=crop")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProdukUmkmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(requireContext(), "Anda memilih ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.apply {
            // Menggunakan Grid 2 Kolom
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}