package com.example.todoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val isShortTerm: Boolean = false,
    val durationDays: Int? = null,
    val keepHistory: Boolean = true,
    val createdDate: Long = System.currentTimeMillis(),
    val dueDate: Long? = null,
    val completedDate: Long? = null
) {
    val isOverdue: Boolean
        get() {
            if (dueDate == null) return false
            return !isCompleted && dueDate < System.currentTimeMillis()
        }
}