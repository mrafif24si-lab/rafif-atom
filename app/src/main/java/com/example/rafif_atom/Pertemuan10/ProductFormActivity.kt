package com.example.rafif_atom.Pertemuan10

// Ganti nama package di atas sesuai dengan lokasi folder tempat kamu menaruh file ini

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.rafif_atom.data.AppDatabase
import com.example.rafif_atom.data.entity.ProductEntity
import com.example.rafif_atom.databinding.ActivityProductFormBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnSaveProduct.setOnClickListener {
            val name = binding.etProductName.text.toString()
            val price = binding.etProductPrice.text.toString()
            val desc = binding.etProductDesc.text.toString()

            if (name.isNotBlank() && price.isNotBlank() && desc.isNotBlank()) {
                // Gunakan background thread agar aman dari bug KSP
                lifecycleScope.launch(Dispatchers.IO) {
                    val product = ProductEntity(
                        name = name,
                        price = price,
                        description = desc
                    )
                    db.productDao().insertProduct(product)

                    withContext(Dispatchers.Main) {
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "Isi semua detail produk!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}