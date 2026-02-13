package com.example.image

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.image.ui.theme.ImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageTheme {
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
    Column(modifier = Modifier.padding(8.dp)) {
        BannerCompose(contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(12.dp))
//        BannerCompose(contentScale = ContentScale.Fit)
//        Spacer(modifier = Modifier.height(12.dp))
//        BannerCompose(contentScale = ContentScale.Inside)
//        Spacer(modifier = Modifier.height(12.dp))
        CircleAvatar()
        UrlImageCompose()

//        VectorImageCompose()
    }
}

@Composable
fun BannerCompose(contentScale: ContentScale) {
    Image(painter = painterResource(R.drawable.banner),
        contentDescription = "Banner fashion",
        contentScale = contentScale,
        modifier = Modifier.height(150.dp)
            .fillMaxWidth()
            .shadow( // bo goc
                elevation = 8.dp,
                shape = RoundedCornerShape(size = 8.dp)
            ),
//            .aspectRatio(2f),
        alignment = Alignment.TopEnd
    )
}

// lam avatar khung tron
@Composable
fun CircleAvatar() {
    Image(painterResource(R.drawable.avatar),
        contentDescription = "Circle Avatar",
        modifier = Modifier.size(150.dp).clip(CircleShape).border(BorderStroke(2.dp, color = Color.Gray), shape = CircleShape)
    )
}

// hien thi anh tu URL
@Composable
fun UrlImageCompose() {
    Image(
        rememberAsyncImagePainter(
            model = "https://i0.wp.com/thatnhucuocsong.com.vn/wp-content/uploads/2022/09/Hinh-anh-meo-cute-dang-yeu-qua-doi.jpg?ssl=1",

            // hiển thị ảnh tạm thời trong lúc đợi load ảnh từ url
            placeholder = painterResource(id = R.drawable.avatar)
        ),
        contentDescription = null,
        modifier = Modifier.size(150.dp)
    )
}

//@Composable
//fun VectorImageCompose() {
//    Image(imageVector = Icons.Filled.Person, contentDescription = "person")
//}
//
//@Composable
//fun CustomePainterImageCompose() {
//    Image(ColorPainter(Color.Red), contentDescription = null, modifier = Modifier.size(50.dp))
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageTheme {
        HomeScreen()
    }
}