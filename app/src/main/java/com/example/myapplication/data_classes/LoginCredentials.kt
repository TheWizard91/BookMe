package com.example.myapplication.data_classes

import android.content.Context
import android.widget.Toast

data class LoginCredentials(
    var eml: String = "",
    var pwd: String = "",
) {

    private fun isNotEmpty(): Boolean {
        return eml != "" && pwd != ""
    }
    fun checkLoginCredentials(cxt: Context) : Boolean {
        return if (isNotEmpty()) {
            Toast.makeText(cxt, "Right Credentials", Toast.LENGTH_SHORT).show()
            true
        } else {
            Toast.makeText(cxt, "Wrong Credentials", Toast.LENGTH_SHORT).show()
            false
        }
    }
}
