package com.example.todoapp.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.example.todoapp.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerManager @Inject constructor(
    private val context: Context
) {
    private val workManager = WorkManager.getInstance(context)

    fun scheduleDailyTaskCheck() {
        val workRequest = DailyTaskCheckWorker.createWorkRequest()
        workManager.enqueueUniquePeriodicWork(
            "daily_task_check",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }

    fun scheduleReminderNotification(reminderId: Long, reminderTime: Long) {
        val currentTime = System.currentTimeMillis()
        val notificationTime = reminderTime - (Constants.REMINDER_ADVANCE_DAYS * Constants.ONE_DAY_MILLIS)
        
        if (notificationTime > currentTime) {
            val delayMillis = notificationTime - currentTime
            val workRequest = ReminderNotificationWorker.createWorkRequest(reminderId, delayMillis)
            workManager.enqueue(workRequest)
        }
    }

    fun cancelReminderNotification(reminderId: Long) {
        workManager.cancelAllWorkByTag("reminder_$reminderId")
    }

    fun cancelAllWork() {
        workManager.cancelAllWork()
    }
}