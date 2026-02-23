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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
//    Column(
//        modifier = modifier.fillMaxWidth()
//    ) {
//
//        ConstraintLayout(
//            modifier = Modifier.paint(painterResource(R.drawable.ic_launcher_background))
//
//        ) {  }

        Ingredients()
//    }
}

@Composable
fun Ingredients(modifier: Modifier = Modifier) {
    ConstraintLayout() {

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val horizontalPadding = 16.dp
        val itemWidth = (screenWidth - (horizontalPadding * 2)) / 3

        val (tvIngredients, imgArrow) = createRefs()

        Text(
            text = "Ingredients",
            style = TextStyle(
                lineHeight = 14.sp,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFFB7D8A)
            ),
            modifier = Modifier.constrainAs(tvIngredients) {
                top.linkTo(parent.top, margin = 30.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )

        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFFFB7D8A),
            modifier = Modifier.size(24.dp).constrainAs(imgArrow) {
                start.linkTo(tvIngredients.end, margin = 6.dp)
                bottom.linkTo(tvIngredients.bottom)
            }
        )

        val (line1, line2) = createRefs()

        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(line1) {
                start.linkTo(tvIngredients.start)
                top.linkTo(tvIngredients.bottom, margin = 14.dp)
            }
//            .background(color = Color.Black)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Ingredient(icon = R.drawable.mint_leaves, value = 8, unit = null, name = "Mint Leaves", modifier = Modifier.size(itemWidth))
                Ingredient(icon = R.drawable.lemon, value = 2, unit = null, name = "Lemon Wedges", modifier = Modifier.size(itemWidth))
                Ingredient(icon = R.drawable.lemon_juice, value = 30, unit = "ml", name = "Lemon Juice", modifier = Modifier.size(itemWidth))
            }
        }

        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(line2) {
                start.linkTo(line1.start)
                top.linkTo(line1.bottom, margin = 12.dp)
            }
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Ingredient(icon = R.drawable.ice_cubes, value = 6, unit = null, name = "Ice Cubes", modifier = Modifier.size(itemWidth))
                Ingredient(icon = R.drawable.sugar, value = 2, unit = "tbsp", name = "Sugar", modifier = Modifier.size(itemWidth))
                Ingredient(icon = R.drawable.soda, value = 30, unit = "ml", name = "Club Soda", modifier = Modifier.size(itemWidth))
            }
        }
    }
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

        val verticalGuideLine = createGuidelineFromStart(0.4f)
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

        unit?.let {     // check unit khong null
            Text(
                text = unit.toString(),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    color = valueTextColor,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false      // Giúp loại bỏ khoảng trống thừa phía trên/dưới font
                    )
                ),
                modifier = Modifier.constrainAs(tvUnit) {
                    top.linkTo(tvValue.bottom)
                    end.linkTo(tvValue.end)
                }
            )
        }

        val bottomBarrier = createBottomBarrier(tvValue, tvUnit)
        val endGuideLine20 = createGuidelineFromEnd(0.2f)
        Text(
            text = name,
            style = TextStyle(
                color = Color(0xFF1E2742),
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                lineHeight = 14.sp,
            ),
            modifier = Modifier.constrainAs(tvName) {
                start.linkTo(verticalGuideLine)
                bottom.linkTo(bottomBarrier)
                top.linkTo(tvValue.top)
                end.linkTo(endGuideLine20)
                width = androidx.constraintlayout.compose.Dimension.fillToConstraints
            },
            maxLines = 2,
            textAlign = TextAlign.Start
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
                modifier = Modifier.size(125.dp)
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