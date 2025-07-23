package com.example.jetpack_compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data_in_android_practice.NavRoute
import com.example.data_in_android_practice.NavRouteGallery
import com.example.jetpack_compose_practice.animation.AnimatedContentScreen
import com.example.jetpack_compose_practice.animation.AnimationsHomeScreen
import com.example.jetpack_compose_practice.animation.ValueBasedAnimations
import com.example.jetpack_compose_practice.component.BadgesHomeScreen
import com.example.jetpack_compose_practice.component.CarouselsScreen
import com.example.jetpack_compose_practice.component.ComponentsHomeScreen
import com.example.jetpack_compose_practice.component.DatePickerScreen
import com.example.jetpack_compose_practice.component.MenusScreen
import com.example.jetpack_compose_practice.component.SnackBarScreen
import com.example.jetpack_compose_practice.component.SwitchScreen
import com.example.jetpack_compose_practice.component.TabsScreen
import com.example.jetpack_compose_practice.component.TopBarScreen
import com.example.jetpack_compose_practice.ui.theme.Jetpack_Compose_PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_Compose_PracticeTheme {

                val navCon = rememberNavController()

                val navRouteList = listOf(
                    NavRoute("Animations", "AnimationsHomeScreen"),
                    NavRoute("Components", "ComponentsHomeScreen"),
                )

                NavHost (navController = navCon, startDestination = "NavRouteGallery") {
                    composable("NavRouteGallery") {
                        NavRouteGallery(navRouteList, navCon)
                    }

                    // Animations composable routes.
                    composable("AnimationsHomeScreen") {
                        AnimationsHomeScreen(navCon)
                    }
                    composable("ValueBasedAnimations") {
                        ValueBasedAnimations()
                    }
                    composable("AnimatedContentScreen") {
                        AnimatedContentScreen()
                    }

                    // Jetpack compose components.
                    composable("ComponentsHomeScreen") {
                        ComponentsHomeScreen(navCon)
                    }
                    composable("TopBarScreen") {
                        TopBarScreen(navCon)
                    }
                    composable("BadgesHomeScreen") {
                        BadgesHomeScreen()
                    }
                    composable("CarouselsScreen") {
                        CarouselsScreen()
                    }
                    composable("DatePickerScreen") {
                        DatePickerScreen()
                    }
                    composable("MenusScreen") {
                        MenusScreen()
                    }
                    composable("SnackBarScreen") {
                        SnackBarScreen()
                    }
                    composable("SwitchScreen") {
                        SwitchScreen()
                    }
                    composable("TabsScreen") {
                        TabsScreen()
                    }
                }

            }
        }
    }
}

