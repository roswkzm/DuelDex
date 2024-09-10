package com.example.dueldex.feature.search.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.component.TagButton
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography
import com.example.dueldex.feature.search.R
import com.example.dueldex.feature.search.setFilterTagText
import com.example.dueldex.feature.search.ui.SearchFilterType.CARD_TYPE

@Composable
fun SearchFilters(
    selectedFilterValues: MutableMap<SearchFilterType, String?>,
    onFilterValueChange: (Pair<SearchFilterType, String?>) -> Unit,
    levelValue: Float,
    onLevelValueChange: (Float) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isShowFilterBottomSheet by remember { mutableStateOf(false) }
    var filterType: SearchFilterType by remember { mutableStateOf(CARD_TYPE) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = if (isExpanded) Icons.Filled.FilterAltOff else Icons.Filled.FilterAlt

            if (isExpanded) {
                Text(
                    text = stringResource(id = R.string.search_filters),
                    style = MaterialTheme.ddTypography.fontLabelL,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { isExpanded = !isExpanded },
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth(),
            visible = isExpanded
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(SearchFilterType.entries.size) { index ->
                        val currentSearchFilterType = SearchFilterType.entries[index]
                        val selectedValue = selectedFilterValues[currentSearchFilterType]
                        val buttonText = selectedValue ?: stringResource(
                            id = setFilterTagText(currentSearchFilterType)
                        )
                        TagButton(
                            name = buttonText,
                            color = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            icon = Icons.Filled.KeyboardArrowDown,
                            onClickedTag = {
                                filterType = currentSearchFilterType
                                isShowFilterBottomSheet = true
                            }
                        )
                    }
                }

                LevelFilter(
                    levelValue = levelValue,
                    onLevelValueChange = onLevelValueChange
                )
            }
        }

        if (isShowFilterBottomSheet) {
            SearchFilterBottomSheet(
                onDismissRequest = { isShowFilterBottomSheet = false },
                filterType = filterType,
                onClickedFilterValue = { selectedValue ->
                    onFilterValueChange(filterType to selectedValue)
                    isShowFilterBottomSheet = false
                }
            )
        }
    }
}

@Composable
internal fun LevelFilter(
    levelValue: Float,
    onLevelValueChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        val displayValue = if (levelValue == 0f) {
            stringResource(id = R.string.tag_filter_title_level)
        } else {
            "${levelValue.toInt()} ${stringResource(id = R.string.tag_filter_title_level_tailing)}"
        }

        Text(
            text = displayValue,
            style = MaterialTheme.ddTypography.fontLabelL,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Slider(
            value = levelValue,
            onValueChange = onLevelValueChange,
            valueRange = 0f..12f,
            steps = 11,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.onSurface,
                activeTrackColor = MaterialTheme.colorScheme.onSurfaceVariant,
                activeTickColor = MaterialTheme.colorScheme.surface,
                inactiveTrackColor = MaterialTheme.colorScheme.onSurfaceVariant,
                inactiveTickColor = MaterialTheme.colorScheme.surface,
            )
        )
    }
}

@ThemePreviews
@Composable
internal fun SearchFilterPreview() {
    DuelDexTheme {
        val selectedFilterValues = remember { mutableStateMapOf<SearchFilterType, String?>() }
        var levelValue by remember { mutableStateOf(0f) }

        SearchFilters(
            selectedFilterValues = selectedFilterValues,
            onFilterValueChange = {},
            levelValue = levelValue,
            onLevelValueChange = {},
        )
    }
}