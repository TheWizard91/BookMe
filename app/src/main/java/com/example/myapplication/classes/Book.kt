package com.example.myapplication.classes

class Book(
    id: Int,
    title: String,
    author: String,
    publicationYear: Int,
    genres: Array<String>,
    description: String,
    coverImage: String,
    localCoverImagePath: Int,
    price: Int
) {
    private var id: Int = 0
    private var title: String = ""
    private var author: String = ""
    private var publicationYear: Int = 0
    private var genres: Array<String>
    private var description: String = ""
    private var coverImage: String = ""
    private var localCoverImagePath: Int = 0
    private var price: Int = 0

    init {
        this.id = id
        this.title = title
        this.author = author
        this.publicationYear = publicationYear
        this.genres = genres
        this.description = description
        this.coverImage = coverImage
        this.localCoverImagePath = localCoverImagePath
        this.price = price
    }

    fun setId(id: Int) { this.id = id }
    fun getId(): Int { return id }
    fun setTitle(title: String) { this.title = title }
    fun getTitle(): String { return title }
    fun setAuthor(author: String) { this.author = author }
    fun getAuthor(): String { return author }
    fun setPublicationYear(publicationYear: Int) { this.publicationYear = publicationYear; }
    fun getPublicationYear(): Int { return publicationYear }
    fun setGenres(genres: Array<String>) { this.genres = genres }
    fun getGenres(): Array<String> { return genres }
    fun setDescription(description: String) { this.description = description }
    fun getDescription(): String { return description }
    fun setCoverImage(coverImage: String) { this.coverImage = coverImage }
    fun getCoverImage(): String { return coverImage }
    fun setLocalCoverImagePath(localCoverImagePath: Int) { this.localCoverImagePath = localCoverImagePath }
    fun getLocalCoverImagePath(): Int { return localCoverImagePath }
    fun setPrice(price: Int) { this.price = price }
    fun getPrice(): Int { return price}
}