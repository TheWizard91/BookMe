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
import com.example.myapplication.data_classes.BookData
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import java.io.BufferedReader
import java.io.File

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
        // var listOfAllBooks: List<BookData> = emptyList<BookData>().toMutableStateList()
        var listMapOfBooksInShoppingCart: List<Map<String, String>> = emptyList<Map<String, String>>().toMutableStateList()

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

                // Get books from storage
//                getBooksInRepository { books ->
////                    listOfAllBooks = books
//                }

                // Notifications
                getNotifications {  listOfNotificationMaps ->
                    listOfMaps = listOfNotificationMaps
                }

                // Books Owned View Info
                getListOfBooksOwnedByTheUser { books ->
                    listOfBooksOwnedByTheUser = books
                }

                // Likes info
                getLikesDataFromFirebaseDatabase (mapOfUser) { likes ->
                    listOfMapsOfLikes = likes
                }

                // Shopping Cart Info
                getBooksInShoppingCart { booksInShoppingCartDB ->
                    listMapOfBooksInShoppingCart = booksInShoppingCartDB
                }

                var isDataLoaded by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    delay(1000L)
                    isDataLoaded = true
                }

                if (isDataLoaded)
                    MainBody(mapOfUser, listOfMaps, userId, listOfMapsOfLikes, listMapOfBooksInShoppingCart)
                else
                    Loading()

            }
        }
    }

    private fun getBooksInShoppingCart(onBooksInShoppingCartDBCallback: (List<Map<String, String>>) -> Unit) {
        database
            .collection("ShoppingCart/${userId}/Books/")
            .get()
            .addOnSuccessListener { task ->
                if (task.isEmpty) {
                    val myEmptyList: List<Map<String, String>> = listOf()
                    onBooksInShoppingCartDBCallback(myEmptyList)
                } else {
                    val mapsOfBooksInShoppingCartDB: MutableList<MutableMap<String, String>> = emptyList<MutableMap<String, String>>().toMutableList()
                    val listOfKeys: MutableList<List<String>> = emptyList<List<String>>().toMutableList()
                    val listOfValues: MutableList<List<String>> = emptyList<List<String>>().toMutableList()

                    // Get all the keys.
                    task.forEach {  query ->
                        listOfKeys.add(query.data.keys.toList())
                    }

                    // Get all the values.
                    task.forEach { query ->
                        listOfValues.add(query.data.values.toList() as List<String>)
                    }

                    // Make the map.
                    for (i in 0 until listOfKeys.size) {
                        val map: MutableMap<String, String> = emptyMap<String, String>().toMutableMap()
                        for (j in 0 until listOfValues[i].size) {
                            map[listOfKeys[i][j]] = listOfValues[i][j]
                        }
                        mapsOfBooksInShoppingCartDB.add(map)
                    }

                    onBooksInShoppingCartDBCallback(mapsOfBooksInShoppingCartDB)
                }
            }
            .addOnFailureListener { exception ->
                logMessage("error", exception.toString())
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

    private fun getLikesDataFromFirebaseDatabase (
        mapOfUser: MutableMap<String, String>,
        onLikesInDatabaseCallback: (List<Map<String, String>>) -> Unit
    ) {
        database
            .collection("Likes")
            .document(mapOfUser["uid"].toString())
            .collection("Books")
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
    listOfMapsOfLikes: List<Map<String, String>>,
    listMapOfBooksInShoppingCart: List<Map<String, String>>
) {
    MainContent(userInfo, mapOfNotifications, userId, listOfMapsOfLikes, listMapOfBooksInShoppingCart)
}

@SuppressLint("RestrictedApi", "UnrememberedMutableState")
@Composable
fun MainContent(
    mapOfUser: MutableMap<String, String>,
    listOfNotifications: List<Map<String, String>>,
    userId: String,
    listOfMapsOfLikes: List<Map<String, String>>,
    listMapOfBooksInShoppingCart: List<Map<String, String>>
) {
    /*pre:---.
    * post: this is the main view of the app.*/

    var user: MutableMap<String, String> by remember { mutableMapOf() }
    user = mapOfUser

    val cxt: Context = LocalContext.current

    val navigationBarView = NavigationBarView()

    val booksInLibrary: MutableList<BookData> = emptyList<BookData>().toMutableList()
    readJsonFileFromTheAssetsFolder(cxt, "assets/books.json") { books->
//            bestSellerBookState[0]=books
    }

    navigationBarView.InitialViewOfNavigationBarView(
        cxt = cxt,
        mapOfUser = user,
        userId = userId,
        listOfNotifications = listOfNotifications,
        listOfMapsOfLikes = listOfMapsOfLikes,
        listMapOfBooksInShoppingCart = listMapOfBooksInShoppingCart,
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

private fun readJsonFileFromTheAssetsFolder (context: Context, path: String, onGetBooksFromJsonFileCallback: (List<BookData>) -> Unit) {
    // TODO: Cannot open the json file (not only locally, but in db too), figure out why.
    val identifier = "ReadJSON"
    try {

//        val file = context.assets.open(path)
//        logMessage(identifier, "$file")

//        val bufferReader = BufferedReader(InputStreamReader(file))

        val bufferReader: BufferedReader = File(path).bufferedReader()
        val inputString = bufferReader.use { it.readText() }
        logMessage("inputString",inputString)
        val booksInLibrary: MutableList<MutableMap<String, String>> = emptyList<MutableMap<String, String>>().toMutableList()

        bufferReader.useLines { lines ->

            var id = 0
            var title = ""
            var author = ""
            var publicationYear = 0
            val genres: MutableList<String> = emptyList<String>().toMutableList()
            var description = ""
            var longDescription = ""
            var coverImage = ""
            var localCoverImagePath = ""
            var stockCoverImagePath ="https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/book_store_repository%2Fstock_cover_image_path%2Fbook_image.png?alt=media&token=cc06abec-4490-4118-8a7e-d5ca0c2b753f"
            var price = 0
            var rates = 0
            val subList: MutableList<String> = emptyList<String>().toMutableList()
            val bookObject: MutableMap<String, String> = emptyMap<String, String>().toMutableMap()

            lines.forEach {  line ->
//                logMessage("lineIs",line.toString())
                if (line.contains("bookId")) {
                    id = line.split(":", ",")[1].toInt()
                    logMessage("myId", id.toString())
//                    subList.add(id)
//                    bookObject["id"] = id
                }
                if (line.contains("bookTitle")) {
                    title = line.split(":")[1]
                    logMessage("title", title)
//                    subList.add(title)
                    bookObject["title"] = title
                }
                if (line.contains("bookAuthor")) {
                    author = line.split(":", ",")[1]
                    logMessage("author", author)
                    subList.add(author)
                    bookObject["author"] = author
                }
                if (line.contains("bookPublicationYear")) {
                    publicationYear = line.split(":", ",")[1].toInt()
                    logMessage("publicationYear", publicationYear.toString())
//                    subList.add(publicationYear)
//                    bookObject["publicationYear"] = publicationYear
                }
                if (line.contains("bookGenres")) {
                    logMessage("genres", line.split(":").toString())
                    val split: List<String> = line.split(":","[","]",",")
                    split.forEach {  str ->
//                            logMessage("str",str)
                        if (str != "genres")
                            genres.add(str)
                    }
//                    subList.add(genres.toString())
                    bookObject["genres"] = genres.toString()
                }
                if (line.contains("bookDescription")) {
                    description = line.split(":")[1]
                    logMessage("description", description)
//                    subList.add(description)
                    bookObject["description"] = description
                }
                if (line.contains("bookLongDescription")) {
                    longDescription = line.split(":")[1]
                    logMessage("longDescription", longDescription)
//                    subList.add(longDescription)
                    bookObject["longDescription"] = longDescription
                }
                if (line.contains("bookCoverImage")) {
                    coverImage = line.split(":")[1]
                    logMessage("coverImage", coverImage)
//                    subList.add(coverImage)
                    bookObject["coverImage"] = coverImage
                }
                if (line.contains("localCoverImagePath")) {
                    localCoverImagePath = "0"
//                    logMessage("localCoverImagePath", line.split(":")[1])
//                    subList.add(localCoverImagePath)
                    bookObject["localCoverImagePath"] = localCoverImagePath
                }
                if (line.contains("bookStockCoverImagePath")) {
//                    logMessage("stockCoverImagePath", line.split(":")[1])
                    stockCoverImagePath = "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/book_store_repository%2Fstock_cover_image_path%2Fbook_image.png?alt=media&token=cc06abec-4490-4118-8a7e-d5ca0c2b753f"
//                    subList.add(stockCoverImagePath)
                    bookObject["stockCoverImagePath"] = stockCoverImagePath
                }
                if (line.contains("bookPrice")) {
                    price = line.split(":", ",")[1].toInt()
                    logMessage("price", price.toString())
//                    subList.add(price)
//                    bookObject["price"] = price
                }
                if (line.contains("bookRates")) {
                    rates = line.split(":")[1].toInt()
                    logMessage("rates", rates.toString())
//                    subList.add(rates)
//                    bookObject["rates"] = rates
                }
                booksInLibrary.add(bookObject)
//                    booksInLibrary.add(book)
            }
            val book = BookData(id, title, author, publicationYear, genres.toString(), description, longDescription, coverImage, localCoverImagePath, stockCoverImagePath, price, rates)
            logMessage("[bookList]", book.toString())
            logMessage("[bookObject]", bookObject.toString())
        }
//        Log.d(identifier, "$stringBuilder")
        logMessage("[superList]",booksInLibrary.toString())
    } catch (e: Exception) {
        // Online json file.
        // https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/book_store_repository%2Fbooks_in_store%2Fbooks.json?alt=media&token=be13bfb2-1237-4f6d-ab17-8477c3307e3b
        logMessage("[FAILEDOPENINGFILE]", e.message.toString())
        e.printStackTrace()
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
            val listMapOfBooksInShoppingCart: MutableList<Map<String, String>> = emptyList<Map<String, String>>().toMutableList()
            listMapOfBooksInShoppingCart.add(
                mapOf(
                    "longDescription" to "Austen's most popular novel, the unforgettable story of Elizabeth Bennet and Mr. Darcy Few have failed to be charmed by the witty and independent spirit of Elizabeth Bennet in Austen’s beloved classic Pride and Prejudice. " +
                            "When Elizabeth Bennet first meets eligible bachelor Fitzwilliam Darcy, she thinks him arrogant and conceited; he is indifferent to her good looks and lively mind. " +
                            "When she later discovers that Darcy has involved himself in the troubled relationship between his friend Bingley and her beloved sister Jane, she is determined to dislike him more than ever. " +
                            "In the sparkling comedy of manners that follows, Jane Austen shows us the folly of judging by first impressions and superbly evokes the friendships, gossip and snobberies of provincial middle-class life. " +
                            "This Penguin Classics edition, based on Austen's first edition, contains the original Penguin Classics introduction by Tony Tanner and an updated introduction and notes by Viven Jones. " +
                            "For more than seventy years, Penguin has been the leading publisher of classic literature in the English-speaking world. " +
                            "With more than 1,700 titles, Penguin Classics represents a global bookshelf of the best works throughout history and across.",
                    "author" to "Jane Austen",
                    "genres" to "[Classic, Romance]",
                    "price" to "36",
                    "coverImage" to "https://i.pinimg.com/564x/5c/12/6e/5c126ebc616400330587845a172c110d.jpg",
                    "rates" to "4",
                    "publicationYear" to "1813",
                    "description" to "A classic novel exploring themes of love, marriage, and social norms.",
                    "id" to "3",
                    "title" to "Pride and Prejudice"
                ),
//                mapOf(
//                    "longDescription" to "Anyone who has read J.D. Salinger's New Yorker stories--particularly A Perfect Day for Bananafish, Uncle Wiggily in Connecticut, The Laughing Man, and For Esme With Love and Squalor--will not be surprised by the fact that his first novel is full of children. The hero-narrator of The Catcher in the Rye is an ancient child of sixteen, a native New Yorker named Holden Caulfield. Through circumstances that tend to preclude adult, secondhand description, he leaves his prep school in Pennsylvania and goes underground in New York City for three days. The boy himself is at once too simple and too complex for us to make any final comment about him or his story. Perhaps the safest thing we can say about Holden is that he was born in the world not just strongly attracted to beauty but, almost, hopelessly impaled on it. There are many voices in this novel: children's voices, adult voices, underground voices-but Holden's voice is the most eloquent of all. Transcending his own vernacular, yet remaining marvelously faithful to it, he issues a perfectly articulated cry of mixed pain and pleasure. However, like most lovers and clowns and poets of the higher orders, he keeps most of the pain to, and for, himself. The pleasure he gives away, or sets aside, with all his heart. It is there for the reader who can handle it to keep.",
//                    "price" to "13",
//                    "author" to "J.D. Salinger",
//                    "genres" to "[Fiction, Coming-of-age]",
//                    "rates" to "2",
//                    "coverImage" to "https://i.pinimg.com/564x/4b/be/9c/4bbe9c695bd2c2005847108120df0612.jpg",
//                    "publicationYear" to "1951",
//                    "description" to "A classic coming-of-age novel following Holden Caulfield's journey.",
//                    "id" to "7",
//                    "title" to "The Catcher in the Rye",
//                )
            )
            MainBody(
                map,
                listOfNotifications,
                "YXUBtBZd8vPgBJ9viOU4Z7OL1PR2",
                listOfNotifications,
                listMapOfBooksInShoppingCart
            )
        }
    }
}