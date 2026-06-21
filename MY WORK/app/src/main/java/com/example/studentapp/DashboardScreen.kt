package com.example.studentapp

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DashboardScreen(
    onLogout: () -> Unit,
    onGoToComponents: () -> Unit,
    onGoToProfile: () -> Unit,
    onGoToSettings: () -> Unit
) {
    var posts by remember { mutableStateOf(listOf<Post>()) }
    var localUsers by remember { mutableStateOf(listOf<User>()) }
    var isLoading by remember { mutableStateOf(true) }
    var userToDelete by remember { mutableStateOf<User?>(null) }
    
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)

    fun refreshData() {
        isLoading = true
        scope.launch {
            try {
                posts = ApiService.getInstance().getPosts().take(5)
                localUsers = db.userDao().getAllUsers()
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        refreshData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Student Dashboard") },
                actions = {
                    IconButton(onClick = onGoToComponents) {
                        Text("UI", fontWeight = FontWeight.Bold)
                    }
                    IconButton(onClick = onGoToProfile) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                    IconButton(onClick = onGoToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                    TextButton(onClick = onLogout) {
                        Text("Logout")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AnimatedVisibility(
                visible = !isLoading,
                enter = fadeIn() + slideInVertically(),
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    item {
                        Text(text = "API Data (Posts):", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    
                    items(posts) { post ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = post.title, fontWeight = FontWeight.Bold)
                                Text(text = post.body, fontSize = 12.sp, maxLines = 2)
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = "Local Registered Students:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "(Long press to delete)", fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(localUsers) { user ->
                        ListItem(
                            modifier = Modifier.combinedClickable(
                                onClick = { /* Maybe go to detail */ },
                                onLongClick = { userToDelete = user }
                            ),
                            headlineContent = { Text(user.name) },
                            supportingContent = { Text("${user.studentId} • ${user.course}") },
                            leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
                        )
                        HorizontalDivider()
                    }
                }
            }

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }

        // Delete Confirmation Dialog
        if (userToDelete != null) {
            AlertDialog(
                onDismissRequest = { userToDelete = null },
                title = { Text("Delete Student?") },
                text = { Text("Are you sure you want to remove ${userToDelete?.name}?") },
                confirmButton = {
                    TextButton(onClick = {
                        scope.launch {
                            userToDelete?.let { db.userDao().deleteUser(it) }
                            userToDelete = null
                            refreshData()
                        }
                    }) {
                        Text("Delete", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { userToDelete = null }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
