package com.example.myapplication.data_classes

import android.net.Uri

data class NewUserCredentials(
    var userProfileImageImage: Uri?,
    val userProfileImageNumber: String,
//    val profileImage: String,
    var firstname: String = "",
    var lastname: String = "",
    var phoneNumber: String = "",
    var gender: String = "",
    val moreInfoAboutUser: String,
    val userId: String,
    val email: String
)

