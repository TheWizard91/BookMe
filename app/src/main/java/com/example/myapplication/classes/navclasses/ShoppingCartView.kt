package com.example.myapplication.classes.navclasses

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import coil.compose.AsyncImage
import coil.request.ImageRequest

class ShoppingCartView {

    @Composable
    internal fun InitializeShoppingView(
        context: Context,
        listMapOfBooksInShoppingCart: List<Map<String, String>>,
        mapOfUser: MutableMap<String, String>
    ) {
//        Column(
//            modifier = Modifier.padding(all = 8.dp)
//        ) {
//            Text(
//                modifier = Modifier.padding(8.dp),
//                text = "SCart",
//                fontSize = 32.sp,
//            )
//        }
        if (listMapOfBooksInShoppingCart.isNotEmpty()) {
            Column (
                modifier = Modifier.padding(all = 8.dp)
            ) {
                LazyColumn {
                    items (listMapOfBooksInShoppingCart.size) { idx ->
                        ShoppingCartCard (context, listMapOfBooksInShoppingCart[idx], mapOfUser)
                    }
                }
            }
        }
    }

    @OptIn(UnstableApi::class)
    @Composable
    private fun ShoppingCartCard(context: Context, book: Map<String, String>, mapOfUser: MutableMap<String, String>) {

        Card (
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            border = BorderStroke(2.dp, Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(8.dp)
        ) {
            Row (modifier = Modifier.padding(all = 8.dp)) {
                AsyncImage(
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
                    contentDescription = "Image of the book"
                )
                Column (modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()) {
//                    Row (
//                        modifier = Modifier.fillMaxWidth().wrapContentHeight()
//                    ) {
                    // Description short version.
                    Text (
                        modifier = Modifier.padding(4.dp),
                        text = book["title"].toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
//                    }
                    MyScrollingFunction(book["description"].toString())

                    var q: String = ""
                    // Quantity and Price
                    QuantityAndPrice(book = book){ quantity ->
                        q = quantity
                        Log.d("myQuantity",q)
                    }
                }
            }
        }
    }

    @Composable
    private fun QuantityAndPrice(book: Map<String, String>, onChangeQuantityValue: (String) -> Unit) {

        val focusManager = LocalFocusManager.current
        var quantityNumber by remember { mutableStateOf("1") }
        val myBrush = remember {
            Brush.linearGradient(
                colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow),//rainbowColors
                start = Offset(0.0f, 50.0f),
                end = Offset(0.0f, 100.0f)
            )
        }

        Column {
//            Text(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(4.dp),
//                text = "Qty",
//                fontSize = 8.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.End
//            )

            OutlinedTextField(
                value = quantityNumber,
                onValueChange = { quantityValue ->
                    onChangeQuantityValue(quantityValue)
                    quantityNumber = quantityValue
                },
                modifier = Modifier.width(60.dp).height(60.dp),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                textStyle = TextStyle(brush = myBrush),
                placeholder = { Text(text = quantityNumber, style = TextStyle(fontSize = 4.sp, brush = myBrush)) },
                label = { Text(text = "Qty:", fontSize = 8.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold) },
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                shape = RoundedCornerShape(8.dp)
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp),
                text = "${(book["price"]?.toInt() ?:0)*quantityNumber.toInt()}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
        }
    }

//    @Composable
//    fun OutlinedTextFieldForQuantity() {
//        val focusManager = LocalFocusManager.current
//        var quantity by remember { mutableStateOf("") }
//        val myBrush = remember {
//            Brush.linearGradient(
//                colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow),//rainbowColors
//                start = Offset(0.0f, 50.0f),
//                end = Offset(0.0f, 100.0f)
//            )
//        }
//        OutlinedTextField(
////            value = text,
////            onValueChange = { text = it },
////            label = { Text("Label") }
//
//            value = quantity,
//            onValueChange = { quantityValue ->
//                onChangeQuantityValue(quantityValue)
//                quantity = quantityValue
//            },
//            modifier = Modifier.width(60.dp),
//            keyboardActions = KeyboardActions(
//                onNext = { focusManager.moveFocus(FocusDirection.Down) }
//            ),
//            textStyle = TextStyle(brush = myBrush),
//            placeholder = { Text(text = quantity) },
//            label = { Text(text = "Qty") },
//            singleLine = true,
//            visualTransformation = VisualTransformation.None,
//        )
//    }

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