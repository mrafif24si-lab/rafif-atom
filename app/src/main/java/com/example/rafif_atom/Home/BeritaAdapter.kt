package com.example.rafif_atom.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rafif_atom.data.model.BeritaModel
import com.example.rafif_atom.databinding.ItemBeritaBinding

class BeritaAdapter(private val items: List<BeritaModel>) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    inner class BeritaViewHolder(val binding: ItemBeritaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val binding = ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val item = items[position]

        // Mengatasi kemungkinan judul atau tanggal null dari API
        holder.binding.tvJudulBerita.text = item.title ?: "Tanpa Judul"
        holder.binding.tvTanggalBerita.text = item.isoDate?.take(10) ?: "-"

        // Ambil URL gambar besar, jika null otomatis pakai yang kecil
        val imageUrl = item.image?.large ?: item.image?.small

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .centerCrop() // Agar gambar terpotong rapi menyesuaikan ImageView
            .into(holder.binding.imgBerita)
    }

    override fun getItemCount(): Int = items.size
}