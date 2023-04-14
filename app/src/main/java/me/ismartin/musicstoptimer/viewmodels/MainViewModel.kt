package me.ismartin.musicstoptimer.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import me.ismartin.musicstoptimer.repositories.SettingsRepository
import me.ismartin.musicstoptimer.repositories.models.SettingDisplayModel
import me.ismartin.musicstoptimer.repositories.models.SettingType
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _settingsState = MutableStateFlow<List<SettingDisplayModel>>(emptyList())
    val settingsState: StateFlow<List<SettingDisplayModel>> get() = _settingsState

    var isDarkThemeState: Boolean by mutableStateOf(false)

    init {
        getAppTheme()
        getSettings()
    }

    private fun getAppTheme() = viewModelScope.launch(Dispatchers.IO) {
        val isDarkTheme = settingsRepository.isDarkTheme().first()
        isDarkThemeState = isDarkTheme
    }

    private fun getSettings() = viewModelScope.launch(Dispatchers.IO) {
        _settingsState.emit(settingsRepository.getSettings().first())
    }

    fun updateSwitchSettingValue(setting: SettingDisplayModel, newValue: Boolean) {
        val index = settingsState.value.indexOf(setting)
        if (index >= 0) {
            val list = settingsState.value.minus(setting).toMutableList()
            val settingUpdated = setting.copy(type = SettingType.Switch(newValue))
            list.add(index, settingUpdated)
            updateSettings(list)
        }
    }

    private fun updateSettings(updatedSettingsList: List<SettingDisplayModel>) =
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.saveSettings(updatedSettingsList)
            getAppTheme()
            getSettings()
        }
}
