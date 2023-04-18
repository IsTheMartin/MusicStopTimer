package me.ismartin.musicstoptimer.ui.navigation

sealed class Destinations(val route: String) {
    object Home : Destinations("home")
    object Settings : Destinations("settings")
}
