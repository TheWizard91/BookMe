package com.example.myapplication.data_classes

import android.net.Uri

data class StorageDB(
    var username: String = "",
    var uId: String = "",
    var uri: Uri?
)
