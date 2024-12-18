package com.example.tusroomseeker.component.profile

import android.net.Uri
import android.os.Build
import android.widget.Toast
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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tusroomseeker.BaseContainer
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.ui.theme.TusGold
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
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
                    loginViewModel
                )
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class,
    ExperimentalPermissionsApi::class
)
@Composable
private fun ProfileScreenContent(
    user: State<Profile?>,
   profileViewModel: ProfileViewModel,
    loginViewModel: LoginViewModel
) {
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf(user.value?.name ?: "") }
    var gender by remember { mutableStateOf(user.value?.gender ?: "") }
    var email by remember { mutableStateOf(user.value?.email ?: "") }
    var knum by remember { mutableStateOf(user.value?.knum ?: "") }
    var userType by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    LaunchedEffect(user.value) {
        user.value?.let {
            name = it.name
            gender = it.gender
            email = it.email
            knum = it.knum
            userType = it.userType
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
        //gpt walked me through how to set up permissions for notifications
        //as well as this video https://www.youtube.com/watch?v=QVKo6B7Xd-4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissionsState: PermissionState = rememberPermissionState(
                android.Manifest.permission.POST_NOTIFICATIONS
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val permissionState: PermissionState = rememberPermissionState(
                    android.Manifest.permission.POST_NOTIFICATIONS
                )

                if (!permissionState.status.isGranted) {
                    LaunchedEffect(Unit) {
                        permissionState.launchPermissionRequest()
                    }
                }

                if (!permissionState.status.isGranted) {
                    Text(
                        text = "Permission is required for notifications.",
                        fontSize = 24.sp
                    )
                } else {
                    Text(
                        text = "Permission granted!",
                        fontSize = 24.sp
                    )
                }
            } else {
                Text(
                    text = "Permission not required on this Android version.",
                    fontSize = 24.sp
                )
            }
        }//

        Spacer(modifier = Modifier.height(8.dp))



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
            Button(onClick={userType=0},
                    colors = ButtonDefaults.buttonColors(containerColor = TusGold),
                    modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(8.dp)) {
                Text(text = "Student", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            Button(onClick={userType=1},
                colors = ButtonDefaults.buttonColors(containerColor = TusGold),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(8.dp)) {
                Text(text = "Landlord", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }

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
                        knum = knum,
                        userType = userType
                    ) ?: Profile(
                        id = 0,

                        name = name,
                        gender = gender,
                        email = email,
                        knum = knum,
                        userType = userType
                    )
                   profileViewModel.saveProfile(updatedProfile)
                    Toast.makeText(context, "Profile saved successfully!", Toast.LENGTH_SHORT).show()
                    loginViewModel.loggedInEmail.value=updatedProfile.email
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

        }
    }
}




