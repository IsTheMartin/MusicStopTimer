package me.ismartin.musicstoptimer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(all = 24.dp),
                onClick = { navController.navigate("SettingsScreen") }
            ) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
            }
            CountDownTimer()
        }
    }
}

@Composable
fun CountDownTimer() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Time Left")
        Text(text = "00:00:00")
        Spacer(modifier = Modifier.height(24.dp))
        MediaButtons()
    }
}

@Composable
fun MediaButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "Media1")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "Media2")
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Call, contentDescription = "Media3")
        }
    }
}

@Composable
fun StartStopButton() {
    IconToggleButton(checked = false, onCheckedChange = {
    }) {
    }
}
