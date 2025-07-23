package com.example.jetpack_compose_practice.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun TabsScreenPreview() = TabsScreen()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabsScreen() {

    val tabList = listOf("Home", "Photos", "Videos")
    var selectedDestination by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tabs") },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer)
            )
        },
        bottomBar = {
            TabRow(
                selectedTabIndex = selectedDestination,
                containerColor = Color(0x606B31D7),
                indicator = { tabPositions ->
                    val currentTab = tabPositions[selectedDestination]
                    val animatedLeftOffset by animateDpAsState(
                        // returns the distance from origin of the current's left tab in the form of .dp
                        targetValue = currentTab.left,
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
                        label = "Animating the 'X' distance or the position"
                    )

                    HorizontalDivider(
                        thickness = 4.dp,
                        color = Color.White,
                        modifier = Modifier
                            // Used .BottomStart because we say, hey get aligned from the bottomStart(from x = 0)
                            // and move from the distance animatedLeftOffset.
                            .wrapContentSize(Alignment.BottomStart)
                            .width(currentTab.width)
                            .offset(animatedLeftOffset)
                    )
                }
            ) {
                tabList.forEachIndexed { index, item ->
                    Tab(
                        selected = selectedDestination == index,
                        onClick = { selectedDestination = index },
                        text = { Text(item) },
                        selectedContentColor = Color(0xFFFFFFFF),
                        unselectedContentColor = Color(0xFF270842),
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }

}
