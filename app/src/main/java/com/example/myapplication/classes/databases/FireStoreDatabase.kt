package com.example.myapplication.classes.databases

import androidx.core.net.toUri
import com.example.myapplication.classes.Book
import com.example.myapplication.data_classes.NewUserCredentials
import com.example.myapplication.logMessage
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreDatabase(var user: NewUserCredentials) {

    /**Firebase object.
     * pre: See constructor.
     * post: Set, modify, and delete tables in Firebase.*/

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

        /**Make the user to be set in cloud db.
         * pre: ---.
         * post: A new user is on the DB. */

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
        logMessage("userId",user.userId)
    }

    internal fun updateFirestoreDatabase () {

        /**Update User's info in Firebase.
         * pre: ---.
         * post: User's info is changed(updated.)*/

        cloudDB
            .collection("Users")
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

    private fun setLikedBookTable (book: Book) {

        /**This method is addLikes' helper.
         * It sets the table of the newly lied book.
         * pre: book is the clicked book.
         * post: A new book is added to the Likes table.*/

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

        cloudDB
            .document("Likes/${ user.userId }/${ user.firstname }' s/${ book.getId() }/")
            .set(mapOfBook)
    }
    
    internal fun likesCheck(book: Book, onLikeUpdateCallback: (Boolean) -> Unit) {

        /**Check if book is already liked.
         * pre: book is a book.
         * post: Like button will be red or black according to the lies table on db.*/
        
        cloudDB
            .document("Likes/${ user.userId }/${ user.firstname }' s/${ book.getId() }/")
            .get()
            .addOnSuccessListener {  documentSnapshot ->
                if (documentSnapshot.exists())
                    onLikeUpdateCallback(true)
                else
                    onLikeUpdateCallback(false)
            }
            .addOnFailureListener { }
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

        /**This method is the helper of likesHandler method.
         * pre: book is the liked book.
         * post: calling the helper method setLikedBookTable.*/

        setLikedBookTable(book)
    }

    private fun removeLike(book: Book) {

        /**This is likesHandler's helper.
         * pre: book is the book unliked.
         * post: Removed the liked book from DB.*/

        cloudDB
            .collection("Likes")
            .document(user.userId)
            .collection("${ user.firstname }' s")
            .document(book.getId().toString())
            .delete()
            .addOnSuccessListener { }
            .addOnFailureListener { }
    }

}