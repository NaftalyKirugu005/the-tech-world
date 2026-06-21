package com.example.studentapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentPracticeScreen(onBack: () -> Unit) {
    var textInput by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UI Components") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Back") }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Practice Components", fontSize = 24.sp)

            // Text Field
            OutlinedTextField(
                value = textInput,
                onValueChange = { textInput = it },
                label = { Text("Enter something") },
                modifier = Modifier.fillMaxWidth()
            )

            // Button
            Button(onClick = { showDialog = true }) {
                Text("Show Alert")
            }

            // Image (Network)
            AsyncImage(
                model = "https://developer.android.com/static/images/brand/Android_Robot.png",
                contentDescription = "Android Robot",
                modifier = Modifier.size(150.dp)
            )
        }

        // Alert Dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Alert") },
                text = { Text("You entered: $textInput") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
