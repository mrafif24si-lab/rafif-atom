package com.example.rafif_atom.data.model

// Menambahkan tanda tanya (?) agar aman jika API mengembalikan nilai null
data class BeritaResponse(val data: List<BeritaModel>?)

data class BeritaModel(
    val title: String?,
    val isoDate: String?,
    val image: ImageModel?
)

data class ImageModel(
    val small: String?,
    val large: String?
)