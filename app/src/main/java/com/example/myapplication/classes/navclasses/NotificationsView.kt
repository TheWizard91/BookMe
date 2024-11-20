package com.example.myapplication.classes.navclasses

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.showMessage

class NotificationsView {

    @Composable
    internal fun InitialNotificationsView (
        context: Context,
        listOfNotifications: List<Map<String, String>>,
        userProfileImageURL: String
    ) {
        // list of all books
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            LazyColumn {
                items (listOfNotifications.size) { idx ->
                    NotificationCard(context = context, userProfileImageURL = userProfileImageURL, listOfNotifications[idx])
                }
            }
        }
    }

    @Composable
    private fun NotificationCard (
        context: Context,
        userProfileImageURL: String,
        map: Map<String, String>,
    ) {

            Card (
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                border = BorderStroke(2.dp, Color.DarkGray),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp)
            ) {

                Row {
                    IconButton(
                        modifier = Modifier
                            .size(80.dp, 80.dp)
                            .padding(10.dp),
                        onClick = { showMessage(context, "Clicked, ${"userProfileImageURL"}") }
                    ) {

                        AsyncImage(
                            model = ImageRequest
                                .Builder(LocalContext.current)
                                .data(
//                                    "https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/ayAebp8hE8NACQvgrJ4jj5FC0422%2Fcurrent_profile_image%2Fcontent%3A%2Fmedia%2Fpicker%2F0%2Fcom.android.providers.media.photopicker%2Fmedia%2F1000002089.jpg?alt=media&token=db96e15e-3dd9-4d07-a5a1-a0b29062e8ef"
//                                    map["userProfileImageURL"]
                                    userProfileImageURL
                                )
                                .crossfade(true)
                                .build(),
                            error = painterResource(id = R.drawable.account_image),
                            contentDescription = "Image of the user",
                            modifier = Modifier
                                .size(80.dp, 80.dp)
                                .clip(RectangleShape)
                                .border(
                                    border = BorderStroke(4.dp, Color.White),
                                    shape = CircleShape,
                                ),
                        )
                    }

                    Text(
                        text = map["message"].toString(),
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                }
        }
    }
}