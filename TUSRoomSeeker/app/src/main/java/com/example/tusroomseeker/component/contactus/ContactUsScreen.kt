package com.example.tusroomseeker.component.contactus

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tusroomseeker.BaseContainer
import com.example.tusroomseeker.R
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.component.messages.Message
import com.example.tusroomseeker.ui.theme.TusGold

@Composable
fun ContactUsScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {

    BaseContainer(
        navController = navController,
        pageTitle="Messages",
        loginViewModel = loginViewModel
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding).fillMaxSize()
                .background(Color.Black)
        ) {

            ContactUsScreenContent(
            )

        }
    }
}

@Composable
private fun ContactUsScreenContent(

) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {


            Row(){
                Text(
                    text = "Contact Us",
                    fontSize = 30.sp,
                    color = Color.White
                )
            }


            Row(){
                Image(
                    painter = painterResource(R.drawable.phone_icon_large),
                    contentDescription = "1")
            }

            Row(){
                Button(
                    onClick = {
                        val phoneNumber = "tel:+353830673945"//patricks number
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.width(140.dp),
                    shape = RoundedCornerShape(5.dp),
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TusGold)
                ) {
                    Text(text = "Call Us")
                }
            }
        }
    }
