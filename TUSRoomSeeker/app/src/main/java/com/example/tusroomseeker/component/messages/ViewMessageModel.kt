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

    //these are all the firebase functions i tried to get working. My theory was the time enitity
    //was causing the issue but i could not get it working so i didnt use it!



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

//    fun getAllMessages() {
//        fireBaseRepository.listenForMessages { messages ->
//            viewModelScope.launch {
//                _messageList.value = messages as List<Message>
//            }
//        }
//    }
//
//    fun sendMessage(message: Message):Boolean {
//        try{
//            fireBaseRepository.sendMessage(message.senderId,message.receiverId,message.senderName,message.messageBody)
//            return true
//        }catch (e: Exception){
//            return false
//        }
//    }

//    fun listenForMessages(userId: Int, otherUserId: Int) {
//        db.collection("messages")
//            .whereIn("senderId", listOf(userId, otherUserId))
//            .whereIn("receiverId", listOf(userId, otherUserId))
//            .orderBy("timestamp")
//            .addSnapshotListener { querySnapshot, exception ->
//                if (exception != null) {
//                    exception.printStackTrace()
//                    return@addSnapshotListener
//                }
//
//                querySnapshot?.documents?.forEach { document ->
//                    val message = document.toObject(Message::class.java)
//                    if (message != null) {
//                        CoroutineScope(Dispatchers.IO).launch {
//                            messageDao?.insertMessage(message)
//                        }
//                    }
//                }
//            }
//    }
//
//    fun listenForReceiverMessages(receiverId: Int, onMessagesChanged: (List<Message>) -> Unit) {
//        db.collection("messages")
//            .whereEqualTo("receiverId", receiverId)
//            .orderBy("timestamp")
//            .addSnapshotListener { querySnapshot, exception ->
//                if (exception != null) {
//                    exception.printStackTrace()
//                    return@addSnapshotListener
//                }
//
//                val messages = querySnapshot?.documents?.mapNotNull { document ->
//                    document.toObject(Message::class.java)
//                } ?: emptyList()
//
//                onMessagesChanged(messages)
//            }
//    }
//


}