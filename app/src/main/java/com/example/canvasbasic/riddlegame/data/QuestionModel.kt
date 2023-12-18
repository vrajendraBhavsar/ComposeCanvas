package com.example.canvasbasic.riddlegame.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset


data class QuestionModel(
    val globalQueId: String,
//    val startingPoint: Offset,
    val composable: @Composable () -> Unit,
    val associatedAnswerId: String = ""
)
