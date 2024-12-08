package com.example.tusroomseeker.component.messages

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tusroomseeker.BaseContainer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.component.profile.Profile
import com.example.tusroomseeker.ui.theme.TusGold
import kotlinx.coroutines.launch

@Composable
fun ViewMessageScreen(

    navController: NavHostController,
    viewMessageModel: ViewMessageModel,
    loginViewModel: LoginViewModel,
    senderId:Int
    ) {
    val user by loginViewModel.getLoggedInUser().observeAsState()
    val messages = viewMessageModel.fetchMessages(user?.id?:0,senderId).observeAsState()
    //val messages = viewMessageModel.getMessagesForReceiver(user?.id?:0).observeAsState()
    val firstMessage = messages.value?.firstOrNull()
    BaseContainer(
        navController = navController,
        pageTitle = firstMessage?.senderName ?: "unknown",
        loginViewModel = loginViewModel
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding).fillMaxSize()
                .background(Color.Black)
        ) {
            user?.let {
                ViewMessageScreenContent(
                    messages.value ?: emptyList(),
                    viewMessageModel,
                    senderId, it
                )
            }
        }
    }
}

@Composable
private fun ViewMessageScreenContent(
    messages: List<Message>,
    viewMessageModel: ViewMessageModel,
    senderId: Int,
    user: Profile
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier .weight(1f)
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {


                for (message in messages) {

                    Text(
                        text = message.messageBody,
                        color = if (message.senderId != message.receiverId) Color.Green else Color.Blue,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .background(Color(0xFFEFEFF4), RoundedCornerShape(8.dp))
                            .padding(8.dp)
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


        }
        var message by remember { mutableStateOf("") }
        MessageInputField(value = message,
            onValueChange = { message = it },
            onSendClick = {
                scope.launch {
                    val success = viewMessageModel.sendMessageToBothDatabases(
                        senderId = user.id,
                        receiverId = senderId,
                        senderName = user.name,
                        messageBody = message
                    )
                    if (success) {
                        message = ""
                        Toast.makeText(context, "Message sent", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to send message", Toast.LENGTH_SHORT).show() }
                }
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInputField(
    value: String,
    onValueChange: (String) -> Unit,
    onSendClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("Type...", color = Color.Gray) },
            modifier = Modifier
                .weight(0.8f)
                .padding(end = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            )
        )


        Button(
            onClick = onSendClick,
            modifier = Modifier
                .height(50.dp)
                .background(TusGold, shape = RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = TusGold)
        ) {
            Text(text = "Send", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
