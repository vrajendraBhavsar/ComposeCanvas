package com.example.canvasbasic.riddlegame

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import com.example.canvasbasic.R
import com.example.canvasbasic.riddlegame.composable.AnswerCard
import com.example.canvasbasic.riddlegame.composable.QuestionCard
import com.example.canvasbasic.riddlegame.data.AnswerModel
import com.example.canvasbasic.riddlegame.data.Line
import com.example.canvasbasic.riddlegame.data.QuestionModel
import com.example.canvasbasic.riddlegame.util.generateGlobalId

@Composable
fun ConnectionRiddleGame() {
    var questionList: MutableList<QuestionModel> = remember {
        mutableListOf(
            QuestionModel(
                globalQueId = generateGlobalId(),
                { QuestionCard(R.drawable.ic_ice_mario) }),
            QuestionModel(globalQueId = generateGlobalId(), { QuestionCard(R.drawable.ic_mario) }),
            QuestionModel(
                globalQueId = generateGlobalId(),
                { QuestionCard(R.drawable.ic_spike_top) }),
            QuestionModel(
                globalQueId = generateGlobalId(),
                { QuestionCard(R.drawable.ic_toadette) })
        )
    }

    var answerList: MutableList<AnswerModel> = remember {
        mutableListOf(
            AnswerModel(globalAnsId = generateGlobalId(), { AnswerCard(text = "Ice Mario") }),
            AnswerModel(globalAnsId = generateGlobalId(), { AnswerCard(text = "Mario") }),
            AnswerModel(globalAnsId = generateGlobalId(), { AnswerCard(text = "Spike top") }),
            AnswerModel(globalAnsId = generateGlobalId(), { AnswerCard(text = "Toadette") })
        )
    }

    val lines = remember { mutableStateListOf<Line>() }

    if (questionList.size == answerList.size) {
        val mutableQuestionList = questionList.toMutableList()

        for (index in questionList.indices) {
            mutableQuestionList[index] =
                mutableQuestionList[index].copy(associatedAnswerId = answerList[index].globalAnsId)
        }

        // Now updating the original questionList with the changes...
        questionList = mutableQuestionList

        Log.d("TAG", "!@# questionList: $questionList, answerList: $answerList")
    }

    //Shuffling the answer list
    answerList = answerList.shuffled().toMutableList()

    //.............................

    /*data class BoxPosition(
        val index: Int,
        val bounds: Rect
    )

    val boxPositions = remember { mutableStateListOf<BoxPosition>() }

    LazyColumn(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    //Change -> Shows where the finger is right now
                    //DragAmount -> Initial position of a finger and last position
                    change.consume()    //Make sure to consume the event

                    val line = Line(
                        start = change.position - dragAmount,
                        end = change.position,
                        strokeWidth = 5.dp
                    )
                    lines.add(line)

                    // Check for intersections with box positions
                    for (boxPosition in boxPositions) {
                        if (lineIntersectsRect(line, boxPosition.bounds)) {
                            // The line intersects with the box at index boxPosition.index
                            // Do something with this information
                        }
                    }
                }
            }
            .drawBehind {
                lines.forEach { line ->
                    drawLine(
                        color = line.color,
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
    ) {
        itemsIndexed(questionList) { index, questionItem ->
            val answerItem = answerList[index]

            val questionBoxModifier = Modifier
                .background(Color.Blue)
                .size(50.dp)
                .pointerInput(Unit) {
                    *//*detectTransformGestures { _, pan, _ ->
                        // Calculate the bounding rectangle of the box
                        val bounds = Rect(offset.x, offset.y, offset.x + size.width, offset.y + size.height)

                        // Update the box position
                        boxPositions[index] = BoxPosition(index, bounds)
                    }*//*

                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        // Calculate the bounding rectangle of the box
                        val bounds = Rect(centroid.x, centroid.y, centroid.x + size.width, centroid.y + size.height)

                        // Update the box position
                        boxPositions[index] = BoxPosition(index, bounds)
                    }
                }
            Box(
                modifier = questionBoxModifier,
                contentAlignment = Alignment.Center
            ) {
                questionItem.composable.invoke()
            }

            Spacer(modifier = Modifier.width(16.dp))

            val answerBoxModifier = Modifier
                .background(Color.Green)
                .size(50.dp)
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        // Calculate the bounding rectangle of the box
                        val bounds = Rect(centroid.x, centroid.y, centroid.x + size.width, centroid.y + size.height)

                        // Update the box position
                        boxPositions[index + questionList.size] = BoxPosition(index, bounds)
                    }
                }
            Box(
                modifier = answerBoxModifier,
                contentAlignment = Alignment.Center
            ) {
                answerItem.composable.invoke()
            }
        }
    }*/

    //........................

//    var lines = mutableStateListOf<Line>()

    /*LazyColumn(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val line = Line(
                        start = change.position - dragAmount,
                        end = change.position,
                        strokeWidth = 5.dp
                    )
                    lines.add(line)
                }
            }
            .drawBehind {
                lines.forEach { line ->
                    drawLine(
                        color = line.color,
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
    ) {
        itemsIndexed(questionList) { index, questionItem ->
            val answerItem = answerList[index]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val questionBoxModifier = Modifier
                    .background(Color.Blue)
                    .size(50.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { centroid, pan, zoom, rotation ->
                            // Detect the box's center position
                            val center = Offset(centroid.x, centroid.y)

                            // Update the line end point
                            lines.lastOrNull()?.let {
                                it.end = center + pan
                            }
                        }
                    }
                Box(
                    modifier = questionBoxModifier,
                    contentAlignment = Alignment.Center
                ) {
                    questionItem.composable.invoke()
                }

                Spacer(modifier = Modifier.width(16.dp))

                val answerBoxModifier = Modifier
                    .background(Color.Green)
                    .size(50.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { centroid, pan, zoom, rotation ->
                            // Detect the box's center position
                            val center = Offset(centroid.x, centroid.y)

                            // Update the line end point
                            lines.lastOrNull()?.let {
                                it.end = center + pan
                            }
                        }
                    }
                Box(
                    modifier = answerBoxModifier,
                    contentAlignment = Alignment.Center
                ) {
                    answerItem.composable.invoke()
                }
            }
        }
    }*/

    //......................

    /*LazyColumn(
        modifier = Modifier
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val line = Line(
                        start = change.position - dragAmount,
                        end = change.position,
                        strokeWidth = 5.dp
                    )
                    lines.add(line)
                }
            }
            .drawBehind {
                lines.forEach { line ->
                    drawLine(
                        color = line.color,
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
    ) {
        itemsIndexed(questionList) { index, questionItem ->
            val answerItem = answerList[index]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val questionBoxModifier = Modifier
                    .background(Color.Blue)
                    .size(50.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { centroid, pan, zoom, rotation ->
                            // Detect the box's center position
                            val center = center.absolutePosition

                            // Update the line end point
                            lines.lastOrNull()?.let {
                                it.end = center + pan
                            }
                        }
                    }
                Box(
                    modifier = questionBoxModifier,
                    contentAlignment = Alignment.Center
                ) {
                    questionItem.composable.invoke()
                }

                Spacer(modifier = Modifier.width(16.dp))

                val answerBoxModifier = Modifier
                    .background(Color.Green)
                    .size(50.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { centroid, pan, zoom, rotation ->
                            // Detect the box's center position
                            val center = center.absolutePosition

                            // Update the line end point
                            lines.lastOrNull()?.let {
                                it.end = center + pan
                            }
                        }
                    }
                Box(
                    modifier = answerBoxModifier,
                    contentAlignment = Alignment.Center
                ) {
                    answerItem.composable.invoke()
                }
            }
        }
    }*/

    //Column UI
    LazyColumn(
        modifier = Modifier
            .pointerInput(true) {
                detectDragGestures { change, dragAmount ->
                    //Change -> Shows where the finger is right now
                    //DragAmount -> Initial position of a finger and last position
                    change.consume()    //Make sure to consume the event

                    val line = Line(
                        start = change.position - dragAmount,
                        end = change.position,
                        strokeWidth = 5.dp
                    )
                    lines.add(line)
                }
            }
            .drawBehind {
                lines.forEach { line ->
                    drawLine(
                        color = line.color,
                        start = line.start,
                        end = line.end,
                        strokeWidth = line.strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                }
            }
    ) {
        items(questionList.size) { index ->
            val questionItem = questionList[index]
            val answerItem = answerList[index]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                questionItem.composable.invoke()
                answerItem.composable.invoke()
            }
        }
    }

    /*Canvas(modifier = Modifier
        .fillMaxSize()
        .pointerInput(true) {
            detectDragGestures { change, dragAmount ->
                //Change -> Shows where the finger is right now
                //DragAmount -> Initial position of a finger and last position

                change.consume()    //Make sure to consume the event

                val line = Line(
                    start = change.position - dragAmount,
                    end = change.position,
                    strokeWidth = 5.dp
                )

                lines.add(line)
            }
        }) {
        lines.forEach { line ->
            drawLine(
                color = line.color,
                start = line.start,
                end = line.end,
                strokeWidth = line.strokeWidth.toPx(),
                cap = StrokeCap.Round
            )
        }
    }*/
}

// Function to calculate the center position of a composable

/*
fun centerPosition(pan: Offset, composable: DrawScope): Offset {
    val width = composable.size.width
    val height = composable.size.height
//    val topLeft = composable.localToGlobal(Offset.Zero)
    val topLeft = composable.drawContext.canvas.topLeft

    return Offset(
        x = topLeft.x + width / 2 + pan.x,
        y = topLeft.y + height / 2 + pan.y
    )
}*/

// Function to check if a line intersects with a rectangle
private fun lineIntersectsRect(line: Line, rect: Rect): Boolean {
    // Perform intersection check logic here
    // ...
    return false
}
