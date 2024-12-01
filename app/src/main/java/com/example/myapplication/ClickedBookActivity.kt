package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.classes.Book
import com.example.myapplication.classes.databases.FireStoreDatabase
import com.example.myapplication.classes.databases.NotificationsDatabase
import com.example.myapplication.mainactivityscomponents.BestSellersActivity
import com.example.myapplication.objects.SampleBook
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

class ClickedBookActivity : ComponentActivity() {

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

                val parentActivity = intent.getStringExtra("PARENT_ACTIVITY").toString()
                val bookName = intent.getStringExtra("BOOK_NAME").toString()
                val uid = intent.getStringExtra("UID").toString()
                val firstname = intent.getStringExtra("FIRSTNAME").toString()
                val gender = intent.getStringExtra("Gender").toString()
                val phone = intent.getStringExtra("PHONE").toString()
                val profileImageSerialNumber = intent.getStringExtra("PROFILE_IMAGE_SERIAL_NUMBER").toString()
                val profileImage = intent.getStringExtra("PROFILE_IMAGE").toString()
                val moreInfo = intent.getStringExtra("MORE_INFO").toString()
                val email = intent.getStringExtra("EMAIL").toString()
                val lastname = intent.getStringExtra("LASTNAME").toString()
                val userProfileImageURL = intent.getStringExtra("USER_PROFILE_IMAGE_URL").toString()
                val listOfBooks = SampleBook.books

                var clickedBook = Book()
                listOfBooks.forEach { book ->
                    if (book.getTitle() == bookName)
                        clickedBook = book
                }

                MainViewOfClickedBook(
                    book = clickedBook,
                    firstname = firstname,
                    lastname = lastname,
                    email = email,
                    gender = gender,
                    phone = phone,
                    moreInfo = moreInfo,
                    profileImageSerialNumber = profileImageSerialNumber,
                    profileImage = profileImage,
                    userProfileImageURL = userProfileImageURL,
                    uid = uid,
                    parentActivity = parentActivity
                )
            }
        }
    }
}

@Composable
private fun MainViewOfClickedBook (
    book: Book,
    firstname: String,
    lastname: String,
    email: String,
    gender: String,
    phone: String,
    moreInfo: String,
    profileImageSerialNumber: String,
    profileImage: String,
    userProfileImageURL: String,
    uid: String,
    parentActivity: String,
) {

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
//            .size(width = screenWidth, height = screenHeight)
            .padding(all = 8.dp)
    ) {
        BookView(
            book = book,
            firstname = firstname,
            lastname = lastname,
            email = email,
            gender = gender,
            phone = phone,
            moreInfo = moreInfo,
            profileImageSerialNumber = profileImageSerialNumber,
            profileImage = profileImage,
            userProfileImageURL = userProfileImageURL,
            uid = uid,
            parentActivity = parentActivity
        )
        MyTonalButton {}
    }
}

@Composable
fun MyTonalButton(onClickHandler: () -> Unit) {

    /** A buy button. */

    FilledTonalButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .height(50.dp),
        onClick = { onClickHandler() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = "Buy this book",
            fontSize = 24.sp,
        )
    }
}

@Composable
fun OnBackArrow (
    onBack: (Boolean) -> Unit,
    isPressed: Boolean = false
) {

    /** Lambda function of the back button.
     * pre: ---.
     * post: Brings user back to main activity. */

    Icon (
        imageVector = Icons.Filled.ChevronLeft,
        contentDescription = "Back button",
    )
}

@Composable
fun LargeButton(
    screenWidth: Dp = 0.dp,
    screenHeight: Dp = 0.dp,
    onBack: (Boolean) -> Unit,
    isPressed: Boolean = false
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)
    LargeFloatingActionButton(
        modifier = Modifier
            .absoluteOffset(x = screenWidth - 100.dp, y = screenHeight - 500.dp)
            .shadow(2.dp, RoundedCornerShape(50)),
        onClick = { onBack(!isPressed) },
        shape = CircleShape
    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Large floating action button")
    }
}

@Composable
private fun BuiltInBackButton() {
    val currentContext: Context = LocalContext.current
    var backIsPressed: Int by remember { mutableIntStateOf(0) }
    BackHandler (
        enabled = true,
        onBack = {
            backIsPressed++
            currentContext.startActivity(Intent(currentContext, MainActivity()::class.java))
            (currentContext as Activity).finish()
        }
    )
}

@Composable
private fun MyScrollingFunction(myText: String) {

    /** Scrolling down method for description.
     * pre: myText is the description.
     * post: Let user scrolling down.*/

    Text (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        text = myText,//book.getDescription(),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = Color.Gray
    )
}

@Composable
private fun BookView(
    book: Book,
    firstname: String,
    lastname: String,
    email: String,
    gender: String,
    phone: String,
    moreInfo: String,
    profileImageSerialNumber: String,
    profileImage: String,
    userProfileImageURL: String,
    uid: String,
    parentActivity: String
) {

    /** View of the activity.
     * pre: book is the clicked book.
     *      userInfoForFirebase is an array with the info about the user.
     *      uid: is book uid.
     * post: display the activity.*/

    val rnd = Random
    val context = LocalContext.current
    val myText: String = book.getLongDescription()
    val firestoreDB = FireStoreDatabase(firstname, lastname, email, gender, phone, moreInfo, profileImageSerialNumber, profileImage, userProfileImageURL, uid)
    val currentUser = FirebaseAuth.getInstance().currentUser
    val notifications = NotificationsDatabase()
    var pressed: Boolean by remember { mutableStateOf(false) }
    var likesButtonState: Boolean by remember { mutableStateOf(false) }
    var menuButtonState: Boolean by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
    ) {
        Card (
            modifier = Modifier
                .padding(all = 8.dp)
                .height(350.dp),
            shape = RoundedCornerShape(10),
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
                    .wrapContentHeight()
            ) {

                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { backToParentActivity(context, parentActivity) },
                ) {
                    Icon (
                        imageVector = Icons.Filled.ChevronLeft,
                        contentDescription = "Back button",
                    )
                }

                Spacer(modifier = Modifier.weight(3f))
                firestoreDB.likesCheck(book) { isBookLikedAlready ->
                    likesButtonState = isBookLikedAlready
                }
                notifications.isTheBookInNotificationsDB(book, firstname, lastname, uid, userProfileImageURL)

                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        // check the book if not liked already.
                        if (currentUser != null) {
                            firestoreDB.likesHandler(book, likesButtonState) { isLiked ->
                                likesButtonState = isLiked
                            }
                            /** TODO: Check if the book was already liked (already in real time bd) so that
                             *   wed can add/delete it from the real time db (things to do in the following method).*/
                            notifications.setNotificationsDBForLikedBooks(book, firstname, lastname, uid, userProfileImageURL)
                        }
                    }
                ) {
                    LikesButtonHandler(likesButtonState, book, firstname, lastname, uid)
                }

                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = { /*TODO: Handle search button.*/ }
                ) {

                    Icon (
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Back button"
                    )
                }

            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                   modifier = Modifier
                       .padding(
                           start = 0.dp,
                           top = 8.dp,
                           end = 0.dp,
                           bottom = 8.dp
                       ),
                   shape = RoundedCornerShape(percent = 10)
                ) {

                   AsyncImage(
                       modifier = Modifier
                           .size(100.dp, 150.dp)
                           .clip(RectangleShape)
                           .clickable(
                               onClick = { }
                           )
                           .shadow(1.dp),
                       model = book.getCoverImage(),
                       contentDescription = "Books Image",
                       placeholder = painterResource(id = book.getLocalCoverImagePath()),
                       error = painterResource(id = R.drawable.book_image),
                       alignment = Alignment.Center,
                   )

                }
            }

            Column (
                modifier = Modifier
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = book.getTitle(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                Text (
                    text = "by " + book.getAuthor(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    LazyRow {
                        items (book.getRates()) {
                            Icon (
                                Icons.Rounded.StarRate,
                                contentDescription = "Likes of book.",
                                tint = Color.White
                            )
                        }
                    }
                }

            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                shape = RoundedCornerShape(10),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Yellow,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.DarkGray
                ),
            ) {
                Column (
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(2f)
                                .padding(top = 8.dp, start = 8.dp),
                            text = "Description",
                            fontSize = 24.sp,
                        )
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 8.dp, end = 8.dp),
                            text = book.getPrice().toString(),
                            fontSize = 16.sp,
                            textAlign = TextAlign.End,
                        )
                    }
                }
                MyScrollingFunction(myText)
            }
        }
        Column {
            LazyRow (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(book.getGenres().size) { bookIndex ->
                    Card (
                        modifier = Modifier
                            .padding(all = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text (
                            modifier = Modifier
                                .padding(all = 8.dp),
                            text = book.getGenres()[bookIndex],
//                            color = Color.Gray,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LikesButtonHandler(
    likesButtonState: Boolean,
    book: Book,
    firstname: String,
    lastname: String,
    uid: String
) {

    /**This is for the app-visuals ONLY.
     * pre: book is the clicked book.
     *      firstname is firstname of the current user,
     *      lastname is the lastname of the current user,
     *      uid is the id of the current user.
     * post: Display the red on like button or removed it depending on whether it was there already.*/

    if (likesButtonState) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Back button",
            tint = Color.Red,
        )
    } else {
        Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Back button"
        )
    }
}

fun backToParentActivity(context: Context, parentActivity: String) {
    if (parentActivity == "MAIN_ACTIVITY") {
        context.startActivity(Intent(context, MainActivity()::class.java))
        (context as Activity).finish()
    } else if (parentActivity == "BEST_SELLERS_ACTIVITY") {
        context.startActivity(Intent(context, BestSellersActivity()::class.java))
        (context as Activity).finish()
    }
}

@Preview(showBackground = true)
@Composable
fun BookViewPreview() {
    MyApplicationTheme {
        MainViewOfClickedBook(
            Book(
                id = 1,
                title ="To Kill a Mockingbird",
                author = "Harper Lee",
                publicationYear = 1960,
                genres = listOf("Fiction", "Classic"),
                description = "\n"+"A classic novel depicting racial injustice in the American South.",
                longDescription = "The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it, To Kill A Mockingbird became both an instant bestseller and a critical success when it was first published in 1960. " +
                        "It went on to win the Pulitzer Prize in 1961 and was later made into an Academy Award-winning film, also a classic.\n" +
                        "\n" +
                        "Compassionate, dramatic, and deeply moving, To Kill A Mockingbird takes readers to the roots of human behavior - to innocence and experience, kindness and cruelty, love and hatred, humor and pathos. " +
                        "Now with over 18 million copies in print and translated into forty languages, this regional story by a young Alabama woman claims universal appeal. " +
                        "Harper Lee always considered her book to be a simple love story. Today it is regarded as a masterpiece of American literature.",
                coverImage = "https://i.pinimg.com/564x/b3/b6/5b/b3b65b82c7820c5cbc15a5fef871d68d.jpg",
                localCoverImagePath = R.drawable.to_kill_a_mockingbird,
                stockCoverImagePath = "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/book_store_repository%2Fstock_cover_image_path%2Fbook_image.png?alt=media&token=cc06abec-4490-4118-8a7e-d5ca0c2b753f",
                price = Random.nextInt(10, 50),
                rates = Random.nextInt(1, 5)
            ),
            "sarah",
            "kaidanow",
            "a@a.com",
            "Female",
            "1",
            "a beautiful 😍 🤩  woman",
            "1000013835.jpg",
            "content://media/picker/0/com.android.providers.media.photopicker/media/1000013835",
            "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/WsMlp82jsBUb1RVZpbfFX8EvnWz1%2Fcurrent_profile_image%2Fcontent%3A%2Fmedia%2Fpicker%2F0%2Fcom.android.providers.media.photopicker%2Fmedia%2F1000013835.jpg?alt=media&token=a0aafcb7-0cc3-44bb-99f4-cf5e2a3714a6",
            "WsMlp82jsBUb1RVZpbfFX8EvnWz1",
            ""
        )
    }
}