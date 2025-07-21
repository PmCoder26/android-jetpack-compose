package com.example.jetpack_compose_practice.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


/*
        1. Introduction:
        The snackbar component serves as a brief notification that appears at the bottom of the screen.
        It provides feedback about an operation or action without interrupting the user experience.
        Snackbars disappear after a few seconds. The user can also dismiss them with an action,
        such as tapping a button.
        Consider these three use cases where you might use a snackbar:
            a. Action Confirmation: After a user deletes an email or message, a snackbar appears to
                                    confirm the action and offer an "Undo" option.
            b. Network Status: When the app loses its internet connection, a snackbar pops up to
                               note that it is now offline.
            c. Data Submission: Upon successfully submitting a form or updating settings, a snackbar
                                notes that the change has saved successfully.

        2. Comnposables:
            To implement a snackbar, you first create SnackbarHost, which includes a SnackbarHostState
            property. SnackbarHostState provides access to the showSnackbar() function which you can
            use to display your snackbar. This suspending function requires a CoroutineScope such as
            with using rememberCoroutineScope — and can be called in response to UI events to show a
            Snackbar within Scaffold.
 */


@Preview(showBackground = true)
@Composable
fun SnackBarScreenPreview() {
    SnackBarScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackBarScreen() {

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("SnackBar") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier
                    .shadow(
                        elevation = 17.dp,
                        shape = RoundedCornerShape(bottomStart = 17.dp, bottomEnd = 17.dp),
                        spotColor = Color.Gray
                    )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }

    )
    { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {

            ExtendedFloatingActionButton(
                onClick = {
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Hello from SnackBar!...",
                            actionLabel = "Action",     // clickable text
                            duration = SnackbarDuration.Short,
                            withDismissAction = true        // provides 'X' button to dismiss the snackBar.
                        )
                        when(result) {
                            SnackbarResult.ActionPerformed -> Log.i("SnackBar_Result: ", "Action_Performed")
                            SnackbarResult.Dismissed -> Log.i("SnackBar_Result: ", "Action_Dismissed")
                        }
                    }
                }
            ) { Text("Click to show") }

        }

    }





}