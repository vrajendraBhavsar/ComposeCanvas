package com.example.canvasbasic.riddlegame.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.geometry.Offset


data class QuestionModel(
    val globalQueId: String,
    @DrawableRes val imageResource: Int,
    var startingPoint: Offset = Offset.Zero,
    val associatedAnswerId: String = ""
)
