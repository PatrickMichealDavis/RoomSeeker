package com.example.tusroomseeker.component.listings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.tusroomseeker.R

class ListingViewModel(application: Application) : AndroidViewModel(application) {

//    fun loadMenuItems(): List<Menus> {
//        return listOf(
//            Menus(R.string.delivery, R.drawable.delivery),
//            Menus(R.string.fun_facts, R.drawable.facts),
//            Menus(R.string.health_tips, R.drawable.health),
//            Menus(R.string.research, R.drawable.analytics),
//            Menus(R.string.investments, R.drawable.investment),
//            Menus(R.string.ranking, R.drawable.ranking),
//            Menus(R.string.drive_throughs, R.drawable.drive_thru),
//        )
//    }

    fun loadListings(): List<Listing>{
        return listOf(
            Listing(1,"Single Room", R.drawable.listing1,"20 William street",199.99,"Room for rent price per week"),
            Listing(2,"Double Room", R.drawable.listing1,"20 Knoxs street",880.99,"Room price per month"),
            Listing(3,"Single Room", R.drawable.listing1,"45 Quin street",125.00,"Room price per week"),

            )
    }
}