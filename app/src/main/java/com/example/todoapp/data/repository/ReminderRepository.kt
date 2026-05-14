package com.example.todoapp.data.repository

import com.example.todoapp.data.local.TodoDatabase
import com.example.todoapp.data.local.entity.Reminder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderRepository @Inject constructor(
    private val database: TodoDatabase
) {
    private val reminderDao = database.reminderDao()

    fun getAllReminders(): Flow<List<Reminder>> = reminderDao.getAllReminders()

    fun getReminderById(id: Long): Flow<Reminder?> = reminderDao.getReminderById(id)

    suspend fun insertReminder(reminder: Reminder): Long = reminderDao.insert(reminder)

    suspend fun updateReminder(reminder: Reminder) = reminderDao.update(reminder)

    suspend fun deleteReminder(reminder: Reminder) = reminderDao.delete(reminder)

    suspend fun getPendingNotifications(currentTime: Long, advanceMillis: Long): List<Reminder> =
        reminderDao.getPendingNotifications(currentTime, advanceMillis)

    suspend fun markAsNotified(reminderId: Long) = reminderDao.markAsNotified(reminderId)
}