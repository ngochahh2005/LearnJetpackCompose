package com.example.button


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.button.ui.theme.ButtonTheme
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
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
fun HomeScreen() {
    Column(modifier = Modifier.padding(24.dp)) {
        SimpleButton()
        Spacer(modifier = Modifier.height(12.dp))
//        DisableSimpleButton()
//        RoundedCornerButton()
//        BorderSimpleButton()
//        ElevationSimpleButton()
//        OutlinedButton(onClick = {}) {
//            Text("Outlined Button")
//        }
//        TextButton(onClick = {}) {
//            Text("Text Button")
//        }
//        IconButton (onClick = {}) {
//            Icon(Icons.Default.Person, contentDescription = "")
//        }
//        DemoClickable()
        DemoDetectTapGestures()
    }
}

@Composable
fun SimpleButton() {
    val cnt = remember {
        mutableStateOf(0)
    }
    Column {
        Text(text = "click count ${cnt.value}")
        Button(
            onClick = {
                cnt.value++
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow, // background
                contentColor = Color.Blue
            ),
        ) {
            Column {
                Icon(Icons.Default.ShoppingCart, contentDescription = "")
                Text("Add to cart")
            }
        }
    }
}

@Composable
fun DemoClickable() {
    Column(modifier = Modifier.padding(12.dp).clickable{

    }) {
        Image(painterResource(R.drawable.banner), contentDescription = "")
        Text("Product name")
        Text("200$")
    }
//    Text(text = "Click me", modifier = Modifier.clickable{
//
//    })
}

@Composable
fun DemoDetectTapGestures() {
    val textContent = remember {
        mutableStateOf("")
    }
    Column {
        Text(text = textContent.value)
        Text(
            text = "something",
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        textContent.value = "double tap"
                    },
                    onLongPress = {
                        textContent.value = "long press"
                    },
                    onPress = {
                        textContent.value = "press"
                    }
                )
            }
        )
    }
}

// bo goc button
@Composable
fun RoundedCornerButton() {
    Button(
        onClick = {},
        shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp, topEnd = 12.dp),

    ) { }
}

@Composable
fun BorderSimpleButton() {
    Button(
        onClick = {},
        border = BorderStroke(width = 2.dp, color = Color.Magenta),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
        ) {
        Text("Checkout", color = Color.Magenta)
    }

}

@Composable
fun ElevationSimpleButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
        ) {
        Text("Text")
    }
}

//chi an duoc khi thoa man dieu kien
@Composable
fun DisableSimpleButton() {
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(
            disabledContentColor = Color.White,
            disabledContainerColor = Color.Gray
        ),
        enabled = false
        ) {
        Text("Disable button")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ButtonTheme {
        HomeScreen()
    }
}