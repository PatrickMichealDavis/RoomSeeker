package com.example.tusroomseeker.component.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tusroomseeker.LoadScreen
import com.example.tusroomseeker.component.listings.AddListingScreen
import com.example.tusroomseeker.component.listings.ListingScreen
import com.example.tusroomseeker.component.listings.ListingViewModel
import com.example.tusroomseeker.component.map.LocationViewModel
import com.example.tusroomseeker.component.map.MapScreen
import com.example.tusroomseeker.component.messages.MessagesScreen
import com.example.tusroomseeker.component.messages.ViewMessageModel
import com.example.tusroomseeker.component.messages.ViewMessageScreen
import com.example.tusroomseeker.component.profile.ProfileScreen
import com.example.tusroomseeker.component.profile.ProfileViewModel

@Composable
fun BuildNavigationGraph(

//    burgerViewModel: BurgerViewModel = viewModel(),
//    placesViewModel: PlacesViewModel = viewModel(),
    listingViewModel: ListingViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel(),
    viewMessageModel: ViewMessageModel = viewModel(),
    locationViewModel: LocationViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main"){LoadScreen(navController)}
        composable("listing") { ListingScreen(navController,listingViewModel) }
        composable("profile") { ProfileScreen(navController, profileViewModel) }
        composable("messages"){ MessagesScreen(navController, viewMessageModel)}
        composable("view_message"){ ViewMessageScreen(navController, viewMessageModel)}
        composable("add_listing"){ AddListingScreen(navController, listingViewModel)}
        composable("map"){ MapScreen(navController, locationViewModel)}



    }
}