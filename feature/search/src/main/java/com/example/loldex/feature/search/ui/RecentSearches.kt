package com.example.loldex.feature.search.ui

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
import com.example.loldex.core.designsystem.component.TagWithDeleteButton
import com.example.loldex.core.designsystem.theme.Text10
import com.example.loldex.core.designsystem.theme.Text20
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.feature.search.R

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
                style = MaterialTheme.ldTypography.fontLabelL,
                color = Text10
            )

            Text(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable { onClickedDeleteAll() },
                text = stringResource(id = R.string.recent_searches_delete_all),
                style = MaterialTheme.ldTypography.fontLabelL,
                color = Text20
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
    val recentSearchList = listOf("가나다", "라마바", "사아자", "차카타", "파하")
    RecentSearches(
        recentSearchList = recentSearchList,
        onClickedTag = {},
        onClickedDeleteAll = {},
        onClickedDeleteTag = {}
    )
}