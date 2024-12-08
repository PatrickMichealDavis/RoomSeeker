package com.example.tusroomseeker.database

import android.content.Context
import com.example.tusroomseeker.component.listings.Listing
import com.example.tusroomseeker.component.messages.Message
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FireBaseRepository(context:Context) {
    private val db = FirebaseFirestore.getInstance()
    private val messageDao = RoomSeekerRoomDatabase.getDatabase(context)?.messageDAO()
    private val listingDAO = RoomSeekerRoomDatabase.getDatabase(context)?.listingDAO()


    suspend fun sendMessage(senderId: Int, receiverId: Int, senderName: String, messageBody: String): Boolean {

        if (senderId <= 0 || receiverId <= 0 || messageBody.isEmpty()) {
            return false
        }

        return try {
            val messageData = hashMapOf(
                "senderId" to senderId,
                "receiverId" to receiverId,
                "messageBody" to messageBody,
                "senderName" to senderName,
                "timestamp" to com.google.firebase.Timestamp.now()
            )
            db.collection("messages").add(messageData).await()
           // messageDao?.insertMessage(Message(senderId=senderId, receiverId = receiverId, senderName =  senderName, messageBody =  messageBody, timestamp =  System.currentTimeMillis()))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun fetchMessages(senderId: Int, receiverId: Int): List<Message> {
        return try {
            val messages = db.collection("messages")
                .whereIn("senderId", listOf(senderId, receiverId))
                .whereIn("receiverId", listOf(senderId, receiverId))
                .orderBy("timestamp")
                .get()
                .await()
                .toObjects(Message::class.java)
            messages
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }




    fun addListing(listing: Listing) {
        db.collection("listings").add(listing)
    }

    fun addMultipleListings(listings: List<Listing>) {
        listings.forEach { listing ->
            db.collection("listings").add(listing)
        }
    }

    suspend fun fetchListingsFromFirestore(): List<Listing> {
        return try {
            val querySnapshot = db.collection("listings").get().await()
            querySnapshot.documents.mapNotNull { it.toObject(Listing::class.java) }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun fetchAndSaveListings() {
        val listings = fetchListingsFromFirestore()
        listingDAO?.saveMultipleListings(listings) // Save new listings
    }
}