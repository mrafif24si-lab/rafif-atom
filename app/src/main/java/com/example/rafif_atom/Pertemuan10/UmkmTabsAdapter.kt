package com.example.rafif_atom.Pertemuan10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class UmkmTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProdukUmkmFragment() // Menampilkan Grid RecyclerView
            1 -> InfoUmkmFragment()   // Menampilkan Info statis
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}