package com.example.rafif_atom.Pertemuan10


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rafif_atom.data.entity.ProductEntity
import com.example.rafif_atom.databinding.ItemProductBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ProductAdapter(
    private val products: List<ProductEntity>,
    private val onDeleteClick: (ProductEntity) -> Unit // Menggunakan lambda function agar lebih fleksibel
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.tvProductName.text = product.name
        holder.binding.tvProductPrice.text = "Rp ${product.price}"
        holder.binding.tvProductDesc.text = product.description

        holder.binding.btnDeleteProduct.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Produk")
                .setMessage("Yakin ingin menghapus ${product.name} dari katalog?")
                .setPositiveButton("Ya") { dialog, _ ->
                    onDeleteClick(product)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = products.size
}