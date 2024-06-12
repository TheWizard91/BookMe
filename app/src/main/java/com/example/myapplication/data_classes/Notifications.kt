package com.example.myapplication.data_classes

import android.net.Uri

data class Notifications(
    val fname: String,
    val lname: String,
    val uId: String,
    val userImageUri: Uri?,
    val welcome: String,
    val date: String
)
