package com.example.jetpack_compose_practice.animation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.data_in_android_practice.NavRoute
import com.example.data_in_android_practice.NavRouteGallery


@Preview(showBackground = true)
@Composable
fun AnimationsHomeScreenPreview() {
    AnimationsHomeScreen(rememberNavController())
}

@Composable
fun AnimationsHomeScreen(navCon: NavHostController) {

    val navRouteList = listOf(
        NavRoute("ValueBased Animations", "ValueBasedAnimations")
    )

    NavRouteGallery(navRouteList, navCon)

}