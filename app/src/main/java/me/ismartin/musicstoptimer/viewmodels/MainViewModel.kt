package me.ismartin.musicstoptimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.ismartin.musicstoptimer.repositories.SettingsRepository
import me.ismartin.musicstoptimer.repositories.models.SettingDisplayModel
import me.ismartin.musicstoptimer.repositories.models.SettingType
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val settingsState: StateFlow<List<SettingDisplayModel>> = settingsRepository.getSettings()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val darkThemeState: StateFlow<Boolean> = settingsRepository.isDarkTheme()
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun updateSwitchSettingValue(setting: SettingDisplayModel, newValue: Boolean) {
        val settingIndex = settingsState.value.indexOf(setting)
        if (settingIndex >= 0) {
            val currentSettingsList = settingsState.value.minus(setting).toMutableList()
            val settingUpdated = setting.copy(type = SettingType.Switch(newValue))
            currentSettingsList.add(settingIndex, settingUpdated)
            updateSettings(currentSettingsList)
        }
    }

    private fun updateSettings(updatedSettingsList: List<SettingDisplayModel>) =
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.saveSettings(updatedSettingsList)
        }
}
