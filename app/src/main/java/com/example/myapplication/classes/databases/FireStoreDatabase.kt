package com.example.myapplication.classes.databases

import android.util.Log
import androidx.core.net.toUri
import com.example.myapplication.classes.Book
import com.example.myapplication.data_classes.NewUserCredentials
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreDatabase(
    var user: NewUserCredentials
) {
    // This is needed for book table.
    constructor(
        firstname: String = "",
        lastname: String,
        email: String,
        gender: String,
        phone: String,
        moreInfo: String,
        profileImageSerialNumber: String,
        profileImage: String,
        userProfileImageURL: String,
        uid: String) : this (
        user = NewUserCredentials(
            userProfileImageURL.toUri(), profileImageSerialNumber, firstname,
            lastname, phone, gender, moreInfo, uid, email
        )
    )

    private val cloudDB = FirebaseFirestore.getInstance()

    internal fun setFirestoreDatabase() {
        // Make the user to be set in cloud db.
        val m: HashMap<String, Any> = HashMap()
        m["firstname"] = user.firstname
        m["lastname"] = user.lastname
        m["email"] = user.email
        m["phone"] = user.phoneNumber
        m["gender"] = user.gender
        m["moreInfo"] = user.moreInfoAboutUser
        m["profileImage"] = user.userProfileImageImage.toString()
        m["profileImageSerialNumber"] = user.userProfileImageNumber

        // Create user in cloud firebase.
        cloudDB.collection("Users")
            .document(user.userId)
            .set(m)
    }

    internal fun updateFirestoreDatabase () {
        cloudDB.collection("Users")
            .document(user.userId)
            .update(
                "firstname", user.firstname,
                "lastname", user.lastname,
                "moreInfo", user.moreInfoAboutUser,
                "phone", user.phoneNumber,
                "gender", user.gender,
                "profileImage", user.userProfileImageImage,
                "profileImageSerialNumber", user.userProfileImageNumber
            )
    }

    private fun setBookTable (book: Book) {
        val mapOfBook: Map<String, String> = mapOf(
            "id" to book.getId().toString(),
            "title" to book.getTitle(),
            "author" to book.getAuthor(),
            "publicationYear" to book.getPublicationYear().toString(),
            "genres" to book.getGenres().toString(),
            "description" to book.getDescription(),
            "coverImage" to book.getCoverImage(),
            "price" to book.getPrice().toString(),
            "description" to book.getDescription(),
            "longDescription" to book.getLongDescription(),
            "rates" to book.getRates().toString()
        )
        cloudDB.document("Likes/${book.getId()}/")
            .set(mapOfBook)
//            .addOnCompleteListener {
////                Log.d("apple5", "Book in db removing: ${book.getTitle()}")
//            }.addOnFailureListener { exception ->
//                Log.d("setBookError", exception.toString())
//            }
    }
    
    internal fun likesCheck(book: Book, onLikeUpdateCallback: (Boolean) -> Unit) {

        /**Check if book is already liked.
         * pre: book is a book.
         * post: like button will be red or black according to the lies table on db.*/
        
        cloudDB
            .document("Likes/${book.getId()}/")
            .get()
            .addOnSuccessListener {  documentSnapshot ->
                if (documentSnapshot.exists()) {
//                    Log.d("apple0", "Book already exists.")
                    onLikeUpdateCallback(true)
                } else {
                    onLikeUpdateCallback(false)
                }
            }.addOnFailureListener { exception -> 
                Log.d("likesCheckError",exception.toString())
                onLikeUpdateCallback(false)
            }
    }

    internal fun likesHandler(book: Book, likeStatus: Boolean, onLikeUpdateCallback: (Boolean) -> Unit) {

        /**Check if book is already liked.
         * pre: book is a book and uid is the user id.
         * post: book table is modified whether the user added or removed the like.*/

        when (likeStatus) {
            false -> {
                addLike(book)
                onLikeUpdateCallback(true)
            } else -> {
                removeLike(book)
                onLikeUpdateCallback(false)
            }
        }

    }

    private fun addLike(book: Book) {
        setBookTable(book)
    }

    private fun removeLike(book: Book) {
        cloudDB
            .collection("Likes")
            .document(book.getId().toString())
            .delete()
            .addOnSuccessListener {
                Log.d("apple4", "Removed book: ${book.getTitle()}")
            }.addOnFailureListener { exception ->
                Log.d("removeError", exception.toString())
            }
    }

}