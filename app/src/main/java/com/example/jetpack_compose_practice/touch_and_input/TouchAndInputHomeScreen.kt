package com.example.jetpack_compose_practice.touch_and_input

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.data_in_android_practice.NavRoute
import com.example.data_in_android_practice.NavRouteGallery


@Preview(showBackground = true)
@Composable
fun TouchAndInputHomeScreenPreview() {
    TouchAndInputHomeScreen(rememberNavController())
}

@Composable
fun TouchAndInputHomeScreen(navCon: NavHostController) {

    val navRouteList = listOf(
        NavRoute("Tap And Press", "TapAndPressScreen"),
        NavRoute("Scrollable", "ScrollableScreen"),
        NavRoute("Draggable", "DraggableScreen"),
    )

    NavRouteGallery(navRouteList, navCon)
}