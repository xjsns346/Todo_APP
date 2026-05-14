package com.example.todoapp.domain.usecase

import com.example.todoapp.data.repository.ExtraPlanRepository
import com.example.todoapp.data.local.entity.ExtraPlan
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExtraPlanUseCases @Inject constructor(
    private val repository: ExtraPlanRepository
) {
    fun getAllPlans(): Flow<List<ExtraPlan>> = repository.getAllPlans()

    fun getPlanById(id: Long): Flow<ExtraPlan?> = repository.getPlanById(id)

    suspend fun createPlan(title: String, description: String? = null): Long {
        val plan = ExtraPlan(title = title, description = description)
        return repository.insertPlan(plan)
    }

    suspend fun deletePlan(plan: ExtraPlan) = repository.deletePlan(plan)

    suspend fun getRandomPlan(): ExtraPlan? = repository.getRandomPlan()
}