package com.example.loldex.feature.home.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loldex.core.designsystem.component.SkeletonContent

@Composable
fun SkeletonContactCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(12.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(30.dp)
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
fun PreviewSkeletonContactCard() {
    SkeletonContactCard()
}