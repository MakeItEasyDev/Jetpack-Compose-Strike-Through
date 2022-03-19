package com.jetpack.composestrikethru

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jetpack.composestrikethru.ui.theme.ComposeStrikeThruTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStrikeThruTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Compose Strike Through",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Image Strike",
                                    color = Color.Red
                                )
                                StrikeThruImage()
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(top = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Icon Strike",
                                    color = Color.Red
                                )
                                StrikeThruIcon()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StrikeThruIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Outlined.Person
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        var enabled by remember { mutableStateOf(false) }
        val transition = updateTransition(targetState = enabled, label = "Transition")
        val progress by transition.animateFloat(label = "Progress") { state ->
            if (state) 1f else 0f
        }
        val overlay = StrikeThruOverlay(
            color = Color.Red,
            widthDp = 4.dp,
            getProgress = { progress }
        )
        Icon(
            imageVector = imageVector,
            contentDescription = "Icon",
            tint = Color.Red,
            modifier = modifier
                .clickable { enabled = !enabled }
                .padding(8.dp)
                .animatedOverlay(overlay)
                .padding(12.dp)
                .size(52.dp)
        )
    }
}

@Composable
fun StrikeThruImage(
    modifier: Modifier = Modifier,
    @DrawableRes drawableId: Int = R.drawable.eye
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        var enabled by remember { mutableStateOf(false) }
        val transition = updateTransition(targetState = enabled, label = "Transition")
        val progress by transition.animateFloat(label = "progress") { state ->
            if (state) 1f else 0f
        }
        val overlay = StrikeThruOverlay(
            color = Color.Red,
            widthDp = 4.dp,
            getProgress = { progress }
        )

        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "Image",
            colorFilter = ColorFilter.tint(Color.Red),
            modifier = modifier
                .clickable { enabled = !enabled }
                .padding(8.dp)
                .animatedOverlay(overlay)
                .size(64.dp)
        )
    }
}




















