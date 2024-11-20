package com.example.myapplication.classes

import android.util.Log
import com.example.myapplication.data_classes.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class User {

//    private val currentUser = FirebaseAuth.getInstance()
//    private val database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var user: UserInfo = UserInfo()

    private fun userQuery(userInfo: UserInfo, callback: (u: UserInfo) -> Unit) {
        callback(userInfo)
        Log.d("userInQueryCallb",userInfo.toString()) // good
        Log.d("isUser",user.toString()) // good
    }

    private fun firebaseDatabase() {
        val currentUser = FirebaseAuth.getInstance()
        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
        database.collection("Users")
            .document(currentUser.uid.toString())
            .get()
            .addOnCompleteListener {
                val u: UserInfo = UserInfo().copy(
                    firstname = it.result.data!!["firstname"].toString(),
                    lastname = it.result.data!!["lastname"].toString(),
                    email = it.result.data!!["email"].toString(),
                    phoneNumber = it.result.data!!["phone"].toString(),
                    gender = it.result.data!!["gender"].toString(),
                    moreInfo = it.result.data!!["moreInfo"].toString(),
                    uri = it.result.data!!["profileImage"].toString()
                )
                userQuery(u) { userValues ->
                    user = userValues
                }
            }
    }

    fun getUser() {
        /*TODO: Same issue as the one in the main activity --  can't retrieve data outside of the listener. Fix it.*/
        firebaseDatabase()
        Log.d("userInGetUser",firebaseDatabase().toString())//empty
    }

}
