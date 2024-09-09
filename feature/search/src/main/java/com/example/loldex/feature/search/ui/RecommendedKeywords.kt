package com.example.loldex.feature.search.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.component.RectangleTag
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.feature.search.R

@Composable
fun RecommendedKeywords(
    recommendedKeywordList: List<String>,
    onClickedTag: (String) -> Unit,
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
                text = stringResource(id = R.string.recommended_keywords),
                style = MaterialTheme.ldTypography.fontLabelL,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(recommendedKeywordList.size) { index ->
                RectangleTag(
                    name = recommendedKeywordList[index],
                    containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClickedTag = onClickedTag,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun RecommendedKeywordsPreview() {
    LolDexTheme {
        val recommendedKeywordList = listOf("Dark Magician")
        RecommendedKeywords(
            recommendedKeywordList = recommendedKeywordList,
            onClickedTag = {}
        )
    }
}