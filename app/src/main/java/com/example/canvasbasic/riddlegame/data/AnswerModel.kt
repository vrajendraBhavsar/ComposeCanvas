package com.example.canvasbasic.riddlegame.data

import androidx.compose.ui.geometry.Offset
data class AnswerModel(
    val globalAnsId: String,
    val text: String,
    var updatedEndOffset: Offset = Offset.Zero,
)
