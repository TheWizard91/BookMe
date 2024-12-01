package com.example.myapplication.classes.mainbody.upperbody

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.classes.Book
import com.example.myapplication.data_classes.MyStateAndViewData
import com.example.myapplication.mainactivityscomponents.BestSellersActivity
import com.example.myapplication.showMessage

class MyLibraryView {

    @Composable
    fun MyLibrary(
        context: Context,
        bool: Boolean,
        stateOfViewAll: Boolean = false,
        viewOfViewAll: String = "in my library",
        myStatesAndView: MyStateAndViewData,
        uid: String,
        userOwnedBooks: List<Book>,
        mapOfUser: MutableMap<String, String>
    ) {
        /**pre:----.
         * post: Displays my library view.*/

        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "My Library",
                fontSize = 32.sp,
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .background(Color.Red)
            )
        }

        if (userOwnedBooks.isNotEmpty()) {
            MyLibraryList(
                context = context,
                userOwnedBooks = userOwnedBooks,
                myStatesAndView = myStatesAndView
            )
        } else {
            EmptyLibrary(mapOfUser, context)
        }
    }

    @Composable
    private fun EmptyLibrary(mapOfUser: MutableMap<String, String>, context: Context) {

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { /* Handle the result if needed */ }

        Column(
            modifier = Modifier.fillMaxWidth()
                .height(150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
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
    fun MyLibraryList(
        context: Context,
        userOwnedBooks: List<Book>,
        myStatesAndView: MyStateAndViewData,
    ) {
        Row(modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                LazyRow {
                    items(userOwnedBooks) { book ->
                        BookCardTop(
                            context = context,
                            book = book,
                            myStatesAndView = myStatesAndView
                        )
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    fun BookCardTop(
        context: Context,
        book: Book,
        myStatesAndView: MyStateAndViewData
    ) {
        /**pre: book is a book.
         * post: creating book view.*/

        Column(modifier = Modifier.padding(all = 8.dp)) {
            Card(
                shape = RoundedCornerShape(10)
            ) {
                AsyncImage(
                    model = book.getCoverImage(),
                    contentDescription = "Books Image",
                    placeholder = painterResource(id = book.getLocalCoverImagePath()),
                    error = painterResource(id = R.drawable.book_image),
                    modifier = Modifier
                        .size(100.dp, 150.dp)
                        .clip(RectangleShape)
                        .clickable(
                            onClick = {
                                // TODO: Need zoom effect as user clicks!
                                myStatesAndView.view = "selectedBook"
                                myStatesAndView.state = !myStatesAndView.state
                                showMessage(
                                    context = context,
                                    message = "state is " +
                                            "${myStatesAndView.state} " +
                                            "view is ${myStatesAndView.view} " +
                                            "and books is ${book.getTitle()}"
                                )
                            }
                        )
//                        .border(border = BorderStroke(2.dp, Color(0xFFD4B4DC)),),
                )
            }

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
    }

}