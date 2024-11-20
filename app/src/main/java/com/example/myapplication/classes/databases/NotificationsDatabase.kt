package com.example.myapplication.classes.databases

import android.os.Build
import com.example.myapplication.data_classes.NewUserCredentials
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class NotificationsDatabase {

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

        // Notifications instance.
        val realtimeDB = FirebaseDatabase.getInstance().getReference()
        val notificationId: String = UUID.randomUUID().toString()
        realtimeDB.child("Notifications")
            .child(user.userId)
            .child(notificationId)
            .setValue(m)
    }
}
