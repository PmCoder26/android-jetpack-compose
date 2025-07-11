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
import com.example.jetpack_compose_practice.ui.theme.Jetpack_Compose_PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Jetpack_Compose_PracticeTheme {

                val navCon = rememberNavController()

                val navRouteList = listOf(
                    NavRoute("Animations", "AnimationsHomeScreen")
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

                }

            }
        }
    }
}

