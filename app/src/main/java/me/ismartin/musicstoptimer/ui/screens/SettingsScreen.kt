package me.ismartin.musicstoptimer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.ismartin.musicstoptimer.BuildConfig
import me.ismartin.musicstoptimer.R
import me.ismartin.musicstoptimer.repositories.models.SettingDisplayModel
import me.ismartin.musicstoptimer.repositories.models.SettingType
import me.ismartin.musicstoptimer.repositories.models.SettingsEvent
import me.ismartin.musicstoptimer.ui.theme.MusicStopTimerTheme

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsState: List<SettingDisplayModel>,
    settingsEvent: (SettingsEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(onNavigationClick = { navController.navigateUp() })
        }
    ) { paddingValues ->
        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentPadding = paddingValues
            ) {
                items(settingsState) { setting ->
                    when (setting.type) {
                        is SettingType.SingleSelection -> {
                            // todo: SingleSelectionSetting -> change to appSelectionSetting?
                        }

                        is SettingType.Switch -> SwitchSetting(setting = setting) { newValue ->
                            settingsEvent(SettingsEvent.OnSwitchSettingChange(setting, newValue))
                        }
                    }
                }
            }
            VersionSetting(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun TopBar(onNavigationClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier,
        title = {
            Text(text = stringResource(id = R.string.settings))
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.content_back_navigation)
                )
            }
        }
    )
}

@Composable
fun VersionSetting(
    modifier: Modifier = Modifier
) {
    val appVersion = BuildConfig.VERSION_NAME
    Box(modifier = modifier) {
        Column {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.onSecondary
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                text = stringResource(id = R.string.app_version, appVersion),
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SwitchSetting(
    setting: SettingDisplayModel,
    onCheckedChange: (Boolean) -> Unit
) {
    var settingValue by remember {
        mutableStateOf((setting.type as SettingType.Switch).value)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 48.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = setting.displayName)
            )
            Switch(
                modifier = Modifier.align(Alignment.Bottom),
                checked = settingValue,
                onCheckedChange = {
                    settingValue = it
                    onCheckedChange(it)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    MusicStopTimerTheme {
        SettingsScreen(
            navController = rememberNavController(),
            settingsState = listOf(),
            settingsEvent = {}
        )
    }
}
