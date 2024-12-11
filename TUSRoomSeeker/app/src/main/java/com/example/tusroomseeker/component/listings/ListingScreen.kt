package com.example.tusroomseeker.component.listings

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tusroomseeker.BaseContainer
import com.example.tusroomseeker.R
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.database.MessageStateHolder
import com.example.tusroomseeker.ui.theme.TusGold

@Composable
fun ListingScreen(

    navController: NavHostController,
    listingViewModel: ListingViewModel,
    loginViewModel: LoginViewModel

) {
    val listing:String="Listings"
    val listingList by listingViewModel.loadListings().observeAsState(listOf())
//    if(listingList.isEmpty()){
//        listingViewModel.refreshListings()
//    }



    BaseContainer(
        navController = navController,
        pageTitle=listing,
        loginViewModel = loginViewModel
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding).fillMaxSize()
                .background(Color.Black)
        ) {
            if (listingList.isEmpty()) {
                EmptyListPlaceholder()
            } else {
                ListingScreenContent(
                    listingList,
                    navController
                )
            }
        }
    }
}

@Composable
private fun ListingScreenContent(
    listings: List<Listing>,
    navController: NavHostController
) {
    Column {

        LazyColumn(
            modifier = Modifier.padding(0.dp)
                .fillMaxWidth().background(Color.Black)
        ) {
            items(listings.shuffled()) {
                ListingsCard(it,navController)
            }
        }

    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ListingsCard(listing: Listing,navController: NavHostController) {
    Column(modifier = Modifier
        .background(Color.Black)
        .padding(12.dp).shadow(elevation = 2.dp,
            shape = RoundedCornerShape(8.dp),
            ambientColor = Color.White)
        .border(
            width = 2.dp,
            color = TusGold,
            shape = RoundedCornerShape(8.dp)
        ).padding(16.dp)) {
        Text(
            text = listing.title,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        GlideImage(
            model = Uri.parse("file:///android_asset/${listing.image}"),
            //model = Uri.parse("file:///android_asset/1"),

            contentDescription = "Listing image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(corner = CornerSize(8.dp)))
        )
//        Image(
//            painter = painterResource(R.drawable.listing1),
//            contentDescription = "1",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp)))


        Text(
            text = listing.address,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = listing.price.toString(),
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = listing.description,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {
                navController.navigate("view_message/${listing.userId}")

            },
            modifier = Modifier.width(140.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = ButtonDefaults.buttonElevation(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TusGold,
                contentColor = Color.White
            )
        ){
            Text(text = " Message ", color = Color.Black)
        }

    }
}

@Composable
fun EmptyListPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No listings available.",
            color = Color.White
        )
    }
}



