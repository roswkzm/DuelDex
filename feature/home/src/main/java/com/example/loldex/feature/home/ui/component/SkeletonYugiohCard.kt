package com.example.loldex.feature.home.ui.component

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
import com.example.loldex.core.designsystem.component.SkeletonContent
import com.example.loldex.feature.home.R

@Composable
fun YugiohSkeletonCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f),
                painter = painterResource(id = R.drawable.yugioh_card_back),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
            )
        }
    }
}

@Composable
fun SkeletonBox(
    modifier: Modifier = Modifier
) {
    SkeletonContent {
        Box(
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun YugiohSkeletonCardPreview() {
    YugiohSkeletonCard()
}