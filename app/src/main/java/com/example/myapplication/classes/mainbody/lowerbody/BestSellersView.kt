package com.example.myapplication.classes.mainbody.lowerbody

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.ClickedBookActivity
import com.example.myapplication.R
import com.example.myapplication.classes.Book
import com.example.myapplication.data_classes.MyStateAndViewData
import com.example.myapplication.logMessage
import com.example.myapplication.objects.SampleBook
import com.example.myapplication.showMessage
import java.io.File

class BestSellersView {

    @Composable
    internal fun BestSellers (
        context: Context,
        bool: Boolean,
        bestSellerBookState: Boolean = false,
        bestSellerBookView: String = "in best seller",
        myStatesAndView: MyStateAndViewData,
        user: Map<String, String>,
        uid: String,
    ) {
        /**pre:----.
         * post: Displays my best sellers view.*/

        var state: Boolean by remember { mutableStateOf(false) }
        var view: String by remember { mutableStateOf("") }

        Row(
            modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Bestsellers",
                fontSize = 32.sp,
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .background(Color.Red)
            ) // height and background only for demonstration
        }

        BestSellerList(
            context = context,
            books = SampleBook.books,
            myState = state,
            myView = view,
            user = user,
            uid = uid,
            myStatesAndView = myStatesAndView
        ) { s, v ->
            state = s
            view = v
        }
    }

    @Composable
    private fun BestSellerList(
        context: Context,
        books: List<Book>,
        myState: Boolean = false,
        myView: String = "in best seller",
        myStatesAndView: MyStateAndViewData,
        user: Map<String, String>,
        uid: String,
        onChangeViewInBestSellerList: (s: Boolean, v: String) -> Unit
    ) {
        Box (
            modifier = Modifier
                .padding(all = 8.dp)
                .background(
                    Color.Black.copy(alpha = .1f),
                    RoundedCornerShape(10.dp)
                ),
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                LazyRow {
                    items(books) { book ->
                        testBooks(book)
//                        testBooksTwo(book)
                        BookCardBottom(context, book, user, uid) { s, v ->
                            //TODO: Display book user wants to buy.
                        }
                    }
                }
            }
        }
    }

    private fun testBooksTwo(book: Book) {
        try {
            File("../").printWriter().use { file ->
                file.write("id = " + book.getId().toString())
                file.write("title = " + book.getTitle())
                file.write("author = " + book.getAuthor())
                file.write("publicationYear = " + book.getPublicationYear().toString())
                file.write("genres = " + book.getGenres().toString())
                file.write("description = " + book.getDescription())
                file.write("longDescription = " + book.getLongDescription())
                file.write("coverImage = " + book.getCoverImage())
                file.write("localCoverImagePath = " + book.getLocalCoverImagePath().toString())
                file.write("price = " + book.getPrice().toString())
                file.write("rates = " + book.getRates().toString())
                file.append("\n")
            }
        } catch (e: Exception) {
            logMessage("fileError", e.toString())
        }

    }

    private fun testBooks(book: Book) {
//        val fileName: String = "/home/emmanuel/AndroidStudioProjects/BookMe/app/src/main/assets/set_books.txt"
//        try {
//            val file: File = File(fileName)
//            logMessage("fileIs",file.isFile.toString())
//            file.writeText("id = " + book.getId().toString())
//            file.writeText("title = " +  book.getTitle())
//            file.writeText("author = " + book.getAuthor())
//            file.writeText("publicationYear = " + book.getPublicationYear().toString())
//            file.writeText("genres = " + book.getGenres().toString())
//            file.writeText("description = " + book.getDescription())
//            file.writeText("longDescription = " + book.getLongDescription())
//            file.writeText("coverImage = " + book.getCoverImage())
//            file.writeText("localCoverImagePath = " + book.getLocalCoverImagePath().toString())
//            file.writeText("price = " + book.getPrice().toString())
//            file.writeText("rates = " + book.getRates().toString())
//            file.appendText("\n")
//        } catch (e: Exception) {
//            logMessage("fileError", e.toString())
//        }


        logMessage("id", book.getId().toString())
        logMessage("title", book.getTitle())
        logMessage("author", book.getAuthor())
        logMessage("publicationYear", book.getPublicationYear().toString())
        logMessage("genres", book.getGenres().toString())
        logMessage("description", book.getDescription())
        logMessage("longDescription", book.getLongDescription())
        logMessage("coverImage", book.getCoverImage())
        logMessage("localCoverImagePath", book.getLocalCoverImagePath().toString())
        logMessage("price", book.getPrice().toString())
        logMessage("rates", book.getRates().toString())
    }

    @Composable
    fun BookCardBottom(
        context: Context,
        book: Book,
        mapOfUser: Map<String, String>,
        uid: String,
        st: Boolean = false,
        ve: String = " in book card bottom",
        onChangeView: (s: Boolean, v: String) -> Unit
    ) {
        val matrix = ColorMatrix()
        matrix.setToSaturation(0F)
        val offsetInPx = with(LocalDensity.current) { 36.dp.roundToPx() }
        /*TODO: sideways navbar.*/
        // Creating a launcher for the new activity
        // Create a launcher for starting the activity
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { /* Handle the result if needed */ }

        Row(modifier = Modifier.padding(all = 8.dp)) {
            Card(shape = RoundedCornerShape(10)) {
                // Image of the book.
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(book.getCoverImage())
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(id = book.getLocalCoverImagePath()),
                    error = painterResource(id = R.drawable.book_image),
                    contentDescription = "Books Image",
                    modifier = Modifier
                        .size(100.dp, 150.dp)
                        .clip(RectangleShape)
                        .clickable(
                            onClick = {
                                showMessage(
                                    context = context,
                                    message = "Clicked on the book " + "'" + book.getTitle() + "'",
                                )
                                val intent: Intent =
                                    Intent(context, ClickedBookActivity::class.java).apply {
                                        // Pass data as an extra with the intent.
                                        putExtra("PARENT_ACTIVITY" ,"MAIN_ACTIVITY")
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
                            }
                        )
                        .border(
                            border = BorderStroke(2.dp, Color.White),
                            shape = RectangleShape
                        ),
                    colorFilter = ColorFilter.colorMatrix(matrix),
                )
            }

            // Textview for the price.
            Text(
                text = "${book.getPrice()}$",
                modifier = Modifier
                    .size(width = 30.dp, height = 30.dp)
                    .offset { IntOffset(-offsetInPx, offsetInPx) }
                    .shadow(2.dp, RoundedCornerShape(50))
                    .align(Alignment.Top)
                    .background(Color.White),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }

    @Composable
    internal fun OnChangeState(
        currentState: Boolean,
        buttonName: String,
        updateButtonState: (Boolean, String) -> Unit,
        nextView: String,
    ) {
        TextButton(
            onClick = { updateButtonState(!currentState, nextView) },
            colors = ButtonDefaults.textButtonColors(),
        ) {
            Text(text = buttonName)
        }
    }
}
