package com.example.tusroomseeker.component.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tusroomseeker.R
import com.example.tusroomseeker.ui.theme.TusGold

@Composable
fun LoginScreen(
    openAndPopUp: (String, String) -> Unit,
    viewModel: LoginViewModel
) {
    val uiState by viewModel.uiState

    LoginScreenContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSignInClick(openAndPopUp) },

    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginDetails,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,

) {
    Column(
        modifier = modifier

            .fillMaxSize()
            .background(Color.Black)//here is background color
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       Box(modifier=Modifier.padding(15.dp)){
           Spacer(modifier = Modifier.height(20.dp))
           Image(
               painter = painterResource(R.drawable.room_seeker_full),
               contentDescription = "1")
       }

        TextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            label = { Text("Enter Email") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        TextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            label = { Text("Enter Password") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Button(
            onClick = onSignInClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = TusGold,
                contentColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(0.8f).padding(top = 16.dp)
        ) {
            Text("Sign In")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreenContent() {
    LoginScreenContent(
        uiState = LoginDetails(
            email = "example@example.com",
            password = "password123"
        ),
        onEmailChange = {},
        onPasswordChange = {},
        onSignInClick = {},

    )
}


