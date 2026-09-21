package com.example.taskmanager

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TaskTest {

    @Test
    fun tasksWithSameValuesAreEqual() {
        val task = Task(1, "Buy Milk", "Description", isCompleted = false)
        val task2 = Task(1, "Buy Milk", "Description", isCompleted = false)
        assertEquals(task, task2)
    }

    @Test
    fun pendingTasksExcludesCompleted() {
        val milk = Task(id = 1, title = "Buy milk", description = null, isCompleted = false)
        val bread = Task(id = 2, title = "Buy bread", description = null, isCompleted = true)
        val eggs = Task(id = 3, title = "Buy eggs", description = "A dozen", isCompleted = false)

        val result = pendingTasks(listOf(milk, bread, eggs))

        assertEquals(listOf(milk, eggs), result)
    }

    @Test
    fun searchTasksByTitle() {
        val milk = Task(id = 1, title = "Buy milk", description = null, isCompleted = false)
        val bread = Task(id = 2, title = "Buy bread", description = null, isCompleted = true)
        val eggs = Task(id = 3, title = "Buy eggs", description = "A dozen", isCompleted = false)

        val result = searchTasks(listOf(milk, bread, eggs), "milk")

        assertEquals(listOf(milk), result)
    }

    @Test
    fun searchTasksByDescription() {
        val milk = Task(id = 1, title = "Buy milk", description = null, isCompleted = false)
        val bread = Task(id = 2, title = "Buy bread", description = null, isCompleted = true)
        val eggs = Task(id = 3, title = "Buy eggs", description = "A dozen", isCompleted = false)

        val result = searchTasks(listOf(milk, bread, eggs), "dozen")

        assertEquals(listOf(eggs), result)
    }

    @Test
    fun tasksWithDifferentPriorityAreNotEqual() {
        val task = Task(
            id = 1,
            title = "Buy milk",
            description = null,
            isCompleted = false,
            priority = Priority.HIGH,
        )
        val task2 = Task(
            id = 1,
            title = "Buy milk",
            description = null,
            isCompleted = false,
            priority = Priority.LOW,
        )
        assertNotEquals(task, task2)
    }

    @Test
    fun priorityLabelForHigh() {
        val task = Task(
            id = 1,
            title = "Buy milk",
            description = null,
            isCompleted = false,
            priority = Priority.HIGH,
        )
        assertEquals("High", priorityLabel(task.priority))
    }

    @Test
    fun priorityLabelForUrgent() {
        val task = Task(
            id = 1,
            title = "Buy milk",
            description = null,
            isCompleted = false,
            priority = Priority.URGENT,
        )
        assertEquals("Urgent", priorityLabel(task.priority))
    }

    @Test
    fun tasksByPriorityPutsUrgentFirst() {
        val task = Task(
            id = 1,
            title = "Buy milk",
            description = null,
            isCompleted = false,
            priority = Priority.URGENT,
        )
        val task2 = Task(
            id = 2,
            title = "Buy bread",
            description = null,
            isCompleted = true,
            priority = Priority.HIGH,
        )
        val task3 = Task(
            id = 3,
            title = "Buy eggs",
            description = "A dozen",
            isCompleted = false,
            priority = Priority.LOW,
        )
        val result = tasksByPriority(listOf(task, task2, task3))
        assertEquals(listOf(task, task2, task3), result)
    }
}