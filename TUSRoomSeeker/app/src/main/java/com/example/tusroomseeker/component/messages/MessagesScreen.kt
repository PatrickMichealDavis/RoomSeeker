package com.example.tusroomseeker.component.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tusroomseeker.BaseContainer

@Composable
fun MessagesScreen(navController: NavHostController,
                   viewMessageModel: ViewMessageModel
)
{
    BaseContainer(
        navController = navController,
        pageTitle="Messages",
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding).fillMaxSize()
                .background(Color.Black)
        ) {
            MessagesScreenContent(
                viewMessageModel.loadMessages(),
                navController
            )
        }
    }
}

@Composable
private fun MessagesScreenContent(
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
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(messages) { message ->
                    Text(
                        text = "testData",
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .background(Color(0xFFEFEFF4), RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .fillMaxWidth()
                            .padding(vertical = 4.dp).clickable {
                                //navController.navigate("view_message/${message.sender}")
                                navController.navigate("view_message")

                            }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}