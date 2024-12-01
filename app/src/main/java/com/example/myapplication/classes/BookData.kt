package com.example.myapplication.classes

import com.example.myapplication.R

class Book(
    id: Int,
    title: String,
    author: String,
    publicationYear: Int,
    genres: List<String>,
    description: String,
    longDescription: String,
    coverImage: String,
    localCoverImagePath: Int,
    stockCoverImagePath: String,
    price: Int,
    rates: Int
) {
    constructor() : this(
        -1,
        "Fake Book",
        "Fake Author",
        1991,
        listOf("No Genre"),
        "Fake Description",
        "Fake Long Description",
        "No Image",
        R.drawable.profile_picture,
        "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/book_store_repository%2Fstock_cover_image_path%2Fbook_image.png?alt=media&token=cc06abec-4490-4118-8a7e-d5ca0c2b753f",
        0,
        0
    )

    private var id: Int = 0
    private var title: String = ""
    private var author: String = ""
    private var publicationYear: Int = 0
    private var genres: List<String>
    private var description: String = ""
    private var longDescription: String = ""
    private var coverImage: String = ""
    private var localCoverImagePath: Int = 0
    private var stockCoverImagePath: String = ""
    private var price: Int = 0
    private var rates: Int = 0

    init {
        this.id = id
        this.title = title
        this.author = author
        this.publicationYear = publicationYear
        this.genres = genres.toMutableList()
        this.description = description
        this.longDescription = longDescription
        this.coverImage = coverImage
        this.localCoverImagePath = localCoverImagePath
        this.stockCoverImagePath = stockCoverImagePath
        this.price = price
        this.rates = rates
    }

    fun setId(id: Int) { this.id = id }
    fun getId(): Int { return id }
    fun setTitle(title: String) { this.title = title }
    fun getTitle(): String { return title }
    fun setAuthor(author: String) { this.author = author }
    fun getAuthor(): String { return author }
    fun setPublicationYear(publicationYear: Int) { this.publicationYear = publicationYear; }
    fun getPublicationYear(): Int { return publicationYear }
    fun setGenres(genres: MutableList<String>) { this.genres = genres }
    fun getGenres(): MutableList<String> { return genres.toMutableList() }
    fun setDescription(description: String) { this.description = description }
    fun getDescription(): String { return description }
    fun setLongDescription(longDescription: String) { this.longDescription = longDescription }
    fun getLongDescription(): String { return longDescription }
    fun setCoverImage(coverImage: String) { this.coverImage = coverImage }
    fun getCoverImage(): String { return coverImage }
    fun setLocalCoverImagePath(localCoverImagePath: Int) { this.localCoverImagePath = localCoverImagePath }
    fun getLocalCoverImagePath(): Int { return localCoverImagePath }
    fun setStockCoverImagePath(stockCoverImagePath: String) { this.stockCoverImagePath = stockCoverImagePath }
    fun getStockCoverImagePath(): String { return stockCoverImagePath }
    fun setPrice(price: Int) { this.price = price }
    fun getPrice(): Int { return price}
    fun setRates(rates: Int) { this.rates = rates }
    fun getRates(): Int { return this.rates; }
}