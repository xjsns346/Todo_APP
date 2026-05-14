package com.example.todoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("DELETE FROM task_table WHERE id = :taskId")
    suspend fun deleteById(taskId: Long)

    @Query("SELECT * FROM task_table ORDER BY isCompleted ASC, createdDate DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    fun getTaskById(taskId: Long): Flow<Task?>

    @Query("SELECT * FROM task_table WHERE isShortTerm = 1 AND isCompleted = 0 AND dueDate < :currentTime")
    suspend fun getOverdueShortTermTasks(currentTime: Long): List<Task>

    @Query("UPDATE task_table SET isCompleted = :completed, completedDate = :completedDate WHERE id = :taskId")
    suspend fun updateCompletion(taskId: Long, completed: Boolean, completedDate: Long?)

    @Query("UPDATE task_table SET isCompleted = 0, dueDate = :newDueDate WHERE id = :taskId")
    suspend fun resetShortTermTask(taskId: Long, newDueDate: Long)
}