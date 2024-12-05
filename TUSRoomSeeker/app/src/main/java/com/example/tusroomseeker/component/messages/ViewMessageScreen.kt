package com.example.tusroomseeker.component.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.tusroomseeker.ui.theme.TusGold

@Composable
fun ViewMessageScreen(

    navController: NavHostController,
    viewMessageModel: ViewMessageModel,

    ) {
    var messages = viewMessageModel.loadMessages();
    val user: Message? = messages.find { it.receiver==1 }//need to add sender name
    BaseContainer(
        navController = navController,
        pageTitle="SenderName",//hard codded here patrick!!!!!!
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding).fillMaxSize()
                .background(Color.Black)
        ) {
            ViewMessageScreenContent(
                viewMessageModel.loadMessages(),
                navController
            )
        }
    }
}

@Composable
private fun ViewMessageScreenContent(
    messages: List<Message>,
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
                val receiver =1//patrick hard coded should be user Id
                for (message in messages) {
                    Text(
                        text = message.messageBody,
                        color = if (message.sender != receiver) Color.Green else Color.Blue,
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
                println("Message Sent: $message")//add to database patrick?
                message = ""
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
