package com.example.myapplication.classes.navclasses

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.LoginActivity
import com.example.myapplication.R
import com.example.myapplication.UpdateUserAccountActivity
import com.example.myapplication.classes.Book
import com.example.myapplication.classes.mainbody.MainView
import com.example.myapplication.mainactivityscomponents.BestSellersActivity
import com.example.myapplication.mainactivityscomponents.ViewAllActivity
import com.example.myapplication.objects.UserBooksList

data class StatesManager(
    var bool: Boolean = false,
    var currentBottomNavView: String = "",
)

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: String? = null,
)

class NavigationBarView {
    // This is used for the top and bottom bars in the parameters () and body is in the body code{}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun InitialViewOfNavigationBarView(
        cxt: Context,
        mapOfUser: MutableMap<String, String>,
        userId: String,
        listOfNotifications: List<Map<String, String>>,
        listOfMapsOfLikes: List<Map<String, String>>,
        listMapOfBooksInShoppingCart: List<Map<String, String>>
    ) {

        // App-related variables
        var currentFragmentTitle: String by remember { mutableStateOf("Home") }

        // setting up the individual tabs
        val homeTab = TabBarItem(title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon =  Icons.Outlined.Home)
        val notificationsTab = TabBarItem(title = "Notifications", selectedIcon = Icons.Filled.Notifications, unselectedIcon = Icons.Outlined.Notifications, badgeAmount = "${listOfNotifications.size}")
        val favoriteTab = TabBarItem(title = "Favorite", selectedIcon = Icons.Filled.Favorite, unselectedIcon = Icons.Outlined.Favorite, badgeAmount = "${listOfMapsOfLikes.size}")
        val shoppingCartTab = TabBarItem(title = "SCart", selectedIcon = Icons.Filled.ShoppingCart, unselectedIcon = Icons.Outlined.ShoppingCart, badgeAmount = "${listMapOfBooksInShoppingCart.size}")

        // creating a list of all the tabs
        val tabBarItems: List<TabBarItem> = listOf(homeTab, notificationsTab, favoriteTab, shoppingCartTab)

        // creating our navController
        val navController: NavHostController = rememberNavController()
        // val actions: Actions = remember(navController) { Actions(navController) }

        // Variables for button tab bar
        var pressedProfileImageButton: Boolean by remember { mutableStateOf(true) }

        // Holds state for view change related to bottom nav bar.
        var statesManager: StatesManager by remember { mutableStateOf(StatesManager()) }

        // Fragments.
        val notificationsView = NotificationsView()
        val favoritesView = FavoritesView()
        val shoppingCartView = ShoppingCartView()
        val mainView = MainView()

        val seeYourLibraryString = "See Your Library >"
        val viewAllTheBooksString = "View All Books >"

        val userOwnedBooks = UserBooksList.listOfBooksOwnedByUser.toList()

        Scaffold(
            // Top bar.
            topBar = {
                CenterAlignedTopAppBar(
                    // Color of top bar.
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    //Top bar title view.
                    title = {
                        Text(
                            text = currentFragmentTitle,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                        )
                    },
                    // User image button view.
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                pressedProfileImageButton = !pressedProfileImageButton
                            }
                        ) {
                            ProfileImage(cxt, mapOfUser, mapOfUser["userProfileImageURL"].toString())
                        }
                    },
                    // Menu button view.
                    actions = {
                        MyDropDownMenu(cxt, seeYourLibraryString, viewAllTheBooksString, userOwnedBooks, mapOfUser)
                    }
                )
            },
            // Bottom bar view.
            bottomBar = {
                // Injecting the bottomBar buttons so now we can see them.
                BottomNavigationBar(tabBarItems, navController)
                // Setting up the logic of the bottom nav bar.
                NavHost(
                    navController = navController,
                    startDestination = homeTab.title,
                    modifier = Modifier,
                ) {
                    // Set the destination after clicking a particular button in nav bar.
                    // home/main activity
                    composable(homeTab.title) {
                        statesManager = statesManager.copy(bool = false, currentBottomNavView = "Home")
                        currentFragmentTitle = "Home"
                    }
                    // notifications
                    composable(notificationsTab.title) {
                        statesManager = statesManager.copy(bool = false, currentBottomNavView = "Notifications")
                        currentFragmentTitle = "Notifications"
                    }
                    // favorites
                    composable(favoriteTab.title) {
                        statesManager = statesManager.copy(bool = false, currentBottomNavView = "Favorites")
                        currentFragmentTitle = "Favorites"
                    }
                    // shopping
                    composable(shoppingCartTab.title) {
                        statesManager = statesManager.copy(bool = false, currentBottomNavView = "SCart")
                        currentFragmentTitle = "Shopping"
                    }
                }
            },
        ) { innerPadding ->
            // everything in between.
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                // state control.
                when (
                    statesManager.currentBottomNavView
                ) {
                    "Notifications" -> notificationsView.InitialNotificationsView(context = cxt, listOfNotifications = listOfNotifications, userProfileImageURL = mapOfUser["userProfileImageURL"].toString())
                    "Favorites" -> favoritesView.InitializeFavoritesView(context = cxt, listOfMapsOfLikes = listOfMapsOfLikes, mapOfUser = mapOfUser)
                    "SCart" -> shoppingCartView.InitializeShoppingView(context = cxt, listMapOfBooksInShoppingCart = listMapOfBooksInShoppingCart, mapOfUser = mapOfUser)
                    else -> mainView.InitMainView(context = cxt, user = mapOfUser, uid = userId, userOwnedBooks = emptyList(), mapOfUser = mapOfUser)
                }
            }
        }
    }


@Composable
    private fun MyDropDownMenu(
    cxt: Context,
    seeYourLibraryString: String,
    viewAllTheBooksString: String,
    userOwnedBooks: List<Book>,
    mapOfUser: MutableMap<String, String>
    ) {

        var expanded by remember {
            mutableStateOf(false)
        }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { /* Handle the result if needed */ }

        Box (
            modifier = Modifier
                .wrapContentSize(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Menu button"
                )
            }

            DropdownMenu(
//                modifier = Modifier.background(color = Color.White),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                // View all button.
                DropdownMenuItem(
                    text = { Text(seeYourLibraryString) },
                    onClick = {
                        cxt.startActivity(Intent(cxt, ViewAllActivity()::class.java))
                        (cxt as Activity).finish()
                        Toast.makeText(cxt, seeYourLibraryString, Toast.LENGTH_SHORT).show()
                    }
                )
                HorizontalDivider()

                // Best seller button.
                DropdownMenuItem(
                    text = { Text(viewAllTheBooksString) },
                    onClick = {

                        val intent: Intent =
                            Intent(cxt, BestSellersActivity::class.java).apply {
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
                    },
                )

                HorizontalDivider()

                // Log out button.
                DropdownMenuItem(
                    text = { Text("Log out") },
                    onClick = {
                        cxt.startActivity(Intent(cxt, LoginActivity()::class.java))
                        (cxt as Activity).finish()
                        showMessage(cxt, "Logged out")
                    },
                )

            }
        }
    }


    @Composable
    private fun ProfileImage(cxt: Context, user: MutableMap<String, String>, userProfileImageURL: String) {

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { /* Handle the result if needed */ }

        // Image of the book.
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(userProfileImageURL)//user["userProfileImageURL"]
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.account_image),
            contentDescription = "Books Image",
            modifier = Modifier
                .size(40.dp, 40.dp)
                .clip(RectangleShape)
                .clickable(
                    onClick = {
                        showMessage(
                            context = cxt,
                            message = "User's name is " +
                                    user["firstname"] +
                                    "  " + user["lastname"],
                        )
                        // Change user credentials...
                        val intent: Intent =
                            Intent(cxt, UpdateUserAccountActivity::class.java).apply {
                                // Pass data as an extra with the intent.
                                putExtra(
                                    "USER_PROFILE_IMAGE_URL",
                                    user["userProfileImageURL"].toString()
                                )
                                putExtra(
                                    "USER_PROFILE_IMAGE_SERIAL_NUMBER",
                                    user["profileImageSerialNumber"].toString()
                                )
                                putExtra("USER_FIRSTNAME", user["firstname"].toString())
                                putExtra("USER_LASTNAME", user["lastname"].toString())
                                putExtra("USER_MORE_INFO", user["moreInfo"].toString())
                                putExtra("USER_PHONE_NUMBER", user["phone"].toString())
                                putExtra("USER_GENDER", user["gender"].toString())
                            }
                        // Start the activity using the launcher.
                        launcher.launch(intent)
                    }
                )
                .border(
                    border = BorderStroke(2.dp, Color.White),
                    shape = CircleShape
                ),
        )
    }

    // -------------------------------------------------
    // This is a wrapper view that allows us to easily and cleanly
    // reuse this component in any future project.
    @Composable
    private fun BottomNavigationBar(tabBarItems: List<TabBarItem>, navController: NavController) {
        var selectedTabIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        NavigationBar {
            // looping over each tab to generate the view and navigation for each item
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        navController.navigate(tabBarItem.title)
                    },
                    icon = {
                        TabBarIconView(
                            isSelected = selectedTabIndex == index,
                            selectedIcon = tabBarItem.selectedIcon,
                            unselectedIcon = tabBarItem.unselectedIcon,
                            title = tabBarItem.title,
                            badgeAmount = tabBarItem.badgeAmount
                        )
                    },
                    label = { Text(tabBarItem.title) }
                )
            }
        }
    }

    // This component helps to clean up the API call from out BottomNavigationBar above,
    // but could just as easily be added inside the BottomNavigationBar without creating this component
    @Composable
    private fun TabBarIconView(
        isSelected: Boolean,
        selectedIcon: ImageVector,
        unselectedIcon: ImageVector,
        title: String,
        badgeAmount: String? = null
    ) {
        BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
            Icon(
                imageVector = if (isSelected) {selectedIcon} else {unselectedIcon},
                contentDescription = title
            )
        }
    }

    // This component helps to clean up the API call from our TabBarIconView above,
    // but could just as easily be added inside the TabBarIconView without creating custom component
    @Composable
    private fun TabBarBadgeView(count: String? = null) {
        if (count != null) {
            Badge {
                Text(count.toString())
            }
        }
    }

    private fun showMessage(context: Context, message: String) {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

}