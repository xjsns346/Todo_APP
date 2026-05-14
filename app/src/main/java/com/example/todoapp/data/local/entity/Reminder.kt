package com.example.todoapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder_table")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val date: Long,
    val notified: Boolean = false
) {
    val shouldNotify: Boolean
        get() {
            val threeDaysInMillis = 3L * 24 * 60 * 60 * 1000
            val notificationTime = date - threeDaysInMillis
            return !notified && System.currentTimeMillis() >= notificationTime
        }
}