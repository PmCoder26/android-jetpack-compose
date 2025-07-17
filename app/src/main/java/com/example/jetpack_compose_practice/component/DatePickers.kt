package com.example.jetpack_compose_practice.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date


/*
    1. Introduction:
            Date pickers let users select a date, a date range, or both. They use a calendar dialog
            or text input to let users select dates.
            There are three types of date pickers:
                a. Docked: Appears inline within the layout. It's suitable for compact layouts where
                           a dedicated dialog might feel intrusive.
                b. Modal: Appears as a dialog overlaying the app's content.
                          This provides a clear focus on date selection.
                c. Modal input: Combines a text field with a modal date picker.

    2. Composables:
        a. DatePicker: General composable for a date picker.
                       The container you use determines whether it is docked or model.
        b. DatePickerDialog: The container for both modal and modal input date pickers.
        c. DateRangePicker: For any date picker where the user can select a range with a start and end date.
        State: The key parameter that the different date picker composables share in common is state,
               which takes either a DatePickerState or DateRangePickerState object. Their properties
               capture information about the user's selection using the date picker, such as the current
               selected date.
 */


@Preview(showBackground = true)
@Composable
fun DatePickerScreenPreview() {
    DatePickerScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerScreen() {

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Date Picker")
                },
                navigationIcon = {
                    if(showDatePicker) {
                        IconButton(
                            onClick = { showDatePicker = !showDatePicker },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "navigate back to date picker gallery"
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var currentDatePicker by remember {
                mutableStateOf("")
            }

            AnimatedVisibility(!showDatePicker) {
                val datePickerList = listOf("DatePickerDocked", "DatePickerModal", "DateRangePicker")

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(datePickerList) { datePicker ->
                        ExtendedFloatingActionButton(
                            onClick = {
                                currentDatePicker = datePicker
                                showDatePicker = !showDatePicker
                            }
                        ) {
                            Text(datePicker)
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

            AnimatedVisibility(showDatePicker && currentDatePicker.isNotBlank()) {
                when(currentDatePicker) {
                    "DatePickerDocked" -> DatePickerDocked()
                    "DatePickerModal" -> {
                        DatePickerModal() {
                            currentDatePicker = ""
                            showDatePicker = !showDatePicker
                        }
                    }
                    "DateRangePicker" -> {
                        MyDateRangePicker() {
                            currentDatePicker = ""
                            showDatePicker = !showDatePicker
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {

    val datePickerState = rememberDatePickerState()
    // compute when the selected date changes.
    val selectedDate = remember(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let {
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            formatter.format(Date(it))
        } ?: ""
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        DatePicker(
            state = datePickerState,
            headline = { Text("Pick a date") },
            modifier = Modifier
                .padding(20.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .border(
                    border = BorderStroke(1.dp, Color(0xFF9B7B9B)),
                    shape = MaterialTheme.shapes.extraLarge
                ),
            colors = DatePickerDefaults.colors(Color(0xA4E6CEF3))
        )

        Text(
            text = "Selected Date: $selectedDate",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            fontSize = 18.sp
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(onDismissOrConfirm: () -> Unit) {

    DatePickerDialog(
        onDismissRequest = { onDismissOrConfirm },
        confirmButton = { Button(onDismissOrConfirm) { Text("Done") } },
        dismissButton = { Button(onDismissOrConfirm) { Text("Cancel") } },
        shape = MaterialTheme.shapes.extraLarge
    ) {
        DatePickerDocked()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDateRangePicker(onDismissOrConfirm: () -> Unit) {

    DatePickerDialog(
        onDismissRequest = { onDismissOrConfirm },
        confirmButton = { Button(onDismissOrConfirm) { Text("Done") } },
        dismissButton = { Button(onDismissOrConfirm) { Text("Cancel") } },
        shape = MaterialTheme.shapes.extraLarge
    ) {
        val dateRangePickerState = rememberDateRangePickerState()
        val selectedStartDate = remember(dateRangePickerState.selectedStartDateMillis,) {
            dateRangePickerState.selectedStartDateMillis?.let {
                val formatter = SimpleDateFormat("dd/MM/yyyy").run {
                    format(Date(it))
                }
                formatter.format()
            } ?: ""
        }
        val selectedEndDate = remember(dateRangePickerState.selectedEndDateMillis,) {
            dateRangePickerState.selectedEndDateMillis?.let {
                val formatter = SimpleDateFormat("dd/MM/yyyy").run {
                    format(Date(it))
                }
                formatter.format()
            } ?: ""
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                headline = { Text("Pick a date range") },
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraLarge)
                    .border(
                        border = BorderStroke(1.dp, Color(0xFF9B7B9B)),
                        shape = MaterialTheme.shapes.extraLarge
                    ),
                colors = DatePickerDefaults.colors(Color(0xA4E6CEF3))
            )

            Text(
                text = "From: $selectedStartDate To: $selectedEndDate",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                fontSize = 18.sp
            )
        }
    }
}