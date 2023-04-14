package me.ismartin.musicstoptimer

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.ismartin.musicstoptimer.datasource.models.SettingModel
import me.ismartin.musicstoptimer.datasource.prefdatastore.SettingsDataStore
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SettingsDataStoreTest {

    private lateinit var context: Context
    private lateinit var settingsDataStore: SettingsDataStore

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        settingsDataStore = SettingsDataStore(context)
    }

    @Test
    fun `saveSettings`() = runBlocking {
        val settingModel = SettingModel(
            darkTheme = true,
            turnOffBluetooth = false,
            mediaPlayer1 = "mediaPlayer1",
            mediaPlayer2 = "mediaPlayer1",
        )
        settingsDataStore.saveSettings(settingModel)

        val preferences = settingsDataStore.getSettingPreferences().first()
        val savedSettingModel = SettingModel(
            darkTheme = preferences.darkTheme,
            turnOffBluetooth = preferences.turnOffBluetooth,
            mediaPlayer1 = preferences.mediaPlayer1,
            mediaPlayer2 = preferences.mediaPlayer2
        )
        assertEquals(settingModel, savedSettingModel)
    }
}
