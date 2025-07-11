package com.example.jetpack_compose_practice.animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun ValueBasedAnimationsPreview() {
    ValueBasedAnimations()
}

@Composable
fun ValueBasedAnimations() {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Animating the Float.
        var enabled by remember {
            mutableStateOf(false)
        }

        val alpha by animateFloatAsState(
            targetValue = if(enabled) 1f else 0.1f,
            animationSpec = tween(500)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .height(100.dp)
                .alpha(alpha)
                .background(Color.Red)
        )
        Button(onClick = { enabled = !enabled }) { Text("Change alpha") }

        // Animating IntSize.
        var currentState by remember { mutableStateOf(BoxState.Collapsed) }
        val intSize by animateIntSizeAsState(
            targetValue = when(currentState){
                BoxState.Collapsed -> IntSize(100, 100)
                BoxState.Expanded -> IntSize(300 , 300)
            },
            animationSpec = tween()
        )

        Box(
          modifier = Modifier
              .size(
                  width = intSize.width.dp,
                  height = intSize.height.dp
              )
              .background(Color.Red)
        )

        Button(onClick = {
            currentState = if (currentState.equals(BoxState.Collapsed)) BoxState.Expanded else BoxState.Collapsed
        }) { Text("Change state") }


    }

}