package com.jetpack.composestrikethru

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface AnimatedOverlay {
    fun drawOverlay(drawScope: DrawScope)
}

class StrikeThruOverlay(
    private val color: Color = Color.Unspecified,
    private val widthDp: Dp = 4.dp,
    private val getProgress: () -> Float
): AnimatedOverlay {
    override fun drawOverlay(drawScope: DrawScope) {
        with(drawScope) {
            val width = widthDp.toPx()
            val halfWidth = width / 2f
            val progressHeight = size.height * getProgress()
            rotate(-45f) {
                drawLine(
                    color = color,
                    start = Offset(size.center.x + halfWidth, 0f),
                    end = Offset(size.center.x + halfWidth, progressHeight),
                    strokeWidth = width,
                    blendMode = BlendMode.Clear
                )

                drawLine(
                    color = color,
                    start = Offset(size.center.x - halfWidth, 0f),
                    end = Offset(size.center.x - halfWidth, progressHeight),
                    strokeWidth = width
                )
            }
        }
    }
}

fun Modifier.animatedOverlay(animatedOverlay: AnimatedOverlay) = this.then(
    Modifier
        .graphicsLayer {
            alpha = 0.99f
        }
        .drawWithContent {
            drawContent()
            animatedOverlay.drawOverlay(this)
        }
)



















