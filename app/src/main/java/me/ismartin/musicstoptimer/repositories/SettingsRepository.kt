package me.ismartin.musicstoptimer.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.ismartin.musicstoptimer.datasource.models.SettingModel
import me.ismartin.musicstoptimer.datasource.prefdatastore.SettingsDataStore
import me.ismartin.musicstoptimer.repositories.models.SettingDisplayModel
import me.ismartin.musicstoptimer.repositories.models.SettingName
import me.ismartin.musicstoptimer.repositories.models.SettingType
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val dataStore: SettingsDataStore,
) {

    fun getSettings(): Flow<List<SettingDisplayModel>> {
        return dataStore.getSettingPreferences().map {
            settingToDisplay(it)
        }
    }

    fun isDarkTheme() = dataStore.getDarkThemePreference()

    suspend fun saveSettings(updatedSettingsList: List<SettingDisplayModel>) {
        var setting = SettingModel()
        updatedSettingsList.forEach { updatedSetting ->
            setting = when (updatedSetting.id) {
                SettingName.DARK_THEME.id -> setting.copy(darkTheme = (updatedSetting.type as SettingType.Switch).value)
                SettingName.TURN_OFF_BLUETOOTH.id -> setting.copy(turnOffBluetooth = (updatedSetting.type as SettingType.Switch).value)
                SettingName.MEDIA_PLAYER_1.id -> setting.copy(mediaPlayer1 = (updatedSetting.type as SettingType.SingleSelection).value)
                SettingName.MEDIA_PLAYER_2.id -> setting.copy(mediaPlayer2 = (updatedSetting.type as SettingType.SingleSelection).value)
                else -> setting
            }
        }
        dataStore.saveSettings(setting)
    }

    private fun settingToDisplay(settingModel: SettingModel): List<SettingDisplayModel> {
        return with(settingModel) {
            listOf(
                SettingDisplayModel(
                    id = SettingName.DARK_THEME.id,
                    displayName = SettingName.DARK_THEME.displayName,
                    type = SettingType.Switch(darkTheme)
                ),
                SettingDisplayModel(
                    id = SettingName.TURN_OFF_BLUETOOTH.id,
                    displayName = SettingName.TURN_OFF_BLUETOOTH.displayName,
                    type = SettingType.Switch(turnOffBluetooth)
                ),
                SettingDisplayModel(
                    id = SettingName.MEDIA_PLAYER_1.id,
                    displayName = SettingName.MEDIA_PLAYER_1.displayName,
                    type = SettingType.SingleSelection(mediaPlayer1)
                ),
                SettingDisplayModel(
                    id = SettingName.MEDIA_PLAYER_2.id,
                    displayName = SettingName.MEDIA_PLAYER_2.displayName,
                    type = SettingType.SingleSelection(mediaPlayer2)
                )
            )
        }
    }
}
