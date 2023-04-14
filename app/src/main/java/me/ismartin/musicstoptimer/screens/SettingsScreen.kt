package me.ismartin.musicstoptimer.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.ismartin.musicstoptimer.repositories.models.SettingDisplayModel
import me.ismartin.musicstoptimer.repositories.models.SettingType
import me.ismartin.musicstoptimer.viewmodels.MainViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: MainViewModel) {
    val settingsList = viewModel.settingsState.collectAsState()

    Scaffold(
        topBar = { TopBar(navController = navController) }
    ) { paddingValues ->
        paddingValues
        LazyColumn {
            items(settingsList.value) { setting ->
                when (setting.type) {
                    is SettingType.SingleSelection -> {
                        Text(text = stringResource(id = setting.displayName))
                    }
                    is SettingType.Switch -> SwitchSetting(setting = setting) { newValue ->
                        viewModel.updateSwitchSettingValue(setting, newValue)
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(navController: NavController) {
    TopAppBar(
        modifier = Modifier,
        title = {
            Text(text = "Settings")
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
fun SwitchSetting(setting: SettingDisplayModel, onCheckedChange: (Boolean) -> Unit) {
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
