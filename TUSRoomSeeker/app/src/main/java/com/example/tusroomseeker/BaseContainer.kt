package com.example.tusroomseeker

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.tusroomseeker.component.PageTopAppBar
import com.example.tusroomseeker.component.navbar.PageNavigationBar
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun BaseContainer(
    navController: NavHostController,
    pageTitle: String,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {},

) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()


        Scaffold(
            modifier = Modifier.background(Color.Black).fillMaxSize(),
            topBar = {
                PageTopAppBar( title = pageTitle,navController
                )
            },
            bottomBar = { PageNavigationBar(navController) },
            content = { innerPadding -> pageContent(innerPadding) }
        )

}