@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplication.classes.Book
import com.example.myapplication.data_classes.MyStateAndViewData
import com.example.myapplication.data_classes.UserInfo
import com.example.myapplication.mainactivityscomponents.ViewAll
import com.example.myapplication.objects.SampleBook
import com.example.myapplication.objects.UserBooksList
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.File

data class UserData(var myUser: UserInfo = UserInfo())

data class StatesManager(
    var bool: Boolean = false,
    var currentBottomNavView: String = "",
)

data class TabBarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeAmount: String? = null, // Int? = null
)

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainBody()
                }
            }
        }
    }
}

private fun userQuery(userInfo: UserInfo, callback: (u: UserInfo) -> Unit) {
    callback(userInfo)
}

private fun setUser(userInfo: UserInfo) {

}

private fun firebaseDatabase(currentUser: FirebaseAuth) {
//    val currentUser = FirebaseAuth.getInstance()
    val database: FirebaseFirestore = FirebaseFirestore.getInstance()
    database.collection("Users")
        .document(currentUser.uid.toString())
        .get()
        .addOnCompleteListener {
            val u: UserInfo = UserInfo().copy(
                firstname = it.result.data!!["firstname"].toString(),
                lastname = it.result.data!!["lastname"].toString(),
                email = it.result.data!!["email"].toString(),
                phoneNumber = it.result.data!!["phone"].toString(),
                gender = it.result.data!!["gender"].toString(),
                moreInfo = it.result.data!!["moreInfo"].toString(),
                uri = it.result.data!!["profileImage"].toString()
            )
            userQuery(u) { user: UserInfo ->
                setUser(user)
            }
        }
}

@SuppressLint("AutoboxingStateCreation", "UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MainBody() {
//    initDatabaseData()
    MainContent()
}

fun initDatabaseData() {
    // Firebase section. Initializing its instances.
    // Check if the user is already logged in.
    val currentUser = FirebaseAuth.getInstance()
    val notificationsDatabase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    val userId: String = ""
    var numberOfNotifications: Int = setNotifications(notificationsDatabase)
    storage(currentUser)
    firebaseDatabase(currentUser)
}

fun storage(currentUser: FirebaseAuth) {
    // /KlFmro5VyqYeclOndH3IEIU4kOY2/profile_images/content:/media/picker/0/com.android.providers.media.photopicker/media/1000017879.jpg
    val storageDB = FirebaseStorage.getInstance().getReference()
    val localFile = File.createTempFile("images", "jpg")
    Log.d("downloadUrl:",
        storageDB.child("Notifications/"
                +currentUser.uid.toString()
                +"/profile_images"+"/content:"+"/media"+
                "/picker"+"/0"+"/com.android.providers.media.photopicker"
                +"/media/").toString())
    storageDB.child("Notifications/"
            +currentUser.uid.toString()
            +"/profile_images"+"/content:"+"/media"+
            "/picker"+"/0"+"/com.android.providers.media.photopicker"
            +"/media/")
        .getFile(localFile)
        .addOnSuccessListener { taskSnapshot ->
            Log.d("taskSnapshot:",taskSnapshot.toString())
        }.addOnFailureListener {

        }
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    /*pre:---.
    * post: this is the main view of the app.*/

    val cxt = LocalContext.current

    /*Fake values that will reflect what I see in the database, need this for preview mode to work*/
    val fakeUserInfo = UserInfo(
        firstname="Emmanuel",
        lastname="Agyapong",
        email="EmmanuelCodes@gmail.com",
        phoneNumber="9293038986",
        gender="Male",
        moreInfo="An incoming software engineer",
        uri="https://firebasestorage.googleapis.com/v0/b/bookme-dc582.appspot.com/o/meAtBCC.jpg?alt=media&token=3d9b481c-9880-445f-939f-3b7bb60f3cbd"
    )

    val numberOfNotifications = 1

    // App-related variables
    var currentFragmentTitle: String by remember { mutableStateOf("Home") }

    // setting up the individual tabs
    val homeTab = TabBarItem(title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon =  Icons.Outlined.Home)
    val notificationsTab = TabBarItem(title = "Notifications", selectedIcon = Icons.Filled.Notifications, unselectedIcon = Icons.Outlined.Notifications, badgeAmount = "$numberOfNotifications")
    val favoriteTab = TabBarItem(title = "Favorite", selectedIcon = Icons.Filled.Favorite, unselectedIcon = Icons.Outlined.Favorite)
    val shoppingCartTab = TabBarItem(title = "SCart", selectedIcon = Icons.Filled.ShoppingCart, unselectedIcon = Icons.Outlined.ShoppingCart)

    // creating a list of all the tabs
    val tabBarItems: List<TabBarItem> = listOf(homeTab, notificationsTab, favoriteTab, shoppingCartTab)

    // creating our navController
    val navController: NavHostController = rememberNavController()
    // val actions: Actions = remember(navController) { Actions(navController) }

    // Variables for button tab bar
    var pressedProfileImageButton: Int by remember { mutableIntStateOf(0) }

    // Holds state for view change related to bottom nav bar.
    var statesManager: StatesManager by remember { mutableStateOf(StatesManager()) }

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
                        onClick = {
                            pressedProfileImageButton = 1 - pressedProfileImageButton
                        }
                    ) {
                        ProfileImage(isVisible = pressedProfileImageButton, cxt, fakeUserInfo)
                    }
                },
                // Search button view.
                actions = {
                    IconButton(onClick = { /*TODO: Handle search button.*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search button"
                        )
                    }
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
                composable(homeTab.title) {
                    statesManager = statesManager.copy(bool = false, currentBottomNavView = "Home")
                    currentFragmentTitle = "Home"
                }
                composable(notificationsTab.title) {
                    statesManager = statesManager.copy(
                        bool = false,
                        currentBottomNavView = "Notifications"
                    )
                    currentFragmentTitle = "Notifications"
                }
                composable(favoriteTab.title) {
                    statesManager = statesManager.copy(
                        bool = false,
                        currentBottomNavView = "Favorites"
                    )
                    currentFragmentTitle = "Favorites"
                }
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
                "Notifications" -> NotificationsView(context = cxt)
                "Favorites" -> FavoritesView(context = cxt)
                "SCart" -> ShoppingView(context = cxt)
                else -> MainView(context = cxt)
            }
        }
    }
}

fun setNotifications(notificationsDatabase: DatabaseReference): Int {
//    var numberOfNotifications: Int by remember { mutableIntStateOf(0) }
    var numberOfNotifications = 0
    notificationsDatabase.child("Notifications")
        .orderByPriority()
        .addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.childrenCount.toInt().also { numberOfNotifications = it }
                Log.d("numOfNots",numberOfNotifications.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("A child has been changed!", "Yes")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                Log.d("A child has been removed!", "Yes")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("A child has been moved!", "Yes")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("A child has been cancelled!", "Yes")
            }
        })

    return numberOfNotifications
}

//@Composable
//fun SeeMore() {
//    Text(text = "See More Pressed!")
//    LargeButton {
//
//    }
//}

//@Composable
//fun ViewAll(userBooks: List<Book>) {
//    Text(text = "View All Pressed!")
//    LargeButton {
//
//    }
//}
//
//@Composable
//fun LargeButton(onBack:() ->Unit) {
//    LargeFloatingActionButton(
//        onClick = { onBack() },
//        shape = CircleShape
//    ) {
//        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Large floating action button")
//    }
//}

@Composable
fun ShoppingView(context: Context) {
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

@Composable
fun FavoritesView(context: Context) {
    Column(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Favorites",
            fontSize = 32.sp,
        )
    }
}

@Composable
fun NotificationsView(context: Context) {
    Column(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        LazyRow() {
//            items(notification) { book ->
//                NotiCard(book)
//            }
            // TODO: Need to retrieve the notifications from the real time database.
        }
    }
}

@Composable
fun OnChangeState(
    currentState: Boolean,
    buttonName: String,
    updateButtonState: (Boolean, String) -> Unit,
    nextView: String,
) {
    TextButton(
        onClick = { updateButtonState(!currentState, nextView) },
        colors = ButtonDefaults.textButtonColors(),
    ) {
        Text(text = buttonName)
    }
}

@Composable
fun MainView(context: Context) {
    /**pre:---.
     * post:displays the view of main body.*/

    val bool: Boolean by remember { mutableStateOf(false) }

    // If view all and see more button are not pressed display the two main view
//    BothViewsFunctional(context, bool)
    BothViewsModular(context, bool)
}

@Composable
fun BothViewsModular(context: Context, bool: Boolean) {
    MyLibraryAndBestSellersViews(context, bool)
}

@Composable
fun MyLibraryAndBestSellersViews(
    context: Context,
    bool: Boolean
) {

    val myStatesAndView: MyStateAndViewData by remember { mutableStateOf(MyStateAndViewData("",false)) }
    var state: Boolean by remember { mutableStateOf(false) }
    var view: String by remember { mutableStateOf("") }

    if (view == "view all" || view == "") {
        MyLibraryView(
            context = context,
            viewAllState = state,
            viewAllView = view,
            bool = bool,
            myStatesAndView = myStatesAndView
        ) { s, v ->
            state = s
            view = v
        }
    }

    if (view == "see more" || view == "") {
        BestSellersView(
            context = context,
            bool = bool,
            bestSellerBookState = state,
            bestSellerBookView = view,
            myStatesAndView = myStatesAndView
        ) { s, v ->
            state = s
            view = v
        }
    }
}

@Composable
fun BookOwned(book: List<Book>) {
    Text(text = "Book Clicked!")
}

@Composable
fun BestSellersView(
    context: Context,
    bool: Boolean,
    bestSellerBookState: Boolean = false,
    bestSellerBookView: String = "in best seller",
    myStatesAndView: MyStateAndViewData,
    onChangeViewInBestSellerView: (s: Boolean, v: String) -> Unit
) {
    /**pre:----.
     * post: Displays my best sellers view.*/

    var state: Boolean by remember { mutableStateOf(false) }
    var view: String by remember { mutableStateOf("") }
    logMessage(tag = "bestSellerBookState", msg = bestSellerBookState.toString())
    logMessage(tag = "bestSellerBookView", msg = bestSellerBookView)

    if (!state && view == "") {
        Row(
            modifier = Modifier.padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Bestsellers",
                fontSize = 32.sp,
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .background(Color.Red)
            ) // height and background only for demonstration
            OnChangeState(
                currentState = state,
                nextView = view,
                buttonName = "See more >",
                updateButtonState = { newState, newView ->
                    state = newState
                    view = "see more"
                    onChangeViewInBestSellerView(state, view)
                },
            )
        }
        BestSellerList(
            context = context,
            books = SampleBook.books,
            myState = state,
            myView = view,
            myStatesAndView = myStatesAndView
        ) { s, v ->
            state = s
            view = v
        }
    } else if (state && view == "see more") {
//        SeeMore()
    } else if (state && view == "book in store library") {
        BookView()
    }
}

@Composable
fun BookView() {
    TODO("Not yet implemented")
}

@Composable
fun MyLibraryView(
    context: Context,
    bool: Boolean,
    viewAllState: Boolean = false,
    viewAllView: String = "in my library",
    myStatesAndView: MyStateAndViewData,
    onChangeViewInMyLibraryView: (s: Boolean, v: String) -> Unit
) {
    /**pre:----.
     * post: Displays my library view.*/

    var state: Boolean by remember { mutableStateOf(bool) }
    var view: String by remember { mutableStateOf("") }

    if (!state && view == "") {
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
                    .background(Color.Red)
            ) // height and background only for demonstration
            OnChangeState(
                currentState = state,
                nextView = view,
                buttonName = "View All >",
                updateButtonState = { newState, newView ->
                    state = newState
                    view = "view all"
                    onChangeViewInMyLibraryView(newState, view)
                },
            )
        }
        MyLibraryList(
            context = context,
            books = UserBooksList.listOfBooksOwnedByUser.toList(),
            myStatesAndView = myStatesAndView
        )
    } else if (state && view == "view all") {
        val viewAll = ViewAll()
            /*TODO: the list of books is fake, make sure there is in the database so we can extract from there.*/
        viewAll.ViewAllMainView(userBooks = UserBooksList.listOfBooksOwnedByUser.toList())
    } else if (state && view == "book in my library") {

    }
}

@Composable
fun ProfileImage(isVisible: Int, cxt: Context, user: UserInfo) {
    if (isVisible == 1) {
        Icon(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = "User Image",
            modifier = Modifier
                .size(40.dp) // Set image size to 40
        )
        // Change user credentials...
        cxt.startActivity(Intent(cxt, UpdateUserAccountActivity()::class.java))
    } else {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
    }
}

@Composable
fun MyLibraryList(
    context: Context,
    books: List<Book>,
    myStatesAndView: MyStateAndViewData,
) {
    Row(modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            LazyRow() {
                items(books) { book ->
                    BookCardTop(
                        context = context,
                        book = book,
                        myStatesAndView = myStatesAndView)
                }
            }
        }
    }
}

@Composable
fun BestSellerList(
    context: Context,
    books: List<Book>,
    myState: Boolean = false,
    myView: String = "in best seller",
    myStatesAndView: MyStateAndViewData,
    onChangeViewInBestSellerList: (s: Boolean, v: String) -> Unit
) {
    Box (
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(
                Color.Black.copy(alpha = .1f),
                RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            LazyRow() {
                items(books) { book ->
                    BookCardBottom(context, book) { s, v ->
                    }
                }
            }
        }
    }
}

@Composable
fun BookCardBottom(
    context: Context,
    book: Book,
    st: Boolean = false,
    ve: String = " in book card bottom",
    onChangeView: (s: Boolean, v: String) -> Unit
) {
    val matrix = ColorMatrix()
    matrix.setToSaturation(0F)
    val offsetInPx = with(LocalDensity.current) { 36.dp.roundToPx() }
    /*TODO: sideways navbar.*/
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
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
                    .fillMaxSize()
                    .size(100.dp, 250.dp)
                    .clip(RectangleShape)
                    .clickable(
                        onClick = {
                            showMessage(
                                context = context,
                                message = "Clicked on the book " + book.getTitle(),
                            )
                            onChangeView(!st, "book in store library")
                        }
                    )
                    .border(
                        border = BorderStroke(2.dp, Color.White),
                        shape = RectangleShape
                    ),
                colorFilter = ColorFilter.colorMatrix(matrix),
            )
        }
        Text(
            text = "${book.getPrice()}$",
            modifier = Modifier
                .size(width = 30.dp, height = 30.dp)
                .offset {
                    IntOffset(-offsetInPx, offsetInPx)
                }
                .shadow(
                    2.dp,
                    RoundedCornerShape(50),
                )
                .align(Alignment.Top)
                .background(Color.White),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BookCardTop(
    context: Context,
    book: Book,
    myStatesAndView: MyStateAndViewData
) {
    /*pre: book is a book.
    * post: creating book view.*/

    Column(modifier = Modifier.padding(all = 8.dp)) {
        Card(shape = RoundedCornerShape(10),) {
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
//                            Log.d("Clicked top notice", book.getTitle())
                            myStatesAndView.view = "selectedBook"
                            myStatesAndView.state = !myStatesAndView.state
                            showMessage(
                                context = context,
                                message = "state is " +
                                        "${myStatesAndView.state} " +
                                        "view is ${myStatesAndView.view} " +
                                        "and books is ${book.getTitle()}"
                            )
                            logMessage("whatBook", myStatesAndView.view)
                        }
                    ),
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

@Composable
fun ViewSelectedBook(book: Book) {
    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(shape = RoundedCornerShape(10),) {
            AsyncImage(
                model = book.getCoverImage(),
                contentDescription = "Books Image",
                placeholder = painterResource(id = book.getLocalCoverImagePath()),
                error = painterResource(id = R.drawable.book_image),
                modifier = Modifier
                    .size(100.dp, 150.dp)
                    .clip(RectangleShape)
            )
        }
    }
}

private fun logMessage(tag: String, msg: String) {
    Log.d(tag, msg)
}

private fun showMessage(context: Context, message: String) {
    Toast.makeText(context,message,Toast.LENGTH_LONG).show()
}

@Composable
fun BookDetails(book: Book) {
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
}

// -------------------------------------------------
// This is a wrapper view that allows us to easily and cleanly
// reuse this component in any future project.
@Composable
fun BottomNavigationBar(tabBarItems: List<TabBarItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable() {
        mutableIntStateOf(0)
    }

    NavigationBar() {
        // looping over each tab to generate the view and navigation for each item
        tabBarItems.forEachIndexed() { index, tabBarItem ->
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon:ImageVector,
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
fun TabBarBadgeView(count: String? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
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