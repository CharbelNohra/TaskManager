package com.example.taskmanager

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource

import taskmanager.shared.generated.resources.Res
import taskmanager.shared.generated.resources.compose_multiplatform

@Composable
fun TaskManagerTitle() {
    Text("Task Manager")
}

@Composable
@Preview
fun App() {
    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
        var taskCount by remember { mutableStateOf(0) }
        var tasks by remember {
            mutableStateOf(
                listOf(
                    Task(id = 1, title = "Buy milk", description = null, isCompleted = false),
                    Task(id = 2, title = "Buy bread", description = null, isCompleted = true),
                    Task(id = 3, title = "Walk the dog", description = null, isCompleted = false),
                )
            )
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn {
                items(tasks) { task ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = {
                                tasks = tasks.map {
                                    if (it.id == task.id) it.copy(isCompleted = !it.isCompleted) else it
                                }
                            }
                        )
                        Text(task.title)
                    }
                }
            }

            TaskManagerTitle()
            Button(onClick = { taskCount++ }) {
                Text("Add task")
            }
            Text("$taskCount tasks")
            Text("${completedCount(tasks)} Completed")

//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
        }
    }
}