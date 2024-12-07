package com.example.tusroomseeker.component.profile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class Profile(
                   @PrimaryKey(autoGenerate = true)
                   val id: Int=0,
                   val userImage:String,
                   val name:String,
                   val gender:String,
                   val email:String,
                   val knum:String,
                   val userType:Int
)