package com.example.dueldex.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun YugiohSkeletonCardBack() {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp)
    ) {
        val modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(268f / 391f)
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.yugioh_card_back),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            SkeletonBox(
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun YugiohSkeletonCardPreview() {
    YugiohSkeletonCardBack()
}