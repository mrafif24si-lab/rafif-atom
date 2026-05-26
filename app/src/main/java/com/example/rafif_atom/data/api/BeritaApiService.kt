package com.example.rafif_atom.data.api

import com.example.rafif_atom.data.model.BeritaResponse
import retrofit2.http.GET

interface BeritaApiService {
    // Menggunakan CNN Ekonomi dari repository hansputera
    @GET("api/cnn-news/ekonomi")
    suspend fun getBeritaUMKM(): BeritaResponse
}