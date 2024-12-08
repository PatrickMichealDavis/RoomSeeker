package com.example.tusroomseeker.component.profile

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tusroomseeker.BaseContainer
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.ui.theme.TusGold
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen (
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    loginViewModel: LoginViewModel
    ) {
    //need to add an if statement to change this to registration too
    val profile:String="Profile"
    val user =loginViewModel.getLoggedInUser().observeAsState()

    BaseContainer(
    navController = navController,
    pageTitle=profile,
        loginViewModel = loginViewModel
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding).fillMaxSize()
                .background(Color.Black)
        ) {

                ProfileScreenContent(
                    user,
                    profileViewModel,
                    navController
                )
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
private fun ProfileScreenContent(
    user: State<Profile?>,
   profileViewModel: ProfileViewModel,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf(user.value?.name ?: "") }
    var gender by remember { mutableStateOf(user.value?.gender ?: "") }
    var email by remember { mutableStateOf(user.value?.email ?: "") }
    var knum by remember { mutableStateOf(user.value?.knum ?: "") }

    LaunchedEffect(user.value) {
        user.value?.let {
            name = it.name
            gender = it.gender
            email = it.email
            knum = it.knum
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                model = Uri.parse("file:///android_asset/${user.value?.userImage?: "noone.jpg"}"),
                //model = Uri.parse("file:///android_asset/1"),

                contentDescription = "user image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(top = 4.dp, start = 4.dp, end = 4.dp)
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(corner = CornerSize(8.dp)))
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Handle add photo */ },
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
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Name", color = Color.Black) },
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
            value = gender,
            onValueChange = { gender = it },
            label = { Text(text = "Gender", color = Color.Black) },
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
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email", color = Color.Black) },
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
            value = knum,
            onValueChange = { knum = it },
            label = { Text(text = "K number", color = Color.Black) },
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
                    val updatedProfile = user.value?.copy(
                        name = name,
                        gender = gender,
                        email = email,
                        knum = knum
                    ) ?: Profile(
                        id = 0,
                        userImage = "",
                        name = name,
                        gender = gender,
                        email = email,
                        knum = knum,
                        userType = 0
                    )
                   profileViewModel.saveProfile(updatedProfile)
                },
                colors = ButtonDefaults.buttonColors(containerColor = TusGold),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Update",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

//            Button(
//                onClick = { /* Handle save */ },
//                colors = ButtonDefaults.buttonColors(containerColor = TusGold),
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(start = 8.dp),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Text(
//                    text = "Save",
//                    color = Color.Black,
//                    fontWeight = FontWeight.Bold
//                )
//            }
        }
    }
}




