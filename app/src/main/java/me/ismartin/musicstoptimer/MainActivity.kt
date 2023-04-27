package me.ismartin.musicstoptimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.ismartin.musicstoptimer.ui.navigation.Destinations
import me.ismartin.musicstoptimer.ui.screens.HomeScreen
import me.ismartin.musicstoptimer.ui.screens.SettingsScreen
import me.ismartin.musicstoptimer.ui.theme.MusicStopTimerTheme
import me.ismartin.musicstoptimer.viewmodels.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: MainViewModel = viewModel()
            MusicStopTimerTheme(darkTheme = viewModel.darkThemeState.collectAsState().value) {
                NavHost(
                    navController = navController,
                    startDestination = Destinations.Home.route
                ) {
                    composable(Destinations.Home.route) {
                        HomeScreen(navController)
                    }
                    composable(Destinations.Settings.route) {
                        SettingsScreen(
                            navController = navController,
                            settingsState = viewModel.settingsState.collectAsState().value,
                            settingsEvent = viewModel::processSettingsEvents
                        )
                    }
                }
            }
        }
    }
}
