package com.example.tusroomseeker.component.messages

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    val messageId:Int=0,
    val senderId:Int,
    val receiverId:Int,
    val senderName:String,
    val messageBody:String,
    val timestamp: Long = System.currentTimeMillis()
)