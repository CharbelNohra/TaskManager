package com.example.taskmanager

data class Task(
    val id: Int,
    var title: String,
    var description: String?,
    var isCompleted: Boolean,
    var priority: Priority = Priority.MEDIUM
)