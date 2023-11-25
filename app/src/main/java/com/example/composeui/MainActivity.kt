package com.example.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeui.ui.customView.OverlappingCircularImageRow
import com.example.composeui.ui.customView.OverlappingRow
import com.example.composeui.ui.theme.ComposeUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUITheme {
                OverlappingCircularImageRow(
                    list = listOf(
                        R.drawable.ic_compose,
                        R.drawable.ic_compose,
                        R.drawable.ic_compose,
                        R.drawable.ic_compose,
                        R.drawable.ic_compose,
                        R.drawable.ic_compose,
                        R.drawable.ic_compose,
                        R.drawable.ic_compose
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.DarkGray),
                    imageSize = 90.dp,
                    overlappingValue = 0.4f
                )
            }
        }
    }
}