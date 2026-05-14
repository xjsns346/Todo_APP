package com.example.todoapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.util.Constants
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

@HiltWorker
class DailyTaskCheckWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val taskRepository: TaskRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            val currentTime = System.currentTimeMillis()
            
            // Get overdue short-term tasks
            val overdueTasks = taskRepository.getOverdueShortTermTasks(currentTime)
            
            // Reset or delete overdue short-term tasks
            overdueTasks.forEach { task ->
                if (task.keepHistory) {
                    // Reset task for today
                    val newDueDate = currentTime + Constants.ONE_DAY_MILLIS - (60 * 1000) // Today 23:59
                    taskRepository.resetShortTermTask(task.id, newDueDate)
                } else {
                    // Delete task
                    taskRepository.deleteTask(task)
                }
            }
            
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        fun createWorkRequest() = PeriodicWorkRequestBuilder<DailyTaskCheckWorker>(
            24, TimeUnit.HOURS
        )
            .setInitialDelay(1, TimeUnit.HOURS) // Start after 1 hour
            .build()
    }
}