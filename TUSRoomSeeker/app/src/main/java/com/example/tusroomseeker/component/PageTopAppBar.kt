package com.example.tusroomseeker.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tusroomseeker.R
import com.example.tusroomseeker.component.login.LoginViewModel
import com.example.tusroomseeker.ui.theme.TusGold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageTopAppBar(
    title:String,
    navController: NavHostController,
   loginViewModel: LoginViewModel,
    onClick: () -> Unit = {},

) {
    CenterAlignedTopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {
            IconButton(onClick = { loginViewModel.signout() }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Sign Out",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate("profile") }) {
                ProfileIconWithImage(
                    imageResource = R.drawable.female1,//patrick hard coded
                    size = 40
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = TusGold,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun ProfileIconWithImage(
    imageResource: Int,
    size: Int = 80,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = "Profile Icon",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    )
}
