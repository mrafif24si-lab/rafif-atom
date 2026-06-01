package com.example.rafif_atom.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rafif_atom.data.entity.NoteEntity

@Dao
interface NoteDao {
    // TAHAP PERBAIKAN: Hapus kata suspend di sini
    @Query("SELECT * FROM notes")
    fun getAll(): List<NoteEntity>

    @Insert
    fun insert(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)
}