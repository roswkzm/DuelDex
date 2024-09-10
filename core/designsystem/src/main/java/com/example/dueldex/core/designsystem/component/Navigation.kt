package com.example.dueldex.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews

@Composable
fun RowScope.DdNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = DdNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = DdNavigationDefaults.navigationContentColor(),
            selectedTextColor = DdNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = DdNavigationDefaults.navigationContentColor(),
            indicatorColor = DdNavigationDefaults.navigationIndicatorColor(),
        )
    )
}

@Composable
fun DdNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        contentColor = DdNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

@ThemePreviews
@Composable
fun DdNavigationPreview() {
    val items = listOf("Home", "Saved")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Bookmarks,
    )
    val selectedIcons = listOf(
        Icons.Rounded.Home,
        Icons.Rounded.Bookmarks,
    )


    DuelDexTheme {
        DdNavigationBar {
            items.forEachIndexed { index, item ->
                DdNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}

object DdNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.inverseSurface

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.surfaceContainer
}