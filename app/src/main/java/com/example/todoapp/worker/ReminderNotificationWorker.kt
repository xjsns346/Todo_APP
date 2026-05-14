package com.example.todoapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.todoapp.data.repository.ReminderRepository
import com.example.todoapp.util.NotificationUtil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class ReminderNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val reminderRepository: ReminderRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            // Get pending notifications
            val pendingReminders = reminderRepository.getPendingNotifications(
                System.currentTimeMillis(),
                3L * 24 * 60 * 60 * 1000
            )
            
            // Send notifications and mark as notified
            pendingReminders.forEach { reminder ->
                NotificationUtil.showReminderNotification(applicationContext, reminder.title)
                reminderRepository.markAsNotified(reminder.id)
            }
            
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        fun createWorkRequest(reminderId: Long, delayMillis: Long) = 
            OneTimeWorkRequestBuilder<ReminderNotificationWorker>()
                .setInitialDelay(delayMillis, TimeUnit.MILLISECONDS)
                .addTag("reminder_$reminderId")
                .build()
    }
}