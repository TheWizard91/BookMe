package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.classes.Book
import com.example.myapplication.classes.databases.StorageDatabase
import com.example.myapplication.classes.navclasses.NavigationBarView
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    /** Main activity of the app -- where it starts*/

    private lateinit var mapOfUser: MutableMap<String, String>
    private lateinit var mapOfNotifications: MutableMap<String, String>
    private lateinit var notificationsDatabase: DatabaseReference
    private lateinit var firebaseFirestoreInstance: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var storageDB: StorageDatabase
    //TODO: userId is not needed as uid C mapOfUser.
    private lateinit var userId: String

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseFirestoreInstance = FirebaseAuth.getInstance()
        storageDB = StorageDatabase()
        database = FirebaseFirestore.getInstance()
        notificationsDatabase = FirebaseDatabase.getInstance().getReference()
        mapOfUser = emptyMap<String,String>().toMutableMap()
        mapOfNotifications = emptyMap<String, String>().toMutableMap()
        userId = firebaseFirestoreInstance.uid.toString()
        mapOfUser["uid"] = firebaseFirestoreInstance.uid.toString()
        var listOfMaps: List<Map<String, String>> = emptyList<Map<String, String>>().toMutableStateList()
        var listOfMapsOfLikes: List<Map<String, String>> = emptyList<Map<String, String>>().toMutableStateList()
        var listOfBooksOwnedByTheUser: List<Map<String, String>> = emptyList<Map<String, String>>().toMutableStateList()
        var listOfAllBooks: List<Book> = emptyList<Book>().toMutableStateList()

        setContent {

            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                // User info
                getUserInfoFromDatabase { userMap ->
                    mapOfUser.putAll(userMap)
                    setStorageDatabase()
                }

                getBooksInRepository { books ->
//                    listOfAllBooks = books
                }
                // Notifications
                getNotifications {  listOfNotificationMaps ->
                    listOfMaps = listOfNotificationMaps
                }

                getListOfBooksOwnedByTheUser { books ->
                    listOfBooksOwnedByTheUser = books
                }

                // Likes info
                getLikesDataFromFirebaseDatabase { likes ->
                    listOfMapsOfLikes = likes
                }

                var isDataLoaded by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    delay(1000L)
                    isDataLoaded = true
                }

//                if (isDataLoaded)
//                    MainBody(mapOfUser, listOfMaps, userId, listOfMapsOfLikes)
//                else
//                    Loading()

            }
        }
    }

    private fun getBooksInRepository (onBooksInStoreRepositoryCallback: (List<Book>) -> Unit) {
//        var listOfAllBooks: List<Book> = emptyList<Book>().toMutableStateList()
        storageDB.getBooksInStore ("book_store_repository/books_in_store/books.json") { books ->
            onBooksInStoreRepositoryCallback(books)
        }
    }

    private fun getListOfBooksOwnedByTheUser(onMyLibraryBooksCallback: (List<Map<String, String>>) -> Unit) {
        database
            .collection("UserOwnedBooks")
            .get()
            .addOnSuccessListener { task ->
                if (task.isEmpty) {
                    val myEmptyList: List<Map<String, String>> = listOf()//emptyMap<String, String>().toMutableMap()
                    onMyLibraryBooksCallback(myEmptyList)
                } else {
                    val listOfMapsOfOwnedBooks: MutableList<MutableMap<String, String>> = emptyList<MutableMap<String, String>>().toMutableList()
                    val listOfKeys: MutableList<List<String>> = emptyList<List<String>>().toMutableList()
                    val listOfValues: MutableList<List<String>> = emptyList<List<String>>().toMutableList()

                }
            }
    }

    private fun getLikesDataFromFirebaseDatabase (onLikesInDatabaseCallback: (List<Map<String, String>>) -> Unit) {
        database
            .collection("Likes")
            .get()
            .addOnSuccessListener { task ->
                // Is the db empty
                if (task.isEmpty) {
                    val myEmptyList: List<Map<String, String>> = listOf()//emptyMap<String, String>().toMutableMap()
                    onLikesInDatabaseCallback(myEmptyList)
                } else { // The db is not empty.
                    val listOfMapsOfLikes: MutableList<MutableMap<String, String>> = emptyList<MutableMap<String, String>>().toMutableList()
                    val listOfKeys: MutableList<List<String>> = emptyList<List<String>>().toMutableList()
                    val listOfValues: MutableList<List<String>> = emptyList<List<String>>().toMutableList()

                    // Get all the keys.
                    task.forEach {  query ->
                        listOfKeys.add(query.data.keys.toList())
                    }
                    // Get all the values.
                    task.forEach {  query ->
                        listOfValues.add(query.data.values.toList() as List<String>)
                    }
                    // Make the map.
                    for (i in 0 until listOfKeys.size) {
                        val map: MutableMap<String, String> = emptyMap<String, String>().toMutableMap()
                        for (j in 0 until listOfValues[i].size) {
                            map[listOfKeys[i][j]] = listOfValues[i][j]
                        }
                        listOfMapsOfLikes.add(map)
                    }
                    onLikesInDatabaseCallback(listOfMapsOfLikes)
                }
            }
            .addOnFailureListener { exception ->
                logMessage("error", exception.toString())
            }
    }

    private fun getUserInfoFromDatabase (callback: (MutableMap<String,String>) -> Unit) {
        val m: MutableMap<String, String> = emptyMap<String,String>().toMutableMap()
        database
            .collection("Users")
            .document(userId)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result.data!!.mapValues { entryInMap ->
                        m[entryInMap.key] = entryInMap.value.toString()
                    }
                    callback(m)
                }
            }
    }

    private fun setStorageDatabase () {

        /** Set info to storage database.
         * pre:---.
         * post: Image added to storage db.*/

        storageDB.getUserProfileImageFromStorage(
            mapOfUser["uid"] +
                    "/current_profile_image" +
                    "/content:" + "/media" +
                    "/picker" + "/0" +
                    "/com.android.providers.media.photopicker" +
                    "/media/" + mapOfUser["profileImageSerialNumber"].toString()
        ) { url ->
            mapOfUser["userProfileImageURL"] = url
        }
    }

    private fun deleteNotifications() {
        notificationsDatabase.child("Notifications").removeValue()
    }

    private fun getNotifications (onNotificationsDatabaseCallback: (List<Map<String, String>>) -> Unit) {
        /***/

        val notificationsList: MutableList<Map<String, String>> = emptyList<Map<String, String>>().toMutableList()

        notificationsDatabase
            .child("Notifications")
            .child(userId)
            .orderByPriority()
            .addChildEventListener(
                object : ChildEventListener {

                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        var counterOfNotificationsInDB = 0
                        val notificationMap: MutableMap<String, String> = mutableMapOf("notificationId" to snapshot.key.toString())

                        // The following code gives you access to the objects in db.
                        snapshot.children.forEach { data ->
                            notificationMap[data.key.toString()] = data.value.toString()
                            counterOfNotificationsInDB += 1
                            if (counterOfNotificationsInDB == snapshot.childrenCount.toInt())
                                notificationsList.add(notificationMap)
                        }
                        onNotificationsDatabaseCallback(notificationsList)
                    }

                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
    //                    Log.d("A child has been changed!", "Yes")
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
    //                    Log.d("A child has been removed!", "Yes")
                    }

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
    //                    Log.d("A child has been moved!", "Yes")
                    }

                    override fun onCancelled(error: DatabaseError) {
    //                    Log.d("A child has been cancelled!", "Yes")
                    }
                }
            )
    }

}

@SuppressLint("AutoboxingStateCreation", "UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainBody(
    userInfo: MutableMap<String, String>,
    mapOfNotifications: List<Map<String, String>>,
    userId: String,
    listOfMapsOfLikes: List<Map<String, String>>
) {
    MainContent(userInfo, mapOfNotifications, userId, listOfMapsOfLikes)
}

@SuppressLint("RestrictedApi", "UnrememberedMutableState")
@Composable
fun MainContent(
    mapOfUser: MutableMap<String, String>,
    listOfNotifications: List<Map<String, String>>,
    userId: String,
    listOfMapsOfLikes: List<Map<String, String>>
) {
    /*pre:---.
    * post: this is the main view of the app.*/

    var user: MutableMap<String, String> by remember { mutableMapOf() }
    user = mapOfUser

    val cxt: Context = LocalContext.current

    val navigationBarView = NavigationBarView()
    navigationBarView.InitialViewOfNavigationBarView(
        cxt = cxt,
        mapOfUser = user,
        listOfNotifications = listOfNotifications,
        listOfMapsOfLikes = listOfMapsOfLikes,
        userId = userId,
    )
}

@Composable
private fun Loading () {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            text = "Loading...",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            color = Color.Magenta
        )
    }
}
fun logMessage(tag: String, msg: String) {
    Log.d(tag, msg)
}

fun showMessage(context: Context, message: String) {
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}

@Preview(name = "Top Bar Preview")
@Composable
fun PreviewMainBody () {
    MyApplicationTheme {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val map: MutableMap<String, String> = mutableMapOf()
            map["firstname"] = "Emmanuel"
            map["lastname"] = "Agyapong"
            map["email"] = "EmmanuelCodes@gmail.com"
            map["phoneNumber"] = "9293038986"
            map["gender"] = "Male"
            map["moreInfo"] = "An incoming software engineer"
            map["uri"] = "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/meAtBCC.jpg?alt=media&token=3d9b481c-9880-445f-939f-3b7bb60f3cbd"
            map["uid"] = "YXUBtBZd8vPgBJ9viOU4Z7OL1PR2"
            val notifications: MutableMap<String, String> = mutableMapOf(
                "id" to "0897fc10-8680-4ba0-8da1-1b468c07555d",
                "date" to "2024/10/23 13:46:24",
                "bookImageUri" to "https://i.pinimg.com/736x/70/15/41/70154164a2967cc25fbac089e855db38.jpg",
                "firstname" to "sarah",
                "message" to "sarah kaidanow just liked War and Peace",
                "userId" to "WsMlp82jsBUb1RVZpbfFX8EvnWz1",
                "bookTitle" to "War and Peace",
                "lastname" to "kaidanow"
            )
            val listOfNotifications: MutableList<Map<String, String>> = emptyList<Map<String, String>>().toMutableList()
            listOfNotifications.add(
                mapOf(
                    "notificationId" to "08541fae-fbde-4ed8-a139-adca0411ee54",
                    "bookImageUri" to "https://i.pinimg.com/564x/b3/b6/5b/b3b65b82c7820c5cbc15a5fef871d68d.jpg",
                    "bookTitle" to "To Kill a Mockingbird",
                    "date" to "2024/11/13 10:29:20",
                    "firstname" to "sarah",
                    "lastname" to "kaidanow",
                    "message" to "sarah kaidanow just liked To Kill a Mockingbird",
                    "userId" to "ayAebp8hE8NACQvgrJ4jj5FC0422",
                    "userProfileImageURL" to "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/ayAebp8hE8NACQvgrJ4jj5FC0422%2Fcurrent_profile_image%2Fcontent%3A%2Fmedia%2Fpicker%2F0%2Fcom.android.providers.media.photopicker%2Fmedia%2F1000002089.jpg?alt=media&token=db96e15e-3dd9-4d07-a5a1-a0b29062e8ef"
                )
            )
            listOfNotifications.add(
                mapOf(
                    "notificationId" to "986944bf-c7b4-4eee-86c4-31b542076122",
                    "bookImageUri" to "https://i.pinimg.com/736x/11/91/8f/11918f3c356159ed09856eb1664f9bff.jpg",
                    "bookTitle" to "The Great Gatsby",
                    "date" to "2024/11/13 11:12:29",
                    "firstname" to "sarah",
                    "lastname" to "kaidanow",
                    "message" to "sarah kaidanow just liked The Great Gatsby",
                    "userId" to "ayAebp8hE8NACQvgrJ4jj5FC0422",
                    "userProfileImageURL" to "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/ayAebp8hE8NACQvgrJ4jj5FC0422%2Fcurrent_profile_image%2Fcontent%3A%2Fmedia%2Fpicker%2F0%2Fcom.android.providers.media.photopicker%2Fmedia%2F1000002089.jpg?alt=media&token=db96e15e-3dd9-4d07-a5a1-a0b29062e8ef"
                )
            )
            MainBody(map, listOfNotifications, "YXUBtBZd8vPgBJ9viOU4Z7OL1PR2", listOfNotifications)
        }
    }
}