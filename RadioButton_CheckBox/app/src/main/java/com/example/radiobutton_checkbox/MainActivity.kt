package com.example.radiobutton_checkbox

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.getValue
import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.radiobutton_checkbox.ui.theme.RadioButton_CheckBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioButton_CheckBoxTheme {
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
    Column(modifier = Modifier.padding(start = 40.dp, top = 40.dp, bottom = 24.dp, end = 24.dp)) {
//        Radiobutton()
//        DemoRadioButtonWithTitle("Female")
//        DemoCustomRadioButton("Male")
        DemoCheckboxWithTitle("Coffee")
        Spacer(modifier = Modifier.height(12.dp))
        DemoCustomChecbox("Latte")
    }
}

@Composable
fun Radiobutton() {
    RadioButton(
        selected = true,
        onClick = {},
        colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Green,
            disabledSelectedColor = Color.Gray
        )
    )
    RadioButton(
        selected = false,
        onClick = {},
        colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Green,
            disabledSelectedColor = Color.Gray
        )
    )
    RadioButton(
        enabled = false,
        selected = false,
        onClick = {},
        colors = RadioButtonDefaults.colors(
            selectedColor = Color.Red,
            unselectedColor = Color.Green,
            disabledSelectedColor = Color.Gray
        )
    )
}

@Composable
fun DemoRadioButtonWithTitle(title: String) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        RadioButton(selected = true, onClick = {})
//        Text(text = title, color = Color.Cyan)
//    }

    // bien de luu lai trang thai cua radio button duoc chon hay chua
    var isSelected by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.selectable(
            selected = isSelected,
            onClick = {isSelected = !isSelected},    // khi click vao thi dang false -> true va nguoc lai

        )
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Green,
                disabledSelectedColor = Color.Gray
            )
        )
        Text(text = title, color = Color.Black, modifier = Modifier.padding(start = 16.dp))
    }
}

@Composable
fun DemoCustomRadioButton(title: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    CustomRadioButton(title = "Male", isSelected = isSelected) {
        isSelected = !isSelected    // onClick
    }
}

@Composable
fun CustomRadioButton(title: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier.selectable(
            selected = isSelected,
            onClick = onClick,
            role = Role.RadioButton
        )
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Green,
                disabledSelectedColor = Color.Gray
            )
        )
        Text(text = title, color = Color.Black, modifier = Modifier.padding(start = 16.dp))
    }
}

@Composable
fun DemoCheckboxWithTitle(title: String) {
    var isChecked by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier.selectable(
            selected = isChecked,
            onClick = {
                isChecked = !isChecked
            },
            role = Role.Checkbox
        )
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Magenta,
                uncheckedColor = Color.Gray
            )
        )
        Text(text = title, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun DemoCustomChecbox(title: String) {
    var isChecked by remember {
        mutableStateOf(false)
    }
    CustomCheckBox(title, isChecked) { 
        isChecked = !isChecked
    }
}

@Composable
fun CustomCheckBox(title: String, isChecked: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier.selectable(
            selected = isChecked,
            onClick = onClick,
            role = Role.Checkbox
        )
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Magenta,
                uncheckedColor = Color.Gray
            )
        )
        Text(text = title, modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    RadioButton_CheckBoxTheme {
        HomeScreen()
    }
}