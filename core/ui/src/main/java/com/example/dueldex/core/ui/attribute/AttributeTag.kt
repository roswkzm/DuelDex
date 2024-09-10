package com.example.dueldex.core.ui.attribute

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dueldex.core.designsystem.theme.ThemePreviews
import com.example.dueldex.core.model.enums.AttributeType

@Composable
fun AttributeTag(
    modifier: Modifier = Modifier,
    attribute: String,
    attributeSize: AttributeSize,
    onClickedAttribute: (String) -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = { onClickedAttribute(attribute) },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(getAttributeTagColor(attribute)),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        val attributeIconSize = getAttributeIconSize(attributeSize)
        val attributeTextStyle = getAttributeTextStyle(attributeSize)

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(attributeIconSize),
                painter = painterResource(id = getAttributeTagImage(attribute)),
                contentDescription = null
            )

            Text(
                text = attribute,
                style = attributeTextStyle,
                color = Color.White
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@ThemePreviews
@Composable
fun AttributeTagPreview() {
    val tagList = AttributeType.entries
    FlowRow {
        tagList.map {
            AttributeTag(
                modifier = Modifier,
                attribute = it.name,
                attributeSize = AttributeSize.L,
                onClickedAttribute = {}
            )
        }
    }
}