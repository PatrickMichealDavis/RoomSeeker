package com.example.tusroomseeker.database

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FireBaseRepository {
    private val db = FirebaseFirestore.getInstance()


    suspend fun sendMessage(senderId: Int, receiverId: Int, message: String) {
        val messageData = hashMapOf(
            "senderId" to senderId,
            "receiverId" to receiverId,
            "message" to message,
            "timestamp" to com.google.firebase.Timestamp.now()
        )
        db.collection("messages")
            .add(messageData)
            .await()
    }
}