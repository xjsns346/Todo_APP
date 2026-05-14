package com.example.todoapp.domain.usecase

import com.example.todoapp.data.repository.TaskRepository
import com.example.todoapp.data.local.entity.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskUseCases @Inject constructor(
    private val repository: TaskRepository
) {
    fun getAllTasks(): Flow<List<Task>> = repository.getAllTasks()

    fun getTaskById(id: Long): Flow<Task?> = repository.getTaskById(id)

    suspend fun createTask(title: String, description: String? = null, isShortTerm: Boolean = false, 
                          durationDays: Int? = null, keepHistory: Boolean = true, dueDate: Long? = null): Long {
        val task = Task(
            title = title,
            description = description,
            isShortTerm = isShortTerm,
            durationDays = durationDays,
            keepHistory = keepHistory,
            dueDate = dueDate
        )
        return repository.insertTask(task)
    }

    suspend fun updateTask(task: Task) = repository.updateTask(task)

    suspend fun deleteTask(task: Task) = repository.deleteTask(task)

    suspend fun toggleTaskCompletion(taskId: Long, completed: Boolean) {
        val completedDate = if (completed) System.currentTimeMillis() else null
        repository.updateTaskCompletion(taskId, completed, completedDate)
    }

    suspend fun getOverdueShortTermTasks(currentTime: Long) = 
        repository.getOverdueShortTermTasks(currentTime)

    suspend fun resetShortTermTask(taskId: Long, newDueDate: Long) =
        repository.resetShortTermTask(taskId, newDueDate)
}