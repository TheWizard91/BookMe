package com.example.myapplication.test.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.classes.Book
import com.example.myapplication.objects.UserBooksList
import com.example.myapplication.ui.theme.MyApplicationTheme

class ViewAllActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewAllMainView(bookList = UserBooksList.listOfBooksOwnedByUser.toList())
                }
            }
        }
    }
}

@Composable
fun ViewAllMainView(bookList: List<Book>) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    var isClicked: Boolean by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
            .size(screenWidth,screenHeight)
            .padding(all = 8.dp)
    ) {
        Text(
            text = "All Your Books!",
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Box (
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                columns = GridCells.Adaptive(minSize = 128.dp)
            ) {
                items(bookList) { book ->
                    BookCard(LocalContext.current, book) { clickBookImage ->
                        isClicked = clickBookImage
                    }
                }
            }
            LargeButton (screenWidth, screenHeight) {

            }
        }
    }
}

@Composable
fun BookCard(
    context: Context,
    book: Book,
    onBookClicked: (clickBookImage: Boolean) -> Unit
) {
    /**pre: book is a book.
    * post: creating book view.*/

    var isClicked: Boolean by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(all = 8.dp)
    ) {
        Card(shape = RoundedCornerShape(10),) {
            AsyncImage(
                model = book.getCoverImage(),
                contentDescription = "Books Image",
                placeholder = painterResource(id = book.getLocalCoverImagePath()),
                error = painterResource(id = R.drawable.book_image),
                modifier = Modifier
                    .size(150.dp, 200.dp)
                    .clip(RectangleShape)
                    .clickable(
                        onClick = {
                            isClicked = !isClicked
                            onBookClicked(isClicked)
                            showMessage(
                                context = context,
                                message = "state is " +
                                        "$isClicked " +
                                        "and books is ${book.getTitle()}"
                            )
                            logMessage("whatBook", book.getTitle())
                        }
                    ),
            )
        }
    }
}

@Composable
fun LargeButton(
    screenWidth: Dp,
    screenHeight: Dp,
    onBack:() ->Unit
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)
    LargeFloatingActionButton(
        modifier = Modifier
            .absoluteOffset(x = 400.dp, 600.dp)
            .shadow(2.dp, RoundedCornerShape(50),),
        onClick = { onBack() },
        shape = CircleShape
    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Large floating action button")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewViewAll() {
    MyApplicationTheme {
        ViewAllMainView(UserBooksList.listOfBooksOwnedByUser.toList())
    }
}

private fun logMessage(tag: String, msg: String) {
    Log.d(tag, msg)
}

private fun showMessage(context: Context, message: String) {
    Toast.makeText(context,message, Toast.LENGTH_LONG).show()
}