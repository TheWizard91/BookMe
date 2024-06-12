package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.test.ui.theme.BookMeTheme

class UpdateUserAccountActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UpdateAccountForm()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UpdateAccountForm() {
    var fnameTxt: String by remember { mutableStateOf("") }
    var lnameTxt: String by remember { mutableStateOf("") }
    var moreTxt:String by remember { mutableStateOf("") }
    var phone: String by remember { mutableStateOf("") }
    var profImage: String by remember { mutableStateOf("") }
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

            PhotoSelection { data -> profImage = data }
            Log.d("profImage",profImage)

            Spacer(modifier = Modifier.padding(8.dp))

            // Firstname section.
            FirstNameField(
                value ="firstname",
                onChange = { data ->
                    fnameTxt = data
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Lastname section,
            LastNameField(
                value = "lastname",
                onChange = { data ->
                    lnameTxt = data
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Who are you section.
            WhoAreYouField(
                value = "who are you",
                onChange = { data ->
                    moreTxt = data
                },
                onSubmit = {},
                modifier = Modifier.fillMaxWidth(),
            )

            // Phone number section.
            PhoneNumberField(
                value = "phone number",
                onChange = { data ->
                    phone = data
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
            FilledButton(onSubmit = {}, profImage,fnameTxt,lnameTxt,phone,gender,moreTxt,currentContext)
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookMeTheme {
        UpdateAccountForm()
    }
}