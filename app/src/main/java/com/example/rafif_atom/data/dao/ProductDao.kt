package com.example.rafif_atom.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rafif_atom.data.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): List<ProductEntity>

    @Insert
    fun insertProduct(product: ProductEntity)

    @Delete
    fun deleteProduct(product: ProductEntity)
}