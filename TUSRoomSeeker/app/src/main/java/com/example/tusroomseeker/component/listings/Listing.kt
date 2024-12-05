package com.example.tusroomseeker.component.listings

data class Listing( val id: Int = 0,
                    val title:String,
                   val image: Int,
                   val address:String,
                   val price:Double,
                   val description: String,
                   val userId:Int)