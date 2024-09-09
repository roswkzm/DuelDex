package com.example.loldex.feature.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.theme.LolDexTheme
import com.example.loldex.core.designsystem.theme.ThemePreviews
import com.example.loldex.core.designsystem.theme.ldTypography
import com.example.loldex.feature.search.R

@Composable
fun SearchTextField(
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClickedDeleteString: () -> Unit,
    onClickedClose: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = searchValue,
            onValueChange = onSearchValueChange,
            textStyle = MaterialTheme.ldTypography.fontLabelL,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = stringResource(id = R.string.search_bar_placeholder),
                    style = MaterialTheme.ldTypography.fontLabelL,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.6f)
                )
            },
            leadingIcon = {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { onClickedClose() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
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
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            suffix = {
                if (searchValue.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { onClickedDeleteString() },
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(searchValue)
                }
            )
        )
    }
}

@ThemePreviews
@Composable
fun SearchTextFieldPreview() {
    LolDexTheme {
        SearchTextField(
            searchValue = "",
            onSearchValueChange = {},
            onSearch = {},
            onClickedDeleteString = { },
            onClickedClose = {}
        )
    }
}