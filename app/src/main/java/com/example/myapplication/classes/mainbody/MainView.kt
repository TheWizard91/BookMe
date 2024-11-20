package com.example.myapplication.classes.mainbody

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.myapplication.classes.Book
import com.example.myapplication.classes.mainbody.lowerbody.BestSellersView
import com.example.myapplication.classes.mainbody.upperbody.MyLibraryView
import com.example.myapplication.data_classes.MyStateAndViewData

class MainView {

    /**pre:---.
     * post:displays the view of main body/home.*/

    @Composable
    internal fun InitMainView(
        context: Context,
        user: Map<String, String>,
        uid: String,
        userOwnedBooks: List<Book>
    ) {
        // If view all and see more button are not pressed display the two main view
        BothViewsModular(context, user, uid, userOwnedBooks)
    }

    @Composable
    fun BothViewsModular(
        context: Context,
        user: Map<String, String>,
        uid: String,
        userOwnedBooks: List<Book>
    ) {
        MyLibraryAndBestSellersViews(context, user, uid, userOwnedBooks)
    }

    @Composable
    fun MyLibraryAndBestSellersViews(
        context: Context,
        user: Map<String, String>,
        uid: String,
        userOwnedBooks: List<Book>
    ) {

        val bool: Boolean by remember { mutableStateOf(false) }
        val myStatesAndView: MyStateAndViewData by remember {
            mutableStateOf(
                MyStateAndViewData(
                    "",
                    false
                )
            )
        }

        val state: Boolean by remember { mutableStateOf(false) }
        val view: String by remember { mutableStateOf("") }

        val myLibraryView = MyLibraryView()
        val bestSellersView = BestSellersView()

        myLibraryView.MyLibrary(
            context = context,
            bool = bool,
            stateOfViewAll = state,
            viewOfViewAll = view,
            myStatesAndView = myStatesAndView,
            uid = uid,
            userOwnedBooks = userOwnedBooks
        )

        bestSellersView.BestSellers(
            context = context,
            bool = bool,
            bestSellerBookState = state,
            bestSellerBookView = view,
            user = user,
            myStatesAndView = myStatesAndView,
            uid = uid
        )

    }

}
