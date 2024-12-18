package com.example.tusroomseeker.component.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tusroomseeker.LoadScreen
import com.example.tusroomseeker.component.contactus.ContactUsScreen
import com.example.tusroomseeker.component.listings.AddListingScreen
import com.example.tusroomseeker.component.listings.ListingScreen
import com.example.tusroomseeker.component.listings.ListingViewModel
import com.example.tusroomseeker.component.login.LoginScreen
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.component.login.SignUpScreen
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
    locationViewModel: LocationViewModel = viewModel(),
    loginViewModel: LoginViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main"){LoadScreen(navController)}
        composable("login"){ LoginScreen( loginViewModel,navController) }
        composable("signup"){ SignUpScreen( loginViewModel,navController) }
        composable("listing") { ListingScreen(navController,listingViewModel,loginViewModel) }
        composable("profile") { ProfileScreen(navController, profileViewModel,loginViewModel) }
        composable("messages"){ MessagesScreen(navController, viewMessageModel,loginViewModel)}
        //composable("view_message"){ ViewMessageScreen(navController, viewMessageModel,loginViewModel)}
        composable("add_listing"){ AddListingScreen(navController, listingViewModel,loginViewModel)}
        composable("map"){ MapScreen(navController, locationViewModel,loginViewModel)}
        composable("view_message/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull() ?: 0
            ViewMessageScreen(
                navController = navController,
                viewMessageModel = viewMessageModel,
                loginViewModel = loginViewModel,
                recieverId = userId
            )
        }
        composable("contact_us") { ContactUsScreen(navController, loginViewModel) }


    }
}