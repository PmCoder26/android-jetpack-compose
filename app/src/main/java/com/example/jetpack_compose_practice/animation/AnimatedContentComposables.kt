package com.example.jetpack_compose_practice.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun AnimatedContentScreenPreview() {
    AnimatedContentScreen()
}


@Composable
fun AnimatedContentScreen() {

    var expanded by remember {
        mutableStateOf(false)
    }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        onClick = { expanded = !expanded },
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150))
                    .togetherWith(fadeOut(animationSpec = tween(150, 150)))
                    .using(
                        SizeTransform { initialSize, targetSize ->
                            if(targetState) {
                                // Expand width to target while keeping height fixed for first 500ms,
                                // then smoothly animate the height to target over the remaining 500ms.
                                keyframes {
                                    IntSize(targetSize.width, initialSize.height) at 500
                                    durationMillis = 2000
                                }
                            }
                            else {
                                keyframes {
                                    IntSize(targetSize.width, initialSize.height) at 500
                                    durationMillis = 2000
                                }
                            }
                        }
                    )
            }
        ) { targetState ->
            if (targetState) {
                Expanded()
            } else {
                Box() {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call Icon",
                        tint = Color.Black,
                        modifier = Modifier
                            .background(Color.Green)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Expanded() {
    Box(
        modifier = Modifier.size(150.dp)
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Lorem ipsum dolor sit amet consectetur, adipisicing elit. Soluta beatae vitae nisi fugit, cumque, debitis expedita accusantium ipsam, odio fugiat ratione minima consequatur dignissimos optio error! Atque delectus optio suscipit. Illum, corrupti. Nihil minus eligendi beatae culpa exercitationem natus ab adipisci eos fugiat sit quasi error dolor commodi nulla quis tenetur libero laboriosam praesentium voluptates mollitia alias, corrupti placeat voluptatem officia. Sint commodi inventore tempore dolorum possimus. Itaque nemo totam exercitationem, assumenda officiis illo aspernatur sint inventore impedit autem, aperiam, necessitatibus similique aliquam libero excepturi delectus consequuntur. Hic labore quia doloremque provident? Quidem dignissimos itaque quod debitis, tenetur atque odit.",
            style = TextStyle(textAlign = TextAlign.Center)
        )
    }
}