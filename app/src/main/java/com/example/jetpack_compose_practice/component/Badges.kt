package com.example.jetpack_compose_practice.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/*
            1. Introduction:
                Use a badge to display a small visual element to denote status or a numeric value on
                another composable. Here are a few common scenarios where you might use a badge:
                    Notifications: Display the number of unread notifications on an app icon or
                                   notification bell.
                    Messages: Indicate new or unread messages within a chat application.
                    Status updates: Show the status of a task, such as "complete," "in progress,"
                                    or "failed."
                    Cart quantity: Display the number of items in a user's shopping cart.
                    New content: Highlight new content or features available to the user.
            2. Parameters and some theory:
                Use the BadgedBox composable to implement badges in your application.
                It is ultimately a container.
                You control its appearance with these two main parameters:
                    content: The composable content that the BadgedBox contains. Typically Icon.
                    badge: The composable that appears as the badge over the content.
                           Typically the dedicated Badge composable.

     */


@Preview(showBackground = true)
@Composable
fun BadgesHomeScreenPreview() {
    BadgesHomeScreen()
}

@Composable
fun BadgesHomeScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        var itemCount by remember {
            mutableIntStateOf(0)
        }

        BadgedBox(
            badge = {
                if(itemCount > 0) {
                    Badge() {
                        Text(text = itemCount.toString(), fontSize = 18.sp)
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "shopping card badge",
                modifier = Modifier.size(50.dp)
            )
        }

        ExtendedFloatingActionButton(
            onClick = {
                itemCount++
            },
            modifier = Modifier
                .padding(50.dp)
                .align(Alignment.BottomCenter)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "add items")
            Text("Add items")
        }
    }



}