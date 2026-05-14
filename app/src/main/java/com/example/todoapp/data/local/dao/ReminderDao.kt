package com.example.todoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.local.entity.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: Reminder): Long

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)

    @Query("SELECT * FROM reminder_table ORDER BY date ASC")
    fun getAllReminders(): Flow<List<Reminder>>

    @Query("SELECT * FROM reminder_table WHERE id = :reminderId")
    fun getReminderById(reminderId: Long): Flow<Reminder?>

    @Query("SELECT * FROM reminder_table WHERE (date - :advanceMillis) <= :currentTime AND notified = 0")
    suspend fun getPendingNotifications(currentTime: Long, advanceMillis: Long): List<Reminder>

    @Query("UPDATE reminder_table SET notified = 1 WHERE id = :reminderId")
    suspend fun markAsNotified(reminderId: Long)
}