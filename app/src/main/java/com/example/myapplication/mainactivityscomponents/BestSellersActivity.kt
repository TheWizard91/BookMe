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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.ClickedBookActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.classes.Book
import com.example.myapplication.mainactivityscomponents.ui.theme.BookMeTheme
import com.example.myapplication.objects.SampleBook

class BestSellersActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

                val bookName = intent.getStringExtra("BOOK_NAME")
                val allBooks = SampleBook.books

                val mapOfUser: Map<String, String> = mapOf(
                    "uid" to intent.getStringExtra("UID").toString(),
                    "firstname" to intent.getStringExtra("FIRSTNAME").toString(),
                    "gender" to intent.getStringExtra("Gender").toString(),
                    "phone" to intent.getStringExtra("PHONE").toString(),
                    "profileImageSerialNumber" to intent.getStringExtra("PROFILE_IMAGE_SERIAL_NUMBER").toString(),
                    "profileImage" to intent.getStringExtra("PROFILE_IMAGE").toString(),
                    "moreInfo" to intent.getStringExtra("MORE_INFO").toString(),
                    "email" to intent.getStringExtra("EMAIL").toString(),
                    "lastname" to intent.getStringExtra("LASTNAME").toString(),
                    "userProfileImageURL" to intent.getStringExtra("USER_PROFILE_IMAGE_URL").toString()
                )

                logMessage("noci",mapOfUser.toString())

                var clickedBook = Book()
                allBooks.forEach { book ->
                    if (book.getTitle() == bookName)
                        clickedBook = book
                }

                MainViewOfBestSellers(mapOfUser)
            }
        }
    }
}

@Composable
private fun MainViewOfBestSellers(mapOfUser: Map<String, String>) {
    val context: Context = LocalContext.current
    val configuration = LocalConfiguration.current
    var state: Boolean by remember { mutableStateOf(false) }
    var clickedBook: Book by remember { mutableStateOf(Book()) }

    InitialViewOfBestSellers(context, configuration, mapOfUser) { changeState, book ->
        state = changeState
        clickedBook = book
    }
}

@Composable
fun InitialViewOfBestSellers(
    context: Context,
    configuration: Configuration,
    mapOfUser: Map<String, String>,
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
    val allBooks = SampleBook.books

    var isClicked: Boolean by remember { mutableStateOf(true) }
    var pressed: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
            .size(screenWidth, screenHeight)
            .padding(all = 8.dp)
    ) {

        // Title of the fragment saying "All Your Views!"
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = "Books in The Store.",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
        )

        // Section displaying the list of books and back button.
        Box (
            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(),
//                .background(Color.Black.copy(alpha = .1f))
            ,
            contentAlignment = Alignment.TopCenter
        ) {
            /*Component showing the list of books owned by the user.
            * List of books displayed vertically while making a grid that will fit them
            * horizontally (i.e. we have 2 books per row, but could have 3  and more
            * depending the sizes and margins.)*/
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp),
                columns = GridCells.Adaptive(minSize = 128.dp),
                content = {
                    items (allBooks.size) { idx ->
                        Card (
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                        ) {
                            BookCard(context, allBooks[idx], mapOfUser) { clickBookImage ->
                                isClicked = !clickBookImage
                                onChangeState(isClicked, allBooks[idx])
                            }
                        }
                    }
                }
            )
//            {
//                // The list of books.
//                items(allBooks) { book ->
//                    BookCard(context, book, mapOfUser) { clickBookImage ->
//                        isClicked = !clickBookImage
//                        onChangeState(isClicked, book)
//                    }
//                }
//            }

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

@Composable
private fun BookCard(
    context: Context,
    book: Book,
    mapOfUser: Map<String, String?>,
    onBookClicked: (clickBookImage: Boolean) -> Unit
) {
    /**pre: book is a book.
     * post: creating book view in horizontal list.*/

    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)
    val offsetInPx = with(LocalDensity.current) { 0.dp.roundToPx() }

    var currentState: Boolean by remember {
        mutableStateOf(false)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { /* Handle the result if needed */ }

    Column(modifier = Modifier.padding(all = 8.dp)) {
        Card(shape = RoundedCornerShape(percent = 10)) {
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(book.getCoverImage())
                    .crossfade(true)
                    .build(),
                contentDescription = "Books Image",
                placeholder = painterResource(id = book.getLocalCoverImagePath()),
                error = painterResource(id = R.drawable.book_image),
                modifier = Modifier
                    .size(150.dp, 200.dp)
                    .clip(RectangleShape)
                    .clickable(
                        onClick = {
                            currentState = !currentState
                            onBookClicked(currentState)
                            showMessage(
                                context = context,
                                message = "pressed book: ${book.getTitle()}"
                            )
                            val intent: Intent =
                                Intent(context, ClickedBookActivity::class.java).apply {
                                    // Pass data as an extra with the intent.
                                    putExtra("PARENT_ACTIVITY", "BEST_SELLERS_ACTIVITY")
                                    putExtra("BOOK_NAME", book.getTitle())
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
                            logMessage("whatBook", book.getTitle())
                        }
                    )
                    .border(border = BorderStroke(2.dp, Color.White), shape = RectangleShape),
                colorFilter = ColorFilter.colorMatrix(matrix),
            )
            /*TODO: There is an extra layer because of the last text element which is for the price.
            *  The idea was to mimic what I've done in BestSellersView.kt, however, I was not able to
            * because right underneath each book there is an extra space of the card displaying (the last text)
            * that should not be there. I might have to change the entire UI of the card is I can display the price
            * w/t compromising the beauty of the card.*/

            Text(
                text = book.getTitle(),
                fontSize = 8.sp,
                textAlign = TextAlign.Center,
            )

            Text(
                text = book.getAuthor(),
                fontSize = 8.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        // Textview for the price.
//        Text(
//            text = "${book.getPrice()}$",
//            modifier = Modifier
//                .size(width = 30.dp, height = 30.dp)
//                .offset { IntOffset(-(offsetInPx-100), (offsetInPx-500)) }
//                .shadow(2.dp,RoundedCornerShape(50))
//                .align(Alignment.CenterHorizontally)
//                .background(Color.Yellow),
//            textAlign = TextAlign.Center,
//            color = Color.Black,
//            fontSize = 16.sp
//        )
    }
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

private fun logMessage(tag: String, msg: String) {
    Log.d(tag, msg)
}

private fun showMessage(context: Context, message: String) {
    Toast.makeText(context,message, Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true)
@Composable
fun BestSellersActivityPreview() {
    BookMeTheme {
        MainViewOfBestSellers(
            mapOf(
                "firstname" to "Emmanuel",
                "lastname" to "Agyapong",
                "email" to "EmmanuelCodes@gmail.com",
                "phoneNumber" to "9293038986",
                "gender" to "Male",
                "moreInfo" to "An incoming software engineer",
                "uri" to "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/meAtBCC.jpg?alt=media&token=3d9b481c-9880-445f-939f-3b7bb60f3cbd",
                "uid" to "YXUBtBZd8vPgBJ9viOU4Z7OL1PR2",
            )
        )
    }
}