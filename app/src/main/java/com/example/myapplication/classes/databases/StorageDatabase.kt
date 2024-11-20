package com.example.myapplication.classes.databases

import android.net.Uri
import android.util.Log
import com.example.myapplication.classes.Book
import com.google.firebase.storage.FirebaseStorage
import kotlinx.serialization.json.Json

class StorageDatabase {

    /**Storage database reference. */

    private val storageDB = FirebaseStorage.getInstance().getReference()

    internal fun setImageToStorage (path: String, imageJpgFile: Uri) {
        /*Create a new user in cloud db.
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
        var jsonFileFromStorage = storageDB.child(path)//com.google.android.gms.tasks.zzw@c65e7c0
            .downloadUrl
            .addOnCompleteListener { taskUri ->
                if (taskUri.isSuccessful) {
                    val downloadUri: Uri = taskUri.result
                    Log.d("downloadUri", downloadUri.path.toString())
                    val fileUrlPath = downloadUri.toString()
                    Log.d("fileUrlPath",fileUrlPath)
//                    val jsonString =  readJSONFile()
                    val json = Json { ignoreUnknownKeys = true }
//                    val books = json.de
                } else {
                    Log.d("ErrorDownloadUri", "did not work")
                }
            }
    }

//    private fun readJsonFile(): String {
//        // If the JSON file is in the assets folder:
//        val inputStream = assets.open("data.json")
//        return inputStream.bufferedReader().use { it.readText() }
//
//        // If the JSON file is in the local file system:
//        // val file = File("path/to/your/file.json")
//        // return file.readText()
//    }

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