package com.example.canvasbasic.riddlegame.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AnswerCard(text: String, ansRectList: SnapshotStateList<Rect>, endOffset: Offset,  updatedEndOffset: (Offset) -> Unit) {
    var rect: Rect? by remember { mutableStateOf(null) }
    var ansOffset: Offset by remember { mutableStateOf(Offset.Unspecified) }
    var isDraggingOver: Boolean by remember { mutableStateOf(false) }

    var ansLiveOffset: Offset by remember { mutableStateOf(Offset.Unspecified) }

    Card(
        modifier = Modifier
            .size(100.dp)
            .onGloballyPositioned { layoutCoordinates ->
                Log.d("TAG", "!@# AnswerCard: ${layoutCoordinates.boundsInRoot()}")
                rect = layoutCoordinates.boundsInRoot()
                ansRectList.add(layoutCoordinates.boundsInRoot())

                val isIn: Boolean = layoutCoordinates.boundsInRoot().contains(endOffset)
                Log.d("TAG", "!@# AnswerCard: is dragging In:: $isIn")

                if (isIn) {
                    ansOffset = layoutCoordinates.boundsInRoot().centerLeft
                    updatedEndOffset(ansOffset)
                }
            }
            .pointerInput(Unit) {
                /*detectTapGestures(
                    onPress = {
                        Log.d("TAG", "!@# ansOffset ==> $ansOffset")
                    },
                )*/
                /*detectDragGestures { change, dragAmount ->
                    Log.d("TAG", "!@# ansOffset ==> $ansOffset")
                }*/
                /*detectTransformGestures { centroid, pan, zoom, rotation ->
                    Log.d("TAG", "!@# Dragging ONLY: $text")

                    val touchPosition = pan + ansOffset
                    isDraggingOver = rect?.contains(touchPosition) == true

                    if (isDraggingOver) {
                        // Dragging over the AnswerCard
                        Log.d("TAG", "!@# Dragging over AnswerCard: $text")
                    }
                }*/

                /*detectTransformGestures { centroid, pan, zoom, rotation ->
                    Log.d("TAG", "!@# Dragging ONLY: $text")

                    ansLiveOffset = pan + ansOffset
                }*/

                /*detectDragGestures { change, dragAmount ->
                    Log.d("TAG", "!@# change ==> ${change.position}")
                    ansLiveOffset = change.position
                }*/

                detectDragGesturesAfterLongPress(onDragStart = {
                    Log.d("TAG", "!@# detectDragGesturesAfterLongPress offset ==> $it")

                }) { change, dragAmount ->
                    Log.d("TAG", "!@# long press change ==> ${change.position}")
                    change.consume()
                    ansLiveOffset = change.position
                }
            }
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 100.dp
        ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
    }
}