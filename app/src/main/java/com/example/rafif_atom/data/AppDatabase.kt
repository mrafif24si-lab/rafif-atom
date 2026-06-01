package com.example.rafif_atom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rafif_atom.data.entity.NoteEntity
import com.example.rafif_atom.data.entity.ProductEntity
import com.example.rafif_atom.data.dao.NoteDao
import com.example.rafif_atom.data.dao.ProductDao

@Database(entities = [NoteEntity::class, ProductEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    // TAHAP PENTING: Mencegah error crash karena perubahan versi database
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}