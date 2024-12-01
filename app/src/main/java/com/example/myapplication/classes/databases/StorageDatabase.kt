package com.example.myapplication.classes.databases

import android.net.Uri
import android.util.Log
import com.example.myapplication.classes.Book
import com.google.firebase.storage.FirebaseStorage

class StorageDatabase {

    /**Storage database reference. */

    private val storageDB = FirebaseStorage.getInstance().getReference()

    internal fun setImageToStorage (path: String, imageJpgFile: Uri) {
        /**Create a new user in cloud db.
        * pre: path is a uri path to where the image will be stored.
        *      imagePjgFIle is the file.
        * post: New file added to storage fb.*/

        storageDB.child(path).putFile(imageJpgFile)
    }

    internal fun deleteImageInStorage (path: String) {

        /**Deleting users profile image.
         * pre: path is the path to the users profile image.
         * post: user's profile image is deleted. */

        storageDB.child(path)
            .delete()
            .addOnSuccessListener {
                Log.d("the delete is successful", "yes")
            }
            .addOnFailureListener { exception ->
                Log.d("deleteHandlerError", exception.toString())
            }

    }

    internal fun getBooksInStore (path: String, onALlBooksCallback: (List<Book>) -> Unit) {
//        var jsonFileFromStorage = storageDB.child(path)//com.google.android.gms.tasks.zzw@c65e7c0
//            .downloadUrl
//            .addOnCompleteListener { taskUri ->
//                if (taskUri.isSuccessful) {
//                    try {
//                        val downloadUri: Uri = taskUri.result
//                        // /v0/b/bookme-dc582.appspot.com/o/book_store_repository/books_in_store/books.json
//                        Log.d("downloadUri", downloadUri.path.toString())
//                        // https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/book_store_repository%2Fbooks_in_store%2Fbooks.json?alt=media&token=be13bfb2-1237-4f6d-ab17-8477c3307e3b
//                        val fileUrlPath = downloadUri.toString()
//                        Log.d("fileUrlPath",fileUrlPath)
//                        // https:/firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/book_store_repository%2Fbooks_in_store%2Fbooks.json?alt=media&token=be13bfb2-1237-4f6d-ab17-8477c3307e3b
//                        val downloadedFile = File(fileUrlPath).readText()
//                        Log.d("downloadedFile",downloadedFile.toString())
////                    val json = Json { ignoreUnknownKeys = true }
////                    val books = json.decodeFromJsonElement()
//                    } catch (e: Exception) {
//                        Log.d("FileError", e.message.toString())
//                    }
//                } else {
//                    Log.d("ErrorDownloadUri", "did not work")
//                }
//            }
    }

    internal fun getUserProfileImageFromStorage(path: String, onSetUserProfileImageURLCallback: (String) -> Unit){

        /**Return user's profile image path in storage db.
         * pre: path is the path of the user's profile image in storage db.
         *      onSetUserProfileImageURLCallback is the callback to have the url to th map next method.*/

        storageDB.child(path)
            .downloadUrl
            .addOnSuccessListener { taskSnapshot ->
                onSetUserProfileImageURLCallback(taskSnapshot.toString())
            }.addOnFailureListener { exception ->
                Log.d("ErrorInStorage::: ", exception.message.toString())
            }
    }

}