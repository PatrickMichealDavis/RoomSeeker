package com.example.tusroomseeker

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.tusroomseeker.component.PageTopAppBar
import com.example.tusroomseeker.component.login.AuthState
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.component.navbar.PageNavigationBar
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@Composable
fun BaseContainer(
    navController: NavHostController,
    pageTitle: String,
    loginViewModel: LoginViewModel,
    pageContent: @Composable (innerPadding: PaddingValues) -> Unit = {},


) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val authState = loginViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated) {
            navController.navigate("login") {
                popUpTo(0)
            }
        }
    }

        Scaffold(
            modifier = Modifier.background(Color.Black).fillMaxSize(),
            topBar = {
                PageTopAppBar( title = pageTitle,navController,loginViewModel
                )
            },
            bottomBar = { PageNavigationBar(navController) },
            content = { innerPadding -> pageContent(innerPadding) }
        )

}