package com.example.rafif_atom.Pertemuan10

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafif_atom.data.AppDatabase
import com.example.rafif_atom.data.entity.ProductEntity
import com.example.rafif_atom.databinding.FragmentProdukUmkmBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdukUmkmFragment : Fragment() {
    private var _binding: FragmentProdukUmkmBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProdukUmkmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        // Inisialisasi adapter beserta fungsi hapusnya
        adapter = ProductAdapter(productList) { product ->
            deleteProduct(product)
        }

        // Setup RecyclerView
        binding.rvProducts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvProducts.adapter = adapter

        // Tombol Fab untuk pindah ke form tambah produk
        binding.fabAddProduct.setOnClickListener {
            startActivity(Intent(requireContext(), ProductFormActivity::class.java))
        }
    }

    // Mengambil data produk dari Room Database (Background Thread)
    private fun fetchProducts() {
        lifecycleScope.launch(Dispatchers.IO) {
            val data = db.productDao().getAllProducts()

            // Memperbarui UI (Main Thread)
            withContext(Dispatchers.Main) {
                productList.clear()
                productList.addAll(data)
                adapter.notifyDataSetChanged()
            }
        }
    }

    // Menghapus data produk (Background Thread)
    private fun deleteProduct(product: ProductEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.productDao().deleteProduct(product)

            // Muat ulang data setelah dihapus (Main Thread)
            withContext(Dispatchers.Main) {
                fetchProducts()
            }
        }
    }

    // Otomatis refresh data saat kembali ke halaman ini
    override fun onResume() {
        super.onResume()
        fetchProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}