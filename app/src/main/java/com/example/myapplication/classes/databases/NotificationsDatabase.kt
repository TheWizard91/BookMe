package com.example.myapplication.classes.databases

import android.os.Build
import com.example.myapplication.classes.Book
import com.example.myapplication.data_classes.NewUserCredentials
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NotificationsDatabase {


    // Notifications instance.
    private val realtimeDB = FirebaseDatabase.getInstance().getReference()
    private val notificationId: String = UUID.randomUUID().toString()

    internal fun setNotifications(
        message: String,
        user: NewUserCredentials
    ) {

        /**Create a new user in realtime db.
        * pre: newUserCredentials is a user.
        * post: New user added to realtime db.*/

        val dateFormatter: DateTimeFormatter? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        } else {
            null
        }

        val now: LocalDateTime? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            null
        }

        val date: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateFormatter!!.format(now)
        } else ({
            null
        }).toString()

        // Add notification of the new user to db.
        val m = HashMap<String, Any>()
        m["firstname"] = user.firstname
        m["lastname"] = user.lastname
        m["userId"] = user.userId
        m["userImageUri"] = user.userProfileImageImage.toString()
        m["message"] = message + " ${user.firstname} ${user.lastname}"
        m["date"] = date
//
//        // Notifications instance.
//        val realtimeDB = FirebaseDatabase.getInstance().getReference()
//        val notificationId: String = UUID.randomUUID().toString()
        realtimeDB.child("Notifications")
            .child(user.userId)
            .child(notificationId)
            .setValue(m)
    }

    internal fun setNotificationsDBForLikedBooks(
        book: Book,
        firstname: String,
        lastname: String,
        uid: String,
        userProfileImageURL: String
    ) {

        /**Create a new user in realtime db.
         * pre: newUserCredentials is a user.
         * post: New user added to realtime db.*/

        val dateFormatter: DateTimeFormatter? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        } else {
            null
        }
        val now: LocalDateTime? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            null
        }
        val date: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateFormatter!!.format(now)
        } else ({
            null
        }).toString()

        // Add notification of the new user to db.
        val m = HashMap<String, Any>()
        m["userProfileImageURL"] = userProfileImageURL
        m["firstname"] = firstname
        m["lastname"] = lastname
        m["userId"] = uid
        m["message"] = firstname +
                " " +
                lastname +
                " just liked " +
                book.getTitle()
        m["bookTitle"] = book.getTitle()
        m["bookImageUri"] = book.getCoverImage()
        m["date"] = date


//        // Notifications instance.
//        val realtimeDB = FirebaseDatabase.getInstance().getReference()
//        val notificationId: String = UUID.randomUUID().toString()
        realtimeDB.child("Notifications")
            .child(uid)
            .child(notificationId)
            .setValue(m)
    }

    internal fun isTheBookInNotificationsDB(
        book: Book,
        firstname: String,
        lastname: String,
        uid: String,
        userProfileImageURL: String
    ) {
        realtimeDB
            .child("Notifications")
            .child(uid)
            .addChildEventListener(
                object: ChildEventListener {

                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        snapshot.children.forEach { data ->
                            // Check book was in notifications already.
                            if ((data.key == "title") && (data.value == book.getTitle())) {
                                // Was it liked?
                                if ((data.key == "message") && (data.value == "$firstname $lastname just liked ${ book.getTitle() }")) {
                                    // Say that you removed it from the favorite list.

                                }
                            }
                        }
                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                        TODO("Not yet implemented")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
    }

}
