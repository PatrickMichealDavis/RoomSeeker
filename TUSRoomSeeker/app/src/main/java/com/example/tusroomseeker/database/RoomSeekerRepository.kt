package com.example.tusroomseeker.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.tusroomseeker.component.listings.Listing
import com.example.tusroomseeker.component.messages.Message
import com.example.tusroomseeker.component.profile.Profile

class RoomSeekerRepository(application: Application) {

    private val listingDao = RoomSeekerRoomDatabase.getDatabase(application)!!.listingDAO()
    private val profileDao = RoomSeekerRoomDatabase.getDatabase(application)!!.profileDAO()
    private val messageDao = RoomSeekerRoomDatabase.getDatabase(application)!!.messageDAO()

    fun fetchListings() : LiveData<List<Listing>>{
        return listingDao.getAllListings()
    }

    suspend fun saveListing(listing: Listing) {
        listingDao.saveListing(listing)
    }

    fun fetchProfile(id:Int) : LiveData<Profile> {
        return profileDao.getProfile(id)
    }

    fun fetchProfileByEmail(email: String) : LiveData<Profile> {
        return profileDao.getProfileByEmail(email)
    }

    suspend fun saveProfile(profile: Profile) {
        profileDao.saveProfile(profile)
    }

    fun fetchMessages(userId:Int,otherUserId:Int) : LiveData<List<Message>> {
        return messageDao.getMessages(userId,otherUserId)
    }

    suspend fun sendMessage(message: Message) {
        messageDao.insertMessage(message)
    }

    fun getMessagesForReceiver(receiverId: Int): LiveData<List<Message>> {
        return messageDao.getMessagesForReceiver(receiverId)
    }



}