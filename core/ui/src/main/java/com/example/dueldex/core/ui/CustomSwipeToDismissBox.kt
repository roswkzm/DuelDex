package com.example.dueldex.core.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.SpaceBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSwipeToDismissBox(
    modifier: Modifier = Modifier,
    backgroundContent: @Composable RowScope.(SwipeToDismissBoxState) -> Unit,
    enableDismissFromStartToEnd: Boolean = true,
    enableDismissFromEndToStart: Boolean = true,
    enableDismissFromSettled: Boolean = false,
    onStartToEndEvent: () -> Unit = {},
    onEndToStartEvent: () -> Unit = {},
    onSettledEvent: () -> Unit = {},
    content: @Composable RowScope.() -> Unit,
) {
    var lastDismissedValue by remember { mutableStateOf(SwipeToDismissBoxValue.Settled) }
    val swipeDismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissBoxValue ->
            // 상태가 변경될 때만 처리(중복호출 방지)
            if (lastDismissedValue == dismissBoxValue) return@rememberSwipeToDismissBoxState false
            lastDismissedValue = dismissBoxValue

            when (dismissBoxValue) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onStartToEndEvent()
                    enableDismissFromStartToEnd
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    onEndToStartEvent()
                    enableDismissFromEndToStart
                }

                SwipeToDismissBoxValue.Settled -> {
                    onSettledEvent()
                    enableDismissFromSettled
                }
            }
        }
    )

    SwipeToDismissBox(
        modifier = modifier,
        state = swipeDismissState,
        enableDismissFromStartToEnd = enableDismissFromStartToEnd,
        enableDismissFromEndToStart = enableDismissFromEndToStart,
        backgroundContent = {
            backgroundContent(swipeDismissState)
        }
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultSwipeToDismissBackgroundBox(
    modifier: Modifier,
    swipeDismissState: SwipeToDismissBoxState
) {
    val direction = swipeDismissState.dismissDirection
    val alpha = lerp(0.2f, 0.8f, swipeDismissState.progress)
    val iconScale = lerp(1.0f, 1.8f, swipeDismissState.progress)

    val color by animateColorAsState(
        when (direction) {
            SwipeToDismissBoxValue.EndToStart -> {
                Color.Red.copy(alpha = alpha)
            }

            SwipeToDismissBoxValue.StartToEnd -> {
                Color.Green.copy(alpha = alpha)
            }

            SwipeToDismissBoxValue.Settled -> {
                Color.Unspecified
            }
        },
        label = "SwipeBackGroundColor"
    )

    val alignment = when (direction) {
        SwipeToDismissBoxValue.EndToStart -> {
            Alignment.CenterEnd
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            Alignment.CenterStart
        }

        SwipeToDismissBoxValue.Settled -> {
            Alignment.Center
        }
    }

    val icon = when (direction) {
        SwipeToDismissBoxValue.EndToStart -> {
            Icons.Filled.Delete
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            Icons.Filled.Check
        }

        SwipeToDismissBoxValue.Settled -> {
            Icons.Filled.SpaceBar
        }
    }

    Box(
        modifier = modifier
            .background(color),
        contentAlignment = alignment
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .scale(iconScale),
            imageVector = icon,
            contentDescription = "",
        )
    }
}