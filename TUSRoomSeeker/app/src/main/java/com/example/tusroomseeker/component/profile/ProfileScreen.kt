package com.example.tusroomseeker.component.profile

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tusroomseeker.BaseContainer
import com.example.tusroomseeker.R
import com.example.tusroomseeker.ui.theme.TusGold

@Composable
fun ProfileScreen (
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    ) {
    //need to add an if statement to change this to registration too
    val profile:String="Profile"
    BaseContainer(
    navController = navController,
    pageTitle=profile,
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding).fillMaxSize()
                .background(Color.Black)
        ) {
            ProfileScreenContent(
                profileViewModel.loadProfiles(),
                navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreenContent(
    profiles: List<Profile>,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        val female: Profile? = profiles.find { it.id == 1 }//hard coded patrick
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (female != null) {
                Image(
                    painter = painterResource(female.userImage),
                    contentDescription = "1",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp)))
            }
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

        val inputFields = listOf("Name", "Gender", "Email", "K number")
        inputFields.forEach { label ->
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = label, color = Color.Black) },
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFFEFEFF4),
                   // textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = { /* Handle update */ },
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

            Button(
                onClick = { /* Handle save */ },
                colors = ButtonDefaults.buttonColors(containerColor = TusGold),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Save",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}




