package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                Surface (
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
    Column() {
        GreetingText()
        Spacer(modifier = Modifier.height(12.dp))
        MultipleStylesText()
    }
}

//val customH1: TextStyle
//    get() = TextStyle(
//        color = Color.Blue
//    )

@Composable
fun GreetingText() {
    Text(text = stringResource(id = R.string.test_text),
        color = Color.Red,
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left,
        fontFamily = FontFamily.Cursive,
//        style = MaterialTheme.typography.bodyMedium
//        style = customH1
//        textDecoration = TextDecoration.LineThrough
        overflow = TextOverflow.Ellipsis,
        maxLines = 2
    )
}

@Composable
fun MultipleStylesText() {
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textAlign = TextAlign.Right)) {
            withStyle(style = SpanStyle(color = Color.Green)) {
                append("H")
            }
            withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                append("ello")
            }
            withStyle(style = SpanStyle(color = Color.Magenta)) {
                append("W")
            }
            append("orld")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTheme {
        HomeScreen()
    }
}