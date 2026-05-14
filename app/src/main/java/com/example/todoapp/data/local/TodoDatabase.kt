package com.example.todoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.data.local.dao.ExtraPlanDao
import com.example.todoapp.data.local.dao.ReminderDao
import com.example.todoapp.data.local.dao.TaskDao
import com.example.todoapp.data.local.entity.ExtraPlan
import com.example.todoapp.data.local.entity.Reminder
import com.example.todoapp.data.local.entity.Task

@Database(
    entities = [Task::class, Reminder::class, ExtraPlan::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun reminderDao(): ReminderDao
    abstract fun extraPlanDao(): ExtraPlanDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).fallbackToDestructiveMigration()
                 .build()
                INSTANCE = instance
                instance
            }
        }
    }
}