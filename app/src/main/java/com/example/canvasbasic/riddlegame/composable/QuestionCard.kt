package com.example.canvasbasic.riddlegame.composable

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun QuestionCard(
    @DrawableRes imageResource: Int, queRectList: SnapshotStateList<Rect>, startOffset: (Offset) -> Unit
) {
    var rect: Rect? by remember { mutableStateOf(null) }
    var queOffset: Offset by remember { mutableStateOf(Offset.Unspecified) }

    Card(modifier = Modifier
        .size(100.dp)
        .onGloballyPositioned { layoutCoordinates ->
            Log.d("TAG", "!@# AnswerCard: ${layoutCoordinates.boundsInRoot()}")
            rect = layoutCoordinates.boundsInRoot()
            queRectList.add(layoutCoordinates.boundsInRoot())

            queOffset = layoutCoordinates.boundsInRoot().centerRight
        }
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    Log.d("TAG", "!@# queOffset ==> $queOffset")
                    Log.d("TAG", "!@# x ==> ${it.x}, y ==> ${it.y}")
                    startOffset(queOffset)
                },
            )
        }
        .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp) // Adjust the size as needed
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )
        }
    }
}
