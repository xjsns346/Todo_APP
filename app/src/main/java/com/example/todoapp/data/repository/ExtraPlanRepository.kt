package com.example.todoapp.data.repository

import com.example.todoapp.data.local.TodoDatabase
import com.example.todoapp.data.local.entity.ExtraPlan
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExtraPlanRepository @Inject constructor(
    private val database: TodoDatabase
) {
    private val extraPlanDao = database.extraPlanDao()

    fun getAllPlans(): Flow<List<ExtraPlan>> = extraPlanDao.getAllPlans()

    fun getPlanById(id: Long): Flow<ExtraPlan?> = extraPlanDao.getPlanById(id)

    suspend fun insertPlan(plan: ExtraPlan): Long = extraPlanDao.insert(plan)

    suspend fun deletePlan(plan: ExtraPlan) = extraPlanDao.delete(plan)

    suspend fun getRandomPlan(): ExtraPlan? = extraPlanDao.getRandomPlan()
}