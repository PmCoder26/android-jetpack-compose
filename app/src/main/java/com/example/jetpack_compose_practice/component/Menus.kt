package com.example.jetpack_compose_practice.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


/*
        1. Introduction:
            Drop-down menus let users click an icon, text field, or other component, and then select
            from a list of options on a temporary surface. This guide describes how to create both
            basic menus and more complex menus with dividers and icons.
            Use DropdownMenu, DropdownMenuItem, and the IconButton components to implement a custom
            drop-down menu.
        2. Parameters:
            a. DropDownMenu:
                expanded: Indicates whether the menu is visible.
                onDismissRequest: Used to handle menu dismissal.
                content: The composable content of the menu, typically containing DropdownMenuItem
                         composables.
            b. DropdownMenuItem:
                text: Defines the content displayed in the menu item.
                onClick: Callback to handle interaction with the item in the menu.
                leadingIcon: A composable lambda that displays an icon before the text.
                trailingIcon: A composable lambda that displays an icon after the text.
 */


@Preview(showBackground = true)
@Composable
fun MenusScreenPreview() {
    MenusScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenusScreen() {

    var expanded by remember {
        mutableStateOf(true)
    }
    val menuItemList = listOf(
        Pair(Icons.Outlined.Person, "Profile"),
        Pair(Icons.Outlined.Settings, "Settings"),
        Pair(Icons.AutoMirrored.Filled.Send, "Send Feedback"),
        Pair(Icons.Outlined.Info, "About")
    )
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Menus") },
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false},
                        modifier = Modifier.background(MaterialTheme.colorScheme.background),

                    ) {
                        menuItemList.forEach {
                            DropdownMenuItem(
                                text = { Text(it.second) },
                                leadingIcon = { Icon(it.first, it.second) },
                                onClick = {
                                    expanded = !expanded
                                    Toast.makeText(
                                        context,
                                        "Clicked ${it.second}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 17.dp,
                        shape = RoundedCornerShape(bottomStart = 17.dp, bottomEnd = 17.dp),
                        spotColor = Color.Gray,
                    )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0x39C8C2E7)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Welcome to Menus, Click the three dots to open menu",
                style = TextStyle(fontSize = 23.sp, textAlign = TextAlign.Center)
            )
        }
    }

}