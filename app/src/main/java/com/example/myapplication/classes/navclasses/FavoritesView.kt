package com.example.myapplication.classes.navclasses

import android.content.Context
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.StarRate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R

class FavoritesView {

    @Composable
    internal fun InitializeFavoritesView(
        context: Context,
        listOfMapsOfLikes: List<Map<String, String>>
    ) {
        if (listOfMapsOfLikes.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(all = 8.dp)
            ) {
                LazyColumn {
                    items (listOfMapsOfLikes.size) { idx ->
                        FavoritesCard(context, listOfMapsOfLikes[idx])
                    }
                }
            }
        }
    }

    @Composable
    private fun FavoritesCard(context: Context, book: Map<String, String>) {

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
                            modifier = Modifier.weight(1f).padding(4.dp),
                            text = "$${book["price"]}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                        )
                    }
                    // Book description.
                    MyScrollingFunction(book["description"].toString())

                    book["rates"]?.let { ShowStars(it.toInt()) }

                    ShowOptions()
                }
            }
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
}

    @Composable
    private fun ShowOptions() {

        val options: List<String> = listOf("Buy it now", "Add it to chart", "View item", "See similar")

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
                        Text(
                            text = options[optionIdx],
                            modifier = Modifier.padding(all = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
