package com.example.loldex.feature.home.ui.component

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import com.example.loldex.core.designsystem.component.paging.ErrorMessage

// Paging Data 처음 가져올 시 Skeleton Loading Ui
@Composable
fun LazyGridScope.LoadStateRefreshSkeletonLoading() {
    item(span = { GridItemSpan(1) }) {
        this@LoadStateRefreshSkeletonLoading.items(20) {
            YugiohSkeletonCard()
        }
    }
}

// Paging Data 처음 가져올 시 Error Ui
@Composable
fun LazyGridScope.LoadStateRefreshError(
    error: LoadState.Error,
    onClickRetry: () -> Unit
) {
    item(span = { GridItemSpan(2) }) {
        ErrorMessage(
            modifier = Modifier,
            message = error.error.localizedMessage!!,
            onClickRetry = {
                onClickRetry()
            }
        )
    }
}

// Paging Data 추가시 Skeleton Loading Ui
@Composable
fun LazyGridScope.LoadStateAppendSkeletonLoading() {
    item(span = { GridItemSpan(2) }) {
        this@LoadStateAppendSkeletonLoading.items(2) {
            YugiohSkeletonCard()
        }
    }
}

// 추가 로딩 상태에서 에러 UI를 표시하는 컴포저블
@Composable
fun LazyGridScope.LoadStateAppendError(
    error: LoadState.Error,
    onClickRetry: () -> Unit
) {
    item(span = { GridItemSpan(2) }) {
        ErrorMessage(
            modifier = Modifier,
            message = error.error.localizedMessage!!,
            onClickRetry = {
                onClickRetry()
            }
        )
    }
}