package com.example.studentapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(userDao: UserDao, onRegistrationSuccess: () -> Unit, onLoginClick: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Register", fontSize = 32.sp, modifier = Modifier.padding(bottom = 32.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = studentId,
            onValueChange = { studentId = it },
            label = { Text("Student ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = course,
            onValueChange = { course = it },
            label = { Text("Course") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        if (error.isNotEmpty()) {
            Text(text = error, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.isNotEmpty() && studentId.isNotEmpty() && course.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    scope.launch {
                        val existingUser = userDao.getUserByEmail(email)
                        if (existingUser == null) {
                            userDao.register(User(
                                name = name,
                                studentId = studentId,
                                course = course,
                                email = email,
                                password = password
                            ))
                            onRegistrationSuccess()
                        } else {
                            error = "Email already registered"
                        }
                    }
                } else {
                    error = "Please fill all fields"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }

        TextButton(onClick = onLoginClick) {
            Text("Already have an account? Login")
        }
    }
}
