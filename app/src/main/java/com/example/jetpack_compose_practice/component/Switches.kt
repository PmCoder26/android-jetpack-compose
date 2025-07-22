package com.example.jetpack_compose_practice.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun SwitchScreenPreview() = SwitchScreen()

@Composable
fun SwitchScreen() {

    var checked by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .shadow(
                    elevation = 11.dp,
                    shape = MaterialTheme.shapes.large
                )
        ) {
            Switch(
                // Binds the current toggle state of the switch to a Boolean checked variable.
                checked = checked,
                // Updates the checked state when the user interacts with the switch.
                onCheckedChange = { value -> checked = value },
                // Displays a check icon inside the thumb when the switch is in the checked state.
                thumbContent = {
                    if (checked) Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Checked icon",
                        tint = Color.White
                    )
                },
                colors = SwitchDefaults.colors(
                    // Color of the thumb (the round toggle circle) when the switch is ON
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    // Color of the track (the background bar) when the switch is ON
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    // Color of the thumb when the switch is OFF
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    // Color of the track when the switch is OFF
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                modifier = Modifier.padding(10.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(Modifier.height(20.dp))
            }
            items(50) {
                AnimatedVisibility(
                    visible = checked,
                    enter = fadeIn(tween(1000)) + expandVertically(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessVeryLow
                        ),
                    ),
                    exit = fadeOut(tween(1000)) + shrinkVertically(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessVeryLow
                        ),
                    ),
                ) {
                    Surface(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .shadow(
                                elevation = 2.dp,
                                shape = MaterialTheme.shapes.large
                            ),
                    ) {
                        Text(
                            "Item: $it",
                            modifier = Modifier.wrapContentSize()
                        )    // Centers the text.
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}