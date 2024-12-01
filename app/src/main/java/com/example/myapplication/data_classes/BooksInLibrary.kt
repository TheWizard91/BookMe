package com.example.myapplication.data_classes

import com.example.myapplication.classes.Book


data class BooksInLibrary(
    private var numberOfBooks: String = "",
    private var booksOwned: List<Book>,
    private var numberOfBooksLiked: String = "",
    private var booksLiked: List<Book>
)
