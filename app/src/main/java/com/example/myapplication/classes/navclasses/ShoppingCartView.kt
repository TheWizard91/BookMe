package com.example.myapplication.classes.navclasses

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ShoppingCartView {

    @Composable
    fun InitializeShoppingView(context: Context) {
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "SCart",
                fontSize = 32.sp,
            )
        }
    }
}