package com.example.myapplication.classes

import kotlin.properties.Delegates

class UserLibrary {
    private var numberOfBooks by Delegates.notNull<Int>()
    private lateinit var booksOwned: List<Book>
    private var numberOfBooksLiked by Delegates.notNull<Int>()
    private lateinit var booksLiked: List<Book>
}