package com.example.tusroomseeker

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.tusroomseeker.component.navigation.BuildNavigationGraph
import com.example.tusroomseeker.database.MessageStateHolder
import com.example.tusroomseeker.ui.theme.TUSRoomSeekerTheme
import com.example.tusroomseeker.ui.theme.TusGold
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.messaging.FirebaseMessaging
import androidx.compose.ui.text.font.FontWeight
import com.example.tusroomseeker.component.map.LocationViewModel
import com.google.accompanist.permissions.rememberMultiplePermissionsState


class MainActivity : ComponentActivity() {
    private val locationViewModel: LocationViewModel by viewModels()
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get the FCM token
            val token = task.result
            Log.d("FCM", "FCM Token: $token")
            // Optionally, send the token to your server for targeted notifications
        }
        setContent {
            TUSRoomSeekerTheme {
                Scaffold(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                    BuildNavigationGraph()
                    MapPermissions(locationViewModel)
                    MessageDialog()

                }
            }
        }
    }
}


//modified chat gpt code here i got chat to walk me through the setting up of notifications
@Composable
fun MessageDialog() {
    val title = MessageStateHolder.messageTitle
    val body = MessageStateHolder.messageBody
    val context = LocalContext.current

    if (title != null && body != null) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = {
                MessageStateHolder.clearMessage()
            },
            confirmButton = {
                Button(onClick = {
                    Toast.makeText(
                        context,
                        "Message marked as read!",
                        Toast.LENGTH_SHORT
                    ).show()
                    MessageStateHolder.clearMessage()
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier

                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Read",
                        color= TusGold
                    )
                }
            },
            dismissButton = {
                Button(onClick = {
                    MessageStateHolder.clearMessage()
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier

                        .padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp)) {
                    Text("Close",
                        color= TusGold
                    )
                }
            },
            title = { Text(text = title) },
            text = { Text(text = body) }
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun MapPermissions(locationViewModel: LocationViewModel) {
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    )
    locationViewModel.updatePermissionState(locationPermissionState)
    LaunchedEffect(true) {
        locationPermissionState.launchMultiplePermissionRequest()
    }
}

