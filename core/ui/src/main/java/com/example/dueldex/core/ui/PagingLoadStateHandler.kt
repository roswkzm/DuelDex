package com.example.dueldex.core.ui

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

/**
 * 페이징 상태에 따른 UI 처리를 위한 LazyListScope 확장 함수.
 *
 * @param pagingItems LazyPagingItems 인스턴스.
 * @param loadStateRefreshLoading 새로고침 상태(초기 로딩)에서 로딩 UI를 표시하는 컴포저블.
 * @param loadStateRefreshError 새로고침 상태에서 에러 UI를 표시하는 컴포저블. LoadState.Error 객체를 파라미터로 받음.
 * @param loadStateAppendLoading 추가 로딩 상태에서 로딩 UI를 표시하는 컴포저블.
 * @param loadStateAppendError 추가 로딩 상태에서 에러 UI를 표시하는 컴포저블. LoadState.Error 객체를 파라미터로 받음.
 */
fun LazyGridScope.pagingLoadStateHandler(
    pagingItems: LazyPagingItems<*>,
    loadStateRefreshLoading: @Composable LazyGridItemScope.() -> Unit,
    loadStateRefreshError: @Composable LazyGridItemScope.(LoadState.Error) -> Unit,
    loadStateAppendLoading: @Composable LazyGridItemScope.() -> Unit,
    loadStateAppendError: @Composable LazyGridItemScope.(LoadState.Error) -> Unit
) {
    val loadState = pagingItems.loadState

    when {
        loadState.refresh is LoadState.Loading -> {
            item {
                loadStateRefreshLoading()
            }
        }

        loadState.refresh is LoadState.Error -> {
            val error = pagingItems.loadState.refresh as LoadState.Error
            item {
                loadStateRefreshError(error)
            }
        }

        loadState.append is LoadState.Loading -> {
            item {
                loadStateAppendLoading()
            }
        }

        loadState.append is LoadState.Error -> {
            val error = pagingItems.loadState.append as LoadState.Error
            item {
                loadStateAppendError(error)
            }
        }
    }
}