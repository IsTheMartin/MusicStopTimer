package me.ismartin.musicstoptimer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.ismartin.musicstoptimer.R
import me.ismartin.musicstoptimer.ui.navigation.Destinations

@Composable
fun HomeScreen(navController: NavController) {
    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(all = 24.dp),
                onClick = { navController.navigate(Destinations.Settings.route) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(id = R.string.content_description_settings)
                )
            }
            CountDownTimer(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun CountDownTimer(
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Time Left")
        Text(text = "00:00:00")
        Spacer(modifier = Modifier.height(24.dp))
    }
}
