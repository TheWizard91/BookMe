package com.example.myapplication.classes

import com.example.myapplication.objects.SampleBook
import kotlin.random.Random

class UserBooks {
    private val allBooksInLibrary: List<Book> = SampleBook.books

    fun getBooks(): MutableSet<Book> {
        /**Generating a random list of books that will be used as a fake library for testing.*/
        val randomNumber: Int = Random.nextInt(0, 6)
        var i: Int = 0
        val booksOfUser: MutableSet<Book> = mutableSetOf()
        while (i < randomNumber) {
            booksOfUser.add(allBooksInLibrary[Random.nextInt(0,50)])
            i++
        }
        return booksOfUser
    }
}