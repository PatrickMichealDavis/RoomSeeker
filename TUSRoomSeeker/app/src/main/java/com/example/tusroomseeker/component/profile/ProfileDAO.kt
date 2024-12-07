package com.example.tusroomseeker.component.profile

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: Profile)

    @Insert
    suspend fun saveMultipleProfiles(profileList: List<Profile>)

    @Query("SELECT * FROM profile WHERE id = :id")
    fun getProfile(id: Int): LiveData<Profile>

    @Query("SELECT * FROM profile Where email = :email")
    fun getProfileByEmail(email: String): LiveData<Profile>

    @Query("DELETE FROM profile")
    fun clearAllProfiles()



}