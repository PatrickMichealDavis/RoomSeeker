package com.example.tusroomseeker.component.listings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tusroomseeker.R
import com.example.tusroomseeker.database.FireBaseRepository
import com.example.tusroomseeker.database.RoomSeekerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RoomSeekerRepository(application)
    private val firebaseRepo = FireBaseRepository(application)

    fun loadListings(): LiveData<List<Listing>> {
        return repository.fetchListings()
    }

    suspend fun loadFirebaseListings(): List<Listing> {
        return firebaseRepo.fetchListingsFromFirestore()
    }

    fun addAllListingsToFirebase(listings: List<Listing>) {
        firebaseRepo.addMultipleListings(listings)
    }

    fun refreshListings() {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseRepo.fetchAndSaveListings()
        }
    }

//    fun loadListings(): List<Listing>{
//        return listOf(
//            Listing(1,"Single Room", R.drawable.listing1,"20 William street",199.99,"Room for rent price per week",1),
//            Listing(2,"Double Room", R.drawable.listing1,"20 Knoxs street",880.99,"Room price per month",2),
//            Listing(3,"Single Room", R.drawable.listing1,"45 Quin street",125.00,"Room price per week",1),
//
//            )
//    }
}