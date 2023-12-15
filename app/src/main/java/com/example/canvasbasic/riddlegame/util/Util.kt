package com.example.canvasbasic.riddlegame.util

import java.util.UUID

fun generateGlobalId(): String {
    // To make random global key for question and answer list items
    return UUID.randomUUID().toString()
}