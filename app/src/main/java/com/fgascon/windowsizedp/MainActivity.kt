package com.fgascon.windowsizedp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fgascon.windowsizedp.ui.theme.WindowSizeDpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WindowSizeDpTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val configuration = LocalConfiguration.current
                    val widthType by remember {
                        if (configuration.screenWidthDp < 600)
                            mutableStateOf("Compact")
                        else if (configuration.screenWidthDp < 840)
                            mutableStateOf("Medium")
                        else
                            mutableStateOf("Expanded")
                    }
                    val heightType by remember {
                        if (configuration.screenHeightDp < 600)
                            mutableStateOf("Compact")
                        else if (configuration.screenHeightDp < 840)
                            mutableStateOf("Medium")
                        else
                            mutableStateOf("Expanded")
                    }
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .drawBehind {
                                drawLine(
                                    color = Color.Red,
                                    start = Offset(size.width / 2, 0f),
                                    end = Offset(size.width / 2, size.height),
                                    strokeWidth = 5.dp.toPx()
                                )
                                drawLine(
                                    color = Color.Red,
                                    start = Offset(0f, size.height / 2),
                                    end = Offset(size.width, size.height / 2),
                                    strokeWidth = 5.dp.toPx()
                                )
                                drawLine(
                                    color = Color.Red,
                                    start = Offset(0f, size.height / 2),
                                    end = Offset(size.width, size.height / 2),
                                    strokeWidth = 5.dp.toPx()
                                )
                            },
                        contentAlignment = CenterStart
                    ) {
                        Text(
                            text = "Width: ${configuration.screenWidthDp}", modifier = Modifier
                                .padding(16.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .background(
                                    color = Color.White,
                                )
                                .padding(8.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = TopCenter
                    ) {
                        Text(
                            text = "Height: ${configuration.screenHeightDp}", modifier = Modifier
                                .padding(16.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(8.dp),
                                )
                                .background(
                                    color = Color.White,
                                )
                                .padding(8.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = End,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(text = "Width Type: $widthType", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = "Height Type: $heightType")
                    }
                }
            }
        }
    }
}
