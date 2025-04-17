package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class BuyItNowActivity : AppCompatActivity() {

    /**Initialize everything in this activity.*/

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //                var mapOfUser: MutableMap<String, String> = emptyMap<String, String>().toMutableMap()
                    //                var firebaseFirestoreInstance: FirebaseAuth = FirebaseAuth.getInstance()
                    //                var database: FirebaseFirestore = FirebaseFirestore.getInstance()
                    //                mapOfUser["uid"] = firebaseFirestoreInstance.uid.toString()
//                    var mapOfUser: Map<String, String> = mapOf<String, String>(
//                        "uid" to intent.getStringExtra("UID").toString(),
//                        "firstname" to intent.getStringExtra("FIRSTNAME").toString(),
//                        "gender" to intent.getStringExtra("Gender").toString(),
//                        "phone" to intent.getStringExtra("PHONE").toString(),
//                        "profileImageSerialNumber" to intent.getStringExtra("PROFILE_IMAGE_SERIAL_NUMBER")
//                            .toString(),
//                        "profileImage" to intent.getStringExtra("PROFILE_IMAGE").toString(),
//                        "moreInfo" to intent.getStringExtra("MORE_INFO").toString(),
//                        "email" to intent.getStringExtra("EMAIL").toString(),
//                        "lastname" to intent.getStringExtra("LASTNAME").toString(),
//                        "userProfileImageURL" to intent.getStringExtra("USER_PROFILE_IMAGE_URL")
//                            .toString()
//                    )
//                    var book: Map<String, String> = mapOf<String, String>(
//                        "id" to intent.getStringExtra("ID").toString(),
//                        "title" to intent.getStringExtra("TITLE").toString(),
//                        "author" to intent.getStringExtra("AUTHOR").toString(),
//                        "publicationYear" to intent.getStringExtra("PUBLICATION_YEAR").toString(),
//                        "genres" to intent.getStringExtra("GENRES").toString(),
//                        "description" to intent.getStringExtra("DESCRIPTION").toString(),
//                        "longDescription" to intent.getStringExtra("LONG_DESCRIPTION").toString(),
//                        "coverImage" to intent.getStringExtra("COVER_IMAGE").toString(),
//                        "localCoverImagePath" to intent.getStringExtra("LOCAL_COVER_IMAGE_PATH")
//                            .toString(),
//                        "stockCoverImagePath" to intent.getStringExtra("STOCK_COVER_IMAGE_PATH")
//                            .toString(),
//                        "price" to intent.getStringExtra("PRICE").toString(),
//                        "rates" to intent.getStringExtra("RATES").toString()
//                    )
                }
            }
        }
    }
}

@SuppressLint("AutoboxingStateCreation", "UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
private fun MainBodyOfBuyItNow () {
    Text(text = "Buy it Now")

}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(name = "Buy it now preview")
@Composable
fun PreviewMainBodyOfBuyItNow () {
    MyApplicationTheme {
        MainBodyOfBuyItNow()
    }
}