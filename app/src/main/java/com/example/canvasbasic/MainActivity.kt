package com.example.canvasbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.canvasbasic.riddlegame.ConnectionRiddleGame
import com.example.canvasbasic.ui.theme.CanvasBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasBasicTheme {
                ConnectionRiddleGame()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CanvasBasicTheme {
        ConnectionRiddleGame()
    }
}