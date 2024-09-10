package com.example.dueldex.feature.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.ui.SkeletonBox

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardDetailScreenSkeleton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Skeleton for CarouselPager
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(268f / 391f)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Skeleton for AddDeckButton
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            // Skeleton for Level Stars
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(26.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            // Skeleton for Card Name
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            // Skeleton for Attack/Defense Power
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                SkeletonBox(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            // Skeleton for Attribute and Tags
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(4) {
                    SkeletonBox(
                        modifier = Modifier
                            .width(80.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }

            // Skeleton for Description
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            // Skeleton for CardPriceLayout
            SkeletonBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}