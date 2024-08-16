package com.example.loldex.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loldex.core.designsystem.component.RectangleTag
import com.example.loldex.core.designsystem.component.TagWithDeleteButton
import com.example.loldex.core.designsystem.theme.Neutral10
import com.example.loldex.core.designsystem.theme.Text10
import com.example.loldex.core.designsystem.theme.Text20
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
) {
    var searchValue by remember { mutableStateOf("") }
    val recentSearchList = listOf("가나다", "가나다", "가나다", "가나다", "가나다", "가나다")
    val recommendedKeywordList = listOf("Dark Magician")

    SearchScreen(
        searchValue = searchValue,
        onSearchValueChange = { searchValue = it },
        onSearch = {},
        recentSearchList = recentSearchList,
        recommendedKeywordList = recommendedKeywordList,
        onClickedTag = {},
        onClickedDelete = {},
    )
}

@Composable
internal fun SearchScreen(
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    recentSearchList: List<String>,
    recommendedKeywordList: List<String>,
    onClickedTag: (String) -> Unit,
    onClickedDelete: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Neutral10)
    ) {
        SearchTextField(
            searchValue = searchValue,
            onSearchValueChange = onSearchValueChange,
            onSearch = onSearch
        )

        if (recentSearchList.isNotEmpty()) {
            RecentSearches(
                recentSearchList = recentSearchList,
                onClickedTag = onClickedTag,
                onClickedDelete = onClickedDelete
            )
        }

        if (recommendedKeywordList.isNotEmpty()) {
            RecommendedKeywords(
                recommendedKeywordList = recommendedKeywordList,
                onClickedTag = onClickedTag
            )
        }
    }
}

@Composable
fun SearchTextField(
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
) {
    var debounceJob by remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            value = searchValue,
            onValueChange = {
                onSearchValueChange(it)
                debounceJob?.cancel()
                debounceJob = scope.launch {
                    delay(500)
                    onSearch(searchValue)
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Neutral10,
                focusedContainerColor = Neutral10,
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_bar_placeholder),
                    style = MaterialTheme.ldTypography.fontLabelL,
                    color = Color.White
                )
            },
            leadingIcon = {
                Box(
                    modifier = Modifier
                        .size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { onSearch(searchValue) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )
                }
            },
            suffix = {
                if (searchValue.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Text20
                    )
                }
            }
        )
    }
}

@Composable
fun RecentSearches(
    recentSearchList: List<String>,
    onClickedTag: (String) -> Unit,
    onClickedDelete: (String) -> Unit,
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
                    .padding(end = 20.dp),
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
                    onClickedTag = onClickedTag,
                    onClickedDelete = onClickedDelete
                )
            }
        }
    }
}

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
                color = Text10
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
                    onClickedTag = onClickedTag,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun SearchScreenPreview() {
    var searchValue by remember { mutableStateOf("") }
    var recentSearchList = listOf("가나다", "라마바", "사아자", "차카타", "파하")
    val recommendedKeywordList = listOf("Dark Magician")
    SearchScreen(
        searchValue = searchValue,
        onSearchValueChange = { searchValue = it },
        onSearch = {},
        recentSearchList = recentSearchList,
        recommendedKeywordList = recommendedKeywordList,
        onClickedTag = {},
        onClickedDelete = {},
    )
}