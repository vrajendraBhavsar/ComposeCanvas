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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
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

    val queRectList = remember { mutableStateListOf<Rect>() }
    queRectList.clear()

    val ansRectList = remember { mutableStateListOf<Rect>() }
    ansRectList.clear()

    var startOffset by remember { mutableStateOf(Offset.Zero) }
    var endOffset by remember { mutableStateOf(Offset.Zero) }

    var questionList: MutableList<QuestionModel> = remember {
        mutableListOf(
            QuestionModel(
            globalQueId = generateGlobalId(),
            composable = {
                QuestionCard(
                    imageResource = R.drawable.ic_ice_mario,
                    queRectList = queRectList,
                    startOffset = { offset ->
                        Log.d("TAG", "!@# ConnectionRiddleGame: 1 START OFFSET:: $offset")
                        startOffset = offset
                    }
                )
            }
        ),
            QuestionModel(
                globalQueId = generateGlobalId(),
                composable = {
                    QuestionCard(
                        imageResource = R.drawable.ic_mario,
                        queRectList = queRectList,
                        startOffset = { offset ->
                            Log.d("TAG", "!@# ConnectionRiddleGame: 2 START OFFSET:: $offset")
                            startOffset = offset
                        }
                    )
                }),
            QuestionModel(
                globalQueId = generateGlobalId(),
                composable = {
                    QuestionCard(
                        imageResource = R.drawable.ic_spike_top,
                        queRectList = queRectList,
                        startOffset = { offset ->
                            Log.d("TAG", "!@# ConnectionRiddleGame: 3 START OFFSET:: $offset")
                            startOffset = offset
                        }
                    )
                }),
            QuestionModel(
                globalQueId = generateGlobalId(),
                composable = {
                    QuestionCard(
                        imageResource = R.drawable.ic_toadette,
                        queRectList = queRectList,
                        startOffset = { offset ->
                            Log.d("TAG", "!@# ConnectionRiddleGame: 4 START OFFSET:: $offset")
                            startOffset = offset
                        }
                    )
                }
            )
        )
    }

    var answerList: MutableList<AnswerModel> = remember {
        mutableListOf(AnswerModel(globalAnsId = generateGlobalId()) {
            AnswerCard(text = "Ice Mario", ansRectList, endOffset) { updatedEndOffset ->
                Log.d("TAG", "!@# ConnectionRiddleGame:updated End OFFSET 1:: $updatedEndOffset")
                endOffset = updatedEndOffset
            }
        }, AnswerModel(globalAnsId = generateGlobalId()) {
            AnswerCard(text = "Mario", ansRectList, endOffset) { updatedEndOffset ->
                Log.d("TAG", "!@# ConnectionRiddleGame:updated End OFFSET 2:: $updatedEndOffset")
                endOffset = updatedEndOffset
            }
        }, AnswerModel(globalAnsId = generateGlobalId()) {
            AnswerCard(text = "Spike top", ansRectList, endOffset) { updatedEndOffset ->
                Log.d("TAG", "!@# ConnectionRiddleGame:updated End OFFSET 3:: $updatedEndOffset")
                endOffset = updatedEndOffset
            }
        }, AnswerModel(globalAnsId = generateGlobalId()) {
            AnswerCard(text = "Toadette", ansRectList, endOffset) { updatedEndOffset ->
                Log.d("TAG", "!@# ConnectionRiddleGame:updated End OFFSET 4:: $updatedEndOffset")
                endOffset = updatedEndOffset
            }
        })
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

    //Column UI
    LazyColumn(
        modifier = Modifier
            .pointerInput(true) {
                detectDragGestures { change, dragAmount ->
                    //Change -> Shows where the finger is right now
                    //DragAmount -> Initial position of a finger and last position
                    change.consume()    //Make sure to consume the event
                    Log.d("TAG", "!@# LazyColumn: dragAmount:: $dragAmount, change:: $change")
                    /*val line = Line(
                    start = change.position - dragAmount, end = change.position, strokeWidth = 5.dp
                )
                lines.add(line)*/

                    endOffset = change.position
                }
            }
            .drawBehind {
                /*lines.forEach { line ->
                    drawLine(
                        color = line.color,
                        start = startOffset,
                        end = endOffset,
                        strokeWidth = line.strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                }*/
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
