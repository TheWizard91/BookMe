package com.example.myapplication.classes.databases

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.myapplication.data_classes.SignupCredentials
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class Authentication(private val signupCredentials: SignupCredentials) {

    private fun isNotEmpty(): Boolean {
        return !TextUtils.isEmpty(signupCredentials.eml) &&
                !TextUtils.isEmpty(signupCredentials.pwd) &&
                !TextUtils.isEmpty(signupCredentials.confirmPwd) &&
                TextUtils.equals(signupCredentials.pwd, signupCredentials.confirmPwd)
    }

    internal fun newUserAuth(cxt: Context) {
        /**Create first user user in authentication.
         * pre: cxt is the context.
         * post: New user created (not table, but email and pwd are there).*/

        val newUserAuth: FirebaseAuth = FirebaseAuth.getInstance()
        newUserAuth.createUserWithEmailAndPassword(signupCredentials.eml, signupCredentials.confirmPwd)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("SuccessIs:", it.exception.toString() + Toast.LENGTH_SHORT.toString())
                    Toast.makeText(cxt, "Account has been made.", Toast.LENGTH_SHORT).show()
                } else {
                    Log.d("ErrorIs:", it.exception.toString() + Toast.LENGTH_SHORT.toString())
                    Toast.makeText(cxt, "Error: ${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    internal fun updateUserAuth(newEmail: String, newPassword: String) {
        // Updating author's email and password.
        val userAuthorized = FirebaseAuth.getInstance();
        val currentUser: FirebaseUser = userAuthorized.currentUser!!
        currentUser.updateEmail(newEmail)
        currentUser.updatePassword(newPassword)
    }

//    private fun isNotEmpty(): Boolean {
//        return !TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(lastname) && !TextUtils.isEmpty(moreInfoAboutUser) && !TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(gender)
//    }

    internal fun checkSignupCredentials(cxt: Context) : Boolean {
        /**Check every credential ok.
         * pre: cxt is the context.
         * post: ---*/
        return if (isNotEmpty()) {
            Toast.makeText(cxt, "Right Credentials $signupCredentials", Toast.LENGTH_SHORT).show()
            true
        } else {
            Toast.makeText(cxt, "Wrong Credentials", Toast.LENGTH_SHORT).show()
            false
        }
    }
}