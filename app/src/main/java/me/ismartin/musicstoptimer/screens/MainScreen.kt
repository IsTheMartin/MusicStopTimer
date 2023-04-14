package me.ismartin.musicstoptimer.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Button(onClick = { navController.navigate("SettingsScreen") }) {
        Text(text = "Navigate")
    }
}
