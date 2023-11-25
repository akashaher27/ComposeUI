package com.example.composeui.ui.customView

import androidx.annotation.FloatRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun OverlappingCircularImageRow(
    list: List<Int>,
    modifier: Modifier,
    imageSize: Dp,
    @FloatRange(from = 0.1, to = 1.0) overlappingValue: Float,
) {
    OverlappingRow(overlappingValue) {
        for (item in list) {
            Image(
                modifier = modifier
                    .size(imageSize),
                painter = painterResource(id = item),
                contentDescription = null
            )
        }
        Box(
            modifier = Modifier
                .size(imageSize * 0.30f)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
    }
}

@Composable
fun OverlappingRow(
    @FloatRange(from = 0.1, to = 1.0) overlappingValue: Float,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = Modifier,
        content = content,
        measurePolicy = overlappingRowMeasurePolicy(overlappingValue)
    )
}

fun overlappingRowMeasurePolicy(overlappingValue: Float) =
    MeasurePolicy { measurables, constraints ->
        val placebles = measurables.map { it.measure(constraints) }
        val layoutHeight = placebles.maxOf { it.height }
        val layoutWidth = constraints.maxWidth
        val smallestPlacebleWidth = placebles.minOf { it.width }

        var groupPlacebleWidth = 0
        val groupPlaceble = mutableListOf<Placeable>()
        placebles.subList(0, placebles.lastIndex - 1).forEach { placeble ->
            if (smallestPlacebleWidth + groupPlacebleWidth + (placeble.width * (1 - overlappingValue)) < constraints.maxWidth)
                groupPlaceble.add(placeble)
            groupPlacebleWidth += (placeble.width * (1 - overlappingValue)).toInt()
        }
        layout(layoutWidth, layoutHeight) {
            var xPosition = 0
            var yPosition = 0
            for (placeble in groupPlaceble) {
                placeble.placeRelative(xPosition, yPosition, 0f)
                xPosition += (placeble.width * (1 - overlappingValue)).toInt()
            }
            val lastPlaceble = placebles.last()
            lastPlaceble.placeRelative(
                xPosition,
                yPosition,
                0f
            )
        }
    }

