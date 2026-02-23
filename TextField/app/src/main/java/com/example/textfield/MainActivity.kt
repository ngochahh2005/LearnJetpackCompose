package com.example.textfield

import androidx.compose.runtime.getValue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textfield.ui.theme.TextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun CommonSpace() {
    Spacer(modifier = Modifier.height(18.dp))
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.padding(30.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DemoTextField()
        CommonSpace()
        DemoOutlineTextField()
        CommonSpace()
        PasswordCompose()
    }
}

@Composable
fun DemoTextField() {
    var firstName by remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = firstName,
        onValueChange = { newValue -> // newValue -> <=> it: dùng để cập nhật giá trị mới mỗi khi nhập 1 kí tự từ bàn phím
            firstName = newValue
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold),
        label = {
            Text("Firstname")
        },
        placeholder = {
            Text("Enter your first name")
        },
        leadingIcon = {         // icon ben trai
            Icon(Icons.Default.Person, contentDescription = "firstname")
        },
        trailingIcon = {        // icon ben phai
            IconButton(onClick = {
                firstName = ""
            }) {
                Icon(Icons.Default.Clear, contentDescription = "clear text")
            }
        },
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTrailingIconColor = Color.Magenta,
        ),
        shape = RoundedCornerShape(24.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
//            keyboardType = KeyboardType.Phone,
            capitalization = KeyboardCapitalization.Words   // viet hoa chu cai dau cua tu
        ),
        keyboardActions = KeyboardActions (
            onDone = {
                keyboardController?.hide()
            }
        )
    )
}

@Composable
fun DemoOutlineTextField() {
    var email by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = email,
        onValueChange = {
            newValue ->
            email = newValue
        },
        placeholder = {
            Text("username/email")
        },
        label = {
            Text("username/email")
        },
        leadingIcon = {
            Icon(Icons.Default.Email, contentDescription = "")
        }
    )
}

@Composable
fun PasswordCompose() {
    var password by remember {
        mutableStateOf("")
    }

    var isShowPassword by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
        },
        placeholder = {
            Text("Enter your password")
        },
        label = {
            Text("password")
        },
        leadingIcon = {
            Icon(Icons.Default.Lock, contentDescription = "")
        },
        trailingIcon = {
            IconButton(onClick = {
                isShowPassword = !isShowPassword
            }) {
                if (!isShowPassword) Icon(Icons.Filled.Visibility, contentDescription = "")
                else Icon(Icons.Filled.VisibilityOff, contentDescription = null)
            }
        },
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(24.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    TextFieldTheme {
        HomeScreen()
    }
}