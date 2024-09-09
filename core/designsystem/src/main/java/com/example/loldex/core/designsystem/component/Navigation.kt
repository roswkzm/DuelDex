package com.example.loldex.core.designsystem.component

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
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews

@Composable
fun RowScope.LdNavigationBarItem(
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
            selectedIconColor = LdNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = LdNavigationDefaults.navigationContentColor(),
            selectedTextColor = LdNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = LdNavigationDefaults.navigationContentColor(),
            indicatorColor = LdNavigationDefaults.navigationIndicatorColor(),
        )
    )
}

@Composable
fun LdNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        contentColor = LdNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

@ThemePreviews
@Composable
fun LdNavigationPreview() {
    val items = listOf("Home", "Saved")
    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Bookmarks,
    )
    val selectedIcons = listOf(
        Icons.Rounded.Home,
        Icons.Rounded.Bookmarks,
    )


    LolDexTheme {
        LdNavigationBar {
            items.forEachIndexed { index, item ->
                LdNavigationBarItem(
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

object LdNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.inverseSurface

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.surfaceContainer
}