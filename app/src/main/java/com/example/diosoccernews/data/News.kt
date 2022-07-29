package com.example.diosoccernews.data

data class News(
    val id: Int,
    val titulo: String,
    val texto: String,
    val imagem: String,
    val link: String,
    var isFavorite: Boolean = false
)
