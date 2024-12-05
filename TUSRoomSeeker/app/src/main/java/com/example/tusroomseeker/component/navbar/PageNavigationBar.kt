package com.example.tusroomseeker.component.navbar

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tusroomseeker.R
import com.example.tusroomseeker.component.navigation.Screen
import com.example.tusroomseeker.component.navigation.screens
import com.example.tusroomseeker.ui.theme.TusGold

@Composable
fun PageNavigationBar(
    navController: NavHostController
) {
    val icons = mapOf(
        Screen.Home to NavBarIcon(
            filledIcon = Icons.Filled.Home,
            outlinedIcon = Icons.Outlined.Home,
            label = stringResource(id = R.string.home)
        ),
        Screen.Messages to NavBarIcon(
            filledIcon = Icons.Filled.MailOutline,
            outlinedIcon = Icons.Outlined.MailOutline,
            label = stringResource(id = R.string.messages)
        ),
        Screen.AddListing to NavBarIcon(
            filledIcon = Icons.Filled.Add,
            outlinedIcon = Icons.Outlined.Add,
            label = stringResource(id = R.string.addListing)
        ),
        Screen.Map to NavBarIcon(
            filledIcon = Icons.Filled.LocationOn,
            outlinedIcon = Icons.Outlined.LocationOn,
            label = stringResource(id = R.string.map)
        )
    )

    NavigationBar(containerColor = TusGold, modifier = Modifier.height(80.dp) ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            val isSelected = currentDestination?.route == screen.route

            val label = icons[screen]!!.label

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected)
                            icons[screen]!!.filledIcon
                        else
                            icons[screen]!!.outlinedIcon,
                        contentDescription = label
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Black,
                    indicatorColor = Color.Transparent
                ),
                label = {
                    Text(label)
                }
            )
        }
    }
}