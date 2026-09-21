package com.example.taskmanager

fun pendingTasks(tasks: List<Task>): List<Task> = tasks.filter { !it.isCompleted }

fun searchTasks(tasks: List<Task>, query: String): List<Task> = tasks.filter {
    it.title.contains(query, ignoreCase = true) || (it.description?.contains(
        query,
        ignoreCase = true
    ) ?: false)
}

fun priorityLabel(priority: Priority): String = when (priority) {
    Priority.LOW -> "Low"
    Priority.MEDIUM -> "Medium"
    Priority.HIGH -> "High"
    Priority.URGENT -> "Urgent"
}

fun tasksByPriority(tasks: List<Task>): List<Task> = tasks.sortedByDescending { it.priority }