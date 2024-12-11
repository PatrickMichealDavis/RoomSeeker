package com.example.tusroomseeker.component.listings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.tusroomseeker.BaseContainer
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.component.profile.Profile
import com.example.tusroomseeker.ui.theme.TusGold
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun AddListingScreen(

    navController: NavHostController,
    listingViewModel: ListingViewModel,
    loginViewModel: LoginViewModel

    ) {

    val listingList by listingViewModel.loadListings().observeAsState(listOf())
    val user by loginViewModel.getLoggedInUser().observeAsState()
    val userId = user?.id?:0

    val listing:String="Add Listing"
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
            AddListingScreenContent(
                listingList,
                navController,
                userId,
                listingViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddListingScreenContent(
    listings: List<Listing>,
    navController: NavHostController,
    userId: Int,
    listingViewModel: ListingViewModel
) {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var eircode by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "title", color = Color.Black) },
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEFEFF4),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        MultiLineTextArea(
            value = text,
            onValueChange = { text = it },
            placeholder = "Write description here..."
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                image=getImage()
            },
            colors = ButtonDefaults.buttonColors(containerColor = TusGold),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(0.5f)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Add Photo",
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text(text = "Address", color = Color.Black) },
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEFEFF4),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        TextField(
            value = eircode,
            onValueChange = { eircode = it },
            label = { Text(text = "Eircode", color = Color.Black) },
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEFEFF4),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text(text = "Price", color = Color.Black) },
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEFEFF4),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )


        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = {
                    val newListing = Listing(
                        title = title,
                        image = image,
                        address = address,
                        price = price.toDouble(),
                        description = text,
                        eircode = eircode,
                        userId = userId

                    )
                    scope.launch {
                        listingViewModel.saveListing(newListing)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = TusGold),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Confirm",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = { /* Handle save */ },
                colors = ButtonDefaults.buttonColors(containerColor = TusGold),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Reset",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiLineTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Enter your text here...",
    maxLines: Int = 5
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 250.dp, max = 600.dp),

        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.LightGray
        ),
        maxLines = maxLines
    )
}

fun getImage(): String {

    val randomNum = (1..10).random()

    return "images/bedroom$randomNum.jpg"
}


