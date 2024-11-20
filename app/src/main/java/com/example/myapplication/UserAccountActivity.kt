package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Subject
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.myapplication.classes.databases.FireStoreDatabase
import com.example.myapplication.classes.databases.NotificationsDatabase
import com.example.myapplication.classes.databases.StorageDatabase
import com.example.myapplication.data_classes.NewUserCredentials
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.firebase.auth.FirebaseAuth

class UserAccountActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AccountForm()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AccountForm() {
    var firstnameTxt: String by remember { mutableStateOf("") }
    var lastnameTxt: String by remember { mutableStateOf("") }
    var moreInfoAboutUserTxt:String by remember { mutableStateOf("") }
    var phoneNumberTxt: String by remember { mutableStateOf("") }
    var profImageUriTxt: String by remember { mutableStateOf("") }
    var profileImageJpg: String by remember {
        mutableStateOf("")
    }
    var gender: String by remember { mutableStateOf("Male") }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {

            // Heading of the app.
            Text(
                text = stringResource(id = R.string.basic_info),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.displaySmall,
            )

            Text(
                text = stringResource(id = R.string.account_setting_title),
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                fontFamily = FontFamily.Monospace,
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(8.dp))

            PhotoSelectionForNewUser { imageUri, imageJpg ->
                profImageUriTxt = imageUri
                profileImageJpg = imageJpg
            }

            Spacer(modifier = Modifier.padding(8.dp))

            // Firstname section.
            FirstNameField(
                value ="firstname",
                onChange = { data ->
                    firstnameTxt = data
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Lastname section,
            LastNameField(
                value = "lastname",
                onChange = { data ->
                    lastnameTxt = data
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Who are you section.
            WhoAreYouField(
                value = "who are you",
                onChange = { data ->
                    moreInfoAboutUserTxt = data
                },
                onSubmit = {},
                modifier = Modifier.fillMaxWidth(),
            )

            // Phone number section.
            PhoneNumberField(
                value = "phone number",
                onChange = { data ->
                    phoneNumberTxt = data
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Radio buttons for gender choice.
            Row {
                RadioButton(
                    selected = gender == "Male",
                    onClick = { gender = "Male"},
                )

                Text(text = "Male")

                RadioButton(
                    selected = gender == "Female",
                    onClick = { gender = "Female" },
                )

                Text(text = "Female")
            }

            val currentContext = LocalContext.current
//            Log.d("profImageUriTxt:",profImageUriTxt.toString())
            FilledButtonForNewUser(onSubmit = {}, profImageUriTxt, profileImageJpg, firstnameTxt, lastnameTxt, phoneNumberTxt, gender, moreInfoAboutUserTxt, currentContext)
        }
    }

}

@Composable
private fun FilledButtonForNewUser(
    onSubmit: (String) -> Unit,
    profImage: String,
    profileImageJpg: String,
    firstnameTxt: String,
    lastnameTxt: String,
    phoneNumberTxt: String,
    gender: String,
    moreInfoAboutUserTxt: String,
    currentContext: Context
) {
    Button(
        onClick = {
            onSubmit(profImage,profileImageJpg, firstnameTxt, lastnameTxt, phoneNumberTxt, gender, moreInfoAboutUserTxt)
            currentContext.startActivity(Intent(currentContext, LoginActivity::class.java))
//        (context as Activity).finish()
        }
    ) {
        Text(text = "Submit")
    }
}

private fun onSubmit(
    profImageTxt: String,
    profileImageJpg: String,
    firstnameTxt: String,
    lastnameTxt: String,
    phoneNumberTxt: String,
    gender: String,
    moreInfoAboutUserTxt: String
) {
    val userAuth = FirebaseAuth.getInstance()
    val newUserCredentials = NewUserCredentials(
        profImageTxt.toUri(),
        profileImageJpg,
        firstnameTxt,
        lastnameTxt,
        phoneNumberTxt,
        gender,
        moreInfoAboutUserTxt,
        userAuth.currentUser!!.uid,
        userAuth.currentUser!!.email.toString()
    )
    setDatabases(newUserCredentials)
}

@Composable
fun PhoneNumberField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier,
    label: String = "phone number",
    placeholder: String = "What's Your Number."
) {

    var phoneNumberText: String by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val inputStyle = remember {
        Brush.linearGradient(
            colors = arrayListOf(Color.Red, Color.Green, Color.Blue),
            start = Offset(0.0f, 50.0f),
            end = Offset(0.0f, 100.0f)
        )
    }

    val phoneNumberIcon = @Composable {
        Icon(
            Icons.Default.ContactPhone,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = phoneNumberText,
        onValueChange = {
            onChange(it)
            phoneNumberText = it
        },
        modifier = modifier,
        leadingIcon = phoneNumberIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions (
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        textStyle = TextStyle(brush = inputStyle),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WhoAreYouField(
    value: String,
    onChange: (String) -> Unit,
    onSubmit: (String) -> Unit,
    modifier: Modifier,
    label: String = "Who Are You?",
    placeholder: String = "Tell More About Yourself."
) {
    var moreInfoAboutUserText by remember { mutableStateOf("") }

    val inputStyle = remember {
        Brush.linearGradient(
            colors = arrayListOf(Color.Red, Color.Green, Color.Blue),
            start = Offset(0.0f, 50.0f),
            end = Offset(0.0f, 100.0f)
        )
    }
    val textIcon = @Composable {
        Icon(
            Icons.AutoMirrored.Filled.Subject,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val focusManager = LocalFocusManager.current

    TextField(
        value = moreInfoAboutUserText,
        onValueChange = {
            onChange(it)
            moreInfoAboutUserText = it
        },
        modifier = modifier,
        leadingIcon = textIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions (
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        textStyle = TextStyle(brush = inputStyle),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )

}

@Composable
fun LastNameField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier,
    label: String = "Lastname",
    placeholder: String = "Enter your lastname"
) {

    var lastnameText by remember {
        mutableStateOf("")
    }

    val inputStyle = remember {
        Brush.linearGradient(
            colors = listOf(Color.Red, Color.Green, Color.Blue),
            start = Offset(0.0f, 50.0f),
            end = Offset(0.0f, 100.0f)
        )
    }

    val lastnameIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    val focusManager = LocalFocusManager.current

    TextField(
        value = lastnameText,
        onValueChange = {
            onChange(it)
            lastnameText = it
        },
        modifier = modifier,
        leadingIcon = lastnameIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions (
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        textStyle = TextStyle(brush = inputStyle),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )

}

@Composable
private fun PhotoSelectionForNewUser(onGetPath: (String, String) -> Unit) {

    var isClicked by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf("") }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            // This is the original path,but need a new one for Firebase
            imageUri = uri.toString()
//            val randomUri: String = UUID.randomUUID().toString()
            val imagePathList: List<String> = imageUri.split('/').toList()
            val imageJpg: String = imagePathList[imagePathList.size - 1] + ".jpg"
            onGetPath(imageUri, imageJpg)
            isClicked = !isClicked
//            Log.d("imageUri", imageUri)
//            Log.d("imageJpg", imageJpg)
        }
    )

    fun launchPhotoPicker() {
        singlePhotoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    val offsetInPx = with(LocalDensity.current) { 16.dp.roundToPx() }
//    val imageWhenNoneIsYetSelected = ImageVector.vectorResource(id = R.drawable.profile_picture)

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .width(200.dp)
            .background(
                Color.LightGray.copy(alpha = .5f),
                RoundedCornerShape(8.dp)
            )
            .padding(4.dp),
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier
                    // Set image size to 40
                    .size(200.dp, 200.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(
                        1.5.dp,
                        MaterialTheme.colorScheme.primary, CircleShape
                    )
                    .clickable(onClick = {
                    }),
            )
            IconButton(
                onClick = {
                    isClicked = !isClicked
                    launchPhotoPicker()
                },
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .offset {
                        IntOffset(-offsetInPx, offsetInPx)
                    }
                    .shadow(
                        2.dp,
                        RoundedCornerShape(40)
                    )
                    .align(Alignment.CenterVertically)
                    .background(Color.White),
            ) {
                Icon(
                    imageVector = Icons.Rounded.PhotoCamera,
                    contentDescription = "Add photo",
                    tint = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp, 50.dp),
                )
            }
        }
    }
}

private fun setDatabases(user: NewUserCredentials) {
    /*Store user info in storage, real time, and cloud databases.
    * pre: newCredentials is the user object.
    * post: user added to storage (for uer profile image),
    *       real time for notifications, and
    *       cloud for user info to be stored. */
    
    val currentUser = FirebaseAuth.getInstance()
    
    // Storage database for user profile image.
    val storage = StorageDatabase()
    // Path for all images of user
    val pathOne: String = currentUser.uid +
            "/all_profile_images/" +
            user.userProfileImageImage.toString()+".jpg"
    // Path for current profile image
    val pathTwo: String = currentUser.uid +
            "/current_profile_image/" +
             user.userProfileImageImage.toString() + ".jpg"
    // Set storage
    storage.setImageToStorage(path = pathOne, imageJpgFile = user.userProfileImageImage!!)
    storage.setImageToStorage(path = pathTwo, imageJpgFile = user.userProfileImageImage!!)


    // Realtime database for notifications.
    val notifications = NotificationsDatabase()
    notifications.setNotifications("Welcome ", user)

    val firestore = FireStoreDatabase(user)
    firestore.setFirestoreDatabase()
}

@Composable
private fun ImageLayoutView(selectedImages: List<Uri?>) {
    LazyRow {
        items(selectedImages) { uri ->
            AsyncImage(
                model = uri,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
private fun FirstNameField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier,
    label: String = "Firstname",
    placeholder: String = "Enter your firstname",
) {
    var firstnameText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val inputStyle = remember {
        Brush.linearGradient(
            colors = listOf(Color.Red, Color.Green, Color.Blue),
            start = Offset(0.0f, 50.0f),
            end = Offset(0.0f, 100.0f)
        )
    }

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = firstnameText,
        onValueChange = {
            onChange(it)
            firstnameText = it
        },
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions (
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        textStyle = TextStyle(brush = inputStyle),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AccountViewPreview() {
    MyApplicationTheme {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AccountForm()
        }
    }
}