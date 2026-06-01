package com.example.rafif_atom.data.api

import com.example.rafif_atom.data.model.BeritaResponse
import retrofit2.http.GET

interface BeritaApiService {
    @GET("api/cnn-news/ekonomi")
    suspend fun getBeritaUMKM(): BeritaResponse
}