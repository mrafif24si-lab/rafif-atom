package com.example.rafif_atom.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafif_atom.data.AppDatabase
import com.example.rafif_atom.data.entity.NoteEntity
import com.example.rafif_atom.databinding.FragmentNoteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentNote : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        adapter = NoteAdapter(notes, this)

        // Setup RecyclerView
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        // Memberikan garis pemisah antar baris catatan
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvNotes.addItemDecoration(dividerItemDecoration)

        // Tombol Fab untuk pindah ke Form
        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), NoteFormActivity::class.java))
        }
    }

    // Fungsi membaca semua data dari Room
    private fun fetchNotes() {
        // TAHAP PERBAIKAN: Gunakan Dispatchers.IO untuk ambil data karena getAll() sudah tidak pakai suspend
        lifecycleScope.launch(Dispatchers.IO) {
            val data = db.noteDao().getAll()

            // TAHAP PERBAIKAN: Kembali ke Main Thread khusus untuk memperbarui tampilan RecyclerView
            withContext(Dispatchers.Main) {
                notes.clear()
                notes.addAll(data)
                adapter.notifyDataSetChanged()
            }
        }
    }

    // Fungsi menghapus data, dipanggil oleh Adapter
    fun deleteNote(note: NoteEntity) {
        // Proses Delete dipindah ke Background Thread (IO) agar aman dari bug KSP
        lifecycleScope.launch(Dispatchers.IO) {
            db.noteDao().delete(note)

            // Kembali ke Main Thread khusus untuk memperbarui tampilan RecyclerView
            withContext(Dispatchers.Main) {
                fetchNotes()
            }
        }
    }

    // Refresh data otomatis saat kembali dari Form
    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}