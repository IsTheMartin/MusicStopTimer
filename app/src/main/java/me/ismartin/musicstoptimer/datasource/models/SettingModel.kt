package me.ismartin.musicstoptimer.datasource.models

data class SettingModel(
    val darkTheme: Boolean = false,
    val turnOffBluetooth: Boolean = false,
    val mediaPlayer1: String = "",
    val mediaPlayer2: String = "",
)
