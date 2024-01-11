package com.example.projetokotlin

data class MusicData(
    val `data`: List<Data>,
    val next: String,
    val total: Int
) {
}