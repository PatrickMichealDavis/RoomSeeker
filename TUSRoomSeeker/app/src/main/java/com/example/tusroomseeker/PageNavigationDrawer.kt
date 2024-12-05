package com.example.tusroomseeker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PageNavigationDrawer(
    navController: NavHostController,
    drawerState: DrawerState,
    closeDrawer: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    var selectedItem by rememberSaveable { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column {
                    Image(
                        painter = painterResource(id = R.mipmap.ic_launcher),
                        contentDescription = stringResource(id = R.string.nav_drawer_image),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = 16.dp, top = 16.dp)
                    )

//                    DisplayNavItem(
//                        iconImage = Icons.AutoMirrored.Filled.Login,
//                        description = stringResource(id = R.string.login),
//                        selectedItem = selectedItem,
//                        updateSelected = { selectedItem = it}
//                    )
//
//                    DisplayNavItem(
//                        iconImage = Icons.AutoMirrored.Filled.Help,
//                        description = stringResource(id = R.string.help),
//                        selectedItem = selectedItem,
//                        updateSelected = { selectedItem = it }
//                    )
//
//                    DisplayNavItem(
//                        iconImage = Icons.Default.Feedback,
//                        description = stringResource(id = R.string.feedback),
//                        selectedItem = selectedItem,
//                        updateSelected = { selectedItem = it }
//                    )

                }
            }
        }
    ){
        content()
    }
}


@Composable
private fun DisplayNavItem(
    iconImage: ImageVector,
    description: String,
    selectedItem: String,
    updateSelected: (String) -> Unit = {},
    doClick: () -> Unit = {}
) {
    NavigationDrawerItem(
        icon = {
            Icon(imageVector = iconImage, contentDescription = description)
        },
        label = {
            Text(text = description)
        },
        selected = selectedItem == description,
        onClick = {
            updateSelected(description)
            doClick()
        }
    )
}