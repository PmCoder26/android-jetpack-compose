package com.example.jetpack_compose_practice.touch_and_input

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun ScrollableScreenPreview() {
    ScrollableScreen()
}

@Composable
fun ScrollableScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    var offset by remember {
        mutableStateOf((screenHeight.toFloat() / 3))
    }
    val scrollableState = rememberScrollableState { delta ->
        offset += delta
        delta
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .offset(x = (screenWidth.dp / 4), y = offset.dp)
                .scrollable(state = scrollableState, orientation = Orientation.Vertical)
                .shadow(
                    elevation = 11.dp,
                    shape = MaterialTheme.shapes.large,
                    spotColor = Color.Gray
                )
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = offset.toString(),
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
    }

}