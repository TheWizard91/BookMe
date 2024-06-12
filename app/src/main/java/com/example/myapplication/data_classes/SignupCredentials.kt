package com.example.myapplication.data_classes

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

data class SignupCredentials(
    var eml: String = "",
    var pwd: String = "",
    var confirmPwd: String = ""
) {

    internal fun isNotEmpty(): Boolean {
        return !TextUtils.isEmpty(eml) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(confirmPwd) && TextUtils.equals(pwd, confirmPwd)
    }

    fun newUserAuth(cxt: Context) {
        val newUserAuth: FirebaseAuth = FirebaseAuth.getInstance()
        newUserAuth.createUserWithEmailAndPassword(eml, pwd)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(cxt, "Account has been made.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(cxt, "Error: ${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun checkSignupCredentials(creds: SignupCredentials, cxt: Context) : Boolean {
        return if (creds.isNotEmpty()) {
            Toast.makeText(cxt, "Right Credentials $creds", Toast.LENGTH_SHORT).show()
            true
        } else {
            Toast.makeText(cxt, "Wrong Credentials", Toast.LENGTH_SHORT).show()
            false
        }
    }

}