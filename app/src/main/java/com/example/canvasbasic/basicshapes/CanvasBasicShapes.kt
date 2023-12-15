package com.example.canvasbasic.basicshapes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.canvasbasic.ui.theme.Purple40

@Composable
fun CanvasBasicShapes() {
    Canvas(
        modifier = Modifier
            .background(Color.Red)
            .padding(20.dp)
            .size(300.dp)
    ) {
        drawRect(
            color = Color.Black,
            size = size,
        )

        drawRect(
            color = Color.Red,
            topLeft = Offset(100f, 100f),
            size = Size(100f, 100f)
        )

        drawRect(
            color = Color.Yellow,
            topLeft = Offset(300f, 300f),
            size = Size(200f, 200f),
            style = Stroke(
                width = 5.dp.toPx()
            )
        )

        drawLine(
            start = Offset(200f, 150f),
            end = Offset(size.width, 150f),
            color = Color.Blue,
            strokeWidth = 10f
        )

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color.Red, Color.Blue),
                center = Offset(700f, 700f),
                radius = 100f
            ),
            radius = 100f,
            center = Offset(700f, 700f)
        )

        drawLine(
            color = Color.Red,
            start = Offset(700f, 0f),
            end = Offset(700f, 600f),
            strokeWidth = 10f
        )

        drawArc(
            color = Purple40,
            startAngle = 0f,
            sweepAngle = 270f,
            useCenter = false,
            style = Stroke(
                width = 5f
            ),
            topLeft = Offset(100f, 500f),
            size = Size(200f, 200f)
        )

        drawOval(
            color = Color.Green,
            topLeft = Offset(400f, 550f),
            size = Size(150f, 250f),
            style = Stroke(width = 5.dp.toPx())
        )
    }
}