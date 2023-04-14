package me.ismartin.musicstoptimer.datasource.prefdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.ismartin.musicstoptimer.datasource.models.SettingModel
import javax.inject.Inject

class SettingsDataStore @Inject constructor(
    private val context: Context
) {

    fun getSettingPreferences(): Flow<SettingModel> {
        return context.dataStore.data.map { pref ->
            SettingModel(
                darkTheme = pref[DARK_THEME_KEY] ?: false,
                turnOffBluetooth = pref[TURN_OFF_BLUETOOTH_KEY] ?: false,
                mediaPlayer1 = pref[MEDIA_PLAYER_1_KEY].orEmpty(),
                mediaPlayer2 = pref[MEDIA_PLAYER_2_KEY].orEmpty()
            )
        }
    }

    fun getDarkThemePreference(): Flow<Boolean> {
        return context.dataStore.data.map { pref ->
            pref[DARK_THEME_KEY] ?: false
        }
    }

    suspend fun saveSettings(settingModel: SettingModel) {
        context.dataStore.edit { pref ->
            pref[DARK_THEME_KEY] = settingModel.darkTheme
            pref[TURN_OFF_BLUETOOTH_KEY] = settingModel.turnOffBluetooth
            pref[MEDIA_PLAYER_1_KEY] = settingModel.mediaPlayer1
            pref[MEDIA_PLAYER_2_KEY] = settingModel.mediaPlayer2
        }
    }

    companion object {
        private const val DATA_STORE_SETTINGS = "settings"
        private val DARK_THEME_KEY = booleanPreferencesKey("darkTheme")
        private val TURN_OFF_BLUETOOTH_KEY = booleanPreferencesKey("turnOffBluetooth")
        private val MEDIA_PLAYER_1_KEY = stringPreferencesKey("mediaPlayer1")
        private val MEDIA_PLAYER_2_KEY = stringPreferencesKey("mediaPlayer2")

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            DATA_STORE_SETTINGS
        )
    }
}
