package com.example.bacsiclayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bacsiclayout.ui.theme.BacsicLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BacsicLayoutTheme {
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
//    testBox()
//    testRow()
//    testColumn()
//    OTPCompose()
    ScrollFeature()
}

@Composable
fun ScrollFeature() {
    Box() {
        Row(
            modifier = Modifier
                .size(300.dp, 400.dp)
                .background(Color.Black)
//                .verticalScroll(rememberScrollState())    // dung voi Column
                .horizontalScroll(rememberScrollState())    // dung voi Row
        ) {
            BoxItem(color = Color.Blue)
            BoxItem(color = Color.Green)
            BoxItem(color = Color.Yellow)
            BoxItem(color = Color.Blue)
            BoxItem(color = Color.Green)
            BoxItem(color = Color.Yellow)
            BoxItem(color = Color.Blue)
            BoxItem(color = Color.Green)
            BoxItem(color = Color.Yellow)
        }
    }
}

@Composable
fun OTPCompose() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Code has been send to your phone number")
            CommonSpace()
            OutlinedTextField(value = "", onValueChange = {})
            CommonSpace()
            Text("Resend code in 00:30")
        }

        CommonSpace()

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(horizontal = 24.dp)
        ) {
            Text("Verify")
        }
    }
}

@Composable
fun CommonSpace() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun testColumn() {
    Box() {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.size(300.dp, 700.dp).background(Color.Black),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            BoxItem(color = Color.Blue)
            BoxItem(color = Color.Green)
            BoxItem(color = Color.Yellow)
        }
    }
}

@Composable
fun testRow() {
//    Box() {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.size(400.dp, 300.dp)
//                                .background(color = Color.Black),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            BoxItem(color = Color.Blue)
//            BoxItem(color = Color.Green)
//            BoxItem(color = Color.Yellow)
//        }
//    }
    Box() {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.weight(2f))
            Icon(Icons.Default.Send, contentDescription = "Send", modifier = Modifier.size(36.dp).weight(1f))
        }
    }
}

@Composable
fun testBox() {
    Box(
        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
    ) {             // Ứng dụng: show loading khi người dùng đợi kết quả trả về
        BoxItem(color = Color.Blue, size = 200.dp, modifier = Modifier.matchParentSize().align(Alignment.TopStart))
        BoxItem(color = Color.Red, size = 150.dp, modifier = Modifier.align(Alignment.TopEnd))
        BoxItem(color = Color.Yellow, modifier = Modifier.align(Alignment.BottomStart))
        BoxItem(color = Color.Magenta, modifier = Modifier.align(Alignment.BottomEnd))
        BoxItem(color = Color.Black, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun BoxItem(modifier: Modifier = Modifier, color: Color, size: Dp = 100.dp) {
    Box(modifier = modifier.size(size).background(color = color))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    BacsicLayoutTheme {
        HomeScreen()
    }
}