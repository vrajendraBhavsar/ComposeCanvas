package com.example.canvasbasic.riddlegame

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.canvasbasic.R
import com.example.canvasbasic.riddlegame.data.AnswerModel
import com.example.canvasbasic.riddlegame.data.QuestionModel
import com.example.canvasbasic.riddlegame.util.generateGlobalId

@Composable
fun ConnectionRiddleGame() {
    var questionList: MutableList<QuestionModel> = remember {
        mutableListOf(
            QuestionModel(globalQueId = generateGlobalId(), { QuestionCard(R.drawable.ic_ice_mario) }),
            QuestionModel(globalQueId = generateGlobalId(), { QuestionCard(R.drawable.ic_mario) }),
            QuestionModel(globalQueId = generateGlobalId(), { QuestionCard(R.drawable.ic_spike_top) }),
            QuestionModel(globalQueId = generateGlobalId(), { QuestionCard(R.drawable.ic_toadette) })
        )
    }

    val answerList: MutableList<AnswerModel> = remember {
        mutableListOf(
            AnswerModel(globalAnsId = generateGlobalId(), { AnswerCard(text = "Ice Mario") }),
            AnswerModel(globalAnsId = generateGlobalId(), { AnswerCard(text = "Mario") }),
            AnswerModel(globalAnsId = generateGlobalId(), { AnswerCard(text = "Spike top") }),
            AnswerModel(globalAnsId = generateGlobalId(), { AnswerCard(text = "Toadette") })
        )
    }

    if (questionList.size == answerList.size) {
        val mutableQuestionList = questionList.toMutableList()

        for (index in questionList.indices) {
            mutableQuestionList[index] = mutableQuestionList[index].copy(associatedAnswerId = answerList[index].globalAnsId)
        }

        // Now updating the original questionList with the changes...
        questionList = mutableQuestionList

        Log.d("TAG", "!@# questionList: $questionList, answerList: $answerList")
    }

    /*if (questionList.size == answerList.size) {
        val updatedQuestionList = questionList.toMutableList()

        for (index in questionList.indices) {
            updatedQuestionList[index] = updatedQuestionList[index].copy(associatedAnswerId = answerList[index].globalAnsId)
        }

        Log.d("TAG", "!@# updatedQuestionList: $updatedQuestionList, answerList: $answerList")
    }*/

    //Randomizing answer list
    Log.d("TAG", "!@# answerList: BEFORE:: $answerList")
    answerList.shuffled()
    Log.d("TAG", "!@# answerList: AFTER:: $answerList")

    //Column UI
    LazyColumn {
        items(questionList.size) { index ->
            val questionItem = questionList[index]
            val answerItem = answerList[index]

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left side (Question)
//                QuestionCard(questionItem)
                questionItem.composable.invoke()

                // Spacer between Question and Answer
//                Spacer(modifier = Modifier.width(16.dp))

                // Right side (Answer)
//                AnswerCard(answerItem)
                answerItem.composable.invoke()
            }
        }
    }
}

@Composable
fun QuestionCard(@DrawableRes imageResource: Int) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
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

/*@Composable
fun QuestionCard(@DrawableRes imageResource: Int) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .shadow(1.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )
        }
    }
}*/

@Composable
fun AnswerCard(text: String) {
    Card(
        modifier = Modifier
            .size(100.dp)
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

/*@Composable
fun AnswerCard(text: String) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .shadow(1.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}*/

/*
@Composable
fun AnswerCard(text: String) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .shadow(1.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
        }
    }
}*/
