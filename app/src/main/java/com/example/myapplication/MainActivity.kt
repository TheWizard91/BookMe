@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.ui.theme.MyApplicationTheme

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: Int? = null,
)

//data class SampleBook(
////    println("User ID: ${book.getId()}")
////    Log.d("User ID",book.getId().toString())
//    val jsonFile: File = File("res/raw/sample_books.jason"),
//    val jsonString: String = jsonFile.readText(),
//    val gson: Gson = Gson(),
//    val book: Book = gson.fromJson(jsonString, Book::class.java),
//)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        MainBody()
                    }
                }
            }
        }
    }
}

@SuppressLint("AutoboxingStateCreation")
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainBody () {
    /*pre:---.
    * post: this is the main view of the app.*/

    val currentFragmentTitle = "Home Page"

    // setting up the individual tabs
    val homeTab = TabBarItem(title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon =  Icons.Outlined.Home)
    val alertsTab = TabBarItem(title = "Alerts", selectedIcon = Icons.Filled.Notifications, unselectedIcon = Icons.Outlined.Notifications, badgeAmount = 7)
    val favoriteTab = TabBarItem(title = "Favorite", selectedIcon = Icons.Filled.Favorite, unselectedIcon = Icons.Outlined.Favorite)
    val shoppingCartTab = TabBarItem(title = "SCart", selectedIcon = Icons.Filled.ShoppingCart, unselectedIcon = Icons.Outlined.ShoppingCart)

    // creating a list of all the tabs
    val tabBarItems = listOf(homeTab, alertsTab, favoriteTab, shoppingCartTab)

    // creating our navController
    val navController = rememberNavController()
    val actions: Actions = remember(navController) { Actions(navController) }

    //
    var pressedProfileImageButton by remember { mutableIntStateOf(0) }
    val mainView:ProvidableCompositionLocal<Context> = LocalContext

    // Holds state.
    val currentBottomNavView = remember { mutableIntStateOf(0) }
//    val jsonFileString = Utils().getJsonDataFromAssets(LocalContext.current, "books.json")
//    if (jsonFileString != null) {
//        Log.i("myData", jsonFileString)
//    }

//    val gson = Gson()
//    val listBookType = object: TypeToken<List<Book>>() {}.type
//    Log.i("gSon",gson.toString())
//    Log.i("listBookType",listBookType.toString())
//    val serialized = gson.toJson(jsonFileString)
//    Log.i("serialized",serialized)

//    Log.i("something", gson.fromJson(jsonFileString, Book::class.java).toString())
//    var books2: List<Book> = gson.fromJson(jsonFileString, listBookType)
//    books2.forEachIndexed { idx, book -> Log.i("myData","> Item $idx:\n$book")

//    // widget.MainBodyView
//    AndroidView(factory = { ctx ->
//        // Initialize a View or View hierarchy here
//
//    })
    // This is used for the top and bottom bars in the parameters () and body is in the body code{}
    Scaffold(
        // Top bar.
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
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
                // User button view.
                navigationIcon = {
                    IconButton(
                        onClick = { pressedProfileImageButton = 1 - pressedProfileImageButton}
                    ) {
                        ProfileImage(isVisible = pressedProfileImageButton)
                    }
                },
                // Search button view.
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search button"
                        )
                    }
//                    IconButton(onClick = { /*TODO*/ }) {}
                }
            )
        },
        // Bottom bar view.
        bottomBar = {
            // Injecting the bottomBar buttons so now we can see them.
            TabView(tabBarItems, navController)

            // Setting up the logic of the bottom nav bar.
            NavHost(
                navController = navController,
                startDestination = homeTab.title,
                modifier = Modifier
            ) {
                // Set the destination after clicking a particular button in nav bar.
                composable(homeTab.title) {
                    HomeScreen(
                        mainViewModel = LocalContext,
//                        addNavigation = actions.addNotifications,
//                        addFavorites = actions.addFavorites,
//                        addShopping = actions.addShopping,
//                        addTask = actions.openTask,
                    )
                }
                composable(alertsTab.title) {
                    NotificationsScreen(
                        mainViewModel = mainView,
                        navigateBack = actions.navigateBack,
                    )
                }
                composable(favoriteTab.title) {
                    FavoriteScreen(
                        mainViewModel = LocalContext,
                        navigateBack = actions.navigateBack,
                    )
                }
                composable(shoppingCartTab.title) {
                    ShoppingCartScreen(
                        mainViewModel = LocalContext,
                        navigateBack = actions.navigateBack,
                    )
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            // TODO: Set visibility based on the current nav bar button.
//            when (currentBottomNavView) {
//                1 -> MainView()
//            }
        }
    }
}

@Composable
fun MainView() {
    /**pre:---.
     * post:displays the view of main body.*/
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MyLibraryView()
    }
    BestSellersView()
}

@Composable
fun BestSellersView() {
    /**pre:----.
     * post: Displays my best sellers view.*/
    Row(modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Bestsellers",
            fontSize = 32.sp,
        )
        Spacer(
            Modifier
                .weight(1f)
                .background(Color.Red)) // height and background only for demonstration
        ClickableText(
            modifier = Modifier.padding(8.dp),
            text = AnnotatedString("See more >"),
            onClick = { offset ->
                Log.d("Clicked it","$offset -th character is clicked.")
            },
            style = TextStyle(
                color = Color.Gray,
            )
        )
    }
    Row(modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            BestSellerList(books = SampleBook.books)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MyLibraryView() {
    /**pre:----.
     * post: Displays my library view.*/
    Row(
        modifier = Modifier.fillMaxWidth(),
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
                .background(Color.Red)) // height and background only for demonstration
        ClickableText(
            modifier = Modifier.padding(8.dp),
            text = AnnotatedString("View all >"),
            onClick = { offset ->
                Log.d("Clicked it","$offset -th character is clicked.")
            },
            style = TextStyle(
                color = Color.Gray,
            )
        )
    }
    MyLibraryList(books = SampleBook.books)
}

@Composable
fun ShoppingCartScreen(mainViewModel: ProvidableCompositionLocal<Context>, navigateBack: () -> Unit) {
    Text(text = "Shopping")
}

@Composable
fun FavoriteScreen(mainViewModel: ProvidableCompositionLocal<Context>, navigateBack: () -> Unit) {
    Text(text = "Favorites")
}

@Composable
fun NotificationsScreen(
    mainViewModel: ProvidableCompositionLocal<Context>,
    navigateBack: () -> Unit,
) {
    Text(text = "Notifications")
}

@Composable
fun HomeScreen(
    mainViewModel: ProvidableCompositionLocal<Context>,
//    addNavigation: () -> Unit,
//    addFavorites: () -> Unit,
//    addShopping: () -> Unit,
//    addTask: (Int) -> Unit
) {
    Text(text = "Home")
}

@Composable
fun ProfileImage(isVisible: Int) {
    if (isVisible == 1) {
        Image(
            painter = painterResource(
            id = R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
    } else {
        Icon(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = "User Image",
            modifier = Modifier
                .size(40.dp)// Set image size to 40
                // Clip image to be shaped as a circle
//                .clip(RectangleShape)
//                .border(1.5.dp, MaterialTheme.colorScheme.primary, RectangleShape)
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MyLibraryList(books: List<Book>) {
    Row(modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            LazyRow() {
                items(books) { book ->
                    BookCard(book)
                }
            }
        }
    }
}

@Composable
fun BestSellerList(books: List<Book>) {
    Row(modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            LazyRow() {
                items(books) { book ->
                    BookCardBottom(book)
                }
            }
        }
    }
}

@Composable
fun BookCardBottom(book: Book) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)
    Column(modifier = Modifier.padding(all = 8.dp)) {
        Column() {
            /*TODO: sideways navbar.*/
            Card(shape = RoundedCornerShape(10)) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(book.getCoverImage())
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(book.getLocalCoverImagePath()),
                    error = painterResource(id = R.drawable.book_image),
                    contentDescription = "Books Image",
                    modifier = Modifier
                        .size(100.dp, 150.dp)
                        .clip(RectangleShape) ,
                    colorFilter = ColorFilter.colorMatrix(matrix)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BookCard(book: Book) {
    /*pre: book is a book.
    * post: creating book view.*/

    Column(modifier = Modifier.padding(all = 8.dp)) {
        Card(shape = RoundedCornerShape(10),) {
//            Image(painter = painterResource(id = R.drawable.book_image),
//                contentDescription = "Books Image",
//                modifier = Modifier
//                    .size(100.dp, 150.dp)
//                    .clip(RectangleShape),
//            )
            AsyncImage(
                model = book.getCoverImage(),
                contentDescription = "Books Image",
                placeholder = painterResource(id = book.getLocalCoverImagePath()),
                error = painterResource(id = R.drawable.book_image),
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .clip(RectangleShape),
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
// TODO: Add sideways nav bar.

// -------------------------------------------------
// This is a wrapper view that allows us to easily and cleanly
// reuse this component in any future project.
@Composable
fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable() {
        mutableIntStateOf(0)
    }

    NavigationBar() {
        // looping over each tab to generate the view and navigation for each item
        tabBarItems.forEachIndexed() {index, tabBarItem ->
            NavigationBarItem(selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    navController.navigate(tabBarItem.title)
                },
                icon = {
                    TabBarIconView(
                        isSelected = selectedTabIndex == index,
                        selectedIcon =tabBarItem.selectedIcon,
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

// This component helps to clean up the API call from out TabView above,
// but could just as easily be added inside the TabView without creating this component
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon:ImageVector,
    title: String,
    badgeAmount: Int? = null
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}

//@Composable
//fun Navigation() {
//    val navController: NavController = rememberNavController(navigators = )
//}

// end if the reusable components that can be copied over to any new projects
// ---------------------------------------------------------
// This was added to demonstrate that we are in fact changing views when we click a new tab.
@Composable
fun MoreView() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Thing 1")
        Text("Thing 2")
        Text("Thing 3")
        Text("Thing 4")
        Text("Thing 5")
    }
}

@Preview(name = "Top Bar Preview")
@Composable
fun PreviewMainBody () {
    MyApplicationTheme {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MainBody()
        }
    }
}