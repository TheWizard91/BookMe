package com.example.myapplication.data_classes

data class BookData(
    val id: Int,
    val title: String,
    val author: String,
    val publicationYear: Int,
    val genres: String,
    val description: String,
    val longDescription: String,
    val coverImage: String,
    val localCoverImagePath: String,
    val stockCoverImagePath: String,
    val price: Int,
    val rates: Int
)
