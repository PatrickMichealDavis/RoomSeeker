package com.example.tusroomseeker.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.tusroomseeker.component.listings.Listing
import com.example.tusroomseeker.component.profile.Profile

class RoomSeekerRepository(application: Application) {

    private val listingDao = RoomSeekerRoomDatabase.getDatabase(application)!!.listingDAO()
    private val profileDao = RoomSeekerRoomDatabase.getDatabase(application)!!.profileDAO()

    fun fetchListings() : LiveData<List<Listing>>{
        return listingDao.getAllListings()
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

}