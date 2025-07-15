package com.example.jetpack_compose_practice.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.data_in_android_practice.NavRoute
import com.example.data_in_android_practice.NavRouteGallery


@Preview(showBackground = true)
@Composable
fun ComponentsHomeScreenPreview() {
    ComponentsHomeScreen(rememberNavController())
}

@Composable
fun ComponentsHomeScreen(navCon: NavHostController) {

    val navRouteList = listOf(
        NavRoute("Top Bar", "TopBarScreen")
    )

    NavRouteGallery(navRouteList, navCon)

}