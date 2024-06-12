package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data_classes.SignupCredentials
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.math.pow

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpForm()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpFormPreview() {
    MyApplicationTheme {
        SignUpForm()
    }
}

@Composable
fun SignUpField (
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Sign Up",
    placeholder: String = "Enter email"
) {

    var textInput: String by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.ContactMail,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value = textInput,
        onValueChange = {
            onChange(it)
            textInput = it
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
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@Composable
fun PasswordField (
    value: String,
    onChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String
) {

    val focusManager = LocalFocusManager.current
    var isPasswordVisible: Boolean by remember { mutableStateOf(false) }
    var pswTxt by remember {
        mutableStateOf("")
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
        value = pswTxt,
        onValueChange = {
            onChange(it)
            pswTxt = it
        },
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions (
            onDone = {
                if (value == "Sign up")
                    focusManager.moveFocus(FocusDirection.Down)
                else
                    onSubmit()
            }
        ),
        placeholder = { Text(text = placeholder) },
        label = { Text(text = label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )

}

@Composable
fun SignUpForm() {

    Surface {

        val context: Context = LocalContext.current
        val currentContext: Context = LocalContext.current
        var emailTxt: String by remember {
            mutableStateOf("")
        }
        var pswTxt: String by remember {
            mutableStateOf("")
        }
        var confirmPswTxt: String by remember {
            mutableStateOf("")
        }
        val kc = LocalSoftwareKeyboardController.current
//        var text by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }
        val callback = {
            result = try {
                val num = confirmPswTxt.toFloat()
                num.pow(2.0F).toString()
            } catch (ex: NumberFormatException) {
                ""
            }
            kc?.hide()
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {

            SignUpField(
                value = "sign up",
                onChange = { data ->
                    emailTxt = data
                },
                modifier = Modifier.fillMaxWidth()
            )

            PasswordField(
                value = "New Password",
                onChange = { data ->
                    pswTxt = data
                },
                onSubmit = {
                    callback()
                },
                modifier = Modifier.fillMaxWidth(),
                label = "password",
                placeholder = "Create a new Password"
            )

            PasswordField(
                value = "Confirm New Password",
                onChange = { data ->
                    confirmPswTxt = data
                },
                onSubmit = {
                    callback()
                },
                modifier = Modifier.fillMaxWidth(),
                label = "Confirm password",
                placeholder = "Confirm the new Password"
            )

            val signupCredentials = SignupCredentials(eml = emailTxt, pwd = pswTxt, confirmPwd = confirmPswTxt)

            Button(
                onClick = {
                    if (
//                        true
                        signupCredentials.checkSignupCredentials(signupCredentials, context)
                        ) {
                        signupCredentials.newUserAuth(context)
                        currentContext.startActivity(Intent(currentContext, UserAccountActivity::class.java))
//                        (context as Activity).finish()
                    }
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sing Up")
            }
        }
    }
}