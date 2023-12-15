package com.example.canvasbasic.riddlegame.data

import androidx.compose.runtime.Composable

/*data class AnswerModel(
    val key: String,
    val text: String
)*/

data class AnswerModel(val globalAnsId: String, val composable: @Composable () -> Unit)
