package com.example.todoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todoapp.data.local.entity.ExtraPlan
import kotlinx.coroutines.flow.Flow

@Dao
interface ExtraPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plan: ExtraPlan): Long

    @Delete
    suspend fun delete(plan: ExtraPlan)

    @Query("SELECT * FROM extra_plan_table ORDER BY createdDate DESC")
    fun getAllPlans(): Flow<List<ExtraPlan>>

    @Query("SELECT * FROM extra_plan_table WHERE id = :planId")
    fun getPlanById(planId: Long): Flow<ExtraPlan?>

    @Query("SELECT * FROM extra_plan_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomPlan(): ExtraPlan?
}