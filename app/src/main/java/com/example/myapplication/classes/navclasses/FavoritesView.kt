package com.example.myapplication.classes.navclasses

import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.BuyItNowActivity
import com.example.myapplication.R
import com.example.myapplication.classes.databases.FireStoreDatabase

class FavoritesView {

    @Composable
    internal fun InitializeFavoritesView(
        context: Context,
        listOfMapsOfLikes: List<Map<String, String>>,
        mapOfUser: MutableMap<String, String>
    ) {
        if (listOfMapsOfLikes.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                LazyColumn {
                    items (listOfMapsOfLikes.size) { idx ->
                        FavoritesCard(context, listOfMapsOfLikes[idx], mapOfUser)
                    }
                }
            }
        }
    }

    @Composable
    private fun FavoritesCard(
        context: Context,
        book: Map<String, String>,
        mapOfUser: MutableMap<String, String>
    ) {
        // Card of the component.
        Card (
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            border = BorderStroke(2.dp, Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(8.dp)
        ) {
            // Row acting as a container.
            Row (modifier = Modifier.padding(all = 8.dp)) {
                // Book image.
                AsyncImage(
                    error = painterResource(id = R.drawable.book_image),
                    contentDescription = "Image of the book",
                    model = ImageRequest
                        .Builder(context)
                        .data(book["coverImage"])
                        .crossfade(true)
                        .build(),
                    modifier = Modifier
                        .size(100.dp, 100.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .border(
                            border = BorderStroke(4.dp, Color.White),
                            shape = CircleShape,
                        ),
                )
                // Column next to the image of the book.
                Column (
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
//                        .height(100.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        // Book title.
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = book["title"].toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        // Book price.
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            text = "$${book["price"]}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                        )
                    }

                    // Book description.
                    MyScrollingFunction(book["description"].toString())

                    var quantity: String = ""
                    QuantityFieldFunction(quantity) { currentQuantity ->
                        quantity = currentQuantity
                    }

                    book["rates"]?.let { ShowStars(it.toInt()) }

                    ShowOptions(book = book, mapOfUser = mapOfUser, context = context)
                }
            }
        }
    }

    @Composable
    private fun QuantityFieldFunction(quantity: String, onChange: (String) -> Unit) {
        val focusManager = LocalFocusManager.current
        var quantity by remember { mutableStateOf("") }
        val brushVariable = remember {
            Brush.linearGradient(
                colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow),
                start = Offset(0.0f, 50.0f),
                end = Offset(0.0f, 100.0f)
            )
        }
    }

    @Composable
    private fun ShowStars(rates: Int) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            LazyRow {
                items(rates) {
                    Icon(
                        Icons.Rounded.StarRate,
                        contentDescription = "Likes of book.",
                        tint = Color.White
                    )
                }
            }
        }
    }

    @Composable
    private fun MyScrollingFunction(myText: String) {

        /** Scrolling down method for description.
         * pre: myText is the description.
         * post: Let user scrolling down.*/

        Text (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
//                .height(60.dp)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
            text = myText,//book.getDescription(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }

    @Composable
    private fun ShowOptions(
        book: Map<String, String>,
        mapOfUser: MutableMap<String, String>,
        context: Context
    ) {

        /**Showing options to either buy the book right now or add it to the shopping cart.*/

        val options: List<String> = listOf("Buy it now", "Add it to chart", "View item", "See similar")
        val firestoreDB = FireStoreDatabase(
            mapOfUser["firstname"].toString(),
            mapOfUser["lastname"].toString(),
            mapOfUser["email"].toString(),
            mapOfUser["gender"].toString(),
            mapOfUser["phone"].toString(),
            mapOfUser["moreInfo"].toString(),
            mapOfUser["profileImageSerialNumber"].toString(),
            mapOfUser["profileImage"].toString(),
            mapOfUser["userProfileImageURL"].toString(),
            mapOfUser["uid"].toString()
        )
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { }

        //
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            LazyRow {
                items(options.size) { optionIdx ->
                    Card(
                        modifier = Modifier.padding(all = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        ClickableText(
                            text = AnnotatedString(options[optionIdx]),
                            modifier = Modifier.padding(all = 8.dp),
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            onClick = {
                                // Clicked Buy it now
                                if (options[optionIdx] == "Buy it now") {
                                    // Buy it now activity.
                                    val intent: Intent = Intent(context, BuyItNowActivity::class.java).apply {
//
//                                        // User information
//                                        putExtra("UID", mapOfUser["uid"])
//                                        putExtra("FIRSTNAME", mapOfUser["firstname"])
//                                        putExtra("Gender", mapOfUser["gender"])
//                                        putExtra("PHONE", mapOfUser["phone"])
//                                        putExtra(
//                                            "PROFILE_IMAGE_SERIAL_NUMBER",
//                                            mapOfUser["profileImageSerialNumber"]
//                                        )
//                                        putExtra("PROFILE_IMAGE", mapOfUser["profileImage"])
//                                        putExtra("MORE_INFO", mapOfUser["moreInfo"])
//                                        putExtra("EMAIL", mapOfUser["email"])
//                                        putExtra("LASTNAME", mapOfUser["lastname"])
//                                        putExtra(
//                                            "USER_PROFILE_IMAGE_URL",
//                                            mapOfUser["userProfileImageURL"]
//                                        )
//
//                                        // Book infos
//                                        putExtra("ID", book["id"])
//                                        putExtra("TITLE", book["title"])
//                                        putExtra("AUTHOR", book["author"])
//                                        putExtra("PUBLICATION_YEAR", book["publicationYear"])
//                                        putExtra("GENRES", book["genres"])
//                                        putExtra("DESCRIPTION", book["description"])
//                                        putExtra("LONG_DESCRIPTION", book["longDescription"])
//                                        putExtra("COVER_IMAGE", book["coverImage"])
//                                        putExtra("LOCAL_COVER_IMAGE_PATH", book["localCoverImagePath"])
//                                        putExtra("STOCK_COVER_IMAGE_PATH", book["stockCoverImagePath"])
//                                        putExtra("PRICE", book["price"])
//                                        putExtra("RATES", book["rates"])
                                    }
                                    launcher.launch(intent)
                                } else {
                                    // Update Shopping cart view.
                                    firestoreDB.setShoppingCartDB(book = book)
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}