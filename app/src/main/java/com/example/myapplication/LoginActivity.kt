package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data_classes.LoginCredentials
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginForm()
                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginFormPreview() {
    MyApplicationTheme {
        LoginForm()
    }
}

@Composable
fun LoginField (
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Login",
    placeholder: String = "Enter your email to login"
) {
    val focusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }
    val myBrush = remember {
        Brush.linearGradient(
            colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow),//rainbowColors
            start = Offset(0.0f, 50.0f),
            end = Offset(0.0f, 100.0f)
        )
    }

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Email,
            contentDescription = "",
            tint= MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = text,
        onValueChange = {
            onChange(it)
            text = it
        },
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        textStyle = TextStyle(brush = myBrush),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None,
    )
}

@Composable
fun LoginPasswordField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your Password"
) {

    val focusManager = LocalFocusManager.current
    var isPasswordVisible: Boolean by remember { mutableStateOf(false) }
    var text by remember{ mutableStateOf("") }
    val myBrush = remember {
        Brush.linearGradient(
            colors = listOf(Color.Red, Color.Green, Color.Blue),
            start = Offset(0.0f, 50.0f),
            end = Offset(0.0f, 100.0f)
        )
    }

    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Key,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

    TextField(
        value = text,
        onValueChange = {
            onChange(it)
            text = it
        },
        textStyle = TextStyle(brush = myBrush),
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun LoginForm() {
    Surface {
        val context = LocalContext.current
        val currentContext = LocalContext.current
        var pswString: String by remember {
            mutableStateOf("")
        }
        var emailString: String by remember {
            mutableStateOf("")
        }
        var remember: Boolean by remember {
            mutableStateOf(false)
        }

        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {

            LoginField(
                value = "login",
                onChange = { data ->
                    emailString = data
                },
                modifier = Modifier.fillMaxWidth()
            )

            LoginPasswordField(
                value = "password",
                onChange = { data ->
                    pswString = data
                },
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(10.dp))

            LabeledCheckbox(
                label ="Remember Me",
                onCheckChanged = {
                    remember = !remember
                },
                isChecked =  remember
            )

            val loginCredentials = LoginCredentials(eml = emailString, pwd = pswString)

            Button(
                onClick = {
                    if (loginCredentials.checkLoginCredentials(context)) {
                        val user: FirebaseAuth = FirebaseAuth.getInstance()
                        login(context, emailString, pswString, user)
                    }
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }

            Button(
                onClick = {
                    currentContext.startActivity(Intent(currentContext, SignUpActivity::class.java))
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}

private fun login(cxt: Context, eml: String, pwd: String, user: FirebaseAuth) {
    user.signInWithEmailAndPassword(eml, pwd)
        .addOnCompleteListener(cxt)
}

private fun <TResult> Task<TResult>.addOnCompleteListener(context: Context) {
    addOnCompleteListener { taskResult ->
        if (taskResult.isSuccessful) {
            context.startActivity(Intent(context, MainActivity()::class.java))
            (context as Activity).finish()
        }
    }
}

@Composable
fun LabeledCheckbox(
    label: String,
    onCheckChanged: () -> Unit,
    isChecked: Boolean
) {
    Row(
        Modifier
            .clickable(onClick = onCheckChanged)
            .padding(4.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = null
        )
        Spacer(Modifier.size(6.dp))
        Text(label)
    }
}