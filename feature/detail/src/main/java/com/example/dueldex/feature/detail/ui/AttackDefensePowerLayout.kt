package com.example.dueldex.feature.detail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.R
import com.example.dueldex.core.designsystem.theme.DuelDexTheme
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.designsystem.theme.ddTypography

@Composable
fun AttackDefensePowerLayout(
    atk: Int?,
    def: Int?,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        atk?.let {
            Column {
                Text(
                    text = stringResource(id = com.example.dueldex.feature.detail.R.string.card_detail_atk),
                    style = MaterialTheme.ddTypography.fontTitleM,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.atk),
                        contentDescription = "Attack Power",
                    )

                    Text(
                        text = "$it",
                        style = MaterialTheme.ddTypography.fontTitleL,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        def?.let {
            Column {
                Text(
                    text = stringResource(id = com.example.dueldex.feature.detail.R.string.card_detail_def),
                    style = MaterialTheme.ddTypography.fontTitleM,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.def),
                        contentDescription = "Defence Power",
                    )

                    Text(
                        text = "$it",
                        style = MaterialTheme.ddTypography.fontTitleL,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@ThemePreviews
@Composable
fun AttackDefensePowerLayoutPreview() {
    DuelDexTheme {
        AttackDefensePowerLayout(
            atk = 5000,
            def = 5000
        )
    }
}