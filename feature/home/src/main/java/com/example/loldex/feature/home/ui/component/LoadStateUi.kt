package com.example.loldex.feature.home.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.example.loldex.core.designsystem.component.paging.ErrorMessage
import com.example.loldex.core.designsystem.component.paging.LoadingNextPageItem
import com.example.loldex.core.designsystem.component.paging.PageLoader
import com.example.loldex.core.designsystem.component.shimmerBackground

// Paging Data 처음 가져올 시 Loading Ui
@Composable
fun LazyGridScope.LoadStateRefreshLoading() {
    PageLoader(
        modifier = Modifier
            .fillMaxWidth()
            .shimmerBackground(RoundedCornerShape(4.dp)),
        color = Color.Red,
    )
}

// Paging Data 처음 가져올 시 Skeleton Loading Ui
@Composable
fun LazyGridScope.LoadStateRefreshSkeletonLoading() {
    item(span = { GridItemSpan(1) }) {
        this@LoadStateRefreshSkeletonLoading.items(20) {
            SkeletonContactCard()
        }
    }
}

// Paging Data 처음 가져올 시 Error Ui
@Composable
fun LazyGridScope.LoadStateRefreshError(
    error: LoadState.Error,
    onClickRetry: () -> Unit
) {
    ErrorMessage(
        modifier = Modifier.fillMaxWidth(),
        message = error.error.localizedMessage!!,
        onClickRetry = {
            onClickRetry()
        }
    )
}

// Paging Data 추가시 Loading Ui
@Composable
fun LazyGridScope() {
    LoadingNextPageItem(modifier = Modifier)
}

// Paging Data 추가시 Skeleton Loading Ui
@Composable
fun LazyGridScope.LoadStateAppendSkeletonLoading() {
    item(span = { GridItemSpan(1) }) {
        this@LoadStateAppendSkeletonLoading.items(2) {
            SkeletonContactCard()
        }
    }
}

@Composable
fun LoadStateAppendError(
    error: LoadState.Error,
    onClickRetry: () -> Unit
) {
    ErrorMessage(
        modifier = Modifier,
        message = error.error.localizedMessage!!,
        onClickRetry = {
            onClickRetry()
        }
    )
}