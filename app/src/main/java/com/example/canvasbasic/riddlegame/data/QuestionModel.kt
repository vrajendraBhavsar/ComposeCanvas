package com.example.canvasbasic.riddlegame.data

import androidx.compose.runtime.Composable


data class QuestionModel(
    val globalQueId: String,
    val composable: @Composable () -> Unit,
    val associatedAnswerId: String = ""
    )
