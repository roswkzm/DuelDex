package com.example.dueldex.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import timber.log.Timber

@Composable
fun AsyncImageView(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String?,
    contentScale: ContentScale,
    placeholderContent: @Composable () -> Unit
) {
    Box {
        var imageState by remember { mutableStateOf(ImageState.Loading) }

        val context = LocalContext.current
        val imageRequest = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .listener(
                onStart = {
                    imageState = ImageState.Loading
                },
                onSuccess = { _, _ ->
                    imageState = ImageState.Success
                },
                onError = { _, result ->
                    Timber.tag("AsyncImageView")
                        .e("Load Fail : Message -> ${result.throwable.message}    url -> $url")
                    imageState = ImageState.Error
                }
            )
            .build()

        if (imageState == ImageState.Loading || imageState == ImageState.Error) {
            placeholderContent()
        }
        AsyncImage(
            model = imageRequest,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )

    }
}

enum class ImageState {
    Loading,
    Success,
    Error
}