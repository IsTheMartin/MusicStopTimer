package me.ismartin.musicstoptimer.repositories.models

import androidx.annotation.StringRes
import me.ismartin.musicstoptimer.R

data class SettingDisplayModel(
    val id: String,
    val type: SettingType,
    @StringRes val displayName: Int,
)

sealed class SettingType() {
    data class Switch(
        val value: Boolean,
    ) : SettingType()

    data class SingleSelection(
        val value: String,
    ) : SettingType()
}

enum class SettingName(
    val id: String,
    @StringRes val displayName: Int,
    @StringRes val description: Int? = null
) {
    DARK_THEME("darkTheme", R.string.set_dark_theme),
    TURN_OFF_BLUETOOTH("turnOffBluetooth", R.string.turn_off_bluetooth),
    MEDIA_PLAYER_1("mediaPlayer1", R.string.set_media_player_1),
    MEDIA_PLAYER_2("mediaPlayer2", R.string.set_media_player_2),
}
