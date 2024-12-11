package com.example.tusroomseeker.component.map

import android.location.Location
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tusroomseeker.BaseContainer
import com.example.tusroomseeker.R
import com.example.tusroomseeker.component.listings.Listing
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.component.navigation.Screen
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


@Composable
fun MapScreen(navController: NavHostController,
                   locationViewModel: LocationViewModel,
              loginViewModel: LoginViewModel
)
{
    val places = locationViewModel.loadLocations()
    BaseContainer(
        navController = navController,
        pageTitle="Map",
        loginViewModel = loginViewModel
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding).fillMaxSize()
                .background(Color.Black)
        ) {


            MapScreenContent( currentLocation = locationViewModel.lastLocation,
                getLastLocation = { locationViewModel.getLastLocation() },
                hasLocationPermission = { locationViewModel.hasPermission() },
                listingLocations = places)
        }
    }
}

//@Composable
//fun MapScreenContent(){
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black)
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//       Box(
//           modifier = Modifier
//               .fillMaxWidth()
//               .background(Color.Black, RoundedCornerShape(12.dp)),
//           contentAlignment = Alignment.Center
//       ){
//           Text(text="map screen")
//       }
//   }
//}

@Composable
private fun MapScreenContent(
    modifier: Modifier = Modifier,
    currentLocation: Location?,
    getLastLocation: () -> Unit = {},
    hasLocationPermission:  () -> Boolean = { false },
    listingLocations: List<MapDetails> = listOf()
) {
    //use this for the dimensions of Limerick when running in an emulator
    val defaultLocation = LatLng(52.6638, -8.6267)

    val cameraPositionState = rememberCameraPositionState {}

    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = true,
                myLocationButtonEnabled = true,
                rotationGesturesEnabled = true,
                scrollGesturesEnabled = true,
                scrollGesturesEnabledDuringRotateOrZoom = true,
                tiltGesturesEnabled = true,
                zoomControlsEnabled = true,
                zoomGesturesEnabled = true
            )
        )
    }

    val properties by remember {
        mutableStateOf(
            MapProperties(
                isBuildingEnabled = false,
                isMyLocationEnabled = hasLocationPermission(),
                isIndoorEnabled = false,
                isTrafficEnabled = false,
                mapType = MapType.NORMAL,
                maxZoomPreference = 21f,
                minZoomPreference = 3f
            )
        )
    }

    GoogleMap(
        modifier = modifier,
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        getLastLocation()
        val location = currentLocation?.let {
            //replace defaultLocation with currentLocation when running on a physical device
            LatLng(defaultLocation.latitude, defaultLocation.longitude)
        }
        for (place in listingLocations ) {
            DisplayMarker(place)
        }
        location?.let {
            cameraPositionState.move(
                update = CameraUpdateFactory.newLatLngZoom(it, 12f)
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun DisplayMarker(place: MapDetails) {
    MarkerInfoWindowContent(
        rememberMarkerState(position = place.position()),
        title = place.type
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(width = 250.dp, height = 150.dp)
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = place.type,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            GlideImage(
                model =
                Uri.parse("file:///android_asset/${place.image}"),
                contentDescription = stringResource(id = R.string.listing_image),
                contentScale = ContentScale.Crop, modifier = Modifier
                    .padding(top = 6.dp)
                    .size(100.dp)
                    .clip(RoundedCornerShape(70.dp))
            )
        }
    }
}