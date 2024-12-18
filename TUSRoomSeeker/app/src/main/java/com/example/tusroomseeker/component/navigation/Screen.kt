package com.example.tusroomseeker.component.navigation

sealed class Screen(
    val route: String
){
    data object Home: Screen("listing")
    data object Messages: Screen("messages")
    data object AddListing: Screen("add_listing")
    data object Map: Screen("map")
    data object ContactUs: Screen("contact_us")

}

val screens = listOf(
    Screen.Home,
    Screen.Messages,
    Screen.AddListing,
    Screen.Map,
    Screen.ContactUs
)