package com.example.tusroomseeker.component.messages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tusroomseeker.database.FireBaseRepository
import com.example.tusroomseeker.database.RoomSeekerRepository

class ViewMessageModel(application: Application) : AndroidViewModel(application) {

    private val repository = RoomSeekerRepository(application)
    private val fireBaseRepository = FireBaseRepository(application)

    fun fetchMessages(userId: Int, otherUserId: Int) = repository.fetchMessages(userId, otherUserId)

    suspend fun saveMessage(message: Message) = repository.sendMessage(message)

    fun getMessagesForReceiver(receiverId: Int) = repository.getMessagesForReceiver(receiverId)

//    suspend fun sendMessageToBothDatabases(
//        senderId: Int,
//        receiverId: Int,
//        senderName: String,
//        messageBody: String
//    ): Boolean {
//        return withContext(Dispatchers.IO) {
//            val message = Message(
//                senderId = senderId,
//                receiverId = receiverId,
//                senderName = senderName,
//                messageBody = messageBody,
//                timestamp = System.currentTimeMillis()
//            )
//
//            val firestoreSuccess = fireBaseRepository.sendMessage(
//                senderId,
//                receiverId,
//                senderName,
//                messageBody
//            )
//
//            if (firestoreSuccess) {
//                repository.sendMessage(message)
//            }
//
//            firestoreSuccess
//        }
//    }

}