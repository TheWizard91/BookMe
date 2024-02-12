package com.example.myapplication

class Book(id: Int, title: String, author: String, publicationYear: Int, genres: Array<String>, description: String, coverImage: String, localCoverImagePath: Int) {

    private var id: Int = 0
    private var title: String = ""
    private var author: String = ""
    private var publicationYear: Int = 0
    private var genres: Array<String>
    private var description: String = ""
    private var coverImage: String = ""
    private var localCoverImagePath: Int = 0

    init {
        this.id = id
        this.title = title
        this.author = author
        this.publicationYear = publicationYear
        this.genres = genres
        this.description = description
        this.coverImage = coverImage
        this.localCoverImagePath = localCoverImagePath
    }

    fun setId(id: Int) { this.id = id }
    fun getId(): Int { return this.id }
    fun setTitle(title: String) { this.title = title }
    fun getTitle(): String { return this.title }
    fun setAuthor(author: String) { this.author = author }
    fun getAuthor(): String { return this.author }
    fun setPublicationYear(publicationYear: Int) { this.publicationYear = publicationYear; }
    fun getPublicationYear(): Int { return this.publicationYear }
    fun setGenres(genres: Array<String>) { this.genres = genres }
    fun getGenres(): Array<String> { return this.genres }
    fun setDescription(description: String) { this.description = description }
    fun getDescription(): String { return this.description }
    fun setCoverImage(coverImage: String) { this.coverImage = coverImage }
    fun getCoverImage(): String { return this.coverImage }
    fun setLocalCoverImagePath(localCoverImagePath: Int) { this.localCoverImagePath }
    fun getLocalCoverImagePath(): Int { return this.localCoverImagePath }

}