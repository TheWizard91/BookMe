package com.example.myapplication.objects

import com.example.myapplication.classes.Book
import com.example.myapplication.classes.UserBooks
import kotlin.random.Random

object UserBooksList {
    /**Fake user library for testing.*/
    private val userBooks: UserBooks = UserBooks()
    val listOfBooksOwnedByUser: Set<Book> = userBooks.getBooks()
}