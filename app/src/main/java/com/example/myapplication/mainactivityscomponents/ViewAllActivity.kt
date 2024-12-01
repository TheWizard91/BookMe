package com.example.myapplication.mainactivityscomponents

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.classes.Book
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

class ViewAllActivity : ComponentActivity() {

    /** Activity of clicked book.
     * pre: ---.
     * post: Display the book chosen by the user and let them buy it.*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val mapOfUser: MutableMap<String, String> = mutableMapOf("uid" to FirebaseAuth.getInstance().uid.toString())
                // User info
                getUserInfoFromDatabase (mapOfUser["uid"].toString()) { userMap ->
                    mapOfUser.putAll(userMap)
                }

                MainViewOfViewAll(mapOfUser)
            }
        }
    }

    private fun getUserInfoFromDatabase (userId: String, callback: (MutableMap<String,String>) -> Unit) {
        val m: MutableMap<String, String> = emptyMap<String,String>().toMutableMap()
        val database: FirebaseFirestore = FirebaseFirestore.getInstance()

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
}


@Composable
private fun MainViewOfViewAll(mapOfUser: MutableMap<String, String>) {
    /**pre: ---.
     * post: modify the state of the screen based on user's activities.*/

    val context: Context = LocalContext.current
    val configuration = LocalConfiguration.current
    var state: Boolean by remember { mutableStateOf(false) }
    var clickedBook: Book by remember { mutableStateOf(Book()) }
    val userOwnedBooks = emptyList<Book>()//UserBooksList.listOfBooksOwnedByUser.toList()

    LaunchedEffect(Unit) {
        delay(1000L)
//        isDataLoaded = true
    }

    if (userOwnedBooks.isNotEmpty()) {
        InitialViewOfViewAll(context, configuration, userOwnedBooks) { changeState, book ->
            state = changeState
            clickedBook = book
        }
    } else {
        EmptyLibrary(context, mapOfUser)
    }
}

@Composable
private fun EmptyLibrary(context: Context, mapOfUser: MutableMap<String, String>) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { /* Handle the result if needed */ }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(8.dp)
                .height(100.dp)
                .fillMaxWidth(),
            text = "No Books Owned Yet \uD83D\uDC47...",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            color = Color.Magenta
        )
        Button(
            onClick = {
                val intent: Intent =
                    Intent(context, BestSellersActivity::class.java).apply {
                        // Pass data as an extra with the intent.
                        putExtra("PARENT_ACTIVITY" ,"MAIN_ACTIVITY")
                        putExtra("UID", mapOfUser["uid"])
                        putExtra("FIRSTNAME", mapOfUser["firstname"])
                        putExtra("Gender", mapOfUser["gender"])
                        putExtra("PHONE", mapOfUser["phone"])
                        putExtra(
                            "PROFILE_IMAGE_SERIAL_NUMBER",
                            mapOfUser["profileImageSerialNumber"]
                        )
                        putExtra("PROFILE_IMAGE", mapOfUser["profileImage"])
                        putExtra("MORE_INFO", mapOfUser["moreInfo"])
                        putExtra("EMAIL", mapOfUser["email"])
                        putExtra("LASTNAME", mapOfUser["lastname"])
                        putExtra(
                            "USER_PROFILE_IMAGE_URL",
                            mapOfUser["userProfileImageURL"]
                        )
                    }
                // Start the activity using the launcher.
                launcher.launch(intent)
            }
        ) {
            Text(text = "Buy Books")
        }
    }
}

@Composable
private fun InitialViewOfViewAll (
    context: Context,
    configuration: Configuration,
    userBooks: List<Book>,
    onChangeState: (b: Boolean, clickedOnBook: Book) -> Unit
) {
    /**pre: context is the screen context.
     *      configuration is ...
     *      userBooks is the list of books owned by the user.
     *      onChangeState to change the state of the item/s to display on screen.
     * post: ---.
     * */

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    var isClicked: Boolean by remember { mutableStateOf(true) }
    var pressed: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .size(screenWidth, screenHeight)
            .padding(all = 8.dp)
    ) {

        // Title of the fragment saying "All Your Views!"
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = "All Your Books!",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
        )

        // Section displaying the list of books and back button.
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),//.align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.TopCenter) {
            /*Component showing the list of books owned by the user.
            * List of books displayed vertically.*/
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),//.align(Alignment.Center),
                columns = GridCells.Adaptive(minSize = 128.dp)
            ) {
                // The list of books.
                items(userBooks) { book ->
                    BookCard(context, book) { clickBookImage ->
                        isClicked = !clickBookImage
                        onChangeState(isClicked, book)
                    }
                }
            }

            // Back button
            LargeButton(
                screenWidth,
                screenHeight,
                isPressed = pressed,
                onBack = { newState ->
                    pressed = newState
                    context.startActivity(Intent(context, MainActivity()::class.java))
                    (context as Activity).finish()
                }
            )

            // Back button using android's native back arrow.
            MyBackButton()
        }
    }

}

// Delete books at clicks.
@Composable
private fun ViewDeletedBooks (
    context: Context,
    book: Book
) {

    Text(
        text = book.getTitle()+" is deleted",
        color = Color.White,
        fontSize = 16.sp,
    )
}

// Show clicked book.
@Composable
private fun ViewBook(
    book: Book
) {
    ClickedBookCard(book)
}

@Composable
private fun ClickedBookCard(book: Book) {
    /**Displays clicked book in full screen.
     * pre: book is selected book info.
     * post: the clicked book will take the entire screen.*/

    Column (
        modifier = Modifier.padding(all = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card (
            shape = RoundedCornerShape(10),
            modifier = Modifier
                .padding(all = 8.dp)
                .height(400.dp)
                .fillMaxWidth(),
        ) {
            AsyncImage (
                model = book.getCoverImage(),
                contentDescription = "Book Image",
                placeholder = painterResource(id = book.getLocalCoverImagePath()),
                error = painterResource(id = R.drawable.book_image),
                alignment = Alignment.Center,
                modifier = Modifier
                    .size(width = 150.dp, height = 200.dp)
                    .clip(RectangleShape)
                    .clickable {

                    }
            )
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = book.getTitle(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "by "+book.getAuthor(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    LazyRow () {
                        items(count = 5) {
                            Icon(
                                Icons.Rounded.StarRate,
                                modifier = Modifier.padding(all = 8.dp),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Card (

            ) {
                Row (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Description",
                        fontSize = 32.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "$12",
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                        color = Color.Gray
                    )
                }
                Text(
//                        modifier = Modifier
//                            .verticalScroll(rememberScrollState())
//                            .horizontalScroll(rememberScrollState()),
                    text = book.getDescription(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                )
            }
        }
    }
}

@Composable
private fun MyBackButton(){
    val currentContext: Context = LocalContext.current
    var backIsPressed: Int by remember { mutableIntStateOf(0) }
    BackHandler(enabled = true, onBack = {
        backIsPressed++
        showMessage(currentContext, "is pressed:$backIsPressed")
        currentContext.startActivity(Intent(currentContext, MainActivity()::class.java))
        (currentContext as Activity).finish()
    })
}

@Composable
private fun LargeButton(
    screenWidth: Dp = 0.dp,
    screenHeight: Dp = 0.dp,
    onBack:(Boolean) ->Unit,
    isPressed: Boolean = false
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)
    LargeFloatingActionButton(
        modifier = Modifier
            .absoluteOffset(x = 100.dp, y = 550.dp)
            .shadow(2.dp, RoundedCornerShape(50),),
        onClick = { onBack(!isPressed) },
        shape = CircleShape,
    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Large floating action button")
    }
}

@Composable
private fun BookCard(
    context: Context,
    book: Book,
    onBookClicked: (clickBookImage: Boolean) -> Unit
) {
    /**pre: book is a book.
    * post: creating book view in horizontal list.*/

    var currentState: Boolean by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(all = 8.dp)
//            .align(Alignment.CenterHorizontally)
    ) {
        Card(
            shape = RoundedCornerShape(50)
        ) {
            AsyncImage(
                model = book.getCoverImage(),
                contentDescription = "Books Image",
                placeholder = painterResource(id = book.getLocalCoverImagePath()),
                error = painterResource(id = R.drawable.book_image),
                modifier = Modifier
                    .size(150.dp, 150.dp)
                    .clip(CircleShape)
                    .clickable(
                        onClick = {
                            currentState = !currentState
                            onBookClicked(currentState)
                            showMessage(
                                context = context,
                                message = "pressed book: ${book.getTitle()}"
                            )
                            logMessage("whatBook", book.getTitle())
                        }
                    ),
                )
            }
        }
}

private fun logMessage(tag: String, msg: String) {
    Log.d(tag, msg)
}

private fun showMessage(context: Context, message: String) {
    Toast.makeText(context,message, Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true)
@Composable
fun MainViewOfViewAllPreview() {
    MyApplicationTheme {
        MainViewOfViewAll(emptyMap<String, String>().toMutableMap())
//        MainViewOfViewAll(
//            Book(
//                id = 1,
//                title ="To Kill a Mockingbird",
//                author = "Harper Lee",
//                publicationYear = 1960,
//                genres = listOf("Fiction", "Classic"),
//                description = "\n"+"A classic novel depicting racial injustice in the American South.",
//                longDescription = "The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it, To Kill A Mockingbird became both an instant bestseller and a critical success when it was first published in 1960. " +
//                        "It went on to win the Pulitzer Prize in 1961 and was later made into an Academy Award-winning film, also a classic.\n" +
//                        "\n" +
//                        "Compassionate, dramatic, and deeply moving, To Kill A Mockingbird takes readers to the roots of human behavior - to innocence and experience, kindness and cruelty, love and hatred, humor and pathos. " +
//                        "Now with over 18 million copies in print and translated into forty languages, this regional story by a young Alabama woman claims universal appeal. " +
//                        "Harper Lee always considered her book to be a simple love story. Today it is regarded as a masterpiece of American literature.",
//                coverImage = "https://i.pinimg.com/564x/b3/b6/5b/b3b65b82c7820c5cbc15a5fef871d68d.jpg",
//                localCoverImagePath = R.drawable.to_kill_a_mockingbird,
//                price = Random.nextInt(10, 50)
//            ),
//            "sarah",
//            "kaidanow",
//            "a@a.com",
//            "Female",
//            "1",
//            "a beautiful 😍 🤩  woman",
//            "1000013835.jpg",
//            "content://media/picker/0/com.android.providers.media.photopicker/media/1000013835",
//            "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/WsMlp82jsBUb1RVZpbfFX8EvnWz1%2Fcurrent_profile_image%2Fcontent%3A%2Fmedia%2Fpicker%2F0%2Fcom.android.providers.media.photopicker%2Fmedia%2F1000013835.jpg?alt=media&token=a0aafcb7-0cc3-44bb-99f4-cf5e2a3714a6",
//            "WsMlp82jsBUb1RVZpbfFX8EvnWz1"
////            arrayOf<String>(
////                "WsMlp82jsBUb1RVZpbfFX8EvnWz1",
////                "sarah",
////                "Female",
////                "1",
////                "1000013835.jpg",
////                "content://media/picker/0/com.android.providers.media.photopicker/media/1000013835",
////                "a beautiful 😍 🤩  woman",
////                "a@a.com",
////                "kaidanow",
////                "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/WsMlp82jsBUb1RVZpbfFX8EvnWz1%2Fcurrent_profile_image%2Fcontent%3A%2Fmedia%2Fpicker%2F0%2Fcom.android.providers.media.photopicker%2Fmedia%2F1000013835.jpg?alt=media&token=a0aafcb7-0cc3-44bb-99f4-cf5e2a3714a6"
////            )
//            mutableMapOf<String, String>(
//                "uid" to "WsMlp82jsBUb1RVZpbfFX8EvnWz1",
//                "firstname" to "sarah",
//                "gender" to "Female",
//                "phone" to "1",
//                "profileImageSerialNumber" to "1000013835.jpg",
//                "profileImage" to "content://media/picker/0/com.android.providers.media.photopicker/media/1000013835",
//                "moreInfo" to "a beautiful 😍 🤩  woman",
//                "email" to "a@a.com",
//                "lastname" to "kaidanow",
//                "userProfileImageURL" to "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/WsMlp82jsBUb1RVZpbfFX8EvnWz1%2Fcurrent_profile_image%2Fcontent%3A%2Fmedia%2Fpicker%2F0%2Fcom.android.providers.media.photopicker%2Fmedia%2F1000013835.jpg?alt=media&token=a0aafcb7-0cc3-44bb-99f4-cf5e2a3714a6"
//            )
//        )
    }
}