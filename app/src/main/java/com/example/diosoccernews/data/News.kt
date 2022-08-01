package com.example.diosoccernews.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey
    val id: Int,
    val titulo: String,
    val texto: String,
    val imagem: String,
    val link: String,
    var isFavorite: Boolean = false
)
