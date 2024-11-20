//package com.example.myapplication.test.ui
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.content.res.Configuration
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.BackHandler
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.absoluteOffset
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.rounded.StarRate
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.LargeFloatingActionButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorMatrix
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.AsyncImage
//import com.example.myapplication.MainActivity
//import com.example.myapplication.R
//import com.example.myapplication.classes.Book
//import com.example.myapplication.objects.UserBooksList
//import com.example.myapplication.ui.theme.MyApplicationTheme
//import kotlin.random.Random
//
//class ViewAllActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    ViewAllBooks(userBooks = UserBooksList.listOfBooksOwnedByUser.toList())
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun ViewAllBooks(userBooks: List<Book>) {
//    val context: Context = LocalContext.current
//    val configuration = LocalConfiguration.current
//    var state: Boolean by remember { mutableStateOf(false) }
//    var clickedBook: Book by remember { mutableStateOf(Book()) }
//
//    if (state) {
//        InitialViewOfViewAll(context, configuration, userBooks) { changeState, book ->
//            state = changeState
//            clickedBook = book
//        }
//    } else {
//        val sampleBook: Book = Book(
//        id = 47,
//        title = "The Call of the Wild",
//        author = "Jack London",
//        publicationYear = 1903,
//        genres = listOf("Adventure","Nature"),
//        description = "An adventure novel about a domestic dog's life in the wilds of the Yukon.",
//        longDescription = "Rediscover one of literature’s most beloved classics, richly reissued in a pivotal new audio recording. " +
//                "Emmy and Tony Award-nominated actor Pablo Schreiber (The Wire, Orange Is the New Black) delivers a stirring performance of Jack London’s fierce yet tender tale of loyalty between man and beast, told from the point of view of a dog.\n" +
//                "\n" +
//                "The Call of the Wild remains one of London’s best-loved novels, often regarded as the crowning masterpiece of a writer whose enduring popularity and prolific output hold a unique place in American literature.\n" +
//                "\n" +
//                "On its face, the novel is the story of Buck, a dog stolen from his comfortable home on a California ranch and shipped to the frozen Yukon to be a trained as a sled dog. " +
//                "But this exhilarating tale of a canine hero’s fight for survival is in fact a vivid depiction of the great gold rush to the Klondike in 1897. " +
//                "Brutal and fierce on one hand, this iconic adventure story is also a heart-warming tale of the tenderness and loyalty between humans and animals, brilliantly told from the latter’s perspective.\n" +
//                "\n" +
//                "Narrator Schreiber, whose previous performance of Brett Easton Ellis’ American Psycho has endeared him to listeners, brings a classic American tale to the ears - and hearts - of a new generation.",
//        coverImage = "https://i.pinimg.com/564x/5f/99/6c/5f996cd2f6d632c1039f586f3d8d1d6d.jpg",
//        localCoverImagePath = R.drawable.the_call_of_the_wild,
//        price = Random.nextInt(10, 50)
//        )
//        ClickedBookCard(book = sampleBook)
//    }
//}
//
//@Composable
//private fun InitialViewOfViewAll(context: Context, configuration: Configuration, userBooks: List<Book>, onChangeState: (b: Boolean, clickedOnBook: Book) -> Unit) {
//
//    /**pre: context is the screen context.
//     *      configuration is ...
//     *      userBooks is the list of books owned by the user.
//     *      onChangeState to change the state of the item/s to display on screen.
//     * post: ---.
//     * */
//
////        val context: Context = LocalContext.current
////        val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp.dp
//    val screenWidth = configuration.screenWidthDp.dp
//
////        var bookTitle: String by remember { mutableStateOf("") }
////        var bookDescription:String by remember{ mutableStateOf("") }
////        var bookImage: String by remember { mutableStateOf("") }
////        var bookGenres = mutableListOf<String>()
//    var isClicked: Boolean by remember { mutableStateOf(true) }
//    var pressed: Boolean by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .size(screenWidth, screenHeight)
//            .padding(all = 8.dp)
//    ) {
//
//        // Title of the fragment saying "All Your Views!"
//        Text(
//            modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth(),
//            text = "All Your Books!",
//            textAlign = TextAlign.Center,
//            fontSize = 32.sp,
//        )
//
//        // Section displaying the list of books and back button.
//        Box (
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(),
//            contentAlignment = Alignment.TopCenter) {
//            /*Component showing the list of books owned by the user.
//            * List of books displayed vertically.*/
//            LazyVerticalGrid(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(),
//                columns = GridCells.Adaptive(minSize = 128.dp)
//            ) {
//                // The list of books.
//                items(userBooks) { book ->
//                    BookCard(LocalContext.current, book) { clickBookImage ->
//                        isClicked = !clickBookImage
//                        onChangeState(isClicked, book)
//                    }
//                }
//            }
//
//            // Back button
//            LargeButton(
//                screenWidth,
//                screenHeight,
//                isPressed = pressed,
//                onBack = { newState ->
//                    pressed = newState
//                    context.startActivity(Intent(context, MainActivity()::class.java))
//                    (context as Activity).finish()
//                }
//            )
//
//            // Back button using android's native back arrow.
//            MyBackButton()
//        }
//    }
//}
//
//@Composable
//private fun BookCard(
//    context: Context,
//    book: Book,
//    onBookClicked: (clickBookImage: Boolean) -> Unit
//) {
//    /**pre: book is a book.
//     * post: creating book view in horizontal list.*/
//
//    var currentState: Boolean by remember {
//        mutableStateOf(false)
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .padding(all = 8.dp)
//    ) {
//        Card(shape = RoundedCornerShape(10)) {
//            AsyncImage(
//                model = book.getCoverImage(),
//                contentDescription = "Books Image",
//                placeholder = painterResource(id = book.getLocalCoverImagePath()),
//                error = painterResource(id = R.drawable.book_image),
//                modifier = androidx.compose.ui.Modifier
//                    .size(150.dp, 200.dp)
//                    .clip(RectangleShape)
//                    .clickable(
//                        onClick = {
//                            currentState = !currentState
//                            onBookClicked(currentState)
//                            showMessage(
//                                context = context,
//                                message = "pressed book: ${book.getTitle()}"
//                            )
//                            logMessage("whatBook", book.getTitle())
//                        }
//                    ),
//            )
//        }
//    }
//}
//
//@Composable
//fun ClickedBookCard(book: Book) {
//    /**Displays clicked book in full screen.
//    * pre: book is selected book info.
//    * post: the clicked book will take the entire screen.*/
//    Column (
//        modifier = Modifier.padding(all = 8.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Card (
//            shape = RoundedCornerShape(10),
//            modifier = Modifier
//                .padding(all = 8.dp)
//                .fillMaxWidth(),
//        ) {
//            AsyncImage (
//                model = book.getCoverImage(),
//                contentDescription = "Book Image",
//                placeholder = painterResource(id = book.getLocalCoverImagePath()),
//                error = painterResource(id = R.drawable.book_image),
//                alignment = Alignment.Center,
//                modifier = Modifier
//                    .size(width = 150.dp, height = 200.dp)
//                    .clip(RectangleShape)
//                    .clickable {
//
//                    }
//            )
//            Column (
////                modifier = Modifier.fillMaxWidth(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                Text(
//                    text = book.getTitle(),
//                    fontSize = 16.sp,
//                    textAlign = TextAlign.Center,
//                    color = Color.White
//                )
//                Text(
//                    text = "by "+book.getAuthor(),
//                    fontSize = 16.sp,
//                    textAlign = TextAlign.Center,
//                    color = Color.Gray
//                )
//                Row (
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center,
//                ) {
//                    LazyRow () {
//                        items(5) {
//                            Icon(Icons.Rounded.StarRate, contentDescription = "")
//                        }
//                    }
//                }
//            }
//        }
//        Column (
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Card (
//
//            ) {
//                Row (
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text(
//                        text = "Description",
//                        fontSize = 24.sp,
//                        color = Color.Black
//                    )
//                    Text(
//                        text = "$12",
//                        fontSize = 16.sp,
//                        textAlign = TextAlign.End,
//                        color = Color.Gray
//                    )
//                }
//                Text(
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
//                        .horizontalScroll(rememberScrollState()),
//                    text = book.getDescription(),
//                    fontSize = 16.sp,
//                    textAlign = TextAlign.Center,
//                    color = Color.Black,
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun ViewBook(
//    context: Context,
//    bookTitle: String,
//    bookDescription: String,
//    bookImage: String,
//    bookGenre: MutableList<String>
//) {
//    Text(text = "In Single Book", color = Color.White, fontSize = 16.sp)
//}
//
//@Composable
//private fun MyBackButton() {
//    // val context: AppCompatActivity? = LocalContext.current.getActivity()
//    val currentContext: Context = LocalContext.current
//    var backIsPressed: Int by remember { mutableIntStateOf(0) }
//    BackHandler(enabled = true, onBack = {
//        backIsPressed++
//        showMessage(currentContext, "is pressed:$backIsPressed")
////            context?.finish()
//        currentContext.startActivity(Intent(currentContext, MainActivity()::class.java))
//        (currentContext as Activity).finish()
//    })
//}
//
////    fun Context.getActivity(): AppCompatActivity? = when (this) {
////        is AppCompatActivity -> this
//////        is ContextWrapper -> baseContext.getActivity()
////        is ContextWrapper -> MainActivity.getActivity()
////        else -> null
////    }
//
//@Composable
//private fun LargeButton(
//    screenWidth: Dp = 0.dp,
//    screenHeight: Dp = 0.dp,
//    onBack:(Boolean) ->Unit,
//    isPressed: Boolean = false
//) {
//    val matrix = ColorMatrix()
//    matrix.setToSaturation(0F)
//    LargeFloatingActionButton(
//        modifier = Modifier
//            .absoluteOffset(x = 100.dp, y = 600.dp)
//            .shadow(2.dp, RoundedCornerShape(50)),
//        onClick = { onBack(!isPressed) },
//        shape = CircleShape,
//    ) {
//        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Large floating action button")
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//private fun PreviewViewAll() {
//    MyApplicationTheme {
//        ViewAllBooks(UserBooksList.listOfBooksOwnedByUser.toList())
//    }
//}
//
//private fun logMessage(tag: String, msg: String) {
//    Log.d(tag, msg)
//}
//
//private fun showMessage(context: Context, message: String) {
//    Toast.makeText(context,message, Toast.LENGTH_LONG).show()
//}