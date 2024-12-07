package com.example.tusroomseeker.component.messages

data class Message(
    val messageId:String,
    val sender:Int,
    val receiver:Int,
    val messageBody:String,
    val timestamp: Long = System.currentTimeMillis()
)