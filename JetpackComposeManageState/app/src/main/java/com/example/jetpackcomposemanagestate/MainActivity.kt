package com.example.jetpackcomposemanagestate

import android.annotation.SuppressLint
import androidx. compose. runtime. getValue
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposemanagestate.ui.theme.JetpackComposeManageStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeManageStateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

//state less
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp() {
    // state scaffold
    // coroutine scope
    // navController
    // --> khong can co nhung cai nay trong function MyApp nua vi da co class MyAppState
    val appState = MyAppState()
    Scaffold() {
        appState.navigate("")
        appState.showMessageError("Loi roi")
    }
}

// state holder
class MyAppState() {
    // state scaffold
    // coroutine scope
    // navController
    // --> giu cac trang thai cua MyApp
    fun navigate(screen: String) {

    }

    fun showMessageError(msg: String) {

    }
}

// state full - chứa state
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Log.e("frank", "Login screen start")
//        Welcome()

//        Email(loginViewModel.email) {
//            loginViewModel.updateEmail(it)
//        }
//
//        Password(loginViewModel.password) {
//            loginViewModel.updatePassword(it)
//        }
//
//        Log.e("frank", "Login screen end")
//
//        Button(onClick = {
//            // login(email, password)
//        }) {
//            Text("Login")
//        }

        if (loginViewModel.screenState == LoginScreenState.Loading()) {
            // Loading
        } else if (loginViewModel.screenState == LoginScreenState.Success()) {
            // Success
        } else {
            // Fail
        }
    }
}

class LoginViewModel: ViewModel() {
    var screenState = mutableStateOf(LoginScreenState.Loading())

    fun fetchData() {
//        screenState = success
//        screenState = fail

    }
}

sealed class LoginScreenState{  //sealed class giúp code an toàn hơn và dễ đọc hơn, giúp  định nghĩa rõ ràng: "Ứng dụng của tôi chỉ có thể ở trong các trạng thái A, B hoặc C; không bao giờ có cái thứ D nằm ngoài tầm kiểm soát".
    class Loading: LoginScreenState()
    class Success(): LoginScreenState()
    class Fail(): LoginScreenState()
}

// state holder - bản chất là class bình thường để lưu trữ các state
//class LoginViewModel: ViewModel() {
//    var email by mutableStateOf("")
//        private set
//
//    var password by mutableStateOf("")
//        private set
//
//    fun updateEmail(newEmail: String) {
//        email = newEmail
//    }
//
//    fun updatePassword(newPassword: String) {
//        password = newPassword
//    }
//
//    fun fetchData() {
//        // loading
//
//        // success<List<songs>>
//
//        // fail
//    }
//}

//state full
@Composable
fun SignUpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by rememberSaveable {     // giup giu du lieu khi bi xoay man hinh/ co cuoc goi den
            mutableStateOf("")
        }
        Email(email) {
            email = it
        }
    }
}

//state less - không chứa state nào
@Composable
fun Welcome() {
    Log.e("frank", "welcome start")
    Text(text = "Login to your account")
    Log.e("frank", "welcome end")
}

// state less
@Composable
fun Email(email:String, onEmailChange: (String) -> Unit) {
    Log.e("frank", "Email")
    OutlinedTextField(value = email, onValueChange = onEmailChange)
}

@Composable
fun Password(password: String, onPasswordChange: (String) -> Unit) {
    Log.e("frank", "Password")
    OutlinedTextField(value = password, onValueChange = onPasswordChange)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    JetpackComposeManageStateTheme {
        LoginScreen()
    }
}