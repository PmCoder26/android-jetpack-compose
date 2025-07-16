package com.example.jetpack_compose_practice.component

import android.widget.Toast
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


/*
            A. Scroll behavior of topBar:
                1. pinnedScrollBehavior() - App bar stays fixed during scroll
                2. enterAlwaysScrollBehavior() - App bar hides on scroll down, reappears on scroll up
                3. exitUntilCollapsedScrollBehavior() - App bar collapses into a small bar
                                         (like Medium/LargeTopAppBar collapsing into SmallTopAppBar)
            B. Parameters of topBar:
                1. title: The text that appears across the app bar.
                2. navigationIcon: The primary icon for navigation. Appears on the left of the app bar.
                3. actions: Icons that provide the user access to key actions. They appear on the right of the app bar.
                4. scrollBehavior: Determines how the top app bar responds to scrolling of the scaffold's inner content.
                5. colors: Determines how the app bar appears.
            C. Types of topBars:
                1. TopAppBar() - normal topBar.
                2. CenterAlignedTopAppBar() - same as TopAppBar() with built-in centered alignments
                                              for the title={} parameter's composable.
                3. MediumTopAppBar() - For screens that require a moderate amount of navigation and actions.
                4. LargeTopAppBar() - For screens that require a lot of navigation and actions.
            D. nestedScroll:
                Modifier.nestedScroll(connection) links a scrollable child (like LazyColumn) to a
                scroll-aware parent (like TopAppBar via Scaffold), enabling coordinated scroll behaviors
                such as collapsing or hiding toolbars.
                In the below example of Scaffold:
                    a. Parent -> Scaffold(Hosts and routes scroll info upward)
                    b. Child -> TopAppBar(Uses scrollBehavior to collapse/show)
                    c. Child -> Triggers scroll events

     */

/*
    We pass rememberTopAppBarState() to the scroll behavior to persist and track the app bar’s
    scroll-related state—such as offset and collapse—across recompositions for smooth and
    consistent UI behavior.
    Jetpack Compose doesn’t detect scroll state automatically because scrollBehavior is decoupled
    from the scrollable content, and it needs a shared, explicit state (TopAppBarState) to
    coordinate behavior between the top app bar and the scrolling content.
 */


@Preview(showBackground = true)
@Composable
fun TopBarScreenPreview() {
    TopBarScreen(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen(navCon: NavHostController) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState(),
        snapAnimationSpec = tween(durationMillis = 5000, easing = LinearOutSlowInEasing)    // animation may not work!!
    )

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .fillMaxSize(),
        topBar = {
            // Note - We can customize our topbar without using TopAppBar() or any other built-in
            //        topbar from jetpack library, its our choice or usage that decides what to use.

            val context = LocalContext.current

            TopAppBar(
                modifier = Modifier.fillMaxWidth()
                    .shadow(
                        elevation = 7.dp,
                        spotColor = Color.Gray,
                        shape = RoundedCornerShape(bottomStart = 17.dp, bottomEnd = 17.dp)),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center)
                    {
                        Text("Top Bar")
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navCon.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back arrow for navigation"
                        )
                    }
                },
                // actions - RowScope slot for trailing action icons (e.g., search, menu).
                actions = {
                    Button(
                        onClick = {
                            Toast.makeText(context, "Clicked from the actions={} parameter", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Text("Click to act")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {  innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(100) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(50.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Item", fontSize = 18.sp)
                    }
                }
            }
        }
    }

}