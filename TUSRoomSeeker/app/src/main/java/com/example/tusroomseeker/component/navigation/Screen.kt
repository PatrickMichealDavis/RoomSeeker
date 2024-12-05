package com.example.tusroomseeker.component.navigation

sealed class Screen(
    val route: String
){
    data object Home: Screen("listing")
    data object Messages: Screen("messages")
    data object AddListing: Screen("add_listing")
    data object Map: Screen("map")

}

val screens = listOf(
    Screen.Home,
    Screen.Messages,
    Screen.AddListing,
    Screen.Map
)