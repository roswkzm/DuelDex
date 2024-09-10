package com.example.dueldex.feature.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.component.TagWithDeleteButton
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography
import com.example.dueldex.feature.search.R

@Composable
fun RecentSearches(
    recentSearchList: List<String>,
    onClickedTag: (String) -> Unit,
    onClickedDeleteTag: (String) -> Unit,
    onClickedDeleteAll: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.recent_searches),
                style = MaterialTheme.ddTypography.fontLabelL,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable { onClickedDeleteAll() },
                text = stringResource(id = R.string.recent_searches_delete_all),
                style = MaterialTheme.ddTypography.fontLabelL,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(recentSearchList.size) { index ->
                TagWithDeleteButton(
                    name = recentSearchList[index],
                    color = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    onClickedTag = onClickedTag,
                    onClickedDelete = onClickedDeleteTag
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun RecentSearchesPreview() {
    DuelDexTheme {
        val recentSearchList = listOf("가나다", "라마바", "사아자", "차카타", "파하")
        RecentSearches(
            recentSearchList = recentSearchList,
            onClickedTag = {},
            onClickedDeleteAll = {},
            onClickedDeleteTag = {}
        )
    }
}