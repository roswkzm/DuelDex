package com.example.loldex.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.example.loldex.core.designsystem.component.AsyncImageView
import kotlin.math.abs
import kotlin.math.absoluteValue

@Composable
fun CarouselPager(
    modifier: Modifier = Modifier,
    horizontalPaddingWeight: Float,
    imageList: List<String>,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { imageList.size }
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        beyondViewportPageCount = 2,
        pageSpacing = 0.dp,
    ) { page ->
        val offset = pagerState.calculateOffsetForPage(page)
        Box(
            modifier = Modifier
                .zIndex(1f - abs(offset))
                .padding(
                    horizontal = screenWidth * horizontalPaddingWeight
                )
                .graphicsLayer {
                    translationX = size.width * offset

                    alpha = (2f - abs(offset)) / 2f

                    val scale = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - offset.absoluteValue.coerceIn(0f, 1f)
                    )
                    scaleX = scale
                    scaleY = scale
                }
        ) {
            AsyncImageView(
                modifier = Modifier.fillMaxWidth(),
                url = imageList[page],
                contentDescription = null,
                contentScale = ContentScale.Fit,
                placeholderContent = {
                    SkeletonBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(268f / 391f)
                    )
                }
            )
        }
    }
}

fun PagerState.calculateOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}