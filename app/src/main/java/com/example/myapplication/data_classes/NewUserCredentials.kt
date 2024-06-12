package com.example.myapplication.data_classes

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.widget.Toast

data class NewUserCredentials(
    var uImage: Uri?,
    var fname: String = "",
    var lname: String = "",
    var num: String = "",
    var gender: String = "",
    val moreTxt: String,
    val userId: String,
    val email: String
) {
    private fun isNotEmpty(): Boolean {
        return !TextUtils.isEmpty(fname) && !TextUtils.isEmpty(lname) && !TextUtils.isEmpty(moreTxt) && !TextUtils.isEmpty(num) && !TextUtils.isEmpty(gender)
    }

    fun checkNewUserCredentials(creds: SignupCredentials, context: Context) : Boolean {
        return if (creds.isNotEmpty()) {// && creds.loginOrSignUp == "admin"
//            context.startActivity(Intent(context, MainActivity::class.java))
            Log.d("creds",creds.toString())
//            (context as Activity).finish()
            true
        } else {
            Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
            false
        }
    }
}

