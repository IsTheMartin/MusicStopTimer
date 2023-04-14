package me.ismartin.musicstoptimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.ismartin.musicstoptimer.screens.MainScreen
import me.ismartin.musicstoptimer.screens.SettingsScreen
import me.ismartin.musicstoptimer.ui.theme.MusicStopTimerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicStopTimerTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "MainScreen") {
                    composable("MainScreen") {
                        MainScreen(navController)
                    }
                    composable("SettingsScreen") {
                        SettingsScreen(navController)
                    }
                }
            }
        }
    }
}
