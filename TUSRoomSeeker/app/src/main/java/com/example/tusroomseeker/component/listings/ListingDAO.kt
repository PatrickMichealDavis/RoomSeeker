package com.example.tusroomseeker.component.listings

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListingDAO {

    @Insert
    suspend fun saveListing(listing: Listing)

//    @Insert
//    suspend fun saveMultipleListings(listingList: List<Listing>)

    @Query("SELECT * FROM listing WHERE id = :id")
    fun getListing(id: Int): LiveData<Listing>

    @Query("SELECT * FROM listing")
    fun getAllListings():LiveData< List<Listing>>

    @Query("DELETE FROM listing")
    suspend fun clearAllListings()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMultipleListings(listings: List<Listing>)

}