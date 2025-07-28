package com.example.jetpack_compose_practice.touch_and_input

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Preview(showBackground = true)
@Composable
fun TapAndPressScreenPreview() {
    TapAndPressScreen()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TapAndPressScreen() {

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Taps and Press") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier
                    .shadow(
                        elevation = 11.dp,
                        shape = MaterialTheme.shapes.large,
                        spotColor = Color.Gray,
                    )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .size(width = 250.dp, height = 150.dp)
                    .combinedClickable(
                        onClick = {
                            scope.launch {
                                snackBarHostState.showSnackbar(message = "Single click!", duration = SnackbarDuration.Short)
                            }
                        },
                        onDoubleClick = {
                            scope.launch {
                                snackBarHostState.showSnackbar(message = "Double click!", duration = SnackbarDuration.Short)
                            }
                        },
                        onLongClick = {
                            scope.launch {
                                snackBarHostState.showSnackbar(message = "Long click!", duration = SnackbarDuration.Short)
                            }
                        }
                    ),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large,
                shadowElevation = 7.dp
            ) {
                Text(text = "Tap Me!", modifier = Modifier.wrapContentSize(), fontSize = 18.sp)
            }

            Spacer(Modifier.height(20.dp))

            Surface(
                modifier = Modifier
                    .size(width = 250.dp, height = 150.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                snackBarHostState.showSnackbar(message = "Pressed!", duration = SnackbarDuration.Short)
                            },
                            onLongPress = {
                                scope.launch {
                                    snackBarHostState.showSnackbar(message = "Long Pressed!", duration = SnackbarDuration.Short)
                                }
                            },
                            onTap = {
                                scope.launch {
                                    snackBarHostState.showSnackbar(message = "Tapped!", duration = SnackbarDuration.Short)
                                }
                            },
                            onDoubleTap = {
                                scope.launch {
                                    snackBarHostState.showSnackbar(message = "Double Tapped!", duration = SnackbarDuration.Short)
                                }
                            }
                        )
                        println("Hello gestures")
                    },
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.large,
                shadowElevation = 7.dp
            ) {
                Text(text = "Press Me!", modifier = Modifier.wrapContentSize(), fontSize = 18.sp)
            }
        }
    }
}