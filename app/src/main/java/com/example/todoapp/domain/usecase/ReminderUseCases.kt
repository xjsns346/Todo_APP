package com.example.todoapp.domain.usecase

import com.example.todoapp.data.repository.ReminderRepository
import com.example.todoapp.data.local.entity.Reminder
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderUseCases @Inject constructor(
    private val repository: ReminderRepository
) {
    fun getAllReminders(): Flow<List<Reminder>> = repository.getAllReminders()

    fun getReminderById(id: Long): Flow<Reminder?> = repository.getReminderById(id)

    suspend fun createReminder(title: String, date: Long): Long {
        val reminder = Reminder(title = title, date = date)
        return repository.insertReminder(reminder)
    }

    suspend fun updateReminder(reminder: Reminder) = repository.updateReminder(reminder)

    suspend fun deleteReminder(reminder: Reminder) = repository.deleteReminder(reminder)

    suspend fun getPendingNotifications(currentTime: Long, advanceMillis: Long) =
        repository.getPendingNotifications(currentTime, advanceMillis)

    suspend fun markAsNotified(reminderId: Long) = repository.markAsNotified(reminderId)
}