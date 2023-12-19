package com.example.canvasbasic.riddlegame

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.canvasbasic.R
import com.example.canvasbasic.riddlegame.composable.AnswerCard
import com.example.canvasbasic.riddlegame.composable.QuestionCard
import com.example.canvasbasic.riddlegame.data.AnswerModel
import com.example.canvasbasic.riddlegame.data.QuestionModel
import com.example.canvasbasic.riddlegame.util.generateGlobalId

@Composable
fun ConnectionRiddleGame() {
    var startOffset by remember { mutableStateOf(Offset.Zero) }
    var endOffset by remember { mutableStateOf(Offset.Zero) }

    var questionList: MutableList<QuestionModel> = remember {
        mutableListOf(
            QuestionModel(globalQueId = generateGlobalId(), imageResource = R.drawable.ic_ice_mario),
            QuestionModel(globalQueId = generateGlobalId(), imageResource = R.drawable.ic_mario),
            QuestionModel(globalQueId = generateGlobalId(), imageResource = R.drawable.ic_spike_top),
            QuestionModel(globalQueId = generateGlobalId(), imageResource = R.drawable.ic_toadette)
        )
    }

    var answerList: MutableList<AnswerModel> = remember {
        mutableListOf(
            AnswerModel(globalAnsId = generateGlobalId(), text = "Ice Mario"),
            AnswerModel(globalAnsId = generateGlobalId(), text = "Mario"),
            AnswerModel(globalAnsId = generateGlobalId(), text = "Spike top"),
            AnswerModel(globalAnsId = generateGlobalId(), text = "Toadette"))
    }

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

    //Column UI
    LazyColumn(
        modifier = Modifier
            .pointerInput(true) {
                detectDragGestures { change, dragAmount ->
                    //Change -> Shows where the finger is right now
                    //DragAmount -> Initial position of a finger and last position
                    change.consume()    //Make sure to consume the event
                    Log.d("TAG", "!@# LazyColumn: dragAmount:: $dragAmount, change:: $change")
                    endOffset = change.position // To make line move as you move your finger
                }
            }
            .drawBehind {
                drawLine(
                    color = Color.Black,
                    start = startOffset,
                    end = endOffset,
                    strokeWidth = 5.dp.toPx(),
                    cap = StrokeCap.Round
                )
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
                QuestionCard(
                    imageResource = questionItem.imageResource,
                    startOffset = { startingOffset ->
                        Log.d("TAG", "!@# ConnectionRiddleGame: 1 START OFFSET:: $startingOffset")
                        questionItem.startingPoint = startingOffset
                        startOffset = startingOffset
                    }
                )

                AnswerCard(
                    text = answerItem.text,
                    endOffsetToVerify = endOffset,
                    updatedEndOffset = { updatedEndOffset ->
                        Log.d("TAG", "!@# ConnectionRiddleGame:updated End OFFSET:: $updatedEndOffset, ITEM:: ${answerItem.text}")
                        endOffset = updatedEndOffset
                    }
                )
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
