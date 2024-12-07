package com.example.tusroomseeker.component.listings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listing")
data class Listing(
    @PrimaryKey(autoGenerate = true)
                   val id: Int = 0,
                    val title:String,
                   val image: String,
                   val address:String,
                   val price:Double,
                   val description: String,
                   val userId:Int)