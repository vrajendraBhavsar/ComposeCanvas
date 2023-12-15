package com.example.canvasbasic.riddlegame.data

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Line(
    val start: Offset,
    var end:Offset,
    val color: Color = Color.Black,
    val strokeWidth: Dp = 1.dp
)