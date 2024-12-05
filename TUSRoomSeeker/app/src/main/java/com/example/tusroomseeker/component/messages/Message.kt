package com.example.tusroomseeker.component.messages

data class Message(
    val messageId:Int,
    val sender:Int,
    val receiver:Int,
    val messageBody:String
)