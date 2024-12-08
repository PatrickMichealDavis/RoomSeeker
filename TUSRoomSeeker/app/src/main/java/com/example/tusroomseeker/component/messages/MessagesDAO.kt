package com.example.tusroomseeker.component.messages

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MessageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM messages WHERE (senderId = :userId AND receiverId = :otherUserId) OR (senderId = :otherUserId AND receiverId = :userId) ORDER BY timestamp ASC")
    fun getMessages(userId: Int, otherUserId: Int): LiveData<List<Message>>

    @Query("SELECT * FROM messages WHERE receiverId = :receiverId ORDER BY timestamp")
    fun getMessagesForReceiver(receiverId: Int): LiveData<List<Message>>

    @Query("DELETE FROM messages")
    suspend fun clearAllMessages()
}