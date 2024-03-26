package com.example.loldex.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.loldex.core.designsystem.component.LdBackGround

@Composable
fun MainScreen(
    windowSizeClass : WindowSizeClass,
    appState : AppState = rememberAp
) {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    LdBackGround {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            bottomBar = {

            }
        ) { padding ->

        }
    }
}

@Composable
private fun LdBottomBar(
    destinations : List<TopLe>
){

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}