package com.example.loldex.feature.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.component.LdButton
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.core.model.enums.AttributeType
import com.example.loldex.core.model.enums.CardType
import com.example.loldex.core.model.enums.EffectType
import com.example.loldex.core.model.enums.RaceType
import com.example.loldex.feature.search.R
import com.example.loldex.feature.search.ui.SearchFilterType.ATTRIBUTE
import com.example.loldex.feature.search.ui.SearchFilterType.CARD_TYPE
import com.example.loldex.feature.search.ui.SearchFilterType.EFFECT
import com.example.loldex.feature.search.ui.SearchFilterType.RACE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFilterBottomSheet(
    onDismissRequest: () -> Unit,
    filterType: SearchFilterType,
    onClickedFilterValue: (String?) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val filterList = when (filterType) {
        CARD_TYPE -> CardType.entries.map { it.filterName }
        ATTRIBUTE -> AttributeType.entries.map { it.name }
        RACE -> RaceType.entries.map { it.filterName }
        EFFECT -> EffectType.entries.map { it.filterName }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        containerColor = MaterialTheme.colorScheme.tertiary,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        SearchFilterBottomSheetContent(
            filterType = filterType,
            filterList = filterList,
            onClickedFilterValue = onClickedFilterValue,
        )
    }
}

@Composable
fun SearchFilterBottomSheetContent(
    filterType: SearchFilterType,
    filterList: List<String>,
    onClickedFilterValue: (String?) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(10.dp)
    ) {
        Text(
            text = filterType.name,
            style = MaterialTheme.ldTypography.fontTitleL.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filterList.count()) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClickedFilterValue(filterList[index]) }
                ) {
                    Text(
                        text = filterList[index],
                        style = MaterialTheme.ldTypography.fontTitleM,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        LdButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            onClick = { onClickedFilterValue(null) },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurfaceVariant),
            text = {
                Text(
                    text = stringResource(id = R.string.search_filter_bottom_sheet_reset),
                    style = MaterialTheme.ldTypography.fontBodyS,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        )
    }
}

enum class SearchFilterType {
    CARD_TYPE,
    ATTRIBUTE,
    RACE,
    EFFECT,
}

@ThemePreviews
@Composable
fun SearchFilterBottomSheetPreview() {
    LolDexTheme {
        val filterList = AttributeType.entries.map { it.name }
        SearchFilterBottomSheetContent(
            filterType = ATTRIBUTE,
            filterList = filterList,
            onClickedFilterValue = {}
        )
    }
}