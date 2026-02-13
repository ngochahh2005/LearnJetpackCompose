package com.example.constraintlayout

import androidx.constraintlayout.compose.ConstraintLayout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.constraintlayout.ui.theme.ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintLayoutTheme {
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
fun HomeScreen(modifier: Modifier = Modifier) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

}

@Composable
fun Ingredient(
    @DrawableRes icon: Int,
    value: Int,
    unit: String?,
    name: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = Color(0xFFFEF9E4)
    val borderColor = Color(0xFFFBE897B2).copy(alpha = 0.7f)

    ConstraintLayout(
        modifier = modifier
            .background(color = backgroundColor, shape = CircleShape)
            .border(BorderStroke(width = 1.dp, color = borderColor))
    ) {
        val horizontalGuideLine = createGuidelineFromTop(0.5f)

        val imgIcon = createRef()
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.constrainAs(imgIcon) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(horizontalGuideLine)
                height = androidx.constraintlayout.compose.Dimension.fillToConstraints
            },
            contentScale = ContentScale.FillHeight
        )

        // Tạo 3 references cùng lúc: tvValue, tvUnit, tvName (tv: textView
        val (tvValue, tvUnit, tvName) = createRefs()

        val verticalGuideLine = createGuidelineFromStart(0.5f)
        val valueTextColor = Color(0xFFFB7D8A)
        Text(
            text = value.toString(),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp,
                lineHeight = 14.sp,
                color = valueTextColor
            ),
            modifier = Modifier.constrainAs(tvValue) {
                top.linkTo(horizontalGuideLine, margin = 2.dp)
                end.linkTo(verticalGuideLine, margin = 2.dp)
            }
        )

        Text(
            text = unit.toString(),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                color = valueTextColor
            )
        )

    }
}

@Preview
@Composable
fun IngredientPreview() {
    Row {
        ConstraintLayoutTheme {
            Ingredient(
                icon = R.drawable.lemon_juice,
                value = 30,
                unit = "ml",
                name = "Lemon juice",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    ConstraintLayoutTheme {
        HomeScreen()
    }
}