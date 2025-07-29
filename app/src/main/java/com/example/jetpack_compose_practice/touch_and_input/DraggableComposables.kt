package com.example.jetpack_compose_practice.touch_and_input

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


/*
        1. Comparison:
            Feature	                        .draggable	                                .pointerInput + detectDragGestures
            Drag Axis Support	            Single axis (Horizontal or Vertical)	    Multi-axis (X and Y, free-form)
            Gesture Lifecycle Access	    Limited (onDragStarted, onDragStopped)	    Full access: onDragStart, onDrag, onDragEnd, cancel
            Multi-Touch / Custom Gestures	❌ Not supported	                        ✅ Fully supported
            Ease of Use	                    ✅ Very easy	                            ⚠️ Requires more code and control
            Nested Scrolling Compatibility	✅ Built-in	                                ❌ Needs manual handling
            Custom Behavior (e.g., Bounds)	❌ Hard to implement	                    ✅ Fully customizable
            Use Case	                    Sliders, scrollables	                    Drag-and-drop, drawing, games, complex UIs

        2. Theory:
            a. change: a PointerInputChange object that provides detailed information about the current
                pointer event during a gesture. It contains the finger’s current and previous position,
                press state, timestamp, and a unique ID. This helps track and respond to user touch input precisely.

            b. change.consume(): used to mark a gesture event as handled so that no other gesture
                detectors (like clickable, scrollable, or parent composables) can respond to the same event.
                It ensures exclusive control over the touch input during custom gestures like dragging.

 */


@Preview(showBackground = true)
@Composable
fun DraggableScreenPreview() {
    DraggableScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraggableScreen() {

        // Similar to scrollable.
//    var offset by remember { mutableStateOf(0f) }
//    val draggableState = rememberDraggableState { delta -> offset += delta }

        // More advanced or different way.
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Draggable") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.secondaryContainer),
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.White))
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
//                    .offset(x = offset.dp)       // Similar to scrollable.
                    .offset(offsetX.dp, offsetY.dp)
                    .shadow(
                        elevation = 17.dp,
                        shape = MaterialTheme.shapes.large,
                        spotColor = Color.Gray
                    )
                    .background(MaterialTheme.colorScheme.primaryContainer)
//                    .draggable(         // similar to scrollable.
//                        state = draggableState,
//                        orientation = Orientation.Horizontal,
//                        onDragStarted = {
//                            snackBarHostState.showSnackbar(message = "Drag Started!", duration = SnackbarDuration.Short)
//                        },
//                        onDragStopped = {
//                            snackBarHostState.showSnackbar(message = "Drag Stopped!", duration = SnackbarDuration.Short)
//                        }
//                    )
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = {
                                scope.launch {
                                    snackBarHostState.showSnackbar(message = "Drag Started!", duration = SnackbarDuration.Short)
                                }
                            },
                            onDragEnd = {
                                scope.launch {
                                    snackBarHostState.showSnackbar(message = "Drag Stopped!", duration = SnackbarDuration.Short)
                                }
                            },
                            onDrag = { change, dragAmount ->
                                scope.launch {
                                    change.consume()
                                    // divided by 4 for my convenience.
                                    offsetX += dragAmount.x / 4
                                    offsetY += dragAmount.y / 4
                                }
                            }
                        )
                    }
            ) {
                Text(
                    text = "Drag Me!",
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(
                        fontSize = 18.sp,
                    )
                )
            }
        }
    }




}