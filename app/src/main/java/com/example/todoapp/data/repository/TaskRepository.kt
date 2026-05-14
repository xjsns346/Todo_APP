package com.example.todoapp.data.repository

import com.example.todoapp.data.local.TodoDatabase
import com.example.todoapp.data.local.entity.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val database: TodoDatabase
) {
    private val taskDao = database.taskDao()

    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    fun getTaskById(id: Long): Flow<Task?> = taskDao.getTaskById(id)

    suspend fun insertTask(task: Task): Long = taskDao.insert(task)

    suspend fun updateTask(task: Task) = taskDao.update(task)

    suspend fun deleteTask(task: Task) = taskDao.delete(task)

    suspend fun deleteTaskById(id: Long) = taskDao.deleteById(id)

    suspend fun updateTaskCompletion(taskId: Long, completed: Boolean, completedDate: Long?) {
        taskDao.updateCompletion(taskId, completed, completedDate)
    }

    suspend fun getOverdueShortTermTasks(currentTime: Long): List<Task> {
        return taskDao.getOverdueShortTermTasks(currentTime)
    }

    suspend fun resetShortTermTask(taskId: Long, newDueDate: Long) {
        taskDao.resetShortTermTask(taskId, newDueDate)
    }
}