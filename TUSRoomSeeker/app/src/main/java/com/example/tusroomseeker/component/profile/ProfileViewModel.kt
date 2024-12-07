package com.example.tusroomseeker.component.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tusroomseeker.R
import com.example.tusroomseeker.component.listings.Listing
import com.example.tusroomseeker.database.RoomSeekerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RoomSeekerRepository(application)

    fun loadProfile(id:Int): LiveData<Profile> {
        return repository.fetchProfile(1)
    }

    fun loadProfileByEmail(email: String): LiveData<Profile> {
        return repository.fetchProfileByEmail(email)
    }

    fun saveProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveProfile(profile)
        }
    }

}