package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.local.Converters
import com.example.todoapp.data.local.TodoDatabase
import com.example.todoapp.data.local.dao.ExtraPlanDao
import com.example.todoapp.data.local.dao.ReminderDao
import com.example.todoapp.data.local.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo_database"
        ).fallbackToDestructiveMigration()
         .build()
    }

    @Provides
    fun provideTaskDao(database: TodoDatabase): TaskDao = database.taskDao()

    @Provides
    fun provideReminderDao(database: TodoDatabase): ReminderDao = database.reminderDao()

    @Provides
    fun provideExtraPlanDao(database: TodoDatabase): ExtraPlanDao = database.extraPlanDao()
}
