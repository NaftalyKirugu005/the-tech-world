package com.example.studentapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
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
                .padding(16.dp)
        ) {
            Text(text = "Preferences", style = MaterialTheme.typography.titleMedium)
            
            ListItem(
                headlineContent = { Text("Enable Notifications") },
                trailingContent = {
                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                }
            )
            
            ListItem(
                headlineContent = { Text("Dark Mode") },
                trailingContent = {
                    Switch(
                        checked = darkModeEnabled,
                        onCheckedChange = { darkModeEnabled = it }
                    )
                }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(text = "Account", style = MaterialTheme.typography.titleMedium)
            
            TextButton(onClick = { /* Handle change password */ }) {
                Text("Change Password")
            }
            
            TextButton(onClick = { /* Handle privacy policy */ }) {
                Text("Privacy Policy")
            }
        }
    }
}
